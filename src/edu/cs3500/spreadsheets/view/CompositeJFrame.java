package edu.cs3500.spreadsheets.view;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorkSheet;

/**
 * Adds ability of updating Cell values in the graphical view. Can clear changes, and revert back to
 * original value. Also able to delete Cells.
 */
public class CompositeJFrame extends JFrame implements EditView {

  private ExcelJFrame ejf;
  private JFrame f;
  private JPanel p;
  private JButton confirm;
  private JButton clear;
  private JTextField jtf;
  private JPanel worksheet;
  private Coord currentCoord;
  private JTextArea instructionsPane;
  /**
   * Constructor for Editable Graphical view given Worksheet.
   *
   * @param ws the Worksheet to be represented by the editable graphical view.
   */
  public CompositeJFrame(WorkSheet ws) {
    this.ejf = new ExcelJFrame(ws);
    setUp();
  }

  /**
   * Constructor for Editable Graphical view that is blank and set to a default size.
   */
  public CompositeJFrame() {
    this.ejf = new ExcelJFrame();
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

    this.instructionsPane = new JTextArea();
    this.instructionsPane.setText("Welcome to this spreadsheet. Click a cell to access it. \n" +
            "If you want to change the cell's contents type into the cell and use the confirm  \n" +
            "button to change the cell. If you don't want to commit the changes you put in the \n" +
            "textbar, use the clear button to revert the textbar. Also, you can use the delete \n" +
            "key on a cell to clear its contents (Fn + delete on macs). Enjoy! \n");

    this.f.add(p);
    this.f.setSize(800, 501);
    this.f.pack();
    this.f.add(instructionsPane, BorderLayout.SOUTH);
    this.f.setResizable(false);
    this.currentCoord = new Coord(1, 1);
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
  public void setInputString(String input) {
    this.clearInputString();
    this.jtf.setText(input);
  }

  @Override
  public void updateModel(WorkSheet ws) {
    this.ejf.updateTable(ws);

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


    confirm.addActionListener(evt -> {
      try {
        features.setCellContentsOfCell(this.getInputString());
      } catch (IllegalArgumentException e) {
        System.out.print("Invalid input. Strings must be contained in quotations!");
      }
    });

    clear.addActionListener(evt -> features.resetTextbar());

    this.ejf.getJTable().addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_DELETE) {
          features.clearCell();
        }
      }
    });

    this.ejf.getJTable().addMouseListener(new MouseAdapter() {
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
