package edu.cs3500.spreadsheets.model.cells.functions;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.CellComponentVisitor;
import edu.cs3500.spreadsheets.model.CellComponentVisitorAdd;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorkSheet;

/**
 * A representation of a Function that adds all the given Cells.
 */
public class FunctionSum implements CellComponentFormulaFunction<Double> {
  private final WorkSheet<Cell> workSheet;
  private final CellComponent r1;
  private final CellComponent r2;
  private ArrayList<Coord> toBeAdded;

  /**
   * Creates a sum with one reference.
   *
   * @param toBeAdded list of cells to be sum.
   * @param workSheet current worksheet
   * @param r1        the reference of the sum
   */
  public FunctionSum(ArrayList<Coord> toBeAdded, WorkSheet workSheet, CellComponent r1) {
    this.r1 = r1;
    this.r2 = null;
    this.workSheet = workSheet;
    this.toBeAdded = toBeAdded;
    if (this.hasCycle()) {
      throw new IllegalArgumentException("This formula has a cycle and can't be made.");
    }
  }

  /**
   * Creates a sum with two references.
   *
   * @param toBeAdded list of cells to be added.
   * @param workSheet current worksheet.
   * @param r1        the first reference of the sum.
   * @param r2        the second reference of the sum.
   */
  public FunctionSum(ArrayList<Coord> toBeAdded, WorkSheet workSheet, CellComponent r1,
                     CellComponent r2) {
    this.r1 = r1;
    this.r2 = r2;
    this.workSheet = workSheet;
    this.toBeAdded = toBeAdded;
    if (this.hasCycle()) {
      throw new IllegalArgumentException("This formula has a cycle and can't be made.");
    }
  }

  @Override
  public Double evaluateFormula() {
    double sum = 0.0;
    sum += r1.accept(new CellComponentVisitorAdd());
    if(r2 != null) {
      sum += r2.accept(new CellComponentVisitorAdd());
    }
    return sum;
  }

  @Override
  public <R> R accept(CellComponentVisitor<R> visitor) {
    return visitor.visitFunctionSum(this.evaluateFormula());
  }

  @Override // need to change
  public boolean hasCycle() {
    return this.hasCycle2(new ArrayList<Coord>());
  }

  @Override
  public String evaluate() {
    return String.valueOf(this.evaluateFormula());
  }

  @Override
  public boolean hasCycle2(ArrayList cords) {
    ArrayList<Coord> used = new ArrayList<Coord>(cords);
    ArrayList<Coord> accessed = this.getCellsThatCanBeAccessed();
    Boolean cycle = false;
    if (used.contains(accessed)) {
      return true;
    } else {
      for (Coord a : accessed) {
        ArrayList<Coord> used2 = new ArrayList<Coord>(used);
        if (used.contains(a)) {
          return used.contains(a);
        }
        used2.add(a);
        Cell c = this.workSheet.getCellAt(a.row - 1, a.col - 1);
        cycle = c.getCellContent().hasCycle2(used2);
        if (cycle) {
          return true;
        }
        used2.clear();
      }
    }
    return cycle;
  }

  @Override // need to do
  public ArrayList<Coord> getCellsThatCanBeAccessed() {
    return this.toBeAdded;
  }

  @Override
  public String toString() {
    String toReturn = "=(SUM ";
    toReturn = toReturn + this.r1.toString() + " ";
    if (this.r2 != null) {
      toReturn = toReturn + this.r2.toString();
    }
    return toReturn + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FunctionSum s = (FunctionSum) o;
    return this.evaluateFormula().equals(s.evaluateFormula());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.evaluateFormula());
  }
}
