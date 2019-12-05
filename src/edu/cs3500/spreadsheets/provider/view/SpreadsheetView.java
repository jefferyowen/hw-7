package edu.cs3500.spreadsheets.provider.view;

/**
 * Represents a view that can represent a Spreadsheet model to the user.
 */
public interface SpreadsheetView {

  /**
   * Renders the spreadsheet model. Takes a model and outputs a view.
   */
  void render();

  /**
   * Uodates the view with the most up to date data from the model.
   */
  void refresh();
}
