package edu.cs3500.spreadsheets.model.cells.formula;

import edu.cs3500.spreadsheets.model.CellComponent;

/**
 * A representation of a CellComponent that has a Formula as its value.
 *
 * @param <K> The formula type that the object holds.
 */
public interface CellComponentFormula<K> extends CellComponent {
  /**
   * Returns the value of the CellComponent's formula after evaluated.
   *
   * @return The K object that the Formula evaluates into.    
   */
  public K evaluateFormula();
}
