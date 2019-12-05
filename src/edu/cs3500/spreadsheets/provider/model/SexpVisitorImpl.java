package edu.cs3500.spreadsheets.provider.model;

import java.util.ArrayList;
import java.util.List;


import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;


/**
 * Implementation of a SexpVisitor.
 */
public class SexpVisitorImpl implements SexpVisitor {

  @Override
  public Object visitBoolean(boolean b) {
    return new BooleanValue(b);
  }

  @Override
  public Object visitNumber(double d) {
    return new DoubleValue(d);
  }

  @Override
  public Object visitSymbol(java.lang.String s) {
    boolean isAllLetters = true;
    boolean containsAColon = false;

    for (int i = 0; i != s.length(); i++) {
      if (Character.isDigit(s.charAt(i))) {
        isAllLetters = false;
      }
      if (s.charAt(i) == ':') {
        containsAColon = true;
      }
    }

    //If the string is all alphabetical, it's a command. Special case for < handled
    if (s.equals("<") || (isAllLetters && !containsAColon)) {
      switch (s) {
        case "SUM":
          return new Sum();
        case "PRODUCT":
          return new Product();
        case "<":
          return new LessThan();
        case "APPEND":
          return new Append();
        default:
          return new StringValue(s);
      }
      //If the string has a colon in it, then it is two coords
    } else if (containsAColon && !isAllLetters) {
      java.lang.String[] coords = s.split(":");
      Coord coord1 = this.handleCoord(coords[0]);
      Coord coord2 = this.handleCoord(coords[1]);
      this.checkCoords(coord1, coord2);

      return new Reference(coord1, coord2);
    } else if (!containsAColon && !isAllLetters) {

      Coord coord = this.handleCoord(s);
      System.out.print(coord);
      return new Reference(coord, coord);
    } else {
      throw new IllegalArgumentException("Malformed Symbol; make sure your Function calls and" +
              "your coordinates are in the right format.");
    }

  }

  /**
   * Checks if two coords have the right relationship.
   */
  private void checkCoords(Coord c1, Coord c2) {
    if (c1.row > c2.row && c1.col >= c2.col) {
      throw new IllegalArgumentException("The first coordinate cannot be smaller than the second.");
    }
  }

  /**
   * Handles individual coordinates.
   *
   * @return a Coord object.
   */
  private Coord handleCoord(java.lang.String s) {

    //Here is where we handle coordinates that are passed to visit symbol
    boolean isStillAlpha = true;
    StringBuilder colReference = new StringBuilder();
    StringBuilder rowReference = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      if (Character.isLetter(s.charAt(i))) {
        colReference.append(s.charAt(i));
      } else if (Character.isDigit(s.charAt(i)) && isStillAlpha) {
        isStillAlpha = false;
        rowReference.append(s.charAt(i));
      } else if (Character.isLetter(s.charAt(i)) && (!isStillAlpha)) {
        throw new IllegalArgumentException("This is a malformed Symbol SExp");
      } else {
        throw new IllegalArgumentException("This is a malformed Symbol SExp; "
                + "You may be using something other than a letter or digit in your cell's coords.");
      }
    }

    return new Coord(Coord.colNameToIndex(colReference.toString()),
            Integer.valueOf(rowReference.toString()));
  }

  @Override
  public Object visitString(java.lang.String s) {
    return new StringValue(s);
  }

  @Override
  public Object visitSList(List l) {
    List a = new ArrayList();
    for (Object o : l) {
      a.add(((Sexp) o).accept(this));
    }
    return a;
  }
}
