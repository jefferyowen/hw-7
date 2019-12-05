package edu.cs3500.spreadsheets.provider.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Representation of a cell in the spreadsheet.
 */
public class Cell {

  private final Coord coordinate;
  private Value value;
  private Formula formula;
  private Map<Coord, Cell> theEntireWorksheet;

  /**
   * Initializes a cell from the given coordinate.
   *
   * @param coordinate location of the cell
   */
  Cell(Coord coordinate, Map<Coord, Cell> theEntireWorksheet) {
    this.coordinate = coordinate;
    this.value = null;
    this.formula = null;
    this.theEntireWorksheet = theEntireWorksheet;
  }


  /**
   * Gets the coordinate of the cell.
   */
  Coord getCoordinate() {
    return this.coordinate;
  }

  /**
   * Gets the value of the cell.
   */
  public Value getValue() {
    return this.value;
  }

  /**
   * Gets the formula of the cell.
   */
  public Formula getFormula() {
    return formula;
  }

  /**
   * Gets the entire worksheet.
   */
  public Map<Coord, Cell> getWorksheet() {
    Map<Coord, Cell> roSheet = new HashMap<>(theEntireWorksheet);
    return roSheet;
  }

  /**
   * Updates the entire worksheet.
   */
  void setTheEntireWorksheet(Map<Coord, Cell> list) {
    this.theEntireWorksheet = list;
  }

  /**
   * Sets the value of the cell.
   */
  public void setValue(Value val) { //TODO add checks so values are valid
    this.value = val;
  }

  /**
   * Set the formula of the cell.
   */
  public void setFormula(Formula form) { //TODO add checks so formulas are valid
    this.formula = form;
    this.setValue(form.getEvaluatedValue());
  }

  /**
   * Using SExps, set the function to the results of the evalutated SExp.
   */
  public void setFormulaFromString(String str) {
    Sexp s = Parser.parse(str);
    Object formula =
            s.accept(new SexpVisitorImpl());

    if (formula instanceof Value) {
      this.formula = (Formula) formula;
      this.value = (Value) formula;
    } else if (formula instanceof ArrayList) {
      //If it contains more than one element
      if (((ArrayList) formula).size() >= 2) {
        int size = ((ArrayList) formula).size();
        if (((ArrayList) formula).get(0) instanceof Function) {
          this.formula = new Function((Function.IFunc) ((ArrayList) formula).get(0),
                  ((ArrayList) formula).subList(1, size - 1));
        }
      }
      this.value = this.formula.getEvaluatedValue();

    } else if (formula instanceof Formula) {
      this.formula = (Formula) formula;
      if (formula instanceof Reference) {
        ((Reference) this.formula).setWorksheet(getWorksheet());
      }
      this.value = this.formula.getEvaluatedValue();
    }
  }

  void clearThisCell() {
    value.deleteContents();
    formula.deleteContents();
  }
}