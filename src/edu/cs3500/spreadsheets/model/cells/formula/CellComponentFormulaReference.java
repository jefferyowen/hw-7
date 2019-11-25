package edu.cs3500.spreadsheets.model.cells.formula;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.CellComponentVisitor;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorkSheet;

/**
 * A representation of CellComponent with a reference as its value.
 */
public class CellComponentFormulaReference implements CellComponentFormula<ArrayList<Coord>> {
  private final WorkSheet<Cell> workSheet;
  private ArrayList<Coord> references;
  private Coord currentCoord;

  /**
   * Creates a CellComponentFormulaReference with a given Cell.
   *
   * @param c         the Cells given to create a CellComponentFormulaReference.
   * @param coord     the Coord of thee current Cell.
   * @param worksheet the WorkSheet that the current CellContent is in.
   */
  public CellComponentFormulaReference(ArrayList<Coord> c, Coord coord, WorkSheet worksheet) {
    this.references = c;
    this.currentCoord = coord;
    this.workSheet = worksheet;
    if (this.hasCycle()) {
      throw new IllegalArgumentException("The refrences have a cycle");
    }
  }

  @Override
  public String toString() {
    String ans = "";
    if (this.references.size() == 1) {
      Coord c = this.references.get(0);
      ans = c.toString();
    } else {
      Coord cStart = this.references.get(0);
      Coord cLast = this.references.get(this.references.size() - 1);
      ans = cStart.toString() + ":" + cLast.toString();
    }
    return ans;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CellComponentFormulaReference s = (CellComponentFormulaReference) o;
    return references.equals(s.references);
  }

  @Override
  public int hashCode() {
    return Objects.hash(references);
  }

  @Override
  public ArrayList<Coord> evaluateFormula() {
    return this.getCellsReferenced();
  }

  private ArrayList<Coord> getCellsReferenced() {
    return this.references;
  }

  @Override // need to change
  public boolean hasCycle() {
    if (this.references.contains(this.currentCoord)) {
      return true;
    }
    ArrayList<Coord> cs = new ArrayList<>();
    cs.add(this.currentCoord);
    return this.hasCycle2(cs);
  }

  @Override
  public String evaluate() {
    return this.toString();
  }

  @Override
  public boolean hasCycle2(ArrayList cords) {
    ArrayList<Coord> used = new ArrayList<Coord>(cords);
    boolean cycle = false;
    if (used.contains(this.references)) {
      return used.contains(this.references);
    } else {
      for (Coord a : references) {
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

  @Override
  public ArrayList<Coord> getCellsThatCanBeAccessed() {
    ArrayList<Coord> neighbors = new ArrayList<>(references);
    return neighbors;
  }

  @Override
  public <R> R accept(CellComponentVisitor<R> visitor) {
    ArrayList<CellComponent> evaluateCells = new ArrayList<>();
    for (Coord c : this.references) {
      evaluateCells.add(this.workSheet.getCellAt(c.row - 1, c.col - 1).getCellContent());
    }
    return visitor.visitCellComponentFormulaReference(evaluateCells);
  }
}

