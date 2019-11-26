package edu.cs3500.spreadsheets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import edu.cs3500.spreadsheets.controller.ExcelController;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SimpleWorkSheetBuilder;
import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.model.WorkSheetBasic;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import edu.cs3500.spreadsheets.view.CompositeJFrame;
import edu.cs3500.spreadsheets.view.ExcelJFrame;
import edu.cs3500.spreadsheets.view.TextualView;

/**
 * The main class for our program.
 */
public class BeyondGood {
  static private WorksheetBuilder<WorkSheetBasic> builder = new SimpleWorkSheetBuilder();
  static private WorkSheetBasic toReturn;

  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */
  public static void main(String[] args) {
    if (args[0].equals("-in")) {
      String nameOfFile;
      nameOfFile = args[1];
      File currentFile = new File(nameOfFile);
      try {
        FileReader fileReader = new FileReader(currentFile);
        Readable readable = fileReader;
        toReturn = WorksheetReader.read(builder, readable);
      } catch (FileNotFoundException e) {
        System.out.println(nameOfFile + " " + e.getLocalizedMessage());
      }
      if (args[2].equals("-eval")) {
        Coord toEval = Coord.createCoord(args[3]);
        System.out.print(toReturn.evaluate(toEval.row - 1, toEval.col - 1));
      } else if (args[2].equals("-save")) {
        try {
          PrintWriter pw = new PrintWriter(new FileOutputStream(new File(args[3]), false));
          new TextualView(toReturn, pw).render();
        } catch (FileNotFoundException e) {
          System.out.println(nameOfFile + " " + e.getLocalizedMessage());
        }
      } else if (args[2].equals("-gui")) {
        new ExcelJFrame(toReturn).render();
      } else if (args[2].equals("-edit")) {
        new ExcelController(toReturn, new CompositeJFrame(toReturn));

      }
    } else if (args[0].equals("-gui")) {
      new ExcelJFrame().render();
    } else if (args[0].equals("-edit")) {
      WorkSheet ws = new WorkSheetBasic(10, 30);
      new ExcelController(ws, new CompositeJFrame(ws));
    }
  }
}
