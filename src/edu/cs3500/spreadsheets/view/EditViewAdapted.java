package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.ControllerAdapted;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheetAdapted;
import edu.cs3500.spreadsheets.provider.model.Worksheet;
import edu.cs3500.spreadsheets.provider.view.EditorView;
import edu.cs3500.spreadsheets.provider.view.JPanelEditSpreadsheet;
import edu.cs3500.spreadsheets.provider.view.SpreadsheetView;

public class EditViewAdapted implements SpreadsheetView {
  private ControllerAdapted controllerAdapted;
  private EditorView view;

  public EditViewAdapted(WorkSheet ws) {
    this.controllerAdapted = new ControllerAdapted(ws, this);
    this.view =  new EditorView(this.controllerAdapted);
  }

  @Override
  public void render() {
    this.view.render();
  }

  @Override
  public void refresh() {
    this.view.refresh();
  }

}
