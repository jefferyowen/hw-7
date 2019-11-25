package edu.cs3500.spreadsheets.view;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheetBasic;

/**
 * Graphical view for our Excel Spreadsheet.
 */
public class ExcelJFrame extends JFrame implements View {

  JScrollPane sp;
  private JPanel p;
  private JFrame f;
  private ExcelJTable j;
  private DefaultTableModel dtm;
  private ReadOnlyTextual ws;

  /**
   * Constructor for Graphical view given Worksheet.
   *
   * @param ws the Worksheet to be represented by the graphical view.
   */
  public ExcelJFrame(WorkSheet ws) {

    this.ws = new ReadOnlyTextual(ws);

    this.f = new JFrame();
    this.f.setTitle("Excel Spreadsheet");

    int col = this.ws.getNumCols();
    int row = this.ws.getNumRows();

    if (col < 10) {
      col = 10;
    } else if (row < 30) {
      row = 30;
    }

    this.dtm = new DefaultTableModel(row, col);

    j = new ExcelJTable(dtm);

    p = new JPanel();

    readData();
    setUp();
  }

  /**
   * Constructor for Graphical view that is blank and set to a default size.
   */
  public ExcelJFrame() {
    this.ws = new ReadOnlyTextual(new WorkSheetBasic(30, 10));

    this.f = new JFrame();
    this.f.setTitle("Excel Spreadsheet");

    int col = this.ws.getNumCols();
    int row = this.ws.getNumRows();

    this.dtm = new DefaultTableModel(row, col);

    j = new ExcelJTable(dtm);


    p = new JPanel();

    readData();
    setUp();

  }

  public void getSelectedCell() {
    System.out.println(this.j.getSelectedRow() + "" + this.j.getSelectedColumn());
  }

  @Override
  public void render() {

    f.setVisible(true);
  }

  @Override
  public Container getContentPane() {
    return this.f.getContentPane();
  }


  /**
   * Reduces duplication of code in constructors.
   */
  private void setUp() {

    this.setJTableColumnsWidth(j, 150);

    this.sp = new JScrollPane(j);

    this.freezeRows(sp);

    addScrollListeners();

    p.add(sp);


    f.add(p);
    f.setSize(800, 500);
    f.setResizable(false);
    f.setFocusable(true);
    f.pack();
  }

  /**
   * Reads the data from the Worksheet and puts it into the JTable.
   */
  private void readData() {
    for (int i = 0; i < this.ws.getNumRows(); i++) {
      for (int j = 0; j < this.ws.getNumCols(); j++) {
        this.dtm.setValueAt(ws.getCellAt(i, j).getCellContent().evaluate(), i, j);
      }
    }



  }

  /**
   * Adds ScrollListeners that allow for infinite scrolling.
   */
  private void addScrollListeners() {
    sp.getVerticalScrollBar().addAdjustmentListener(e -> {
      JViewport vp = sp.getViewport();
      if (vp.getView().getHeight() <= vp.getHeight() + vp.getViewPosition().y) {
        String[] s = new String[dtm.getColumnCount()];
        dtm.addRow(s);
        this.freezeRows(sp);
      }
    });

    sp.getHorizontalScrollBar().addAdjustmentListener(e -> {
      JViewport vp = sp.getViewport();
      if (vp.getView().getWidth() <= vp.getWidth() + vp.getViewPosition().x) {
        int cc = dtm.getColumnCount();
        dtm.setColumnCount(cc + 1);
      }
    });
  }

  /**
   * Freezes the row headers, so that they are viewable anywhere on the page.
   *
   * @param scroller The JScroller pane being added to.
   */
  private void freezeRows(JScrollPane scroller) {

    String[][] frozenRows = new String[this.dtm.getRowCount()][1];

    for (int i = 0; i < dtm.getRowCount(); i++) {
      frozenRows[i][0] = Integer.toString(i + 1);
    }
    String[] blank = new String[1];
    blank[0] = "";
    ExcelJTable rowHeader = new ExcelJTable(frozenRows, blank);
    rowHeader.setGridColor(Color.BLACK);
    rowHeader.setShowGrid(true);
    rowHeader.setBackground(Color.lightGray);
    rowHeader.setRowSelectionAllowed(false);
    rowHeader.setColumnSelectionAllowed(false);
    rowHeader.setCellSelectionEnabled(false);
    rowHeader.setFocusable(false);
    rowHeader.getColumnModel().getColumn(0).setMaxWidth(50);
    rowHeader.setPreferredScrollableViewportSize(new Dimension(45, 200));
    rowHeader.getColumnModel().getColumn(0).setCellRenderer(
          new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row, int column) {

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                    column);
            setText(value.toString());
            String s = (String) value;
            int i = Integer.parseInt(s);
            if (i >= 1000) {
              setHorizontalAlignment(CENTER);
            } else {
              setHorizontalAlignment(LEFT);
            }
            setHorizontalAlignment(CENTER);
            setBackground(Color.LIGHT_GRAY);
            setFont(getFont().deriveFont(Font.BOLD));

            return this;
          }

        });

    scroller.setRowHeaderView(rowHeader);

  }

  /**
   * Sets width for all cells.
   *
   * @param table               The table that contains the Cells.
   * @param tablePreferredWidth The width as an int, for each cell.
   */
  private void setJTableColumnsWidth(JTable table, int tablePreferredWidth) {

    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      TableColumn column = table.getColumnModel().getColumn(i);
      column.setPreferredWidth(tablePreferredWidth);
    }

  }

}