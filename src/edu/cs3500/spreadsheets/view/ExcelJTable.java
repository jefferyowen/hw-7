package edu.cs3500.spreadsheets.view;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * Creates  a JTable for our specific specifications. The actual grid model of our worksheet.
 * Contains all the values of the given worksheets, in the correct positions.
 */
public class ExcelJTable extends JTable {

  /**
   * Constructor for the JTable, that takes in data and header names.
   *
   * @param data        the data that will go in the Cells.
   * @param columnNames the names of the Headers for the JTable.
   */
  public ExcelJTable(String[][] data, String[] columnNames) {
    super(data, columnNames);
    setRules();
  }

  /**
   * Constructor for the JTable, that takes in row and col.
   *
   * @param row the number of rows for the Table.
   * @param col the number of cols for the Table.
   */
  public ExcelJTable(int row, int col) {
    super(row, col);
    setRules();
  }

  /**
   * Constructor for the JTable, that takes in a DefaultTableModel.
   *
   * @param dtm a Default Table that has data in it, being passed to the JTable.
   */
  public ExcelJTable(DefaultTableModel dtm) {
    super(dtm);
    setRules();
  }

  /**
   * Sets the visual rules and the modification rules for the table.
   */
  private void setRules() {
    this.getTableHeader().setReorderingAllowed(false);
    this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    this.setBounds(30, 40, 200, 300);
    this.setGridColor(Color.BLACK);
    this.setShowGrid(true);
    this.setRowSelectionAllowed(false);
    this.setColumnSelectionAllowed(false);
    this.setCellSelectionEnabled(true);
    this.getTableHeader().setResizingAllowed(false);
  }


  @Override
  public boolean isCellEditable(int row, int col) {
    return false;
  }


}
