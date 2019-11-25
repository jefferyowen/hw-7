package edu.cs3500.spreadsheets.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.cs3500.spreadsheets.model.WorkSheet;
import edu.cs3500.spreadsheets.view.EditView;
import edu.cs3500.spreadsheets.view.View;

public class ExcelController implements ActionListener, KeyListener, FocusListener {
  private WorkSheet model;
  private EditView view;

  public ExcelController(WorkSheet m, EditView v) {
    this.model = m;
    this.view = v;
    this.view.setListeners(this, this); // This controller can handle both kinds of events directly
    this.view.render();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    switch (e.getActionCommand()) {
      case "Confirm Button":

        this.view.getSelectedCell();
        System.out.println(this.view.getInputString());
        this.view.clearInputString();

        break;
      case "Cancel Button":
        System.exit(0);
        break;
    }

  }

  @Override
  public void keyTyped(KeyEvent e) {
    /**
    switch (e.getKeyChar()) {
      case 'd': //toggle color
        view.toggleColor();
        break;

    }
     **/
  }

  @Override
  public void keyPressed(KeyEvent e) {
    /**
    switch (e.getKeyCode()) {
      case KeyEvent.VK_C: //caps
        String text = model.getString();
        text = text.toUpperCase();
        view.setEchoOutput(text);
        break;

    }
     **/
  }

  @Override
  public void keyReleased(KeyEvent e) {
    /**
    switch (e.getKeyCode()) {
      case KeyEvent.VK_C: //caps
        String text = model.getString();
        view.setEchoOutput(text);
        break;
    }
     **/
  }


  @Override
  public void focusGained(FocusEvent e) {
    this.view.getSelectedCell();
  }

  @Override
  public void focusLost(FocusEvent e) {
    this.view.getSelectedCell();
  }
}