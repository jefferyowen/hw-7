package edu.cs3500.spreadsheets.model.cells.formula.value;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellComponentVisitor;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * The representation of a CellComponent with a String value.
 */
public class CellComponentValueString implements CellComponentValue<String> {
  private String value;

  /**
   * Creates a CellComponentValueString with the given value.
   *
   * @param s the String value that the CellComponent will be set to.
   */
  public CellComponentValueString(String s) {
    this.value = s;
  }

  @Override
  public String toString() {
    return "\"" + this.value + "\"";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CellComponentValueString s = (CellComponentValueString) o;
    return value.equals(s.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String evaluateFormula() {
    return this.value;
  }

  @Override
  public boolean hasCycle() {
    return false;
  }

  @Override
  public boolean hasCycle2(ArrayList<Coord> coords) {
    return false;
  }

  @Override
  public String evaluate() {
    return this.evaluateFormula();
  }

  @Override
  public ArrayList<Coord> getCellsThatCanBeAccessed() {
    ArrayList<Coord> neighbors = new ArrayList<Coord>();
    return neighbors;
  }


  @Override
  public <R> R accept(CellComponentVisitor<R> visitor) {
    return visitor.visitCellComponentValueString(this.evaluateFormula());
  }
}
