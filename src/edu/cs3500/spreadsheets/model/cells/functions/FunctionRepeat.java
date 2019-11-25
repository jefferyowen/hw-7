package edu.cs3500.spreadsheets.model.cells.functions;


import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.CellComponentVisitor;
import edu.cs3500.spreadsheets.model.CellComponentVisitorGetString;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * A representation of a Function that repeats a String and adds it to the end of the Cells value,
 * the specified number of times.
 */
public class FunctionRepeat implements CellComponentFormulaFunction<String> {
  private CellComponent cell;

  /**
   * Creates a function that will repeat a given String, the given number of times.
   *
   * @param c The Cell that will have its String value repeated.
   */
  public FunctionRepeat(CellComponent c) {
    this.cell = c;
    if (this.hasCycle()) {
      throw new IllegalArgumentException("This formula has a cycle and can't be made.");
    }
  }

  @Override
  public String evaluateFormula() {
    String cellString = this.cell.accept(new CellComponentVisitorGetString());
    String answer = "" + cellString;
    answer = answer + " " + cellString;
    return answer;
  }

  @Override
  public <R> R accept(CellComponentVisitor<R> visitor) {
    return visitor.visitFunctionRepeat(this.cell.toString());
  }

  @Override // need to change
  public boolean hasCycle() {
    return false;
  }

  @Override
  public String evaluate() {
    return this.evaluateFormula();
  }

  @Override
  public boolean hasCycle2(ArrayList cords) {
    return false;
  }


  @Override
  public ArrayList<Coord> getCellsThatCanBeAccessed() {
    return this.cell.getCellsThatCanBeAccessed();
  }

  @Override
  public String toString() {
    String toReturn = "=(REPEAT ";
    toReturn = toReturn + this.cell.toString();
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
    FunctionRepeat r = (FunctionRepeat) o;
    return this.evaluateFormula().equals(r.evaluateFormula());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.evaluateFormula());
  }
}
