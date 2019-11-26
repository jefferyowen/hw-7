package edu.cs3500.spreadsheets.view;


import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorkSheet;

public interface EditView extends View {

  /**
   * Get the string from the text field and return it
   */
  public String getInputString();

  public void setInputString(String contents);

  /**
   * Clear the text field. Note that a more general "setInputString" would work for this purpose but
   * would be incorrect. This is because the text field is not set programmatically in general but
   * input by the user.
   */

  public void clearInputString();

  public Coord getSelectedCoord();

  public void addFeatures(Features features);

  public void updateSelectedCoord(int row, int col);

  public void updateModel(WorkSheet ws);
}
