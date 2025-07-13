import java.io.File;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PApplet;

/**
 * This class uses the Processing library to create the window, graphical designs, and calculator
 * functions.
 */
public class CalculatorWindow extends PApplet {

  // This the window that will be produced by this program
  PImage window;

  // This will be the digit that is outputted to the user
  int WindowDigit = 0;

  public void settings() {
    // Set the window size to be 500 pixels wide and 650 pixels deep
    size(500, 650);
  }

  public void draw() {
    // Set the background's color to white
    background(255);
    // Set the font style
    PFont font = createFont("Consolas", 16, true);
    textFont(font);
    // Make title black, set the font size, and then insert the title at the top of the screen
    String title = "Newcomer Calculator";
    fill(0);
    textSize(30);
    text(title, 90, 50);

    // Insert the white output rectangle at the top of the window
    fill(255);
    strokeWeight(3);
    rect(25, 75, 450, 50);

    // Display the output digit that is in the window
    fill(0);
    textSize(40);
    text(WindowDigit, 445, 112);

    // Create the digit buttons
    int numButtons = 11;
    int currentDigit = 7;

    int positionX = 25;
    int positionY = 200;
    int widthSize = 75;
    int heightSize = 75;

    for (int i = 0; i < numButtons; ++i) {
      fill(255);
      rect(positionX, positionY, widthSize, heightSize);
      positionX += 100;
      if (i == 2 || i == 5 || i == 8) {
        positionX -= 300;
        positionY += 100;
      }
    }

    int digitPositionX = 50;
    int digitPositionY = 250;
    for (int i = 0; i < 10; ++i) {
      if (i == 9) {
        currentDigit = 0;
      }
      fill(0);
      text(currentDigit, digitPositionX, digitPositionY);
      digitPositionX += 100;
      ++currentDigit;
      if (currentDigit == 10 || currentDigit == 7) {
        currentDigit -= 6;
      }
      if (i == 2 || i == 5 || i == 8) {
        digitPositionX -= 300;
        digitPositionY += 100;
      }
    }
  }

  public void setup() {

  }
}
