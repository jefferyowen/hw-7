package edu.cs3500.spreadsheets.model.cells.formula.value;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellComponentVisitor;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * The representation of a CellComponent with a Boolean value.
 */
public class CellComponentValueBoolean implements CellComponentValue<Boolean> {
  private boolean value;

  /**
   * Creates a CellComponentValueBoolean with the given value.
   *
   * @param v the Boolean value that the CellComponent will be set to.
   */
  public CellComponentValueBoolean(boolean v) {
    this.value = v;
  }

  @Override
  public String toString() {
    return Boolean.toString(this.value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CellComponentValueBoolean b = (CellComponentValueBoolean) o;
    return Boolean.compare(b.value, value) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public Boolean evaluateFormula() {
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
    return String.valueOf(this.evaluateFormula());
  }

  @Override
  public ArrayList<Coord> getCellsThatCanBeAccessed() {
    ArrayList<Coord> neighbors = new ArrayList<Coord>();
    return neighbors;
  }

  @Override
  public <R> R accept(CellComponentVisitor<R> visitor) {
    return visitor.visitCellComponentValueBoolean(this.evaluateFormula());
  }
}
