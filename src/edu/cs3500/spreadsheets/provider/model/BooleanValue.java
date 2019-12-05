package edu.cs3500.spreadsheets.provider.model;

/**
 * Represents a BooleanValue, which is a Value, that can be contained in a cell.
 */
public class BooleanValue implements Value {
  boolean value;

  /**
   * Constructs a boolean with a given value.
   */
  public BooleanValue(boolean bool) {
    this.value = bool;
  }

  @Override
  public Object getRawValue() {
    return this.value;
  }

  @Override
  public boolean isDouble() {
    return false;
  }

  @Override
  public boolean isString() {
    return false;
  }

  @Override
  public boolean isBoolean() {
    return true;
  }

  @Override
  public Value getEvaluatedValue() {
    return this;
  }

  @Override
  public Formula getRawContents() {
    return this;
  }

  @Override
  public void updateRawContents(Formula form) {
    if (form.isValue()) {
      if (((Value) form).isBoolean()) {
        this.value = ((BooleanValue) form).value;
      }
    }

  }

  @Override
  public void deleteContents() {
    this.value = false;

  }

  @Override
  public boolean isValue() {
    return true;
  }

  @Override
  public boolean isReference() {
    return false;
  }

  @Override
  public boolean isFunction() {
    return false;
  }
}
