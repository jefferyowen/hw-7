import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.SimpleWorkSheetBuilder;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheetBasic;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.ReadOnlyTextual;
import edu.cs3500.spreadsheets.view.TextualView;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for HW 6.
 */
public class Hw6Checks {
  FileReader reader;
  ReadOnlyTextual readOnlyTextual;
  TextualView tv;
  WorkSheet<Cell> ws;
  WorkSheet<Cell> wsBuilt;
  WorksheetReader.WorksheetBuilder<WorkSheetBasic> builder;
  WorksheetReader.WorksheetBuilder<WorkSheetBasic> builder2;

  /**
   * Initializes a worksheet using a file name.
   *
   * @param fileName the file to be evaluated.
   */
  public void initData(String fileName) {
    String filePath = "/Users/pk/Documents/GitHub/hw5/resources";
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
  }

  @Test
  public void testWritingSpreadSheetOne() {
    initData("spreadsheet1.txt");
    PrintWriter pw = null;
    File toBeWrittenOver = new File("/Users/pk/Documents/GitHub/hw5/resources/Empty.txt");
    try {
      pw = new PrintWriter(new FileOutputStream(toBeWrittenOver,
              false));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    TextualView tv = new TextualView(ws, pw);
    String content = null;
    try {
      content = Files.readString(Paths.get(String.valueOf(toBeWrittenOver))
              , StandardCharsets.US_ASCII);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // check that before render the file is empty
    assertEquals(content, "");
    tv.render();
    try {
      content = Files.readString(Paths.get(String.valueOf(toBeWrittenOver))
              , StandardCharsets.US_ASCII);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // check after render file has been updated
    assertEquals(content, "A1  6.00\n" +
            "A2 14.00\n" +
            "A3 false\n" +
            "A4 \"spider\"\n" +
            "B1 true\n" +
            "B3 100.00\n" +
            "B4 13.00\n" +
            "C1 \"google\"\n" +
            "C2 \"deer\"\n" +
            "C4 false\n" +
            "D1 \"gong\"\n" +
            "D2 98.00\n" +
            "D3 44.00\n" +
            "D4 \"inuit\"\n");
    FileReader fileReader = null;
    try {
      fileReader = new FileReader(toBeWrittenOver);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Readable readable = fileReader;
    builder2 = new SimpleWorkSheetBuilder();
    wsBuilt = WorksheetReader.read(builder2, readable);
    //test cells are the same in the two re-written sheets (1st four cells only)
    assertEquals(wsBuilt.getCellAt(0, 0), ws.getCellAt(0, 0));
    assertEquals(wsBuilt.getCellAt(0, 1), ws.getCellAt(0, 1));
    assertEquals(wsBuilt.getCellAt(0, 2), ws.getCellAt(0, 2));
    assertEquals(wsBuilt.getCellAt(0, 3), ws.getCellAt(0, 3));
    assertEquals(wsBuilt.getNumCols(), ws.getNumCols());
    assertEquals(wsBuilt.getNumRows(), ws.getNumRows());
  }


  @Test
  public void testWritingSpreadSheetTwo() {
    initData("spreadsheet2.txt");
    PrintWriter pw = null;
    File toBeWrittenOver = new File("/Users/pk/Documents/GitHub/hw5/resources/Empty.txt");
    try {
      pw = new PrintWriter(new FileOutputStream(toBeWrittenOver,
              false));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    TextualView tv = new TextualView(ws, pw);
    String content = null;
    try {
      content = Files.readString(Paths.get(String.valueOf(toBeWrittenOver))
              , StandardCharsets.US_ASCII);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // check that before render the file is empty
    assertEquals(content, "");
    tv.render();
    try {
      content = Files.readString(Paths.get(String.valueOf(toBeWrittenOver))
              , StandardCharsets.US_ASCII);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // check after render file has been updated
    assertEquals(content, "A1  3.00\n" +
            "A2 \"spider\"\n" +
            "A3 19.00\n" +
            "A4 false\n" +
            "B1 =(< A1 A3)\n" +
            "B3 =(SUM A1 A3)\n" +
            "B4 =(SUM A1 A3)\n" +
            "C1 =(REPEAT A2)\n" +
            "C3 =(SUM A1:B4 )\n" +
            "C4 =(SUM A1:B4 )\n" +
            "D1 =(< 20.00  0.00)\n" +
            "D2  6.00\n" +
            "D3 true\n" +
            "D4 \"fudge\"\n");
    FileReader fileReader = null;
    try {
      fileReader = new FileReader(toBeWrittenOver);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Readable readable = fileReader;
    builder2 = new SimpleWorkSheetBuilder();
    wsBuilt = WorksheetReader.read(builder2, readable);
    //test cells are the same in the two re-written sheets (2nd row cells only)
    assertEquals(wsBuilt.getCellAt(1, 0), ws.getCellAt(1, 0));
    assertEquals(wsBuilt.getCellAt(1, 1), ws.getCellAt(1, 1));
    assertEquals(wsBuilt.getCellAt(1, 2), ws.getCellAt(1, 2));
    assertEquals(wsBuilt.getCellAt(1, 3), ws.getCellAt(1, 3));
    assertEquals(wsBuilt.getNumCols(), ws.getNumCols());
    assertEquals(wsBuilt.getNumRows(), ws.getNumRows());
  }

  // file has a cycle
  @Test(expected = IllegalArgumentException.class)
  public void testWritingSpreadSheetThree() {
    initData("spreadsheet3.txt");
  }

  /**
  @Test(expected = IllegalArgumentException.class)
  public void testTextualViewNull() {
    initData("spreadsheet1.txt");
    TextualView test = new TextualView(ws, null);
  }
 **/
}
