package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Creates a simple WorkSheet that contains Cells.
 */
public class SimpleWorkSheetBuilder implements WorksheetBuilder<WorkSheetBasic> {
  private WorkSheetBasic workSheetToMake;

  /**
   * Basic constructor for a builder worksheet.
   */
  public SimpleWorkSheetBuilder() {
    this.workSheetToMake = new WorkSheetBasic(10, 10);
  }

  @Override
  public WorksheetBuilder<WorkSheetBasic> createCell(int col, int row, String contents) {
    if (col < 1 || row < 1) {
      throw new IllegalArgumentException("Either the row or column size is too small.");
    }
    if (contents.substring(0, 1).equals("=")) {
      contents = contents.substring(1);
    }
    Sexp toAddSexp = Parser.parse(contents);
    CellComponent value = toAddSexp.accept(new SexpVisitorCellComponent(this.workSheetToMake, col
            , row));
    Coord toAddCord = new Coord (col, row);
    Cell toAdd = new Cell(value, toAddCord);
    this.workSheetToMake.addCell(row - 1, col - 1);
    this.workSheetToMake.setCell(row - 1, col - 1,  toAdd);
    return this;
  }

  @Override
  public WorkSheetBasic createWorksheet() {
    return this.workSheetToMake;
  }
}