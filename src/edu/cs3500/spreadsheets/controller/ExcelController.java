package edu.cs3500.spreadsheets.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.cells.CellComponentBlank;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.view.CompositeJFrame;
import edu.cs3500.spreadsheets.view.EditView;
import edu.cs3500.spreadsheets.view.View;

public class ExcelController implements Features {
  private WorkSheet model;
  private EditView view;
  private Coord currentCoord;

  public ExcelController(WorkSheet m, EditView v) {
    this.model = m;
    this.view = v;
    this.view.addFeatures(this); // This controller can handle both kinds of events directly
    this.view.render();
    this.currentCoord = new Coord(1,1);
  }

  @Override
  public void setSelectedCell() {
    Coord currentCord = view.getSelectedCoord();
    this.currentCoord = new Coord(currentCord.row , currentCord.col);

    Cell c = (Cell) this.model.getCellAt(this.currentCoord.row - 1,
            this.currentCoord.col- 1);

    this.view.setInputString(c.getCellContent().toString());

  }

  @Override
  public void setCellContentsOfCell(String contents) {
  }

  @Override
  public void clearCell() {

    this.view.clearInputString();

    this.model.setCell(this.currentCoord.row, this.currentCoord.col, new CellComponentBlank());

  }
}