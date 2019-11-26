package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.cells.CellComponentBlank;


/**
 * A basic worksheet that is a simple ArrayList of ArrayList of Cells.
 */
public class WorkSheetBasic implements WorkSheet<Cell> {
  private ArrayList<ArrayList<Cell>> spreadsheet;

  /**
   * Creates a basic WorkSheet with a given number of rows and columns.
   *
   * @param row the number of rows that Worksheet should have.
   * @param col the number of columns that Worksheet should have.
   */
  public WorkSheetBasic(int row, int col) {
    this.spreadsheet = new ArrayList<ArrayList<Cell>>();
    this.createWorkSheet(row, col);
  }

  @Override
  public void createWorkSheet(int row, int col) {
    for (int i = 0; i < col; i++) {
      ArrayList<Cell> currentRow = new ArrayList<>();
      this.spreadsheet.add(currentRow);
      for (int j = 0; j < row; j++) {
        currentRow.add((new Cell(new CellComponentBlank(), new Coord(col + 1, i + 1))));
      }
    }
  }

  @Override
  public String evaluate(int row, int col) {
    return this.getCellAt(row, col).getCellContent().evaluate();
  }


  @Override
  public void setCell(int row, int col, CellComponent value) throws IllegalArgumentException {
    if (col + 1 > this.spreadsheet.size() || row + 1 > this.spreadsheet.get(col).size()) {
      this.addCell(row, col);
    }
    this.spreadsheet.get(col).set(row, new Cell(value, new Coord(col + 1, row + 1)));
    this.update(row, col);
  }

  @Override
  public void addCell(int row, int col) {
    if (col + 1 > this.spreadsheet.size()) {
      this.addCol(col + 1 - this.spreadsheet.size());
    }
    if (row + 1 > this.spreadsheet.get(col).size()) {
      this.addRow(col, row + 1 - this.spreadsheet.get(col).size());
    }
  }

  @Override
  public void addCol(int colToAdd) {
    ArrayList<Cell> toAdd = new ArrayList<>();
    for (int i = 0; i < colToAdd; i++) {
      for (int j = 0; j < this.spreadsheet.get(0).size(); j++) {
        toAdd.add((new Cell(new CellComponentBlank(), new Coord(colToAdd + 1, i + 1))));
      }
      this.spreadsheet.add(toAdd);
    }
  }

  @Override
  public void addCol() {
    addCol(1);
  }

  @Override
  public void addRow(int col, int rowsToAdd) {
    for (int i = 0; i < rowsToAdd; i++) {
      this.spreadsheet.get(col).add(new Cell(new CellComponentBlank(),
              new Coord(col + 1, i + 1)));
    }
  }

  @Override
  public void addRow() {
    addRow(this.getNumCols() - 1, 1);
  }

  @Override
  public Cell getCellAt(int row, int col) {
    if (row >= this.spreadsheet.get(col).size() || row < 0) {
      this.addRow(col, row - this.getNumRows() + 1);
      return this.spreadsheet.get(col).get(row);
    } else if (col >= this.spreadsheet.size() || col < 0) {
      this.addCol(col - this.getNumCols() + 1);
      return this.spreadsheet.get(col).get(row);
    } else {
      return this.spreadsheet.get(col).get(row);
    }
  }

  @Override
  public String getStringOfCell(int row, int col) {
    if (row >= this.spreadsheet.size() || row < 0) {
      throw new IllegalArgumentException("Invalid position.");
    } else if (col >= this.spreadsheet.get(row).size() || col < 0) {
      throw new IllegalArgumentException("Invalid position.");
    } else {
      return this.spreadsheet.get(row).get(col).toString();
    }
  }

  @Override
  public ArrayList<ArrayList<Cell>> getSpreadSheet() {
    ArrayList<ArrayList<Cell>> toReturn = new ArrayList<ArrayList<Cell>>(this.spreadsheet);
    return toReturn;
  }

  @Override
  public int getNumRows() {
    return this.spreadsheet.get(0).size();
  }

  @Override
  public int getNumCols() {
    return this.spreadsheet.size();
  }


  private void update(int row, int col) {
    //DO THIS
  }
}
