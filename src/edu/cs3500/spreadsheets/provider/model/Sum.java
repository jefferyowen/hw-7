package edu.cs3500.spreadsheets.provider.model;

import java.util.List;

/**
 * The sum function used to evaluate sum expressions.
 */
public class Sum implements Function.IFunc {

  /**
   * Finds the sum of the given list of values.
   */
  public Value apply(List args) {
    List<Formula> formulas = args;
    DoubleValue sum = new DoubleValue(0.0);
    for (Formula formula : formulas) {
      if (formula.getEvaluatedValue().isDouble()) {
        sum.value = sum.value + ((DoubleValue) formula.getEvaluatedValue()).value;
      }
    }
    return sum;
  }
}
