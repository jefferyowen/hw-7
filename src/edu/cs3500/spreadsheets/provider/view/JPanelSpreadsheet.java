package edu.cs3500.spreadsheets.provider.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.provider.model.Coord;


/**
 * Implements JPanel for the spreadsheet.
 */
public class JPanelSpreadsheet extends JPanel {

  Map<Coord, Cell> sheet;
  Border raisedetched;


  JPanelSpreadsheet(Map<Coord, Cell> sheet) {

    this.sheet = sheet;
    GridBagLayout grid = new GridBagLayout();
    this.setLayout(grid);
    raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
    this.setBorder(raisedetched);


    this.setVisible(true);
  }

  /**
   * Returns the preferred size as a Dimension.
   */
  public Dimension getPreferredSize() {
    return new Dimension(250, 200);
  }

  /**
   * Adds a component to paint.
   */
  public void paintComponent(Graphics g) {

    {
      for (int x = 0; x <= 1500; x += 80) {
        for (int y = 40; y <= 1000; y += 40) {
          g.drawRect(x, y, 80, 40);

          for (int r = 1; r < 1500; r += 80) {
            new Coord(r, 1);
            g.drawString(Coord.colIndexToName(1), r + 25, 25);

          }
          for (int u = 1; u < 3000; u++) {
            g.drawString(String.valueOf(u), 0, 80 + u / 40 * 20);
          }


          if (sheet.containsKey(new Coord((x / 80) + 1, (y / 80) + 1))) {
            String str = String.valueOf(sheet.get(new Coord(
                    x / 80 + 1, y / 80 + 1)).getFormula()
                    .getEvaluatedValue().getRawValue());
            g.drawString(str, x + 25, y + 25);
          }
        }
      }

      raisedetched.paintBorder(this, g, 0, 0, 1500, 40);
      this.setVisible(true);
    }


  }


}

