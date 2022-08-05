import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.MockView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test methods for rendering the board and messages.
 */
public class MarbleSolitaireTextViewTest {
  private EnglishSolitaireModel game1;
  private EnglishSolitaireModel game10;
  private EnglishSolitaireModel game11;
  private EnglishSolitaireModel game12;
  private EnglishSolitaireModel game13;
  private EnglishSolitaireModel game14;
  private EnglishSolitaireModel game15;
  private EnglishSolitaireModel game2;
  private EnglishSolitaireModel game3;
  private EnglishSolitaireModel game4;
  private MarbleSolitaireView viewGame1;
  private MarbleSolitaireView viewGame2;
  private MarbleSolitaireView viewGame3;
  private MarbleSolitaireView viewGame4;

  @Before
  public void setUp() {
    game1 = new EnglishSolitaireModel();
    game10 = new EnglishSolitaireModel();
    game11 = new EnglishSolitaireModel();
    game12 = new EnglishSolitaireModel();
    game13 = new EnglishSolitaireModel();
    game14 = new EnglishSolitaireModel();
    game15 = new EnglishSolitaireModel();
    game2 = new EnglishSolitaireModel(3, 1);
    game3 = new EnglishSolitaireModel(5);
    game4 = new EnglishSolitaireModel(3, 3, 5);

    viewGame1 = new MarbleSolitaireTextView(game1);
    viewGame2 = new MarbleSolitaireTextView(game2);
    viewGame3 = new MarbleSolitaireTextView(game3);
    viewGame4 = new MarbleSolitaireTextView(game4);
  }

  @Test
  public void testConstructor() {
    try {
      EnglishSolitaireModel game5 = null;
      MarbleSolitaireView viewGame5 = new MarbleSolitaireTextView(game5);
      fail("Provided model is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }
  }

  @Test
  public void testToString() {
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", viewGame1.toString());
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O _ O O O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", viewGame2.toString());
    assertEquals(
            "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O _ O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O", viewGame3.toString());
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O _ O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", viewGame4.toString());
    game1.move(3, 1, 3, 3);
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O _ _ O O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", viewGame1.toString());

    game2.move(3, 3, 3, 1);
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O _ _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", viewGame2.toString());
    game3.move(4, 6, 6, 6);
    assertEquals(
            "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "O O O O O O _ O O O O O O\n"
                    + "O O O O O O _ O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O", viewGame3.toString());
    game4.move(3, 3, 3, 5);
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ _ O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", viewGame4.toString());
  }

  @Test
  public void testSecondConstructor() {
    try {
      Appendable appendable = null;
      MarbleSolitaireView viewGame6 = new MarbleSolitaireTextView(game1, appendable);
      fail("Provided model or Appendable object is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model or Appendable object is null.", e.getMessage());
    }

    try {
      EnglishSolitaireModel game7 = null;
      Appendable appendable1 = null;
      MarbleSolitaireView viewGame8 = new MarbleSolitaireTextView(game7, appendable1);
      fail("Provided model or Appendable object is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model or Appendable object is null.", e.getMessage());
    }

    try {
      EnglishSolitaireModel game8 = null;
      Appendable appendable2 = new StringBuilder();
      MarbleSolitaireView viewGame9 = new MarbleSolitaireTextView(game8, appendable2);
      fail("Provided model or Appendable object is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model or Appendable object is null.", e.getMessage());
    }
  }

  @Test
  public void testRenderBoard() {
    Appendable appendable = new StringBuilder();
    MarbleSolitaireView aView1 = new MarbleSolitaireTextView(game1, appendable);
    try {
      aView1.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    String[] result = appendable.toString().split("\n");
    assertEquals("    O O O", result[0]);
    assertEquals("    O O O", result[1]);
    assertEquals("O O O O O O O", result[2]);
    assertEquals("O O O _ O O O", result[3]);
    assertEquals("O O O O O O O", result[4]);
    assertEquals("    O O O", result[5]);
    assertEquals("    O O O", result[6]);


    Reader input = new StringReader("4 2 4 4 6 3 4 3 q");
    Appendable appendable1 = new StringBuilder();
    MarbleSolitaireView aView2 = new MarbleSolitaireTextView(game1, appendable1);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(game1, aView2, input);
    controller.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 30\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 30\n", appendable1.toString());

    Reader input1 = new StringReader("q");
    Appendable appendable2 = new StringBuilder();
    MarbleSolitaireView aView3 = new MarbleSolitaireTextView(game2, appendable2);
    MarbleSolitaireController controller1 =
            new MarbleSolitaireControllerImpl(game2, aView3, input1);
    controller1.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            +  "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", appendable2.toString());

    Reader input2 = new StringReader("4 q");
    Appendable appendable3 = new StringBuilder();
    MarbleSolitaireView aView4 = new MarbleSolitaireTextView(game3, appendable3);
    MarbleSolitaireController controller2 =
            new MarbleSolitaireControllerImpl(game3, aView4, input2);
    controller2.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", appendable2.toString());

    Reader input3 = new StringReader("4 2 q 4");
    Appendable appendable4 = new StringBuilder();
    MarbleSolitaireView aView5 = new MarbleSolitaireTextView(game4, appendable4);
    MarbleSolitaireController controller3 =
            new MarbleSolitaireControllerImpl(game4, aView5, input3);
    controller3.playGame();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            +  "O O O O O O O\n"
            +  "    O O O\n"
            +  "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            +  "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n", appendable2.toString());

  }

  @Test
  public void testRenderMessage() {
    Appendable appendable = new StringBuilder();
    MarbleSolitaireView aView1 = new MarbleSolitaireTextView(game1, appendable);
    try {
      aView1.renderMessage("Render this.\n");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }

    String[] result = appendable.toString().split("\n");
    assertEquals("Render this.", result[0]);

    try {
      aView1.renderMessage("Now render this.\n");
      aView1.renderMessage("Oh and this too.\n");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }

    String[] result2 = appendable.toString().split("\n");
    assertEquals("Render this.", result2[0]);
    assertEquals("Now render this.", result2[1]);
    assertEquals("Oh and this too.", result2[2]);


    Reader input = new StringReader("1 1 1 2 q");
    Appendable appendable1 = new StringBuilder();
    MarbleSolitaireView aView2 = new MarbleSolitaireTextView(game1, appendable1);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(game1, aView2, input);
    controller.playGame();
    assertTrue(appendable1.toString().contains("Invalid move. Play again. Move must follow the"
            + " rules of the game and be on the board.\n"));

    Reader input1 = new StringReader("-4 2 4 4 q");
    Appendable appendable2 = new StringBuilder();
    MarbleSolitaireView aView3 = new MarbleSolitaireTextView(game10, appendable2);
    MarbleSolitaireController controller1 =
            new MarbleSolitaireControllerImpl(game10, aView3, input1);
    controller1.playGame();
    assertTrue(appendable1.toString().contains("Invalid move. Play again. Move must follow the"
            + " rules of the game and be on the board.\n"));

    Reader input2 = new StringReader("2 5 4 4 q");
    Appendable appendable3 = new StringBuilder();
    MarbleSolitaireView aView4 = new MarbleSolitaireTextView(game11, appendable3);
    MarbleSolitaireController controller2 =
            new MarbleSolitaireControllerImpl(game11, aView4, input2);
    controller2.playGame();
    assertTrue(appendable1.toString().contains("Invalid move. Play again. Move must follow the"
            + " rules of the game and be on the board.\n"));

    Reader input3 = new StringReader("4 4 4 6 q");
    Appendable appendable4 = new StringBuilder();
    MarbleSolitaireView aView5 = new MarbleSolitaireTextView(game12, appendable4);
    MarbleSolitaireController controller3 =
            new MarbleSolitaireControllerImpl(game12, aView5, input3);
    controller3.playGame();
    assertTrue(appendable1.toString().contains("Invalid move. Play again. Move must follow the"
            + " rules of the game and be on the board.\n"));

    Reader input4 = new StringReader("3 3 3 5 q");
    Appendable appendable5 = new StringBuilder();
    MarbleSolitaireView aView6 = new MarbleSolitaireTextView(game13, appendable5);
    MarbleSolitaireController controller4 =
            new MarbleSolitaireControllerImpl(game13, aView6, input4);
    controller4.playGame();
    assertTrue(appendable1.toString().contains("Invalid move. Play again. Move must follow the"
            + " rules of the game and be on the board.\n"));

    Reader input5 = new StringReader("3 y 3 5 q");
    Appendable appendable6 = new StringBuilder();
    MarbleSolitaireView aView7 = new MarbleSolitaireTextView(game14, appendable6);
    MarbleSolitaireController controller5 =
            new MarbleSolitaireControllerImpl(game14, aView7, input5);
    controller5.playGame();
    String[] result3 = appendable6.toString().split("\n");
    assertEquals("Score: 32", result3[7]);
    assertEquals("An unexpected value was encountered.", result3[8]);
    assertEquals("Game quit!", result3[9]);
    assertEquals("State of game when quit:", result3[10]);

    Reader input6 = new StringReader("x 3 y 3 -1 ? 5 2 q");
    Appendable appendable7 = new StringBuilder();
    MarbleSolitaireView aView8 = new MarbleSolitaireTextView(game15, appendable7);
    MarbleSolitaireController controller6 =
            new MarbleSolitaireControllerImpl(game15, aView8, input6);
    controller6.playGame();
    String[] result4 = appendable7.toString().split("\n");
    assertEquals("An unexpected value was encountered.", result4[8]);
    assertEquals("An unexpected value was encountered.", result4[9]);
    assertEquals("An unexpected value was encountered.", result4[10]);
    assertEquals("Game quit!", result4[20]);
    assertEquals("State of game when quit:", result4[21]);
  }

  @Test
  public void testIOException() {
    StringBuilder log10 = new StringBuilder();
    MarbleSolitaireView mockView10 = new MockView(log10);
    try {
      mockView10.renderBoard();
      fail("from renderBoard()");
    } catch (IOException e) {
      assertEquals("from renderBoard()", e.getMessage());
    }

    StringBuilder log11 = new StringBuilder();
    MarbleSolitaireView mockView11 = new MockView(log11);
    try {
      mockView11.renderMessage("random message");
      fail("from renderMessage()");
    } catch (IOException e) {
      assertEquals("from renderMessage()", e.getMessage());
    }

    try {
      MarbleSolitaireView badView1 = new MockView(new StringBuilder());
      Reader input1 = new StringReader("");
      MarbleSolitaireController controller1 =
              new MarbleSolitaireControllerImpl(game1, badView1, input1);
      controller1.playGame();
    }
    catch (IllegalStateException e) {
      assertEquals("Error in transmitting final game state.", e.getMessage());
    }

    try {
      MarbleSolitaireView badView2 = new MockView(new StringBuilder());
      Reader input2 = new StringReader("4 2 4 4 q");
      MarbleSolitaireController controller2 =
              new MarbleSolitaireControllerImpl(game10, badView2, input2);
      controller2.playGame();
    }
    catch (IllegalStateException e) {
      assertEquals("Error in transmitting final game state.", e.getMessage());
    }
  }

  // test playing through the game to its end and winning
  @Test
  public void testWinningGame() {
    Appendable appendable = new StringBuilder();
    Reader input = new StringReader("4 6 4 4 6 5 4 5 5 7 5 5 5 4 5 6 5 2 5 4 7 3 5 3 4 3 6 3 "
            + "7 5 7 3 7 3 5 3 3 5 5 5 1 5 3 5 2 3 4 3 4 3 6 3 6 3 6 5 6 5 4 5 4 5 2 5 3 7 5 7 5 "
            + "7 5 5 5 5 5 3 3 1 3 3 3 4 3 2 5 1 3 1 3 1 3 3 1 3 1 5 1 5 3 5 3 6 3 4 3 4 3 2 3 2 "
            + "5 2 5 2 5 4 5 4 3 4 2 4 4 4 q");
    MarbleSolitaireView mockView = new MarbleSolitaireTextView(game1, appendable);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(game1, mockView, input);
    controller.playGame();
    String[] result = appendable.toString().split("\n");
    assertEquals("Score: 32", result[7]);
    assertEquals("    O O _", result[104]);
    assertEquals("    _ O _", result[105]);
    assertEquals("O O _ O O O O", result[106]);
    assertEquals("O O _ O _ _ O", result[107]);
    assertEquals("O _ _ O O O _", result[108]);
    assertEquals("    O O _", result[109]);
    assertEquals("    _ _ _", result[110]);
    assertEquals("Score: 19", result[111]);
    assertEquals("Game over!", result[248]);
    assertEquals("    _ _ _", result[249]);
    assertEquals("    _ _ _", result[250]);
    assertEquals("_ _ _ _ _ _ _", result[251]);
    assertEquals("_ _ _ O _ _ _", result[252]);
    assertEquals("_ _ _ _ _ _ _", result[253]);
    assertEquals("    _ _ _", result[254]);
    assertEquals("    _ _ _", result[255]);
    assertEquals("Score: 1", result[256]);
  }

  // test playing through the game and losing
  @Test
  public void testLosingGame() {
    Appendable appendable = new StringBuilder();
    Reader input = new StringReader("2 4 4 4 5 4 3 4 4 2 4 4 4 5 4 3 4 7 4 5 7 4 5 4 q");
    MarbleSolitaireView mockView = new MarbleSolitaireTextView(game1, appendable);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(game1, mockView, input);
    controller.playGame();
    String[] result = appendable.toString().split("\n");
    assertEquals("    O O O", result[16]);
    assertEquals("    O _ O", result[17]);
    assertEquals("O O O O O O O", result[18]);
    assertEquals("O O O _ O O O", result[19]);
    assertEquals("O O O _ O O O", result[20]);
    assertEquals("    O O O", result[21]);
    assertEquals("    O O O", result[22]);
    assertEquals("Score: 30", result[23]);
    assertEquals("Game over!", result[48]);
    assertEquals("    O O O", result[49]);
    assertEquals("    O _ O", result[50]);
    assertEquals("O O O O O O O", result[51]);
    assertEquals("O _ O _ O _ _", result[52]);
    assertEquals("O O O O O O O", result[53]);
    assertEquals("    O _ O", result[54]);
    assertEquals("    O _ O", result[55]);
    assertEquals("Score: 26", result[56]);
  }
}
