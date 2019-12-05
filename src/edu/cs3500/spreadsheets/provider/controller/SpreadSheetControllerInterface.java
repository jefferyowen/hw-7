package edu.cs3500.spreadsheets.provider.controller;

import java.util.Map;

import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.provider.model.Coord;

/**
 * Allows the user to manipulate the model through the view.
 */
public interface SpreadSheetControllerInterface<K,V> {


  /**
   * Edits an existing Cell in some manner.
   */
  void callEditCell(K cell, String s);


  /**
   * Adds a Cell to the model.
   */
  void callAddCell(K cell, String s);


  /**
   * gets the map of the model.
   */
  Map<K, V> getMap();

  /**
   * Returns the user selected cell.
   */
  Coord getSelectedCell();

  /**
   * Sets the user selected cell.
   */
  void setSelectedCell(K c);
}

