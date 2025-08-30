import java.io.File;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class uses the Processing library to create the window, graphical designs, and calculator
 * functions.
 */
public class CalculatorWindow extends PApplet {

  // This the window that will be produced by this program
  PImage window;

  // This will be the digit that is outputted to the user
  String WindowDigit = "0";

  // This will indicate whether the mouse is clicked or not
  boolean mousePressed = false;

  // This will indicate whether the mouse is on a square or not
  boolean onSquare = false;

  // This contains all number buttons
  static Button[] numbers = new Button[10];

  // This contains all symbols
  static Button[] symbols = new Button[6];

  // Create a clear button
  Button clearButton = new Button(350, 135, 125, 50, "CLEAR", this);

  boolean enterDecimal = true;

  public void createButtons() {
    int numButtons = 12;
    int positionX = 25;
    int positionY = 200;
    int widthSize = 75;
    int heightSize = 75;
    int j = 0;
    int buttonNumber = 7;

    // Create the squares for the digit/decimal point/equals buttons
    for (int i = 0; i < numButtons; ++i) {
      Button calculatorButton = new Button(positionX, positionY, widthSize, heightSize,
          String.valueOf(buttonNumber), this);
      calculatorButton.draw(this);
      positionX += 100;
      if (i == 2 || i == 5 || i == 8) {
        positionX -= 300;
        positionY += 100;
      }
      if (i <= 9) {
        numbers[i] = calculatorButton;
      } else {
        symbols[j] = calculatorButton;
        ++j;
      }
      ++buttonNumber;
      if (i == 2 || i == 5) {
        buttonNumber -= 6;
      }
      if (i == 8) {
        buttonNumber -= 4;
      }
    }

    // Create the squares for the mathematical operators
    numButtons = 4;
    positionX += 50;
    positionY -= 300;

    for (int i = 0; i < numButtons; ++i) {
      Button calculatorButton = new Button(positionX, positionY, widthSize, heightSize,
          String.valueOf(i), this);
      calculatorButton.draw(this);
      positionY += 100;
      symbols[j] = calculatorButton;
      ++j;
    }

    // Draw clearButton
    clearButton.draw(this);
  }

  public void displaySymbols() {
    int currentDigit = 7;
    int digitPositionX = 50;
    int digitPositionY = 250;

    // Put all the numbers within the squares
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

    // Add the decimal point symbol and equals symbol in the last two squares
    text('.', digitPositionX, digitPositionY);
    symbols[0].changeSymbol(".");
    digitPositionX += 100;
    text('=', digitPositionX, digitPositionY);
    symbols[1].changeSymbol("=");
    digitPositionY -= 300;
    digitPositionX += 150;
    text('÷', digitPositionX, digitPositionY);
    symbols[2].changeSymbol("÷");
    digitPositionY += 100;
    text('×', digitPositionX, digitPositionY);
    symbols[3].changeSymbol("×");
    digitPositionY += 100;
    text('-', digitPositionX, digitPositionY);
    symbols[4].changeSymbol("-");
    digitPositionY += 100;
    text('+', digitPositionX, digitPositionY);
    symbols[5].changeSymbol("+");

    fill(255, 0, 0);
    text("CLEAR", 357, 175);
  }

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

    float rightEdge = 25 + 450 - 10;
    float baselineY = 75 + 25;

    textAlign(RIGHT, CENTER);
    text(WindowDigit, rightEdge, baselineY);

    textAlign(LEFT, BASELINE);

    createButtons();
    displaySymbols();
  }

  public boolean includesOperators () {
    return (WindowDigit.contains("+") || WindowDigit.contains("-") || WindowDigit.contains("÷")
        || WindowDigit.contains("×"));
  }

  public void mousePressed() {
    // Handle the case where numbers get pressed
    for (int i = 0; i < numbers.length; ++i) {
      if (numbers[i].buttonPressed()) {
        if (WindowDigit.equals("0")) {
          WindowDigit = numbers[i].getSymbol();
        } else {
          WindowDigit += numbers[i].getSymbol();
        }
      }
    }

    // Handle the case where the CLEAR button gets pressed
    if (clearButton.buttonPressed()) {
      WindowDigit = "0";
      enterDecimal = true;
    }

    Button decimalButton = symbols[0];
    Button equalsButton = symbols[1];
    Button divisionButton = symbols[2];
    Button multiplicationButton = symbols[3];
    Button subtractionButton = symbols[4];
    Button additionButton = symbols[5];

    // Case 1: Decimal Button is pressed
    if (decimalButton.buttonPressed()) {
      if (enterDecimal) {
        WindowDigit += ".";
        enterDecimal = false;
      }
    }

    // Case 2: Division Button is pressed
    if (divisionButton.buttonPressed()) {
      if (!includesOperators()) {
        WindowDigit += " ÷ ";
        enterDecimal = true;
      }
    }

    // Case 3: Multiplication Button is pressed
    if (multiplicationButton.buttonPressed()) {
      if (!includesOperators()) {
        WindowDigit += " × ";
        enterDecimal = true;
      }
    }

    // Case 4: Subtraction Button is pressed
    if (subtractionButton.buttonPressed()) {
      if (!includesOperators()) {
        WindowDigit += " - ";
        enterDecimal = true;
      }
    }

    // Case 5: Addition Button is pressed
    if (additionButton.buttonPressed()) {
      if (!includesOperators()) {
        WindowDigit += " + ";
        enterDecimal = true;
      }
    }

    // Case 6: Equals Button is pressed
    if (equalsButton.buttonPressed()) {
      if (includesOperators()) {
        String remainingString = WindowDigit.substring(WindowDigit.indexOf(" "));
        String firstOperand = WindowDigit.substring(0, WindowDigit.indexOf(" "));
        String secondOperand = "";

        boolean containsNumber = false;

        for (int i = 0; i < remainingString.length(); ++i) {
          if (Character.isDigit(remainingString.charAt(i))) {
            containsNumber = true;
            secondOperand = remainingString.substring(i);
          }
        }

        // Addition
        if (WindowDigit.contains("+") && containsNumber) {
          float numberOne = Float.parseFloat(firstOperand);
          float numberTwo = Float.parseFloat(secondOperand);
          float sum = numberOne + numberTwo;
          WindowDigit = sum + "";
        }

        // Subtraction
        if (WindowDigit.contains("-") && containsNumber) {
          float numberOne = Float.parseFloat(firstOperand);
          float numberTwo = Float.parseFloat(secondOperand);
          float subtraction = numberOne - numberTwo;
          WindowDigit = subtraction + "";
        }

        // Multiplication
        if (WindowDigit.contains("×") && containsNumber) {
          float numberOne = Float.parseFloat(firstOperand);
          float numberTwo = Float.parseFloat(secondOperand);
          float product = numberOne * numberTwo;
          WindowDigit = product + "";
        }

        // Division
        if (WindowDigit.contains("÷") && containsNumber) {
          float numberOne = Float.parseFloat(firstOperand);
          float numberTwo = Float.parseFloat(secondOperand);
          float quotient = numberOne / numberTwo;
          if (numberTwo == 0) {
            WindowDigit = "CANNOT DIVIDE BY 0";
          } else {
            WindowDigit = quotient + "";
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    PApplet.main("CalculatorWindow");
  }
}
