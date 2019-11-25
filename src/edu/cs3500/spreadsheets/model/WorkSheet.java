package edu.cs3500.spreadsheets.model;


import java.util.ArrayList;

import edu.cs3500.spreadsheets.view.ReadOnlyView;

/**
 * A worksheet consisted of cells.
 *
 * @param <R> the representation of a cell used.
 */
public interface WorkSheet<R> extends ReadOnlyView {

  public void createWorkSheet(int row, int col);

  /**
   * Evaluate the value of a Cell at a given position.
   *
   * @param row the row of the Cell being evaluated.
   * @param col the column of the Cell being evaluated.
   * @return the CellComponent that is in the Cell being called.
   */
  public String evaluate(int row, int col);

  /**
   * Sets the value of a current Cell, to the CellComponent value passed.
   *
   * @param row   the row of the current Cell being updated.
   * @param col   the column of the current Cell being updated.
   * @param value the value that the Cell is being set to.
   * @throws IllegalArgumentException if the Cell being updated does not exist, or the row and col
   *                                  are not valid.
   */
  public void setCell(int row, int col, CellComponent value) throws IllegalArgumentException;

  /**
   * Adds a cell to the current Spreadsheet.
   *
   * @param row the row of the new Cell.
   * @param col the column of the new Cell.
   */
  public void addCell(int row, int col);

  /**
   * Adds the number of columns specified.
   *
   * @param value the index of the new row that needs to be added.
   */
  public void addCol(int value);

  /**
   * Adds one column.
   */
  public void addCol();

  /**
   * Adds the number of rows specified.
   *
   * @param value the index of the new column that needs to be added. UPDATE
   */
  public void addRow(int row, int value);

  /**
   * Adds one row.
   */
  public void addRow();

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
   * Gets the state of the current spreadsheet.
   *
   * @return a spreadsheet in the for of an List of List of R.
   */
  public ArrayList<ArrayList<R>> getSpreadSheet();

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

}
