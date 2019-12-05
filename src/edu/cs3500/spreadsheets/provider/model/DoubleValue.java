package edu.cs3500.spreadsheets.provider.model;

/**
 * Represents a DoubleValue, which is a Value, that can be contained in a cell.
 */
public class DoubleValue implements Value {
  double value;

  /**
   * Constructs a double with a given value.
   */
  public DoubleValue(double value) {
    this.value = value;
  }

  @Override
  public Object getRawValue() {
    return this.value;
  }

  @Override
  public boolean isDouble() {
    return true;
  }

  @Override
  public boolean isString() {
    return false;
  }

  @Override
  public boolean isBoolean() {
    return false;
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
      if (((Value) form).isDouble()) {
        this.value = ((DoubleValue) form).value;
      }
    }
  }

  @Override
  public void deleteContents() {
    this.value = 0;

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
