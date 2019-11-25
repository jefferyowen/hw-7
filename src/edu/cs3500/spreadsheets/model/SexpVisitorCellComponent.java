package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.cells.formula.CellComponentFormulaReference;
import edu.cs3500.spreadsheets.model.cells.formula.value.CellComponentValueBoolean;
import edu.cs3500.spreadsheets.model.cells.formula.value.CellComponentValueDouble;
import edu.cs3500.spreadsheets.model.cells.formula.value.CellComponentValueString;
import edu.cs3500.spreadsheets.model.cells.functions.FunctionLessThan;
import edu.cs3500.spreadsheets.model.cells.functions.FunctionProduct;
import edu.cs3500.spreadsheets.model.cells.functions.FunctionRepeat;
import edu.cs3500.spreadsheets.model.cells.functions.FunctionSum;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor for each CellComponent to see which Sexp value it is.
 */
public class SexpVisitorCellComponent implements SexpVisitor<CellComponent> {
  private final SSymbol SUM = new SSymbol("SUM");
  private final SSymbol PRODUCT = new SSymbol("PRODUCT");
  private final SSymbol LESSTHAN = new SSymbol("<");
  private final SSymbol REPEAT = new SSymbol("REPEAT");
  private WorkSheet ws;
  private int col;
  private int row;

  /**
   * Creates a Visitor for Sexp in Cell Component.
   *
   * @param ws  the worksheet that the user passed.
   * @param col the column of the Cell being viewed.
   * @param row the row of the Cell being viewed.
   */
  public SexpVisitorCellComponent(WorkSheet ws, int col, int row) {
    this.ws = ws;
    this.row = row;
    this.col = col;
  }

  @Override
  public CellComponent visitBoolean(boolean b) {
    return new CellComponentValueBoolean(b);
  }

  @Override
  public CellComponent visitNumber(double d) {
    return new CellComponentValueDouble(d);
  }

  @Override
  public CellComponent visitSList(List<Sexp> l) {
    Sexp formulaType = l.get(0);
    if (formulaType.equals(SUM)) {
      if (l.size() > 2) {
        CellComponent referenceOne = l.get(1).accept(new SexpVisitorCellComponent(ws, col, row));
        CellComponent referenceTwo = l.get(2).accept(new SexpVisitorCellComponent(ws, col, row));
        ArrayList<Coord> toAdd = new ArrayList<>(referenceOne.getCellsThatCanBeAccessed());
        toAdd.addAll(referenceTwo.getCellsThatCanBeAccessed());
        return new FunctionSum(toAdd, this.ws, referenceOne, referenceTwo);
      } else {
        CellComponent referenceOne = l.get(1).accept(new SexpVisitorCellComponent(ws, col, row));
        ArrayList<Coord> toAdd = new ArrayList<>(referenceOne.getCellsThatCanBeAccessed());
        return new FunctionSum(toAdd, this.ws, referenceOne);
      }
    }
    if (formulaType.equals(PRODUCT)) {
      if (l.size() > 2) {
        CellComponent referenceOne = l.get(1).accept(new SexpVisitorCellComponent(ws, col, row));
        CellComponent referenceTwo = l.get(2).accept(new SexpVisitorCellComponent(ws, col, row));
        ArrayList<Coord> toAdd = new ArrayList<>(referenceOne.getCellsThatCanBeAccessed());
        toAdd.addAll(referenceTwo.getCellsThatCanBeAccessed());
        return new FunctionProduct(toAdd, this.ws, referenceOne, referenceTwo);
      }
      CellComponent referenceOne = l.get(1).accept(new SexpVisitorCellComponent(ws, col, row));
      ArrayList<Coord> toAdd = new ArrayList<>(referenceOne.getCellsThatCanBeAccessed());
      return new FunctionProduct(toAdd, this.ws, referenceOne);
    }
    if (formulaType.equals(LESSTHAN)) {
      CellComponent referenceOne = l.get(1).accept(new SexpVisitorCellComponent(ws, col, row));
      CellComponent referenceTwo = l.get(2).accept(new SexpVisitorCellComponent(ws, col, row));
      return new FunctionLessThan(referenceOne, referenceTwo);
    }
    if (formulaType.equals(REPEAT)) {
      CellComponent referenceOne = l.get(1).accept(new SexpVisitorCellComponent(ws, col, row));
      return new FunctionRepeat(referenceOne);
    } else {
      return formulaType.accept(new SexpVisitorCellComponent(ws, col, row));
    }
  }

  @Override
  public CellComponent visitSymbol(String s) {
    return new CellComponentFormulaReference(Coord.createReference(s), new Coord(col, row),
            this.ws);
  }

  @Override
  public CellComponent visitString(String s) {
    return new CellComponentValueString(s);
  }
}
