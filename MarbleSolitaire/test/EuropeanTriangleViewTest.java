import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TextViews;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.MockView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test methods for rendering the board and messages of European and Triangle Solitaire games.
 */
public class EuropeanTriangleViewTest {

  private EuropeanSolitaireModel game1E;
  private EuropeanSolitaireModel game10E;
  private EuropeanSolitaireModel game11E;
  private EuropeanSolitaireModel game12E;
  private EuropeanSolitaireModel game2E;
  private EuropeanSolitaireModel game3E;
  private EuropeanSolitaireModel game4E;

  private MarbleSolitaireTextView view1E;
  private MarbleSolitaireTextView view2E;
  private MarbleSolitaireTextView view4E;

  private TriangleSolitaireModel game1T;
  private TriangleSolitaireModel game2T;
  private TriangleSolitaireModel game3T;
  private TriangleSolitaireModel game4T;
  private TriangleSolitaireModel game5T;
  private TriangleSolitaireModel game6T;
  private TriangleSolitaireTextView view1T;
  private TriangleSolitaireTextView view2T;
  private TriangleSolitaireTextView view4T;


  @Before
  public void setUp() {
    game1E = new EuropeanSolitaireModel();
    game10E = new EuropeanSolitaireModel();
    game11E = new EuropeanSolitaireModel();
    game12E = new EuropeanSolitaireModel();
    game2E = new EuropeanSolitaireModel(3, 1);
    game3E = new EuropeanSolitaireModel(5);
    game4E = new EuropeanSolitaireModel(5, 3, 5);
    view1E = new MarbleSolitaireTextView(game1E);
    view2E = new MarbleSolitaireTextView(game2E);
    view4E = new MarbleSolitaireTextView(game4E);

    game1T = new TriangleSolitaireModel();
    game2T = new TriangleSolitaireModel(3, 1);
    game3T = new TriangleSolitaireModel(6);
    game4T = new TriangleSolitaireModel(6, 4, 2);
    game5T = new TriangleSolitaireModel(7);
    game6T = new TriangleSolitaireModel(4, 1, 0);
    view1T = new TriangleSolitaireTextView(game1T);
    view2T = new TriangleSolitaireTextView(game2T);
    view4T = new TriangleSolitaireTextView(game4T);
  }

  @Test
  public void testConstructor() {
    try {
      EuropeanSolitaireModel game5 = null;
      TextViews viewGame5 = new MarbleSolitaireTextView(game5);
      fail("Provided model is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }

    try {
      TriangleSolitaireModel game6 = null;
      TextViews viewGame6 = new MarbleSolitaireTextView(game6);
      fail("Provided model is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }
  }

  @Test
  public void testToString() {
    assertEquals(
             "    _\n"
                    + "   O O\n"
                    + "  O O O\n"
                    + " O O O O\n"
                    + "O O O O O", view1T.toString());
    assertEquals(
             "    O\n"
                    + "   O O\n"
                    + "  O O O\n"
                    + " O _ O O\n"
                    + "O O O O O", view2T.toString());
    assertEquals(
            "     O\n"
                    + "    O O\n"
                    + "   O O O\n"
                    + "  O O O O\n"
                    + " O O _ O O\n"
                    + "O O O O O O", view4T.toString());
    game4T.move(4, 0, 4, 2);
    assertEquals(
            "     O\n"
                    + "    O O\n"
                    + "   O O O\n"
                    + "  O O O O\n"
                    + " _ _ O O O\n"
                    + "O O O O O O", view4T.toString());
    game4T.move(2, 1, 4, 1);
    assertEquals(
            "     O\n"
                    + "    O O\n"
                    + "   O _ O\n"
                    + "  O _ O O\n"
                    + " _ O O O O\n"
                    + "O O O O O O", view4T.toString());
    assertEquals(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O", view1E.toString());
    assertEquals(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O _ O O O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O", view2E.toString());
    assertEquals(
            "        O O O O O\n"
                    + "      O O O O O O O\n"
                    + "    O O O O O O O O O\n"
                    + "  O O O O _ O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "  O O O O O O O O O O O\n"
                    + "    O O O O O O O O O\n"
                    + "      O O O O O O O\n"
                    + "        O O O O O", view4E.toString());
    game4E.move(1, 5, 3, 5);
    assertEquals(
            "        O O O O O\n"
                    + "      O O _ O O O O\n"
                    + "    O O O _ O O O O O\n"
                    + "  O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "  O O O O O O O O O O O\n"
                    + "    O O O O O O O O O\n"
                    + "      O O O O O O O\n"
                    + "        O O O O O", view4E.toString());
    game4E.move(4, 5, 2, 5);
    assertEquals(
            "        O O O O O\n"
                    + "      O O _ O O O O\n"
                    + "    O O O O O O O O O\n"
                    + "  O O O O _ O O O O O O\n"
                    + "O O O O O _ O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "  O O O O O O O O O O O\n"
                    + "    O O O O O O O O O\n"
                    + "      O O O O O O O\n"
                    + "        O O O O O", view4E.toString());
  }

  @Test
  public void testSecondConstructor() {
    try {
      Appendable appendable = null;
      TextViews viewGame1 = new MarbleSolitaireTextView(game1E, appendable);
      fail("Provided model or Appendable object is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model or Appendable object is null.", e.getMessage());
    }

    try {
      EuropeanSolitaireModel game7 = null;
      Appendable appendable1 = null;
      TextViews viewGame7 = new MarbleSolitaireTextView(game7, appendable1);
      fail("Provided model or Appendable object is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model or Appendable object is null.", e.getMessage());
    }

    try {
      EuropeanSolitaireModel game8 = null;
      Appendable appendable2 = new StringBuilder();
      TextViews viewGame8 = new MarbleSolitaireTextView(game8, appendable2);
      fail("Provided model or Appendable object is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model or Appendable object is null.", e.getMessage());
    }

    try {
      Appendable appendable = null;
      TextViews viewGame1 = new TriangleSolitaireTextView(game1T, appendable);
      fail("Provided model or Appendable object is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model or Appendable object is null.", e.getMessage());
    }

    try {
      TriangleSolitaireModel game9 = null;
      Appendable appendable1 = null;
      TextViews viewGame9 = new TriangleSolitaireTextView(game9, appendable1);
      fail("Provided model or Appendable object is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model or Appendable object is null.", e.getMessage());
    }

    try {
      TriangleSolitaireModel game10 = null;
      Appendable appendable2 = new StringBuilder();
      TextViews viewGame10 = new TriangleSolitaireTextView(game10, appendable2);
      fail("Provided model or Appendable object is null.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model or Appendable object is null.", e.getMessage());
    }
  }

  @Test
  public void testRenderBoard() {
    Appendable appendable = new StringBuilder();
    TextViews aView1 = new MarbleSolitaireTextView(game2E, appendable);
    try {
      aView1.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    String[] result = appendable.toString().split("\n");
    assertEquals("    O O O", result[0]);
    assertEquals("  O O O O O", result[1]);
    assertEquals("O O O O O O O", result[2]);
    assertEquals("O _ O O O O O", result[3]);
    assertEquals("O O O O O O O", result[4]);
    assertEquals("  O O O O O", result[5]);
    assertEquals("    O O O", result[6]);


    Reader input = new StringReader("4 2 4 4 6 3 4 3 q");
    Appendable appendable1 = new StringBuilder();
    TextViews aView2 = new MarbleSolitaireTextView(game1E, appendable1);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(game1E, aView2, input);
    controller.playGame();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "  O _ O O O\n"
            + "    O O O\n"
            + "Score: 34\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "  O _ O O O\n"
            + "    O O O\n"
            + "Score: 34\n", appendable1.toString());

    Reader input1 = new StringReader("q");
    Appendable appendable2 = new StringBuilder();
    TextViews aView3 = new MarbleSolitaireTextView(game10E, appendable2);
    MarbleSolitaireController controller1 =
            new MarbleSolitaireControllerImpl(game10E, aView3, input1);
    controller1.playGame();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n", appendable2.toString());

    Reader input2 = new StringReader("4 q");
    Appendable appendable3 = new StringBuilder();
    TextViews aView4 = new MarbleSolitaireTextView(game11E, appendable3);
    MarbleSolitaireController controller2 =
            new MarbleSolitaireControllerImpl(game11E, aView4, input2);
    controller2.playGame();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n", appendable3.toString());

    Reader input3 = new StringReader("4 2 q 4");
    Appendable appendable4 = new StringBuilder();
    TextViews aView5 = new MarbleSolitaireTextView(game12E, appendable4);
    MarbleSolitaireController controller3 =
            new MarbleSolitaireControllerImpl(game12E, aView5, input3);
    controller3.playGame();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n", appendable4.toString());

    Reader input4 = new StringReader("4 2 q 4");
    Appendable appendable5 = new StringBuilder();
    TextViews aView6 = new MarbleSolitaireTextView(game2E, appendable5);
    MarbleSolitaireController controller4 =
            new MarbleSolitaireControllerImpl(game2E, aView6, input4);
    controller4.playGame();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n", appendable5.toString());

    Reader input4a = new StringReader("4 2 4 q");
    Appendable appendable50 = new StringBuilder();
    TextViews aView6a = new MarbleSolitaireTextView(game2E, appendable50);
    MarbleSolitaireController controller4a =
            new MarbleSolitaireControllerImpl(game2E, aView6a, input4a);
    controller4a.playGame();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n", appendable50.toString());

    Appendable appendable6 = new StringBuilder();
    TextViews aViewT1 = new TriangleSolitaireTextView(game6T, appendable6);
    try {
      aViewT1.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    String[] resultT1 = appendable6.toString().split("\n");
    assertEquals("   O", resultT1[0]);
    assertEquals("  _ O", resultT1[1]);
    assertEquals(" O O O", resultT1[2]);
    assertEquals("O O O O", resultT1[3]);

    Reader input1T = new StringReader("3 1 1 1 4 3 2 1 q");
    Appendable appendable1T = new StringBuilder();
    TextViews aView2T = new TriangleSolitaireTextView(game1T, appendable1T);
    MarbleSolitaireController controller1T =
            new MarbleSolitaireControllerImpl(game1T, aView2T, input1T);
    controller1T.playGame();
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n"
            + "    O\n"
            + "   O O\n"
            + "  _ _ O\n"
            + " O O _ O\n"
            + "O O O O O\n"
            + "Score: 12\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O\n"
            + "   O O\n"
            + "  _ _ O\n"
            + " O O _ O\n"
            + "O O O O O\n"
            + "Score: 12\n", appendable1T.toString());

    Reader input10T = new StringReader("q");
    Appendable appendable2T = new StringBuilder();
    TextViews aView3T = new TriangleSolitaireTextView(game2T, appendable2T);
    MarbleSolitaireController controller10T =
            new MarbleSolitaireControllerImpl(game2T, aView3T, input10T);
    controller10T.playGame();
    assertEquals("    O\n"
            + "   O O\n"
            + "  O O O\n"
            + " O _ O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O\n"
            + "   O O\n"
            + "  O O O\n"
            + " O _ O O\n"
            + "O O O O O\n"
            + "Score: 14\n", appendable2T.toString());


    Reader input2T = new StringReader("4 q");
    Appendable appendable3T = new StringBuilder();
    TextViews aView4T = new TriangleSolitaireTextView(game5T, appendable3T);
    MarbleSolitaireController controller2T =
            new MarbleSolitaireControllerImpl(game5T, aView4T, input2T);
    controller2T.playGame();
    assertEquals("      _\n"
            + "     O O\n"
            + "    O O O\n"
            + "   O O O O\n"
            + "  O O O O O\n"
            + " O O O O O O\n"
            + "O O O O O O O\n"
            + "Score: 27\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "      _\n"
            + "     O O\n"
            + "    O O O\n"
            + "   O O O O\n"
            + "  O O O O O\n"
            + " O O O O O O\n"
            + "O O O O O O O\n"
            + "Score: 27\n", appendable3T.toString());


    Reader input3T = new StringReader("4 2 q 4");
    Appendable appendable4T = new StringBuilder();
    TextViews aView5T = new TriangleSolitaireTextView(game6T, appendable4T);
    MarbleSolitaireController controller3T =
            new MarbleSolitaireControllerImpl(game6T, aView5T, input3T);
    controller3T.playGame();
    assertEquals("   O\n"
            + "  _ O\n"
            + " O O O\n"
            + "O O O O\n"
            + "Score: 9\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "   O\n"
            + "  _ O\n"
            + " O O O\n"
            + "O O O O\n"
            + "Score: 9\n", appendable4T.toString());

    Reader input4T = new StringReader("4 2 q 4");
    Appendable appendable5T = new StringBuilder();
    TextViews aView6T = new TriangleSolitaireTextView(game6T, appendable5T);
    MarbleSolitaireController controller4T =
            new MarbleSolitaireControllerImpl(game6T, aView6T, input4T);
    controller4T.playGame();
    assertEquals("   O\n"
            + "  _ O\n"
            + " O O O\n"
            + "O O O O\n"
            + "Score: 9\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "   O\n"
            + "  _ O\n"
            + " O O O\n"
            + "O O O O\n"
            + "Score: 9\n", appendable5T.toString());

    Reader input4aT = new StringReader("4 2 4 q");
    Appendable appendable50T = new StringBuilder();
    TextViews aView6aT = new TriangleSolitaireTextView(game6T, appendable50T);
    MarbleSolitaireController controller4aT =
            new MarbleSolitaireControllerImpl(game6T, aView6aT, input4aT);
    controller4aT.playGame();
    assertEquals("   O\n"
            + "  _ O\n"
            + " O O O\n"
            + "O O O O\n"
            + "Score: 9\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "   O\n"
            + "  _ O\n"
            + " O O O\n"
            + "O O O O\n"
            + "Score: 9\n", appendable50T.toString());
  }

  @Test
  public void testRenderMessage() {
    Appendable appendable = new StringBuilder();
    MarbleSolitaireView aView1 = new MarbleSolitaireTextView(game1E, appendable);
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
    MarbleSolitaireView aView2 = new MarbleSolitaireTextView(game1E, appendable1);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(game1E, aView2, input);
    controller.playGame();
    assertTrue(appendable1.toString().contains("Invalid move. Play again. Move must follow the"
            + " rules of the game and be on the board.\n"));

    Reader input1 = new StringReader("-4 2 4 4 q");
    Appendable appendable2 = new StringBuilder();
    MarbleSolitaireView aView3 = new MarbleSolitaireTextView(game2E, appendable2);
    MarbleSolitaireController controller1 =
            new MarbleSolitaireControllerImpl(game2E, aView3, input1);
    controller1.playGame();
    assertTrue(appendable1.toString().contains("Invalid move. Play again. Move must follow the"
            + " rules of the game and be on the board.\n"));

    Reader input2 = new StringReader("2 5 4 4 q");
    Appendable appendable3 = new StringBuilder();
    MarbleSolitaireView aView4 = new MarbleSolitaireTextView(game1T, appendable3);
    MarbleSolitaireController controller2 =
            new MarbleSolitaireControllerImpl(game1T, aView4, input2);
    controller2.playGame();
    assertTrue(appendable1.toString().contains("Invalid move. Play again. Move must follow the"
            + " rules of the game and be on the board.\n"));

    Reader input3 = new StringReader("4 4 4 6 q");
    Appendable appendable4 = new StringBuilder();
    MarbleSolitaireView aView5 = new MarbleSolitaireTextView(game2T, appendable4);
    MarbleSolitaireController controller3 =
            new MarbleSolitaireControllerImpl(game2T, aView5, input3);
    controller3.playGame();
    assertTrue(appendable1.toString().contains("Invalid move. Play again. Move must follow the"
            + " rules of the game and be on the board.\n"));

    Reader input5 = new StringReader("3 y 3 5 q");
    Appendable appendable6 = new StringBuilder();
    MarbleSolitaireView aView7 = new MarbleSolitaireTextView(game3E, appendable6);
    MarbleSolitaireController controller5 =
            new MarbleSolitaireControllerImpl(game3E, aView7, input5);
    controller5.playGame();
    String[] result3 = appendable6.toString().split("\n");
    assertEquals("An unexpected value was encountered.", result3[14]);
    assertEquals("Game quit!", result3[15]);
    assertEquals("State of game when quit:", result3[16]);

    Reader input6 = new StringReader("x 3 y 3 -1 ? 5 2 q");
    Appendable appendable7 = new StringBuilder();
    MarbleSolitaireView aView8 = new MarbleSolitaireTextView(game3T, appendable7);
    MarbleSolitaireController controller6 =
            new MarbleSolitaireControllerImpl(game3T, aView8, input6);
    controller6.playGame();
    String[] result4 = appendable7.toString().split("\n");
    assertEquals("An unexpected value was encountered.", result4[7]);
    assertEquals("An unexpected value was encountered.", result4[8]);
    assertEquals("An unexpected value was encountered.", result4[9]);
    assertEquals("Game quit!", result4[18]);
    assertEquals("State of game when quit:", result4[19]);
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
      Reader input1 = new StringReader("q");
      MarbleSolitaireController controller1 =
              new MarbleSolitaireControllerImpl(game1E, badView1, input1);
      controller1.playGame();
    }
    catch (IllegalStateException e) {
      assertEquals("Error in transmitting final game state.", e.getMessage());
    }

    try {
      MarbleSolitaireView badView2 = new MockView(new StringBuilder());
      Reader input2 = new StringReader("2 0 0 0 q");
      MarbleSolitaireController controller2 =
              new MarbleSolitaireControllerImpl(game1T, badView2, input2);
      controller2.playGame();
    }
    catch (IllegalStateException e) {
      assertEquals("Error in transmitting final game state.", e.getMessage());
    }
  }

  @Test
  public void testWinningGame() {
    Appendable appendable = new StringBuilder();
    Reader input = new StringReader("4 1 2 1 1 1 3 1 3 3 1 1 4 3 4 1 4 1 2 1 1 1 3 1 3 1 3 "
            + "3 4 4 2 2 q");
    MarbleSolitaireModel modelTWin = new TriangleSolitaireModel(4, 1, 0);
    TriangleSolitaireTextView mockView = new TriangleSolitaireTextView(modelTWin, appendable);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(modelTWin, mockView, input);
    controller.playGame();
    String[] result = appendable.toString().split("\n");

    assertEquals("   O", result[5]);
    assertEquals("  O O", result[6]);
    assertEquals(" _ O O", result[7]);
    assertEquals("_ O O O", result[8]);
    assertEquals("Score: 8", result[9]);
    assertEquals("Game over!", result[40]);
    assertEquals("   _", result[41]);
    assertEquals("  _ O", result[42]);
    assertEquals(" _ _ _", result[43]);
    assertEquals("_ _ _ _", result[44]);
    assertEquals("Score: 1", result[45]);
    assertTrue(modelTWin.isGameOver());

    Appendable appendable1 = new StringBuilder();
    Reader input1 = new StringReader("5 3 7 3 5 5 5 3 7 5 5 5 7 4 5 4 4 3 6 3 7 3 5 3 4 1 4 3"
            + " 6 2 4 2 5 4 5 2 5 1 5 3 3 2 5 2 5 2 5 4 3 4 3 2 3 1 3 3 1 4 3 4 2 2 2 4 4 3 2 3"
            + " 1 3 3 3 2 5 2 3 4 5 2 5 3 7 3 5 4 7 4 5 6 6 4 6 5 4 5 6 5 7 5 5 3 4 3 6 3 6 5 6"
            + " 5 6 5 4 5 4 3 4 3 3 3 5 2 6 2 4 4 5 2 5 1 5 3 5 2 3 2 5 3 5 1 5 q");
    MarbleSolitaireModel modelEWin = new EuropeanSolitaireModel(3, 6, 2);
    TextViews mockView1 = new MarbleSolitaireTextView(modelEWin, appendable1);
    MarbleSolitaireController controller1 =
            new MarbleSolitaireControllerImpl(modelEWin, mockView1, input1);
    controller1.playGame();
    String[] result1 = appendable1.toString().split("\n");

    assertEquals("    O O O", result1[56]);
    assertEquals("  O O O O O", result1[57]);
    assertEquals("O O O O O O O", result1[58]);
    assertEquals("_ _ O O O O O", result1[59]);
    assertEquals("O O O O O O O", result1[60]);
    assertEquals("  O _ _ _ O", result1[61]);
    assertEquals("    _ _ _", result1[62]);
    assertEquals("Score: 29", result1[63]);
    assertEquals("Game over!", result1[280]);
    assertEquals("    _ _ O", result1[281]);
    assertEquals("  _ _ _ _ _", result1[282]);
    assertEquals("_ _ _ _ _ _ _", result1[283]);
    assertEquals("_ _ _ _ _ _ _", result1[284]);
    assertEquals("_ _ _ _ _ _ _", result1[285]);
    assertEquals("  _ _ _ _ _", result1[286]);
    assertEquals("    _ _ _", result1[287]);
    assertEquals("Score: 1", result1[288]);
    assertTrue(modelEWin.isGameOver());
  }
}
