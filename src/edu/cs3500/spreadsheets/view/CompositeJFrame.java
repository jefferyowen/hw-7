package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SimpleWorkSheetBuilder;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheetBasic;

public class CompositeJFrame extends JFrame implements EditView {

  private JScrollPane sp;
  private ExcelJFrame ejf;
  private JFrame f;
  private JPanel p;
  private ReadOnlyView ws;
  private JButton confirm;
  private JButton clear;
  private JTextField jtf;
  private JPanel worksheet;
  private Coord currentCoord;

  /**
   * Constructor for Editable Graphical view given Worksheet.
   *
   * @param ws the Worksheet to be represented by the editable graphical view.
   */
  public CompositeJFrame(WorkSheet ws) {
    this.ejf = new ExcelJFrame(ws);
    this.ws = new ReadOnlyTextual(ws);
    setUp();
  }

  /**
   * Constructor for Editable Graphical view that is blank and set to a default size.
   */
  public CompositeJFrame() {

    this.ejf = new ExcelJFrame();
    this.ws = new ReadOnlyTextual(new SimpleWorkSheetBuilder().createWorksheet());
    setUp();
  }

  /**
   * Reduces duplication of code in constructors.
   */
  private void setUp() {

    this.p = new JPanel(new BorderLayout());
    this.f = new JFrame();
    this.jtf = new JTextField(30);

    this.confirm = new JButton("Confirm");
    this.confirm.setActionCommand("Confirm Button");
    this.clear = new JButton("Clear");
    this.clear.setActionCommand("Cancel Button");

    JPanel input = new JPanel();
    input.add(jtf);
    input.add(confirm);
    input.add(clear);
    this.worksheet = new JPanel();
    this.worksheet.add(ejf.getContentPane());
    this.worksheet.setFocusable(true);

    this.p.add(input, BorderLayout.NORTH);
    this.p.add(worksheet, BorderLayout.SOUTH);

    this.f.add(p);
    this.f.setSize(800, 501);
    this.f.pack();
    this.f.setResizable(false);
    this.currentCoord = new Coord(1,1);
  }


  @Override
  public void render() {
    this.f.setVisible(true);
  }

  @Override
  public String getInputString() {
    return this.jtf.getText();
  }

  @Override
  public void clearInputString() {
    this.jtf.setText("");
  }

  @Override
  public Coord getSelectedCoord() {
    return this.currentCoord;
  }


  @Override
  public void addFeatures(Features features) {
    confirm.addActionListener(evt -> features.setCellContentsOfCell(jtf.toString()));
    clear.addActionListener(evt -> features.clearToolbar());
    ejf.getJTable().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        updateSelectedCoord(ejf.getJTable().getSelectedRow() + 1,
                ejf.getJTable().getSelectedColumn() + 1);
       features.setSelectedCell();
      }
    });
  }

  @Override
  public void updateSelectedCoord(int row, int col) {
    this.currentCoord = new Coord(row, col);
  }

}
