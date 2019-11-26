package edu.cs3500.spreadsheets.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.view.EditView;
import edu.cs3500.spreadsheets.view.View;

public class ExcelController implements Features {
  private WorkSheet model;
  private EditView view;
  private Cell currenCell;

  public ExcelController(WorkSheet m, EditView v) {
    this.model = m;
    this.view = v;
    this.view.addFeatures(this); // This controller can handle both kinds of events directly
    this.view.render();
    this.currenCell = (Cell) m.getCellAt(0, 0);
  }

  @Override
  public void setSelectedCell() {
    System.out.println(this.currenCell.getCord());
    Coord currentCord = view.getSelectedCell().getCord();
    this.currenCell = (Cell) model.getCellAt(currentCord.row, currentCord.col);
    System.out.println(this.currenCell.getCord());
  }

  @Override
  public void setCellContentsOfCell(String contents) {

  }

  @Override
  public void clearToolbar() {
    this.view.clearInputString();
  }
}