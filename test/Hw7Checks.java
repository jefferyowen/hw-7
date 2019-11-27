import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.controller.ExcelController;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SimpleWorkSheetBuilder;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheetBasic;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.CompositeJFrame;
import edu.cs3500.spreadsheets.view.EditView;




/**
 * Tester class for HW 7.
 */
public class Hw7Checks {

  EditView model;
  WorkSheet<Cell> ws;
  WorksheetReader.WorksheetBuilder<WorkSheetBasic> builder;
  ExcelController controller;

  /**
   * Initializes a worksheet using a file name.
   *
   * @param fileName the file to be evaluated.
   */
  public void initData(String fileName) {
    String filePath = "/Users/pk/Documents/GitHub/hw5/resources/";
    String file = filePath + "/" + fileName;
    FileReader fileReader = null;
    try {
      fileReader = new FileReader(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Readable readable = fileReader;
    builder = new SimpleWorkSheetBuilder();
    ws = WorksheetReader.read(builder, readable);

    model = new CompositeJFrame(ws);
    controller = new ExcelController(ws, model);
  }


  @Test
  public void setCellContentsOfCell(){
    initData("spreadsheet2.txt");
    Assert.assertEquals(" 3.00", ws.getCellAt(0,0).toString());
    controller.setCellContentsOfCell("=4.00");
    Assert.assertEquals(" 4.00", ws.getCellAt(0,0).toString());

  }

  @Test
  public void setSelectedCell() {
    initData("spreadsheet1.txt");
    model.updateSelectedCoord(1,1);
    assertEquals(model.getSelectedCoord(), new Coord(1,1));
    model.updateSelectedCoord(2,2);
    controller.setSelectedCell();
    assertEquals(model.getSelectedCoord(), new Coord(2,2));
  }

  @Test
  public void setSelectedCellOutOfOriginalSize() {
    initData("spreadsheet1.txt");
    model.updateSelectedCoord(1,1);
    assertEquals(model.getSelectedCoord(), new Coord(1,1));
    model.updateSelectedCoord(20,20);
    controller.setSelectedCell();
    assertEquals(model.getSelectedCoord(), new Coord(20,20));
  }

  @Test
  public void resetTextBar() {
    initData("spreadsheet1.txt");
    assertEquals(ws.getStringOfCell(0,0), "6.00");
    model.setInputString("Ape");
    controller.resetTextbar();
    assertEquals("6.00", model.getInputString());
  }

}
