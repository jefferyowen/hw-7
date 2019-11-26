package edu.cs3500.spreadsheets.view;

import java.io.PrintWriter;
import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorkSheet;

/**
 * A textual view of the worksheet. Where all Coords are followed by the value in the
 * corresponding Cell.
 */
public class TextualView implements View {
  private ReadOnlyTextual textual;
  private PrintWriter printWriter;

  /**
   * Constructor for a textual view of a Worksheet.
   *
   * @param ws the Worksheet being passed, to be represented.
   * @param pr the PrinterWriter that prints out the textual view.
   */
  public TextualView(WorkSheet ws, PrintWriter pr) {
    this.textual = new ReadOnlyTextual(ws);
    if (pr != null) {
      this.printWriter = pr;
    }
  }

  @Override
  public void render() {
    ArrayList<ArrayList<Cell>> toReturn = new ArrayList<>();
    for (int row = 0; row < this.textual.getNumCols(); row++) {
      for (int col = 0; col < this.textual.getNumRows(); col++) {
        String cord = new Coord(row + 1, col + 1).toString();
        String cellComponent = this.textual.getStringOfCell(row, col);
        if (!cellComponent.equals("")) {
          this.printWriter.append(cord + " " + cellComponent + "\n");
        }
      }
    }
    printWriter.close();
  }


}
