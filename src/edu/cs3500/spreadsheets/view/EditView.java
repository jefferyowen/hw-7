package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public interface EditView extends View {
  /**
   * Sets up the listeners for the view.
   * @param clicks The action listener.
   * @param fo the mouse listener.
   */
  public void setListeners(ActionListener clicks, FocusListener fo);

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

}
