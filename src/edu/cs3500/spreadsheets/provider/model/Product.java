package edu.cs3500.spreadsheets.provider.model;

import java.util.List;

/**
 * The product function used to evaluate product expressions.
 */
public class Product implements Function.IFunc {

  /**
   * Returns the product of a list of evaluated values.
   */
  public Value apply(List args) {
    List<Formula> formulas = args;
    DoubleValue product = new DoubleValue(0.0);
    for (Formula formula : formulas) {
      if (formula.getEvaluatedValue().isDouble()) {
        product.value = product.value * ((DoubleValue) formula.getEvaluatedValue()).value;
      }
    }
    return product;
  }
}
