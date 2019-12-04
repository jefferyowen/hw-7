package edu.cs3500.spreadsheets.controller;

import java.util.Map;

import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.provider.controller.SpreadSheetControllerInterface;
import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.provider.model.Coord;

public class ControllerAdapted implements SpreadSheetControllerInterface {

  private Features adaptedController;
  private WorkSheet<Cell> adaptedModel;
  private AdaptedView adaptedView;

  public ControllerAdapted(Features ac, WorkSheet) {
    this.adaptedController = ac;
  }
  @Override
  public void callEditCell(Coord cell, String s) {
      this.adaptedController.setSelectedCell();
      this.adaptedController.setCellContentsOfCell(s);
  }

  @Override
  public void callAddCell(Coord cell, String s) {

  }

  @Override
  public Map<Coord, Cell> getMap() {
    return map;
  }

  @Override
  public Coord getSelectedCell() {
    return null;
  }

  @Override
  public void setSelectedCell(Coord c) {

  }
}
