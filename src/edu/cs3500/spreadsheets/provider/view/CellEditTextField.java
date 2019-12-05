package edu.cs3500.spreadsheets.provider.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JTextField;

import edu.cs3500.spreadsheets.provider.controller.SpreadSheetControllerInterface;


/**
 * Spreadsheet Specific implementation of a text field that keeps track of the currently selected
 * cell.
 */
public class CellEditTextField extends JTextField implements ActionListener {
  private SpreadSheetControllerInterface spread;

  /**
   * Constructs a Cell Edit Text Field given the Coordinate of the edited Cell.
   */
  CellEditTextField(SpreadSheetControllerInterface spread) {
    super();
    this.spread = spread;

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.spread.callEditCell(this.spread.getSelectedCell(), this.getText());
  }
}
