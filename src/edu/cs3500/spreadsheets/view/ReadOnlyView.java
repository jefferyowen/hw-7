package edu.cs3500.spreadsheets.view;

/**
 * Worksheet that is only able to be read, not modified. Used for Views.
 */
public interface ReadOnlyView<R> {

  /**
   * Evaluate the value of a Cell at a given position.
   *
   * @param row the row of the Cell being evaluated.
   * @param col the column of the Cell being evaluated.
   * @return the CellComponent that is in the Cell being called.
   */
  public String evaluate(int row, int col);

  /**
   * Gets the Cell at the given row and col.
   *
   * @param row the row of the Cell being returned.
   * @param col the col of the Cell being returned.
   * @return The Cell at the given index.
   */
  public R getCellAt(int row, int col);

  /**
   * Gets the String value of the Cell being asked for.
   *
   * @param row the row index of the Cell bing asked for.
   * @param col the column index of the Cell being asked for.
   * @return the String value of the Cell being asked for by the user.
   */
  public String getStringOfCell(int row, int col);

  /**
   * Gets the number of rows in the worksheet.
   *
   * @return the number of rows.
   */
  public int getNumRows();

  /**
   * Gets the number of columns in the worksheet.
   *
   * @return the number of columns.
   */
  public int getNumCols();

  /**
   * Sets the value of a current Cell, to the CellComponent value passed.
   *
   * @param row   the row of the current Cell being updated.
   * @param col   the column of the current Cell being updated.
   * @param value the value that the Cell is being set to.
   */
  public void setCell(int row, int col, R value);
}
