package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A value type representing coordinates in a {@link WorkSheet}.
 */
public class Coord {
  public final int row;
  public final int col;

  /**
   * Creates a Coord with the given col and row.
   *
   * @param col the col of the new Coord.
   * @param row the row of the new Coord.
   */
  public Coord(int col, int row) {
    if (row < 1 || col < 1) {
      throw new IllegalArgumentException("Coordinates should be strictly positive");
    }
    this.row = row;
    this.col = col;
  }

  /**
   * Converts from the A-Z column naming system to a 1-indexed numeric value.
   *
   * @param name the column name
   * @return the corresponding column index
   */
  public static int colNameToIndex(String name) {
    name = name.toUpperCase();
    int ans = 0;
    for (int i = 0; i < name.length(); i++) {
      ans *= 26;
      ans += (name.charAt(i) - 'A' + 1);
    }
    return ans;
  }

  /**
   * Converts a 1-based column index into the A-Z column naming system.
   *
   * @param index the column index
   * @return the corresponding column name
   */
  public static String colIndexToName(int index) {
    StringBuilder ans = new StringBuilder();
    while (index > 0) {
      int colNum = (index - 1) % 26;
      ans.insert(0, Character.toChars('A' + colNum));
      index = (index - colNum) / 26;
    }
    return ans.toString();
  }

  /**
   * Given just one reference of a cell in a sheet, create a Coord.
   *
   * @param rawCord the string of the current Coordinate to be processed.
   * @return a Coord that represents the coordinate of the given String
   */
  public static Coord createCoord(String rawCord) {
    if (rawCord.length() < 1) {
      throw new IllegalArgumentException("rawCord is malformed");
    }
    String changeToCol;
    String changeToRow;
    int numOfFirstDigit = 0;
    boolean firstDigitNotFound = true;
    for (int i = 1; i < rawCord.length(); i++) {
      if (Character.isDigit(rawCord.charAt(i)) && firstDigitNotFound) {
        numOfFirstDigit = i;
        firstDigitNotFound = false;
      }
    }
    changeToCol = rawCord.substring(0, numOfFirstDigit);
    changeToRow = rawCord.substring(numOfFirstDigit);

    int row;
    try {
      row = Integer.parseInt(changeToRow);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Row wasn't a number");
    }
    return new Coord(colNameToIndex(changeToCol), row);
  }

  /**
   * Creates an arrayList of referenced Coords.
   *
   * @param rawReference the string value of the references.
   * @return the arrayList of referenced Coords.
   */
  public static ArrayList<Coord> createReference(String rawReference) {
    Coord topLeftCord;
    Coord bottomRightCord;
    ArrayList<Coord> toReturn = new ArrayList<>();
    if (rawReference.contains(":")) {
      int indexOfColan = rawReference.indexOf(":");
      topLeftCord = createCoord(rawReference.substring(0, indexOfColan));
      bottomRightCord = createCoord(rawReference.substring(indexOfColan + 1));
      for (int i = topLeftCord.col; i <= bottomRightCord.col; i++) {
        for (int j = topLeftCord.row; j <= bottomRightCord.row; j++) {
          Coord coordToAdd = new Coord(i, j);
          toReturn.add(coordToAdd);
        }
      }
    } else {
      topLeftCord = createCoord(rawReference);
      toReturn.add(topLeftCord);
    }
    return toReturn;
  }

  @Override
  public String toString() {
    return colIndexToName(this.col) + this.row;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coord coord = (Coord) o;
    return row == coord.row
            && col == coord.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }

}
