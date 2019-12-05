package edu.cs3500.spreadsheets.controller;

import java.util.Map;

import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.provider.controller.SpreadSheetControllerInterface;
import edu.cs3500.spreadsheets.provider.model.Coord;
import edu.cs3500.spreadsheets.provider.model.Worksheet;
import edu.cs3500.spreadsheets.provider.view.EditorView;
import edu.cs3500.spreadsheets.provider.view.SpreadsheetView;


public class ControllerAdapted implements SpreadSheetControllerInterface {

  private SpreadsheetView view;
  private Worksheet<Coord, Cell, CellComponent> adaptedModel;
  private Coord currentCorrd;

  public ControllerAdapted(Worksheet ws) {
    this.adaptedModel = ws;
    this.currentCorrd = new Coord(1, 1);
    this.view = new EditorView(this);
    this.view.render();
  }


  @Override
  public void callEditCell(Coord cell, String s) {
    this.setSelectedCell(new Coord(cell.col, cell.row));
    this.adaptedModel.addCell(new Coord(cell.col, cell.row), s);
    this.view.refresh();
  }

  @Override
  public void callAddCell(edu.cs3500.spreadsheets.provider.model.Coord cell, String s) {
    this.callEditCell(cell, s);
  }

  @Override
  public Map<Coord, Cell> getMap() {
    return this.adaptedModel.getSheet();
  }


  @Override
  public Coord getSelectedCell() {
    Coord gotten = this.currentCorrd;
    return new Coord(gotten.col, gotten.row);
  }

  @Override
  public void setSelectedCell(Coord c) {
    this.currentCorrd = c;
  }


}
