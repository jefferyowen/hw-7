package edu.cs3500.spreadsheets.provider.model;

/**
 * The primitive values that can be in a cell.
 */
public interface Value extends Formula {
  /**
   * Returns the raw Value.
   */
  public Object getRawValue();

  /**
   * Returns true if this value is a double.
   */
  public boolean isDouble();

  /**
   * Returns true if this value is a string.
   */
  public boolean isString();

  /**
   * Returns true if this value is a boolean.
   */
  public boolean isBoolean();

}
