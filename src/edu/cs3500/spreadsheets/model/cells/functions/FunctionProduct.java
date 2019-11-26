package edu.cs3500.spreadsheets.model.cells.functions;


import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.CellComponentVisitor;
import edu.cs3500.spreadsheets.model.CellComponentVisitorMultiply;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorkSheet;

/**
 * A representation of a Function that multiplies all the given Cells.
 */
public class FunctionProduct implements CellComponentFormulaFunction<Double> {
  private final WorkSheet<Cell> workSheet;
  private final CellComponent r1;
  private final CellComponent r2;
  private ArrayList<Coord> toBeMultiplied;

  /**
   * Creates a product with one reference.
   *
   * @param toBeMultiplied list of cells to be multiplied.
   * @param workSheet      current worksheet.
   * @param r1             the reference of the product.
   */
  public FunctionProduct(ArrayList<Coord> toBeMultiplied, WorkSheet workSheet, CellComponent r1) {
    this.r1 = r1;
    this.r2 = null;
    this.toBeMultiplied = toBeMultiplied;
    this.workSheet = workSheet;
    if (this.hasCycle()) {
      throw new IllegalArgumentException("This formula has a cycle and can't be made.");
    }
  }

  /**
   * Creates a product with two references.
   *
   * @param toBeMultiplied list of cells to be multiplied.
   * @param workSheet      current worksheet.
   * @param r1             the first reference of the product.
   * @param r2             the second reference of the product.
   */
  public FunctionProduct(ArrayList<Coord> toBeMultiplied, WorkSheet workSheet, CellComponent r1,
                         CellComponent r2) {
    this.r1 = r1;
    this.r2 = r2;
    this.toBeMultiplied = toBeMultiplied;
    this.workSheet = workSheet;
    if (this.hasCycle()) {
      throw new IllegalArgumentException("This formula has a cycle and can't be made.");
    }
  }

  @Override
  public Double evaluateFormula() {
    double product = 1.0;
    product *= r1.accept(new CellComponentVisitorMultiply());
    if(r2 != null) {
      product *= r2.accept(new CellComponentVisitorMultiply());
    }
    return product;
  }

  @Override
  public <R> R accept(CellComponentVisitor<R> visitor) {
    return visitor.visitFunctionProduct(this.evaluateFormula());
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
        if (used.contains(a)) {
          return used.contains(a);
        }
        ArrayList<Coord> used2 = new ArrayList<Coord>(used);
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
    return this.toBeMultiplied;
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
    FunctionProduct p = (FunctionProduct) o;
    return this.evaluateFormula().equals(p.evaluateFormula());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.evaluateFormula());
  }

}
