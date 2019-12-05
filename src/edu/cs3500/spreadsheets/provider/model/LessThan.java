package edu.cs3500.spreadsheets.provider.model;

import java.util.List;

/**
 * The less than function used to evaluate less than expressions.
 */
public class LessThan implements Function.IFunc {

  /**
   * Returns a BooleanValue if the first value is less than the second value.
   */
  public Value apply(List args) {
    List<Formula> formulas = args;
    DoubleValue product = new DoubleValue(0.0);
    if (formulas.size() == 2) {
      Value func1 = formulas.get(0).getEvaluatedValue();
      Value func2 = formulas.get(1).getEvaluatedValue();
      if (func1.isDouble() && func2.isDouble()) {
        if (((DoubleValue) func1).value < ((DoubleValue) func2).value) {
          return new BooleanValue(true);
        } else {
          return new BooleanValue(false);
        }
      }
    }
    return null;
  }

}
