package edu.cs3500.spreadsheets.provider.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.provider.model.Coord;
import edu.cs3500.spreadsheets.provider.controller.SpreadSheetControllerInterface;

/**
 * Represents a editable panel in the edit view that the user can interact with.
 */
public class JPanelEditSpreadsheet extends JPanelSpreadsheet {
  private SpreadSheetControllerInterface controller;
  private CellEditTextField textbox;

  /**
   * Creates a editable JPanel with user interaction.
   */
  JPanelEditSpreadsheet(Map<Coord, Cell> sheet, SpreadSheetControllerInterface controller) {
    super(sheet);
    this.controller = controller;
    this.textbox = new CellEditTextField(this.controller);
  }

  /**
   * Returns the preferred size as a Dimension.
   */
  public Dimension getPreferredSize() {
    return new Dimension(250, 200);
  }

  @Override
  public void paintComponent(Graphics g) {
    {
      for (int x = 1; x <= 100; x++) {
        for (int y = 1; y <= 100; y++) {
          Coord buttonCoord = new Coord(x, y);
          JButton button = new JButton();
          button.setBounds(x * 80 - 40, y * 40 + 40, 80, 40);
          button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
              controller.setSelectedCell(buttonCoord);
            }
          });
          this.add(button);
          if (sheet.containsKey(buttonCoord)) {
            String str = String.valueOf(sheet.get(buttonCoord).getFormula()
                    .getEvaluatedValue().getRawValue());
            button.setText(str);

            for (int r = 1; r < 1500; r++) {
              new Coord(r, 1);
              g.drawString(Coord.colIndexToName(r), r * 80, 65);

            }

            for (int u = 1; u < 30; u++) {
              g.drawString(String.valueOf(u), 20, u * 40 + 65);
            }
          }
        }
      }

      raisedetched.paintBorder(this, g, 0, 40, 1500, 40);

    }
    textbox.setBounds(0, 0, 9000, 40);
    this.add(textbox);
    textbox.setEditable(true);

    textbox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String text = textbox.getText();


        controller.callEditCell(controller.getSelectedCell(), text);
      }
    });

    if (controller.getSelectedCell() != null) {
      textbox.setText("=" + controller.getMap()
              .get(controller.getSelectedCell()).getValue().getRawValue().toString());
    }

    this.setVisible(true);
  }

}
