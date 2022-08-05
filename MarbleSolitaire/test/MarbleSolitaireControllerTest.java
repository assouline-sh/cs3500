import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MockModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class to test the controller for the Marble Solitaire game.
 */
public class MarbleSolitaireControllerTest {
  private StringBuilder log;
  private StringBuilder log1;
  private StringBuilder log2;
  private StringBuilder log3;
  private StringBuilder log4;
  private StringBuilder log5;
  private StringBuilder log6;
  private StringBuilder log7;
  private StringBuilder log8;
  private StringBuilder log9;
  private MarbleSolitaireView view;
  private MarbleSolitaireModel mockModel;
  private MarbleSolitaireView view1;
  private MarbleSolitaireModel mockModel1;
  private MarbleSolitaireView view2;
  private MarbleSolitaireModel mockModel2;
  private MarbleSolitaireView view3;
  private MarbleSolitaireModel mockModel3;
  private MarbleSolitaireView view4;
  private MarbleSolitaireModel mockModel4;
  private MarbleSolitaireView view5;
  private MarbleSolitaireModel mockModel5;
  private MarbleSolitaireView view6;
  private MarbleSolitaireModel mockModel6;
  private MarbleSolitaireView view7;
  private MarbleSolitaireModel mockModel7;
  private MarbleSolitaireView view8;
  private MarbleSolitaireModel mockModel8;
  private MarbleSolitaireView view9;
  private MarbleSolitaireModel mockModel9;

  @Before
  public void setUp() {
    log = new StringBuilder();
    log1 = new StringBuilder();
    log2 = new StringBuilder();
    log3 = new StringBuilder();
    log4 = new StringBuilder();
    log5 = new StringBuilder();
    log6 = new StringBuilder();
    log7 = new StringBuilder();
    log8 = new StringBuilder();
    log9 = new StringBuilder();
    mockModel = new MockModel(log);
    view = new MarbleSolitaireTextView(mockModel);
    mockModel1 = new MockModel(log1);
    view1 = new MarbleSolitaireTextView(mockModel1);
    mockModel2 = new MockModel(log2);
    view2 = new MarbleSolitaireTextView(mockModel2);
    mockModel3 = new MockModel(log3);
    view3 = new MarbleSolitaireTextView(mockModel3);
    mockModel4 = new MockModel(log4);
    view4 = new MarbleSolitaireTextView(mockModel4);
    mockModel5 = new MockModel(log5);
    view5 = new MarbleSolitaireTextView(mockModel5);
    mockModel6 = new MockModel(log6);
    view6 = new MarbleSolitaireTextView(mockModel6);
    mockModel7 = new MockModel(log7);
    view7 = new MarbleSolitaireTextView(mockModel7);
    mockModel8 = new MockModel(log8);
    view8 = new MarbleSolitaireTextView(mockModel8);
    mockModel9 = new MockModel(log9);
    view9 = new MarbleSolitaireTextView(mockModel9);
  }

  @Test
  public void testConstructor() {
    MarbleSolitaireModel modelEN = new EnglishSolitaireModel();
    MarbleSolitaireModel modelEU = new EuropeanSolitaireModel();
    MarbleSolitaireModel modelTri = new TriangleSolitaireModel();
    MarbleSolitaireModel modelNull = null;
    MarbleSolitaireView viewEN = new MarbleSolitaireTextView(modelEN);
    MarbleSolitaireView viewNull = null;
    Readable input = new StringReader("4 2 4 4 q");
    Readable inputNull = null;
    try {
      MarbleSolitaireController controller1 =
              new MarbleSolitaireControllerImpl(modelNull, viewEN, input);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Model, view, and input cannot be null.", e.getMessage());
    }

    try {
      MarbleSolitaireController controller2 =
              new MarbleSolitaireControllerImpl(modelEN, viewNull, input);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Model, view, and input cannot be null.", e.getMessage());
    }

    try {
      MarbleSolitaireController controller2 =
              new MarbleSolitaireControllerImpl(modelTri, viewNull, input);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Model, view, and input cannot be null.", e.getMessage());
    }

    try {
      MarbleSolitaireController controller3 =
              new MarbleSolitaireControllerImpl(modelEN, viewEN, inputNull);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Model, view, and input cannot be null.", e.getMessage());
    }

    try {
      MarbleSolitaireController controller3 =
              new MarbleSolitaireControllerImpl(modelEU, viewEN, inputNull);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Model, view, and input cannot be null.", e.getMessage());
    }
  }

  // tests that inputs are assigned accordingly to the move method's parameters fromRow
  // fromCol, toRow, and toCol. Note that each user input is one greater
  // than the value passed to the move method
  @Test
  public void testMoveInputs() {
    Reader input = new StringReader("4 2 4 4 q");
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mockModel, view, input);
    controller.playGame();
    assertTrue(log.toString().contains(" MOVE: fromRow: 3 fromCol: 1 toRow: 3 toCol: 3\n"));

    Reader input1 = new StringReader("4 y 2 4 4 q");
    MarbleSolitaireController controller1 =
            new MarbleSolitaireControllerImpl(mockModel1, view1, input1);
    controller1.playGame();
    assertTrue(log1.toString().contains(" MOVE: fromRow: 3 fromCol: 1 toRow: 3 toCol: 3\n"));

    Reader input2 = new StringReader("4 y x ? 2 t y 4 l a 4 q");
    MarbleSolitaireController controller2 =
            new MarbleSolitaireControllerImpl(mockModel2, view2, input2);
    controller2.playGame();
    assertTrue(log2.toString().contains(" MOVE: fromRow: 3 fromCol: 1 toRow: 3 toCol: 3\n"));

    Reader input3 = new StringReader("4 y x ? -1 2 t y 4 l a 4 ' 3 q");
    MarbleSolitaireController controller3 =
            new MarbleSolitaireControllerImpl(mockModel3, view3, input3);
    controller3.playGame();
    assertTrue(log3.toString().contains(" MOVE: fromRow: 3 fromCol: 1 toRow: 3 toCol: 3\n"));

    Reader input4 = new StringReader("4\n2\n4\n4\nq");
    MarbleSolitaireController controller4 =
            new MarbleSolitaireControllerImpl(mockModel4, view4, input4);
    controller4.playGame();
    assertTrue(log4.toString().contains(" MOVE: fromRow: 3 fromCol: 1 toRow: 3 toCol: 3\n"));

    Reader input5 = new StringReader("\n4\n2\n4\n4\nq\n");
    MarbleSolitaireController controller5 =
            new MarbleSolitaireControllerImpl(mockModel5, view5, input5);
    controller5.playGame();
    assertTrue(log5.toString().contains(" MOVE: fromRow: 3 fromCol: 1 toRow: 3 toCol: 3\n"));

    Reader input6 = new StringReader("4\n2 4\n4\n\nq");
    MarbleSolitaireController controller6 =
            new MarbleSolitaireControllerImpl(mockModel6, view6, input6);
    controller6.playGame();
    assertTrue(log6.toString().contains(" MOVE: fromRow: 3 fromCol: 1 toRow: 3 toCol: 3\n"));

    Reader input7 = new StringReader("4 2 4 4 6 3 4 3 q");
    MarbleSolitaireController controller7 =
            new MarbleSolitaireControllerImpl(mockModel7, view7, input7);
    controller7.playGame();
    assertTrue(log7.toString().contains(" MOVE: fromRow: 3 fromCol: 1 toRow: 3 toCol: 3\n"));
    assertTrue(log7.toString().contains(" MOVE: fromRow: 5 fromCol: 2 toRow: 3 toCol: 2\n"));

    Reader input8 = new StringReader("4 2 4 4 6 3 q");
    MarbleSolitaireController controller8 =
            new MarbleSolitaireControllerImpl(mockModel8, view8, input8);
    controller8.playGame();
    assertTrue(log8.toString().contains(" MOVE: fromRow: 3 fromCol: 1 toRow: 3 toCol: 3\n"));
    assertFalse(log8.toString().contains(" MOVE: fromRow: 5 fromCol: 2 toRow: 3 toCol: 2\n"));

    Reader input9 = new StringReader("a a a a 4 a 2 4 a 4 6 3 q");
    MarbleSolitaireController controller9 =
            new MarbleSolitaireControllerImpl(mockModel9, view9, input9);
    controller9.playGame();
    assertTrue(log9.toString().contains(" MOVE: fromRow: 3 fromCol: 1 toRow: 3 toCol: 3\n"));
    assertFalse(log9.toString().contains(" MOVE: fromRow: 5 fromCol: 2 toRow: 3 toCol: 2\n"));
  }

  // tests if invalid inputs for the game throw errors
  @Test(expected = IllegalStateException.class)
  public void testInputExceptions() {
    Appendable ap = new StringBuilder();
    MarbleSolitaireView mockView1 = new MarbleSolitaireTextView(mockModel, ap);
    Reader input = new StringReader("");
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mockModel, mockView1, input);
    controller.playGame();
    assertEquals("Not enough inputs given", ap.toString());

    Appendable ap00 = new StringBuilder();
    MarbleSolitaireView mockView100 = new MarbleSolitaireTextView(mockModel, ap00);
    Reader input00 = new StringReader("1 2\n");
    MarbleSolitaireController controller00 =
            new MarbleSolitaireControllerImpl(mockModel, mockView100, input00);
    controller00.playGame();
    assertEquals("Not enough inputs given", ap00.toString());

    Reader input1 = new StringReader("4 q");
    MarbleSolitaireController controller1 =
            new MarbleSolitaireControllerImpl(mockModel1, view1, input1);
    controller1.playGame();

    Reader input2 = new StringReader("4 5 q");
    MarbleSolitaireController controller2 =
            new MarbleSolitaireControllerImpl(mockModel2, view2, input2);
    controller2.playGame();

    Appendable ap2 = new StringBuilder();
    Reader input3 = new StringReader("4 2 4 q");
    MarbleSolitaireView mockView2 = new MarbleSolitaireTextView(mockModel3, ap2);
    MarbleSolitaireController controller3 =
            new MarbleSolitaireControllerImpl(mockModel3, mockView2, input3);
    controller3.playGame();
    assertEquals("Not enough inputs given", ap2.toString());

    Reader input4 = new StringReader("? q");
    MarbleSolitaireController controller4 =
            new MarbleSolitaireControllerImpl(mockModel4, view4, input4);
    controller4.playGame();

    Reader input5 = new StringReader("z -3 q");
    MarbleSolitaireController controller5 =
            new MarbleSolitaireControllerImpl(mockModel5, view5, input5);
    controller5.playGame();

    Reader input6 = new StringReader("a a a a q");
    MarbleSolitaireController controller6 =
            new MarbleSolitaireControllerImpl(mockModel6, view6, input6);
    controller6.playGame();

    Reader input7 = new StringReader("-4 -2 -4 -4 q");
    MarbleSolitaireController controller7 =
            new MarbleSolitaireControllerImpl(mockModel7, view7, input7);
    controller7.playGame();

    Reader input8 = new StringReader("4 2 4 4");
    MarbleSolitaireController controller8 =
            new MarbleSolitaireControllerImpl(mockModel8, view8, input8);
    controller8.playGame();

    Reader input9 = new StringReader("4244q");
    MarbleSolitaireController controller9 =
            new MarbleSolitaireControllerImpl(mockModel9, view9, input9);
    controller9.playGame();
  }
}
