package edu.cs3500.spreadsheets.provider.model;

import java.util.List;

/**
 * IFunc implementation Function that adds two strings together.
 */
public class Append implements Function.IFunc {

  /**
   * Adds two strings together.
   */
  public Value apply(List args) {
    List<Formula> formulas = args;
    StringBuilder appended = new StringBuilder();

    for (Formula formula : formulas) {
      if (formula.getEvaluatedValue().isString()) {
        appended = appended.append(((StringValue) formula.getEvaluatedValue()).value);
      }
    }
    return new StringValue(appended.toString());
  }
}
