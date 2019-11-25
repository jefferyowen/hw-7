package edu.cs3500.spreadsheets.model;

import java.util.Objects;

/**
 * A representation of a Cell, that has a value as a CellComponent and a Coord.
 */
public class Cell {
  private CellComponent cellContent;
  private Coord cord;

  /**
   * Creates a Cell object with the given content and Coord.
   *
   * @param cellContent the Content that will be contained in the Cell.
   * @param cord        the Location in a Coord of this Cell.
   */
  public Cell(CellComponent cellContent, Coord cord) {
    this.cellContent = cellContent;
    this.cord = cord;
  }

  /**
   * Gets the content contained in this Cell.
   *
   * @return the CellComponent in this Cell.
   */
  public CellComponent getCellContent() {
    return this.cellContent;
  }

  /**
   * Gets the Coord of the current Cell.
   *
   * @return the Coord of this Cell.
   */
  public Coord getCord() {
    return this.cord;
  }


  @Override
  public String toString() {
    return this.cellContent.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cell c = (Cell) o;
    return this.cellContent.equals(c.cellContent) &&
            this.cord.equals(c.cord);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.cellContent, this.cord);
  }


}
