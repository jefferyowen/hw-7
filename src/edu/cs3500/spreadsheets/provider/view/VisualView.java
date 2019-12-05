package edu.cs3500.spreadsheets.provider.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.JFrame;

import javax.swing.JPanel;


import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.provider.model.Coord;
import edu.cs3500.spreadsheets.provider.model.Worksheet;

/**
 * Represents a visual view for the Spreadsheet.
 */
public class VisualView extends JFrame implements SpreadsheetView {
  private JPanel panel;
  private Worksheet model;
  private Map<Coord, Cell> worksheet;

  /**
   * Creates a visual view given a model to build it off of.
   */
  public VisualView(Worksheet model) {
    super();
    this.model = model;
    this.worksheet = this.model.getSheet();
    this.setTitle("Spreadsheet");
    this.setSize(900, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    panel = new JPanel();
    panel.setPreferredSize(new Dimension(1000, 1000));

    render();

    this.pack();
    this.setVisible(true);
  }

  /**
   * Creates a basic visual view without a Worksheet for testing purposes.
   */
  public VisualView() {
    super();
    this.setTitle("Spreadsheet");
    this.setSize(900, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    panel = new JPanel();
    panel.setPreferredSize(new Dimension(1000, 1000));

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void render() {
    this.add(new JPanelSpreadsheet(this.worksheet));
  }

  @Override
  public void refresh() {
    this.worksheet = this.model.getSheet();
    this.render();
  }
}
