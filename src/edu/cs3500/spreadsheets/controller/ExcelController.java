package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellComponent;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SexpVisitorCellComponent;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.cells.CellComponentBlank;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.view.EditView;

public class ExcelController implements Features {
  private WorkSheet model;
  private EditView view;
  private Coord currentCoord;

  public ExcelController(WorkSheet m, EditView v) {
    this.model = m;
    this.view = v;
    this.view.addFeatures(this); // This controller can handle both kinds of events directly
    this.view.render();
    this.currentCoord = new Coord(1, 1);
  }

  @Override
  public void setSelectedCell() {
    Coord currentCord = view.getSelectedCoord();
    this.currentCoord = new Coord(currentCord.row, currentCord.col);

    Cell c = (Cell) this.model.getCellAt(this.currentCoord.row - 1,
            this.currentCoord.col - 1);

    this.view.setInputString(c.getCellContent().toString());

  }

  @Override
  public void setCellContentsOfCell(String contents) {
    if (contents.contains("=")) {
      contents = contents.substring(1);
    }
    Sexp toAddSexp = Parser.parse(contents);
    CellComponent toAdd =
            toAddSexp.accept(new SexpVisitorCellComponent(this.model,
                    this.currentCoord.col, this.currentCoord.row));

    this.model.setCell(this.currentCoord.row - 1, this.currentCoord.col - 1,
            toAdd);
    this.view.updateModel(this.model);
  }

  @Override
  public void clearCell() {
    this.view.clearInputString();
    this.model.setCell(this.currentCoord.row - 1, this.currentCoord.col - 1,
            new CellComponentBlank());
    this.view.updateModel(this.model);
  }

  @Override
  public void resetTextbar() {
    this.view.setInputString(this.model.getStringOfCell(this.currentCoord.col - 1,
            this.currentCoord.row - 1));
  }
}