package edu.cs3500.spreadsheets.model.cells;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.CellComponentVisitor;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * A representation of a CellComponent with a blank value.
 */
public class CellComponentBlank implements CellComponent {
  private Object value;

  /**
   * Creates a CellComponent with a null value.
   */
  public CellComponentBlank() {
    this.value = null;
  }

  @Override
  public <R> R accept(CellComponentVisitor<R> visitor) {
    return visitor.visitCellComponentBlank(this.value);
  }

  @Override
  public ArrayList<Coord> getCellsThatCanBeAccessed() {
    ArrayList<Coord> neighbors = new ArrayList<Coord>();
    return neighbors;
  }

  @Override
  public boolean hasCycle() {
    return false;
  }

  @Override
  public boolean hasCycle2(ArrayList<Coord> cords) {
    return false;
  }

  @Override
  public String evaluate() {
    return "";
  }

  public Object evaluateFormula() {
    return null;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CellComponentBlank b = (CellComponentBlank) o;
    return value == (b.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}
