package edu.cs3500.spreadsheets.provider.controller;

import java.util.Map;

import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.provider.model.Coord;

/**
 * Allows the user to manipulate the model through the view.
 */
public interface SpreadSheetControllerInterface {


  /**
   * Edits an existing Cell in some manner.
   */
  void callEditCell(Coord cell, String s);


  /**
   * Adds a Cell to the model.
   */
  void callAddCell(Coord cell, String s);


  /**
   * gets the map of the model.
   */
  Map<Coord, Cell> getMap();

  /**
   * Returns the user selected cell.
   */
  Coord getSelectedCell();

  /**
   * Sets the user selected cell.
   */
  void setSelectedCell(Coord c);
}

