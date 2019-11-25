package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * The visitor for Repeat. Handles the case when a formula must be represented in a String to be
 * repeated.
 */

public class CellComponentVisitorGetString implements CellComponentVisitor<String> {

  @Override
  public String visitCellComponentValueBoolean(boolean b) {
    return String.valueOf(b);
  }

  @Override
  public String visitCellComponentValueDouble(double d) {
    return String.valueOf(d);
  }

  @Override
  public String visitCellComponentValueString(String s) {
    return s;
  }

  @Override
  public String visitCellComponentFormulaReference(ArrayList<CellComponent> c) {
    String toReturn = "";
    for (CellComponent cell : c) {
      toReturn += toReturn + cell.toString();
    }
    return toReturn;
  }

  @Override
  public String visitFunctionProduct(Double d) {
    return String.valueOf(d);
  }

  @Override
  public String visitFunctionSum(Double d) {
    return String.valueOf(d);
  }

  @Override
  public String visitFunctionLessThan(Boolean b) {
    return String.valueOf(b);
  }

  @Override
  public String visitFunctionRepeat(String s) {
    return s;
  }

  @Override
  public String visitCellComponentBlank(Object o) {
    return "";
  }
}
