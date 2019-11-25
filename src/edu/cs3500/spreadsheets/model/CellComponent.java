package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;


/**
 * A representation of all the values possible to be stored in a Cell.
 */
public interface CellComponent {
  /**
   * Visits a CellComponent sub-class and determines what value should be returned.
   *
   * @param visitor The visitor that will determine what value to be returned.
   * @param <R>     The type of visitor being passed.
   * @return The type of CellComponent being visited.
   */
  <R> R accept(CellComponentVisitor<R> visitor);

  /**
   * Determines what Cells can be accessed by this Cell.
   *
   * @return A list of Cells that can be accessed by this CellComponent.
   */
  public ArrayList<Coord> getCellsThatCanBeAccessed();

  /**
   * Checks to see if a Cell is self-referential.
   *
   * @return if a Cell is self-referential.
   */
  public boolean hasCycle();

  /**
   * Checks to see if a Cell is self-referential.
   *
   * @param cords the Coords already referenced.
   * @return if a Cell is self-referential.
   */
  public boolean hasCycle2(ArrayList<Coord> cords);

  /**
   * Returns the evaluated value of a cell.
   *
   * @return The string representing the evaluation of a cell.
   */
  public String evaluate();
}
