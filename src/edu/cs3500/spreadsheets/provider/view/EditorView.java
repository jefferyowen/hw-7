package edu.cs3500.spreadsheets.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.provider.model.Coord;
import edu.cs3500.spreadsheets.provider.controller.SpreadSheetControllerInterface;

/**
 * Represents an Editable Visual View.
 */
public class EditorView extends JFrame implements SpreadsheetView {
  private JPanelEditSpreadsheet panel;
  private SpreadSheetControllerInterface controller;
  private Map<Coord, Cell> spreadsheet;

  /**
   * Constructs an Editor View with a controller.
   */
  public EditorView(SpreadSheetControllerInterface c) {
    this.controller = c;
    this.spreadsheet = controller.getMap();
    this.panel = new JPanelEditSpreadsheet(spreadsheet, controller);
    panel.setSize(new Dimension(600, 600));
    this.setTitle("Editable Spreadsheet");
    this.setMinimumSize(new Dimension(700, 700));
    this.setResizable(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 100);
    JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 100);
    this.getContentPane().add(hbar, BorderLayout.SOUTH);
    this.getContentPane().add(vbar, BorderLayout.EAST);


    this.pack();
    this.setVisible(true);
  }


  @Override
  public void render() {
    this.add(new JPanelEditSpreadsheet(spreadsheet, controller));

  }

  @Override
  public void refresh() {
    this.spreadsheet = controller.getMap();
    this.render();
  }


}
