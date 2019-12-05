package edu.cs3500.spreadsheets.provider.model;

/**
 * Represents what a cell can contain.
 */
public interface Formula {

  /**
   * Returns a Value representing the evaluated formula.
   */
  Value getEvaluatedValue();


  /**
   * Returns a Function representing the formula (unevaluated).
   */
  Formula getRawContents();


  /**
   * Updates the value of this formula (raw contents).
   */
  void updateRawContents(Formula form);

  /**
   * Clears the contents of this Formula.
   */
  void deleteContents();

  /**
   * Determines if a Formula is a Value.
   */
  boolean isValue();

  /**
   * Determines if a Formula is a Reference.
   */
  boolean isReference();

  /**
   * Determines if a Formula is a Function.
   */
  boolean isFunction();


}
