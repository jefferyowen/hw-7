package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * The visitor class for Add. Returns the correct value for the Add function.
 */
public class CellComponentVisitorAdd implements CellComponentVisitor<Double> {
  @Override
  public Double visitCellComponentValueBoolean(boolean b) {
    return 0.0;
  }

  @Override
  public Double visitCellComponentValueDouble(double d) {
    return d;
  }

  @Override
  public Double visitCellComponentValueString(String s) {
    return 0.0;
  }

  @Override
  public Double visitCellComponentFormulaReference(ArrayList<CellComponent> c) {
    double sum = 0;
    for (CellComponent comp : c) {
      sum += comp.accept(new CellComponentVisitorAdd());
    }
    return sum;
  }

  @Override
  public Double visitFunctionProduct(Double d) {
    return d;
  }

  @Override
  public Double visitFunctionSum(Double d) {
    return d;
  }

  @Override
  public Double visitFunctionLessThan(Boolean b) {
    return 0.0;
  }

  @Override
  public Double visitFunctionRepeat(String s) {
    return 0.0;
  }

  @Override
  public Double visitCellComponentBlank(Object o) {
    return 0.0;
  }
}
