package edu.cs3500.spreadsheets.controller;

import java.util.Map;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheetAdapted;
import edu.cs3500.spreadsheets.provider.controller.SpreadSheetControllerInterface;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.Worksheet;
import edu.cs3500.spreadsheets.provider.view.SpreadsheetView;


public class ControllerAdapted implements SpreadSheetControllerInterface<Coord, Cell> {

  private SpreadsheetView view;
  private Worksheet<Coord, Cell, CellComponent> adaptedModel;
  private Coord currentCorrd;

  public ControllerAdapted(Worksheet<Coord, Cell, CellComponent> ws) {
    this.adaptedModel = ws;
    this.currentCorrd = new Coord(1, 1);
  }

  @Override
  public void callEditCell(Coord cell, String s) {
    this.setSelectedCell(cell);
    this.adaptedModel.addCell(cell, s);
    this.view.refresh();
  }

  @Override
  public void callAddCell(Coord cell, String s) {
    this.callEditCell(cell, s);
  }

  @Override
  public Map<Coord, Cell> getMap() {
    return this.adaptedModel.getSheet();
  }


  @Override
  public edu.cs3500.spreadsheets.provider.model.Coord getSelectedCell() {
    Coord gotten = this.currentCorrd;
    return new edu.cs3500.spreadsheets.provider.model.Coord(gotten.col, gotten.row);
  }

  @Override
  public void setSelectedCell(Coord c) {
    this.currentCorrd = c;
    this.view.refresh();
  }

}
