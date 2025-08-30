import java.io.File;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.Arrays;

public class Button extends PApplet {

  // Create Fields
  int positionX;
  int positionY;
  int width;
  int height;
  String symbol;
  PApplet app;

  // Create Constructor
  Button(int x, int y, int width, int height, String symbol, PApplet app) {
    this.positionX = x;
    this.positionY = y;
    this.width = width;
    this.height = height;
    this.symbol = symbol;
    this.app = app;
  }

  // Create draw() method
  void draw(PApplet app) {
    if (app.mouseX >= positionX && app.mouseX <= positionX + width &&
        app.mouseY >= positionY && app.mouseY <= positionY + height) {
      app.fill(200);
    } else {
      app.fill(255);
    }
    app.rect(positionX, positionY, width, height);
  }

  public String getSymbol() {
    return symbol;
  }

  void changeSymbol(String symbol) {
    this.symbol = symbol;
  }

  // Create method that tell whether the mouse is over the button or not
  boolean mouseIsOver() {
    if (app.mouseX >= positionX && app.mouseX <= positionX + width &&
        app.mouseY >= positionY && app.mouseY <= positionY + height) {
      return true;
    } else {
      return false;
    }
  }

  boolean buttonPressed() {
    return mouseIsOver() && app.mousePressed;
  }
}
