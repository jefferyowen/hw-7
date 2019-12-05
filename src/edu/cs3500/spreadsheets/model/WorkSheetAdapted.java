package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import edu.cs3500.spreadsheets.provider.model.Worksheet;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.view.ReadOnlyView;

public class WorkSheetAdapted implements Worksheet<Coord, Cell, CellComponent>, ReadOnlyView<Cell> {
  private WorkSheet<Cell> wsOriginal;
  private HashMap<Coord, Cell> mapOfSheet;

  public WorkSheetAdapted(WorkSheet ws) {
    this.wsOriginal = ws;
    this.mapOfSheet = new HashMap<>();
    this.setUp();
  }

  /**
   * Changes the ArrayList of Cells to a hashmap of Cells and Coords.
   */
  private void setUp() {
    for(int i = 0; i < this.wsOriginal.getNumCols(); i++) {
      for(int j = 0; j < this.wsOriginal.getNumRows(); j++) {
        Coord toAddCoord = new Coord(i+1, j+1);
        this.mapOfSheet.put(toAddCoord, this.wsOriginal.getCellAt(i,j));
      }
    }
  }

  @Override
  public void addCell(Coord coordinate, String contents) {
    Sexp toAddSexp = Parser.parse(contents);
    CellComponent value = toAddSexp.accept(new SexpVisitorCellComponent(
            this.wsOriginal, coordinate.col, coordinate.row));
    Coord toAddCord = coordinate;
    Cell toAdd = new Cell(value, toAddCord);
    wsOriginal.setCell(coordinate.row, coordinate.col, toAdd);
    this.setUp();
  }

  @Override
  public Cell getCell(Coord coordinate) {
    if (mapOfSheet.containsKey(coordinate)) {
      return this.mapOfSheet.get(coordinate);
    } else {
      throw new IllegalArgumentException("Coordinate doesn't exist");
    }
  }

  @Override
  public Map<Coord, Cell> getSheet() {
    return mapOfSheet;
  }

  @Override
  public CellComponent getCellValue(Coord coordinate) {
    if (mapOfSheet.containsKey(coordinate)) {
      return this.mapOfSheet.get(coordinate).getCellContent();
    } else {
      throw new IllegalArgumentException("Coordinate doesn't exist");
    }
  }

  @Override
  public void updateRawValueCoord(Coord cell, CellComponent formula) {
    if (mapOfSheet.containsKey(cell)) {
      Cell toAdd = new Cell(formula, cell);
      mapOfSheet.replace(cell, toAdd);
    } else {
      throw new IllegalArgumentException("Coordinate doesn't exist");
    }
  }

  @Override
  public List<Coord> selectRegionOfCell(Coord cell1, Coord cell2) {
    ArrayList<Coord> toReturn = new ArrayList<Coord>();
    for (int i = cell1.col; i <= cell2.col; i++) {
      for (int j = cell2.row; i <= cell2.row; j++) {
        Coord check = new Coord(i, j);
        if (this.mapOfSheet.containsValue(check)) {
          toReturn.add(check);
        }
      }
    }
    return toReturn;
  }

  @Override
  public void clearCell(Coord cell) {
    if (mapOfSheet.containsKey(cell)) {
      this.mapOfSheet.remove(cell);
    } else {
      throw new IllegalArgumentException("Coordinate doesn't exist");
    }
  }

  @Override
  public void clearRegion(List<Coord> region) {
    for (Coord c : region) {
      this.clearCell(c);
    }
  }

  @Override
  public void noCycles() {
    for (Cell c : this.mapOfSheet.values()) {
      if (c.getCellContent().hasCycle()) {
        throw new IllegalArgumentException("Can't accept cell as there is a cycle created.");
      }
    }
  }

  @Override
  public String evaluate(int row, int col) {
    return wsOriginal.evaluate(row, col);
  }

  @Override
  public Cell getCellAt(int row, int col) {
    return wsOriginal.getCellAt(row, col);
  }

  @Override
  public String getStringOfCell(int row, int col) {
    return wsOriginal.getCellAt(row, col).toString();
  }

  @Override
  public int getNumRows() {
    return wsOriginal.getNumRows();
  }

  @Override
  public int getNumCols() {
    return wsOriginal.getNumCols();
  }

  @Override
  public void setCell(int row, int col, Cell value) {
    wsOriginal.setCell(row, col, value);
  }
}
