package edu.cs3500.spreadsheets.provider.model;

/**
 * Represents a StringValue, which is a Value, that can be contained in a cell.
 */
public class StringValue implements Value {
  String value;

  /**
   * Constructs a string with a given value.
   */
  public StringValue(String str) {
    this.value = str;
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
    return true;
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
      if (((Value) form).isString()) {
        this.value = ((StringValue) form).value;
      }
    }

  }

  @Override
  public void deleteContents() {
    this.value = null;

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
