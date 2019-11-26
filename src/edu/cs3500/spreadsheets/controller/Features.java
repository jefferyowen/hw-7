package edu.cs3500.spreadsheets.controller;

/**
 * The features that will be offered by our Controllers.
 */
public interface Features {

  /**
   * Gets the currently selected cell in the view, and sets the value of the JTextField
   * accordingly.
   */
  public void setSelectedCell();

  /**
   * Will take the value from the JTextField and set it to the value of the currently selected Cell
   * in the View.
   *
   * @param contents the value that the currently selected Cell is being set to.
   */
  public void setCellContentsOfCell(String contents);

  /**
   * Reverts the Cell back to its state when it was first clicked by the user. Also sets JTextField
   * value to match, and removes any changes.
   */
  public void clearCell();

  /**
   * Sets the tool bar to the currently selected Cell.
   */
  public void resetTextbar();
}
