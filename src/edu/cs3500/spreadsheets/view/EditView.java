package edu.cs3500.spreadsheets.view;


import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorkSheet;

/**
 * Extension of original view Interface, add buttons and ability to edit Cells in Model.
 */
public interface EditView extends View {

  /**
   * Get the string from the text field and return it.
   */
  public String getInputString();

  public void setInputString(String contents);

  /**
   * Clear the text field. Note that a more general "setInputString" would work for this purpose but
   * would be incorrect. This is because the text field is not set programmatically in general but
   * input by the user.
   */
  public void clearInputString();

  /**
   * Finds the currently selected Coord in the table.
   *
   * @return the currently selected Coord.
   */
  public Coord getSelectedCoord();

  /**
   * Adds a list of features to the model.
   *
   * @param features Contains different features for things like buttons and mouse events to have.
   */
  public void addFeatures(Features features);

  /**
   * Sets the currently selected Coord value, to the correct Coord.
   *
   * @param row The row position of the Coord.
   * @param col The col position of the Coord.
   */
  public void updateSelectedCoord(int row, int col);

  /**
   * Takes in a new Worksheet, and updates the model to represent that.
   *
   * @param ws the new Worksheet that will be represented by the model.
   */
  public void updateModel(WorkSheet ws);
}
