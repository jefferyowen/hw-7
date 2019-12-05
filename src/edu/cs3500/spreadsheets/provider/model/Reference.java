package edu.cs3500.spreadsheets.provider.model;

import java.util.Map;

/**
 * Represents a reference to a group/single cell.
 */
public class Reference implements Formula {
  private Coord topLeft;
  private Coord botRight;
  private Map<Coord, Cell> worksheet;

  /**
   * Builds a reference to a group/single cell.
   */
  public Reference(Coord topLeft, Coord botRight) {
    if (topLeft.col <= botRight.col && topLeft.row <= botRight.row) {
      this.topLeft = topLeft;
      this.botRight = botRight;
    } else {
      throw new IllegalArgumentException("The first cell is not above and to the left of the "
              + "bottom corner cell");
    }
  }

  /**
   * Sets the worksheet.
   */
  public void setWorksheet(Map<Coord, Cell> sheet) {
    this.worksheet = sheet;
  }

  @Override
  public Value getEvaluatedValue() {
    if (worksheet != null && topLeft.toString().equals(botRight.toString())) {
      return worksheet.get(topLeft).getValue();
    } else {
      throw new IllegalArgumentException("A reference does not have an evaluated value "
              + "if it refers to more than one cell. Is this due to the worksheet being" +
              " uninitalized:" + (worksheet == null));
    }
  }

  @Override
  public Formula getRawContents() {
    return this;
  }

  @Override
  public void updateRawContents(Formula form) {
    if (form.isReference()) {
      this.topLeft = ((Reference) form).topLeft;
      this.botRight = ((Reference) form).botRight;
    } else {
      throw new IllegalArgumentException("Cannot update reference with another data type");
    }

  }

  @Override
  public void deleteContents() {
    this.botRight = null;
    this.topLeft = null;

  }

  @Override
  public boolean isValue() {
    return false;
  }

  @Override
  public boolean isReference() {
    return true;
  }

  @Override
  public boolean isFunction() {
    return false;
  }
}
