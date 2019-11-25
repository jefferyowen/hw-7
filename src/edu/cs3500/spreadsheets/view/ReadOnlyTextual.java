package edu.cs3500.spreadsheets.view;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.WorkSheet;

/**
 * Implementation for a ReadOnly version of a Worksheet.
 */
public class ReadOnlyTextual implements ReadOnlyView {
  private ArrayList<ArrayList<Cell>> spreadsheet;

  /**
   * Constructor for a ReadOnly Worksheet, that takes in regular Worksheet.
   *
   * @param ws Worksheet to be converted into ReadOnly version.
   */
  public ReadOnlyTextual(WorkSheet ws) {
    this.spreadsheet = ws.getSpreadSheet();
  }

  @Override
  public void createWorkSheet(int row, int col) {
    // shouldn't do anything as this is the read only file.
  }

  @Override
  public String evaluate(int row, int col) {
    return this.spreadsheet.get(col).get(row).getCellContent().evaluate();
  }

  @Override
  public void setCell(int row, int col, CellComponent value) throws IllegalArgumentException {
    // shouldn't do anything as this is the read only file.
  }

  @Override
  public void addCell(int row, int col) {
    // shouldn't do anything as this is the read only file.
  }

  @Override
  public void addCol(int value) {
    // shouldn't do anything as this is the read only file.
  }

  @Override
  public void addCol() {
    // shouldn't do anything as this is the read only file.
  }

  @Override
  public void addRow(int row, int value) {
    // shouldn't do anything as this is the read only file.
  }

  @Override
  public void addRow() {
    // shouldn't do anything as this is the read only file.
  }

  @Override
  public Cell getCellAt(int row, int col) {
    return this.spreadsheet.get(col).get(row);
  }

  @Override
  public String getStringOfCell(int row, int col) {
    return this.spreadsheet.get(row).get(col).toString();
  }

  @Override
  public ArrayList<ArrayList<Cell>> getSpreadSheet() {
    throw new IllegalArgumentException("Shouldn't be accessed as it's a read only file");
  }

  @Override
  public int getNumRows() {
    return this.spreadsheet.get(0).size(); // shouldn't do anything as this is the read only file.
  }

  @Override
  public int getNumCols() {
    return this.spreadsheet.size(); // shouldn't do anything as this is the read only file.
  }
}
