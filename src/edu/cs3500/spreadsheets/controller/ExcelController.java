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
    System.out.println("Start: " + this.currentCoord);
    Coord currentCord = view.getSelectedCoord();
    System.out.println(currentCord.row + " " + currentCord.col);
    this.currentCoord = new Coord(currentCord.row , currentCord.col);
    System.out.println("End: " + this.currentCoord);
  }

  @Override
  public void setCellContentsOfCell(String contents) {

  }

  @Override
  public void clearToolbar() {

  }
}