package edu.cs3500.spreadsheets.model.cells.formula.value;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellComponentVisitor;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * The representation of a CellComponent with a Double value.
 */
public class CellComponentValueDouble implements CellComponentValue<Double> {
  private double value;

  /**
   * Creates a CellComponentValueDouble with the given value.
   *
   * @param v the Double value that the CellComponent will be set to.
   */
  public CellComponentValueDouble(double v) {
    this.value = v;
  }

  @Override
  public String toString() {
    String toReturn = Double.toString(this.value);
    return String.format("%5.2f", this.value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CellComponentValueDouble d = (CellComponentValueDouble) o;
    return Double.compare(d.value, value) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public Double evaluateFormula() {
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
    return String.format("%5.2f", this.evaluateFormula());
  }

  @Override
  public ArrayList<Coord> getCellsThatCanBeAccessed() {
    ArrayList<Coord> neighbors = new ArrayList<Coord>();
    return neighbors;
  }

  @Override
  public <R> R accept(CellComponentVisitor<R> visitor) {
    return visitor.visitCellComponentValueDouble(this.evaluateFormula());
  }
}
