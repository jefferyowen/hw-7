package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.WorkSheet;

/**
 * Worksheet that is only able to be read, not modified.
 */
public interface ReadOnlyView extends WorkSheet<Cell> {

}
