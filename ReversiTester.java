import org.junit.*;
import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** this class tests the methods in Reversi 
  * @author Nate Celeste */
public class ReversiTester{
  
  /** tests setPressedButton and getPressedButton */
  @Test
  public void testPressedButton(){
    Reversi test = new Reversi();
    test.setVisible(false);
    JButton test1 = new JButton();
    test.setPressedButton(test1);
    assertEquals( test1, test.getPressedButton());
  }
  
  /** tests the setPressedButtonX and getPressedButtonX */
  @Test
  public void testPressedButtonX(){
    Reversi test = new Reversi();
    test.setVisible(false);
    int test1 = 1;
    test.setPressedButtonX(test1);
    assertEquals(test1, test.getPressedButtonX());
  }
  
  /** tests the setPressedButtonY and getPressedButtonY */
  @Test
  public void testPressedButtonY(){
    Reversi test = new Reversi();
    test.setVisible(false);
    int test1 = 1;
    test.setPressedButtonY(test1);
    assertEquals(test1, test.getPressedButtonY());
  }
  
  /** tests the setWhiteTurn and getWhiteTurn */
  @Test
  public void testWhiteTurn(){
    Reversi test = new Reversi();
    test.setVisible(false);
    boolean test1 = true;
    test.setWhiteTurn(test1);
    assertEquals(test1, test.getWhiteTurn());
  }
  
  /** tests isLegalMove */
  @Test
  public void testIsLegalMove(){
    Reversi test = new Reversi();
    test.setVisible(false);
    assertTrue(test.isLegalMove(3, 2)); // a legal starting move
    assertFalse(test.isLegalMove(0,0)); // an illegal starting move
  }
  
  /** tests legalMoveExists */
  @Test
  public void testLegalMoveExists(){
    Reversi test = new Reversi();
    test.setVisible(false);
    assertTrue(test.legalMoveExists());
  }
  
  /** tests reverseColor */
  @Test
  public void testReverseColor(){
    Reversi test = new Reversi();
    test.setVisible(false);
    JButton test1 = new JButton();
    test1.setBackground(Color.WHITE);
    JButton test2 = new JButton();
    test2.setBackground(Color.BLACK);
    test.reverseColor(test1);
    test.reverseColor(test2);
    assertEquals(Color.BLACK, test1.getBackground());
    assertEquals(Color.WHITE, test2.getBackground());
  }
  
  /** tests switchTurns */
  @Test
  public void testSwitchTurns(){
    Reversi test = new Reversi();
    test.setVisible(false);
    test.setPressedButton(new JButton());
    test.switchTurns();
    assertTrue(test.getWhiteTurn());
    test.switchTurns();
    assertFalse(test.getWhiteTurn());
  }
  
  /** tests testBounds */
  @Test
  public void testTestBounds(){
    Reversi test = new Reversi();
    test.setVisible(false);
    assertTrue(test.testBounds(4, 4));
    assertFalse(test.testBounds(9,9));
  }
  
  /** test testXY */
  @Test
  public void testTestXY(){
    Reversi test = new Reversi();
    test.setVisible(false);
    assertTrue(test.testXY(4, 4, Color.BLACK));
    assertFalse(test.testXY(4, 4, Color.WHITE));
    assertFalse(test.testXY(9, 9, Color.GREEN));
  }
}