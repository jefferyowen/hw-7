package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * A visitor class for CellComponent, to return the correct values depending on the Class.
 *
 * @param <R> The type of Value that should be returned by that call.
 */
public interface CellComponentVisitor<R> {
  /**
   * Checks to see if CellComponent is a Boolean, and returns the correct value.
   *
   * @param b the Boolean object bing passed.
   * @return The correct value of the Call depending on which Class it is called upon.
   */
  R visitCellComponentValueBoolean(boolean b);

  /**
   * Checks to see if CellComponent is a Double, and returns the correct value.
   *
   * @param d the Double object bing passed.
   * @return The correct value of the Call depending on which Class it is called upon.
   */
  R visitCellComponentValueDouble(double d);

  /**
   * Checks to see if CellComponent is a String, and returns the correct value.
   *
   * @param s the String object bing passed.
   * @return The correct value of the Call depending on which Class it is called upon.
   */
  R visitCellComponentValueString(String s);

  /**
   * Checks to see if CellComponent is a Reference Formula, and returns the correct value.
   *
   * @param c the CellComponent object bing passed.
   * @return The correct value of the Call depending on which Class it is called upon.
   */
  R visitCellComponentFormulaReference(ArrayList<CellComponent> c);

  /**
   * Gets the correct return value for if Product is ran on that Cell.
   *
   * @param d the Double object bing passed.
   * @return The correct value of the Call depending on which Class it is called upon.
   */

  R visitFunctionProduct(Double d);

  /**
   * Gets the correct return value for if Sum is ran on that Cell.
   *
   * @param d the Double object bing passed.
   * @return The correct value of the Call depending on which Class it is called upon.
   */
  R visitFunctionSum(Double d);

  /**
   * Gets the correct return value for if LessThan is ran on that Cell.
   *
   * @param b the Boolean object bing passed.
   * @return The correct value of the Call depending on which Class it is called upon.
   */
  R visitFunctionLessThan(Boolean b);

  /**
   * Gets the correct return value for if Repeat is ran on that Cell.
   *
   * @param s the String object bing passed.
   * @return The correct value of the Call depending on which Class it is called upon.
   */
  R visitFunctionRepeat(String s);

  /**
   * Checks to see if CellComponent is Blank, and returns the correct value.
   *
   * @param o the Object bing passed.
   * @return The correct value of the Call depending on which Class it is called upon.
   */
  R visitCellComponentBlank(Object o);

}
