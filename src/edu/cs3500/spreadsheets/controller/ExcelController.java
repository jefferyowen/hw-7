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

/**
 * Controller for our -edit function. Allows user to have access to a view and a model.
 */
public class ExcelController implements Features {
  private WorkSheet<Cell> model;
  private EditView view;
  private Coord currentCoord;

  /**
   * Constructor for the controller, given the model and view.
   *
   * @param m The model that can be manipulated throughout the program.
   * @param v The graphical view of the current model that the user gets.
   */
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



    this.view.setInputString(this.model.getCellAt(this.currentCoord.row - 1,
            this.currentCoord.col - 1).getCellContent().toString());

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
    Cell toAddCell = new Cell(toAdd, this.currentCoord);
    this.model.setCell(this.currentCoord.row - 1, this.currentCoord.col - 1, toAddCell);
    this.view.updateModel(this.model);
  }

  @Override
  public void clearCell() {
    this.view.clearInputString();
    Cell toAddCell = new Cell(new CellComponentBlank(), this.currentCoord);
    this.model.setCell(this.currentCoord.row - 1, this.currentCoord.col - 1, toAddCell);
    this.view.updateModel(this.model);
  }

  @Override
  public void resetTextbar() {
    this.view.setInputString(this.model.getStringOfCell(this.currentCoord.col - 1,
            this.currentCoord.row - 1));
  }

  @Override
  public Coord getCurrentCoord() {
    return this.currentCoord;
  }
}