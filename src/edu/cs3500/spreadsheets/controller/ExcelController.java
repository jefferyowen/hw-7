package edu.cs3500.spreadsheets.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.view.EditView;
import edu.cs3500.spreadsheets.view.View;

public class ExcelController implements Features{
  private WorkSheet model;
  private EditView view;

  public ExcelController(WorkSheet m, EditView v) {
    this.model = m;
    this.view = v;
    this.view.addFeatures(this); // This controller can handle both kinds of events directly
    this.view.render();
  }

  @Override
  public void setSelectedCell() {

  }

  @Override
  public void setCellContentsOfCell() {

  }

  @Override
  public void clearToolbar() {

  }
}