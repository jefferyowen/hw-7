package edu.cs3500.spreadsheets.model.cells.functions;


import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.CellComponentVisitor;
import edu.cs3500.spreadsheets.model.CellComponentVisitorGetDouble;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * A representation of a Function that determines if a Cell is less than another Cell.
 */
public class FunctionLessThan implements CellComponentFormulaFunction<Boolean> {
  private CellComponent left;
  private CellComponent right;

  /**
   * Creates a function object with two given cells.
   *
   * @param left  The Cell to the left, must find if less than the right Cell.
   * @param right The Cell to the right, must find if greater than the left Cell.
   */
  public FunctionLessThan(CellComponent left, CellComponent right) {
    this.left = left;
    this.right = right;
    if (this.hasCycle()) {
      throw new IllegalArgumentException("This formula has a cycle and can't be made.");
    }
  }

  @Override
  public Boolean evaluateFormula() throws IllegalArgumentException {
    double leftDouble = this.left.accept(new CellComponentVisitorGetDouble());
    double rightDouble = this.right.accept(new CellComponentVisitorGetDouble());
    return leftDouble < rightDouble;
  }

  @Override // need to change
  public boolean hasCycle() {
    return false;
  }

  @Override
  public String evaluate() {
    return String.valueOf(this.evaluateFormula());
  }

  @Override
  public boolean hasCycle2(ArrayList cords) {
    return false;
  }


  @Override
  public ArrayList<Coord> getCellsThatCanBeAccessed() {
    ArrayList<Coord> neighbors = new ArrayList<Coord>();
    ArrayList<Coord> leftAccess = this.left.getCellsThatCanBeAccessed();
    ArrayList<Coord> rightAccess = this.right.getCellsThatCanBeAccessed();
    neighbors.addAll(leftAccess);
    neighbors.addAll(rightAccess);
    return neighbors;
  }

  @Override
  public <R> R accept(CellComponentVisitor<R> visitor) {
    return visitor.visitFunctionLessThan(this.evaluateFormula());
  }

  @Override
  public String toString() {
    String toReturn = "=(< ";
    toReturn = toReturn + this.left.toString();
    toReturn = toReturn + " " + this.right.toString();
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
    FunctionLessThan l = (FunctionLessThan) o;
    return this.evaluateFormula().equals(l.evaluateFormula());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.evaluateFormula());
  }
}
