package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import edu.cs3500.spreadsheets.controller.Features;

public interface EditView extends View {

  /**
   * Get the string from the text field and return it
   */
  public String getInputString();

  /**
   * Clear the text field. Note that a more general "setInputString" would work for this purpose but
   * would be incorrect. This is because the text field is not set programmatically in general but
   * input by the user.
   */

  public void clearInputString();

  public void getSelectedCell();

  public void addFeatures(Features features);

}
