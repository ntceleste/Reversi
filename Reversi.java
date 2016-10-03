/** this class creates a playable instance of the game
  * Reversi
  * @author Nate Celeste */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Reversi extends JFrame implements ActionListener{
  
  /** stores whether or not it is white's turn / set to false if not white's turn*/
  private boolean whiteTurn = false;
  
  /** stores the JButton Array that makes up the board */
  private JButton[][] buttonArray = null;
  
  /** stores the button that was pressed */
  private JButton pressedButton = null;
  
  /** stores the pressed buttons x-coordinate */
  private int pressedButtonX = 0;
  
  /** stores the pressed buttons y-coordinate */
  private int pressedButtonY = 0;
  
  /** stores an array the remembers which buttons must be flipped */
  private boolean[][] flippedArray;
  
  /** allows the user to run Reversi
    * @param args  Input of strings, method will find two numbers and create a board */
  public static void main(String[] args){
    int[] intArgs = new int[2];
    int intArgsI = 0;
    // this loop finds all the int's in a string and adds them to the int array 
    for(int i = 0; i < args.length && intArgsI < 2; i++){
      try{
        intArgs[intArgsI] = Integer.parseInt(args[i]);
        intArgsI++;
      }
      catch(NumberFormatException e){
      }
    }
    //checks to see what constructor should be used based on the inputs
    if(intArgs[0] > 0 && intArgs[1] > 0)
      new Reversi(intArgs[0], intArgs[1]);
    else{
      if(intArgs[0] > 0)
        new Reversi(intArgs[0]);
      else{
        if(intArgs[1] > 0)
          new Reversi(intArgs[1]);
        else
          JOptionPane.showMessageDialog(new JFrame(), "You didn't specify what size board you wanted. Here is a standard 8 by 8 board.");
        new Reversi();
      }
    }
  }
  
  /** creates the standard (8x8) game board and sets the starter pieces */
  public Reversi(){
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
    createBoard(8, 8);
  }
  
  
  /** creates the Reversi game board with a size of input x input and sets the starter pieces 
    * @param  boardsize will be length and width of Reversi gameboard */
  public Reversi(int boardsize){
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
    createBoard(boardsize, boardsize);
  }
  
  /** creates a Reversi game board with a size of input1 x input2 and sets the starter pieces 
    * @param  boardHeight will be the height of the Revesi gameboard 
    * @param  boardWidth will be the width of the Reversi gameboard */
  public Reversi(int height, int width){
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
    createBoard(height, width);
  }
  
  /** creates the game board with a size of input1 x input2 and sets the starter pieces 
    * @param  boardHeight will be the height of the Revesi gameboard 
    * @param  boardWidth will be the width of the Reversi gameboard */
  public void createBoard(int boardHeight, int boardWidth){
    JPanel boardPanels = new JPanel(new GridLayout(boardWidth, boardHeight));
    this.getContentPane().add(boardPanels, "Center");
    buttonArray = new JButton[boardWidth][boardHeight];
    //runs through JButton array and creates correctly colored buttons with listners
    for(int iX = 0; iX < buttonArray.length; iX++){
      for(int iY = 0; iY < buttonArray[iX].length; iY++){
        buttonArray[iX][iY] = new JButton();
        buttonArray[iX][iY].addActionListener(this);
        buttonArray[iX][iY].setBackground(Color.GREEN);
        boardPanels.add(buttonArray[iX][iY]);
      }
    }
    buttonArray[boardWidth/2 - 1][boardHeight/2].setBackground(Color.BLACK);
    buttonArray[boardWidth/2 -1][boardHeight/2 - 1].setBackground(Color.WHITE);
    buttonArray[boardWidth/2][boardHeight/2 -1].setBackground(Color.BLACK);
    buttonArray[boardWidth/2][boardHeight/2].setBackground(Color.WHITE);
    this.setSize(500, 500);
    this.setVisible(true);
    this.setTitle("Reversi: Black's Turn");
  }
  
  /** will reverse the color of the button at the inputted grid coordinate 
    * @param  button will be the button that's color should be reversed */
  public static void reverseColor(JButton button){
    //checks the starting color of the button
    if(button.getBackground().equals(Color.WHITE))
      button.setBackground(Color.BLACK);
    else{
      if(button.getBackground().equals(Color.BLACK))
        button.setBackground(Color.WHITE);
    }
  }
  
  /** sets pressedButton 
    * @param  JButton pressedButton is the button that will be stored as the pressed button */
  public void setPressedButton(JButton pressedButton){
    this.pressedButton = pressedButton;
  }
  
  /** sets pressedButtonX 
    * @param  int pressedButtonX is the int that will store the x-coord of the pressed button */
  public void setPressedButtonX(int pressedButtonX){
    this.pressedButtonX = pressedButtonX;
  }
  
  /** sets pressedButtonY 
    * @param  int pressedButtonY is the int that will store the y-coord of the pressed button */
  public void setPressedButtonY(int pressedButtonY){
    this.pressedButtonY = pressedButtonY;
  }
  
  /** gets pressedButton 
    * @return the button that been stored as the pressedButton */
  public JButton getPressedButton(){
    return pressedButton;
  }
  
  /** gets pressedButtonX 
    * @return an int that represents the x-coord of pressedButton */
  public int getPressedButtonX(){
    return pressedButtonX;
  }
  
  /** gets pressedButtonY 
    * @return an int that represents the y-coord of pressedButton */
  public int getPressedButtonY(){
    return pressedButtonY;
  }
  
  /** gets the whiteTurn value 
    * @return the value of whiteTurn */
  public boolean getWhiteTurn(){
    return whiteTurn;
  }
  
  /** sets the value of whiteTurn
    * @param  whiteTurn true if the players turn */
  public void setWhiteTurn(boolean whiteTurn){
    this.whiteTurn = whiteTurn;
  }
  
  /** tests if a button is within the bounds of the array
    * @param  buttonX the x posistion of the button
    * @param  ButtonY the y posistion of the button 
    * @return true if the button exists with the bounds of the array */
  public boolean testBounds(int buttonX, int buttonY){
    if(buttonY < 0) //checks upper bound
      return false;
    if(buttonY > buttonArray.length - 1) //checks lower bound
      return false;
    if(buttonX < 0) //checks left bound
      return false;
    if(buttonX > buttonArray[0].length - 1) //checks right bound
      return false;
    return true;
  }
  
  /** tests if button at x,y is not green/input color and exists in array
    * @param  buttonX the x-position of the button to check
    * @param  buttonY the y-posistion of the button to check
    * @param  color the color to check against
    * @return  true if button at x,y is not green/input color and exists in array */
  public boolean testXY(int buttonX, int buttonY, Color color){
    if(testBounds(buttonX, buttonY) == false) //checks to see if the the button exists in the array
      return false;
    if(buttonArray[buttonY][buttonX].getBackground() == color) //checks to see if the button has already been given a color
      return false;
    if(buttonArray[buttonY][buttonX].getBackground() == Color.GREEN) //checks to see if the button is the color of the background
      return false;
    return true;
  }
  
  
  /** checks every square to see if a legal move exists
    * @return true if a legal move exists for the current turn */
  public boolean legalMoveExists(){
    //runs through every button to check for a legal move for the current turn
    for(int iX = 0; iX < buttonArray.length; iX++){
      for(int iY = 0; iY < buttonArray[iX].length; iY++){
        if(isLegalMove(iX, iY))
          return true;
      }
    }
    return false;
  }
  
  /** checks a specific square to see if a legal move exists from that space
    * @param  buttonX the x-position of the pressed button
    * @param  buttonY the y-position of the pressed button
    * @return true if a legal move exists from the pressed square */
  public boolean isLegalMove(int buttonX, int buttonY){
    flippedArray = new boolean[buttonArray.length][buttonArray[0].length];
    boolean legalMove = false;
    Color turnColor = Color.BLACK;
    int[][] directions = { { -1, 0 }, {-1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
    //checks to make sure the button pressed has not already been pressed
    if(buttonArray[buttonY][buttonX].getBackground() != Color.GREEN)
      return false;
    //checks the turn color
    if(getWhiteTurn())
      turnColor = Color.WHITE;
    //loops through all the flip directions
    for(int i = 0; i < directions.length; i++){
      boolean legalDirection = false;
      int[] direction = directions[i];
      int iX = buttonX + direction[1];
      int iY = buttonY + direction[0];
      //checks for legal moves in a pre-defined direction
      while(testXY(iX, iY, turnColor)){
        legalDirection = true;
        iX += direction[1];
        iY += direction[0];
      }
      // checks to make sure the button is not green and exists on the board
      if(testBounds(iX, iY) && buttonArray[iY][iX].getBackground() == Color.GREEN)
        legalDirection = false;
      // tests just the bounds
      if(!testBounds(iX, iY))
        legalDirection = false;
      //checks if a legal direction has been found
      if(legalDirection){
        legalMove = true;
        iX = buttonX + direction[1];
        iY = buttonY + direction[0];
        //loops through legal directions and rememebrs what buttons must be flipped
        while(testXY(iX, iY, turnColor)){
          flippedArray[iY][iX] = true;
          iX += direction[1];
          iY += direction[0];
        }
      }
    }
    return legalMove;
  }
  
  /** swaps the turns and checks to see if the next turn has any valid moves */
  public void switchTurns(){
    //swaps turns and checks to see if the next player has valid moves
    //if the player doesn't have valid moves, displays appropiate message or ends the game
    if(getWhiteTurn()){
      pressedButton.setBackground(Color.WHITE);
      setWhiteTurn(false);
      this.setTitle("Reversi: Black's Turn");
      if(legalMoveExists() == false){
        setWhiteTurn(true);
        this.setTitle("Reversi: White's Turn");
        if(legalMoveExists() == false){
          endGame();
          return;
        }
        JOptionPane.showMessageDialog(new JFrame(), "Black has no valid moves. It is now Whites's turn");
      }
    }
    else{
      pressedButton.setBackground(Color.BLACK);
      setWhiteTurn(true);
      this.setTitle("Reversi: White's Turn");
      if(legalMoveExists() == false){
        setWhiteTurn(false);
        this.setTitle("Reversi: Black's Turn");
        if(legalMoveExists() == false){
          endGame();
          return;
        }
        JOptionPane.showMessageDialog(new JFrame(), "White has no valid moves. It is now Black's turn");
      }
    }
  }
  
  /** counts the final tally of squares and reports who won */
  public void endGame(){
    int whiteCount = 0;
    int blackCount = 0;
    //runs through every button and records it's color
    for(int iX = 0; iX < buttonArray.length; iX++){
      for(int iY = 0; iY < buttonArray[iX].length; iY++){
        if(buttonArray[iY][iX].getBackground() == Color.WHITE)
          whiteCount++;
        else{
          if(buttonArray[iY][iX].getBackground() == Color.BLACK)
            blackCount++;
        }
      }
    }
    //checks to see who won and presents an appropiate message
    if(whiteCount > blackCount){
      JOptionPane.showMessageDialog(new JFrame(), "The game is over. White won with a final score of " + whiteCount + " to " + blackCount);
      this.setTitle("Reversi: White has won!");
    }
    else{
      if(blackCount > whiteCount){
        JOptionPane.showMessageDialog(new JFrame(), "The game is over. Black won with a final score of " + blackCount + " to " + whiteCount);
        this.setTitle("Reversi: Black has won!");
      }
      else{
        JOptionPane.showMessageDialog(new JFrame(), "The game is over. Black and White have tied with a final score of " + blackCount + " to " + whiteCount);
        this.setTitle("Reversi: It's a tie!");
      }
    }
  }
  
  /* when a button is pressed it checks to see if a legal move, if legal the correct sqaures are flipped
   * @param  e the ActionEvent that triggered this method */
  @Override
  public void actionPerformed(ActionEvent e){
    setPressedButton((JButton) e.getSource());
    //finds the pressed buttons x and y positions
    for(int iX = 0; iX < buttonArray.length; iX++){
      for(int iY = 0; iY < buttonArray[iX].length; iY++){
        if (buttonArray[iX][iY].equals((JButton) e.getSource())){
          setPressedButtonX(iY);
          setPressedButtonY(iX);
        }
      }
    }
    //checks if pressed button was a legal move, if it is it flips appropiate tiles
    if(isLegalMove(pressedButtonX, pressedButtonY)){
      for(int iX = 0; iX < flippedArray.length; iX++){
        for(int iY = 0; iY < flippedArray[iX].length; iY++){
          if(flippedArray[iX][iY]){
            reverseColor(buttonArray[iX][iY]);
          }
        }
      }
      switchTurns();
    }
  }
  
  /** overides the toString() method so that a ton of info isn't printed every time a game is started */
  @Override
  public String toString(){
    return "";
  }
}