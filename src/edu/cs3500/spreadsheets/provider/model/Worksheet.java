package edu.cs3500.spreadsheets.provider.model;

import java.util.List;
import java.util.Map;

/**
 * The Worksheet interface that provides methods to create and manipulate a spreadsheet.
 */
public interface Worksheet<K,V,T> {


  /**
   * Adds a given coordinate to the worksheet, if it doesn't exist yet.
   */
  public void addCell(K coordinate, java.lang.String contents);


  /**
   * Given a Coord, returns the Cell it points to on the spreadsheet. Throws
   * IllegalArgumentException if the Coord doesn't exist in the worksheet.
   *
   * @return a Cell
   */
  public V getCell(K coordinate);

  /**
   * Returns the worksheet data.
   */
  public Map<K, V> getSheet();

  /**
   * Returns the value of a cell given the Coord that points to it. Throws IllegalArgumentException
   * if the Coord doesn't exist in the worksheet.
   *
   * @return a Value
   */
  public T getCellValue(K coordinate);

  /**
   * Updates the Raw (unevaluated) value of a given cell that the Coord points to. Throws
   * IllegalArgumentException if the Coord doesn't exist in the worksheet.
   */
  public void updateRawValueCoord(K cell, T formula);

  /**
   * Returns a List of Coords that are in the rectangle formed by cell1 and cell2 coords. Throws
   * IllegalArgumentException if either of the Coord don't exist in the worksheet.
   */
  public List<K> selectRegionOfCell(K cell1, K cell2);

  /**
   * Deletes the contents of a cell given it's Coords. Throws IllegalArgumentException if the Coord
   * doesn't exist in the worksheet.
   */
  public void clearCell(K cell);

  /**
   * Clears a region (multiple) of cells given a list of Coords. Throws IllegalArgumentException if
   * one of the Coords in the list doesn't exist in the worksheet. Throws IllegalArgumentException
   * if list region is null.
   */
  public void clearRegion(List<K> region);

  /**
   * Checks to see if there are Cycles in a given worksheet, particularly when a cell is updated.
   */
  void noCycles();

}
