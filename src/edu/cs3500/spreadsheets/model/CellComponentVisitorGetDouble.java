package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * A visitor that gets the Double value for each CellComponent.
 */
public class CellComponentVisitorGetDouble implements CellComponentVisitor<Double> {
  @Override
  public Double visitCellComponentValueBoolean(boolean b) {
    throw new IllegalArgumentException("Not a double");
  }

  @Override
  public Double visitCellComponentValueDouble(double d) {
    return d;
  }

  @Override
  public Double visitCellComponentValueString(String s) {
    throw new IllegalArgumentException("Not a double");
  }

  @Override
  public Double visitCellComponentFormulaReference(ArrayList<CellComponent> c) {
    double d = -1;
    for (CellComponent comp : c) {
      d = comp.accept(new CellComponentVisitorGetDouble());
    }
    return d;
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
    throw new IllegalArgumentException("Not a double");
  }

  @Override
  public Double visitFunctionRepeat(String s) {
    throw new IllegalArgumentException("Not a double");
  }

  @Override
  public Double visitCellComponentBlank(Object o) {
    throw new IllegalArgumentException("Not a double");
  }
}
