package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * The visitor class for Product. Returns the correct value for the Product function.
 */
public class CellComponentVisitorMultiply implements CellComponentVisitor<Double> {
  @Override
  public Double visitCellComponentValueBoolean(boolean b) {
    return 1.0;
  }

  @Override
  public Double visitCellComponentValueDouble(double d) {
    return d;
  }

  @Override
  public Double visitCellComponentValueString(String s) {
    return 1.0;
  }

  @Override
  public Double visitCellComponentFormulaReference(ArrayList<CellComponent> c) {
    double prod = 1;
    for (CellComponent comp : c) {
      prod *= comp.accept(new CellComponentVisitorAdd());
    }
    return prod;
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
    return 1.0;
  }

  @Override
  public Double visitFunctionRepeat(String s) {
    return 1.0;
  }

  @Override
  public Double visitCellComponentBlank(Object o) {
    return 1.0;
  }
}
