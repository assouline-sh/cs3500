import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.SolitaireModels;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * Test methods for a European or Triangle Solitaire game.
 */
public class EuropeanTriangleModelTest {
  private EuropeanSolitaireModel game1E;
  private EuropeanSolitaireModel game2E;
  private EuropeanSolitaireModel game3E;
  private EuropeanSolitaireModel game4E;
  private EuropeanSolitaireModel game5E;

  private TriangleSolitaireModel game1T;
  private TriangleSolitaireModel game2T;
  private TriangleSolitaireModel game3T;
  private TriangleSolitaireModel game4T;
  private TriangleSolitaireModel game5T;

  @Before
  public void setUp() {
    game1E = new EuropeanSolitaireModel();
    game2E = new EuropeanSolitaireModel(3, 1);
    game3E = new EuropeanSolitaireModel(5);
    game4E = new EuropeanSolitaireModel(3, 3, 5);
    game5E = new EuropeanSolitaireModel(7);

    game1T = new TriangleSolitaireModel();
    game2T = new TriangleSolitaireModel(3, 1);
    game3T = new TriangleSolitaireModel(6);
    game4T = new TriangleSolitaireModel(6, 4, 2);
    game5T = new TriangleSolitaireModel(7);
  }

  @Test
  public void testConstructorTwo() {
    try {
      SolitaireModels game12 = new EuropeanSolitaireModel(2);
      fail("Board size must be a positive odd number at least 3");
    } catch (IllegalArgumentException e) {
      assertEquals("Board size must be a positive odd number at least 3", e.getMessage());
    }
    try {
      SolitaireModels game13 = new EuropeanSolitaireModel(6);
      fail("Board size must be a positive odd number at least 3");
    } catch (IllegalArgumentException e) {
      assertEquals("Board size must be a positive odd number at least 3", e.getMessage());
    }
    try {
      SolitaireModels game14 = new EuropeanSolitaireModel(10);
      fail("Board size must be a positive odd number at least 3");
    } catch (IllegalArgumentException e) {
      assertEquals("Board size must be a positive odd number at least 3", e.getMessage());
    }

    try {
      SolitaireModels game15 = new TriangleSolitaireModel(-9);
      fail("Length cannot be negative or zero.");
    } catch (IllegalArgumentException e) {
      assertEquals("Length cannot be negative or zero.", e.getMessage());
    }
    try {
      SolitaireModels game16 = new TriangleSolitaireModel(0);
      fail("Length cannot be negative or zero.");
    } catch (IllegalArgumentException e) {
      assertEquals("Length cannot be negative or zero.", e.getMessage());
    }
  }

  @Test
  public void testConstructorThree() {
    try {
      SolitaireModels game6 = new EuropeanSolitaireModel(-1, 2);
      fail("Empty cell (-1,2) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (-1,2) must be on board", e.getMessage());
    }

    try {
      SolitaireModels game7 = new EuropeanSolitaireModel(0, 10);
      fail("Empty cell (0,10) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (0,10) must be on board", e.getMessage());
    }
    try {
      SolitaireModels game8 = new EuropeanSolitaireModel(9, 2);
      fail("Empty cell (9,2) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (9,2) must be on board", e.getMessage());
    }
    try {
      SolitaireModels game9 = new EuropeanSolitaireModel(5, -3);
      fail("Empty cell (5,-3) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (5,-3) must be on board", e.getMessage());
    }
    try {
      SolitaireModels game10 = new EuropeanSolitaireModel(0, 1);
      fail("Invalid empty cell position (0,1)");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1)", e.getMessage());
    }
    try {
      SolitaireModels game11 = new EuropeanSolitaireModel(5, 6);
      fail("Invalid empty cell position (5,6)");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,6)", e.getMessage());
    }

    try {
      SolitaireModels game12 = new TriangleSolitaireModel(-3, 2);
      fail("Empty cell (-3,2) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (-3,2) must be on board", e.getMessage());
    }
    try {
      SolitaireModels game13 = new TriangleSolitaireModel(0, 7);
      fail("Empty cell (0,7) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (0,7) must be on board", e.getMessage());
    }
    try {
      SolitaireModels game14 = new TriangleSolitaireModel(7, 1);
      fail("Empty cell (7,1) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (7,1) must be on board", e.getMessage());
    }
    try {
      SolitaireModels game15 = new TriangleSolitaireModel(5, -3);
      fail("Empty cell (5,-3) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (5,-3) must be on board", e.getMessage());
    }
    try {
      SolitaireModels game16 = new TriangleSolitaireModel(0, 1);
      fail("Invalid empty cell position (0,1)");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1)", e.getMessage());
    }
    try {
      SolitaireModels game17 = new TriangleSolitaireModel(1, 2);
      fail("Invalid empty cell position (1,2)");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,2)", e.getMessage());
    }
  }


  @Test
  public void testConstructorFour() {
    try {
      SolitaireModels game18 = new EuropeanSolitaireModel(-2, 3, 4);
      fail("Length cannot be negative or zero.");
    } catch (IllegalArgumentException e) {
      assertEquals("Length cannot be negative or zero.", e.getMessage());
    }
    try {
      SolitaireModels game19 = new EuropeanSolitaireModel(8, 5, 3);
      fail("Board size must be a positive odd number at least 3");
    } catch (IllegalArgumentException e) {
      assertEquals("Board size must be a positive odd number at least 3", e.getMessage());
    }
    try {
      SolitaireModels game20 = new EuropeanSolitaireModel(5, 0, 0);
      fail("Invalid empty cell position (0,0)");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,0)", e.getMessage());
    }
    try {
      SolitaireModels game21 = new EuropeanSolitaireModel(5, -2, 4);
      fail("Empty cell (-2,4) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (-2,4) must be on board", e.getMessage());
    }
    try {
      SolitaireModels game22 = new TriangleSolitaireModel(-7, 5, 32);
      fail("Length cannot be negative or zero.");
    } catch (IllegalArgumentException e) {
      assertEquals("Length cannot be negative or zero.", e.getMessage());
    }
    try {
      SolitaireModels game23 = new TriangleSolitaireModel(3, 6, 5);
      fail("Empty cell (6,5) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (6,5) must be on board", e.getMessage());
    }
    try {
      SolitaireModels game24 = new TriangleSolitaireModel(5, 11, 11);
      fail("Empty cell (11,11) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (11,11) must be on board", e.getMessage());
    }
  }


  @Test
  public void testInitializeBoard() {
    assertEquals(game1E.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1E.getSlotAt(0, 1), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1E.getSlotAt(0, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1E.getSlotAt(0, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1E.getSlotAt(0, 5), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1E.getSlotAt(1, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1E.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1E.getSlotAt(2, 6), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1E.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game1E.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1E.getSlotAt(4, 6), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1E.getSlotAt(5, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1E.getSlotAt(5, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1E.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Invalid);

    assertEquals(game2E.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game2E.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game2E.getSlotAt(5, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game2E.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game2E.getSlotAt(2, 4), MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(game3E.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game3E.getSlotAt(1, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game3E.getSlotAt(10, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game3E.getSlotAt(11, 2), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game3E.getSlotAt(12, 12), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game3E.getSlotAt(3, 8), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game3E.getSlotAt(1, 9), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game3E.getSlotAt(1, 10), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game3E.getSlotAt(2, 10), MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(game4E.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game4E.getSlotAt(1, 0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game4E.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game4E.getSlotAt(1, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game4E.getSlotAt(3, 6), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game4E.getSlotAt(5, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game4E.getSlotAt(5, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game4E.getSlotAt(5, 6), MarbleSolitaireModelState.SlotState.Invalid);

    assertEquals(game1T.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game1T.getSlotAt(0, 1), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1T.getSlotAt(0, 2), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1T.getSlotAt(1, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1T.getSlotAt(1, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1T.getSlotAt(1, 2), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1T.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1T.getSlotAt(2, 3), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1T.getSlotAt(2, 4), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1T.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1T.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1T.getSlotAt(4, 4), MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(game2T.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game2T.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game2T.getSlotAt(4, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game2T.getSlotAt(1, 2), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game2T.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game2T.getSlotAt(4, 4), MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(game3T.getSlotAt(5, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game3T.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game3T.getSlotAt(2, 4), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game3T.getSlotAt(4, 5), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game3T.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game3T.getSlotAt(1, 1), MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(game4T.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game4T.getSlotAt(1, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game4T.getSlotAt(4, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game4T.getSlotAt(1, 5), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game4T.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game4T.getSlotAt(5, 5), MarbleSolitaireModelState.SlotState.Marble);
  }


  @Test
  public void testMove() {
    game1E.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1E.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1E.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1E.getSlotAt(3, 2));
    game1E.move(5, 2, 3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1E.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1E.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1E.getSlotAt(4, 2));
    try {
      game1E.move(1, 2, 3, 3);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    }
    catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1E.getSlotAt(1, 2));
    }
    try {
      game1E.move(6, 2, 4, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1E.getSlotAt(4, 2));
    }
    try {
      game1E.move(2, 2, 5, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1E.getSlotAt(2, 2));
    }
    try {
      game1E.move(1, 2, 4, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1E.getSlotAt(4, 2));
    }
    try {
      game1E.move(3, 2, 4, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1E.getSlotAt(4, 2));
    }
    try {
      game1E.move(-1, -1, 4, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1E.getSlotAt(4, 2));
    }
    try {
      game1E.move(1, 4, 1, 6);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1E.getSlotAt(4, 2));
    }
    try {
      game1E.move(1, 4, 3, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1E.getSlotAt(1, 4));
    }

    game2E.move(3, 3, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game2E.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2E.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2E.getSlotAt(3, 2));
    try {
      game2E.move(2, 5, 2, 3);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game2E.getSlotAt(2, 3));
    }
    try {
      game2E.move(3, 2, 1, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2E.getSlotAt(3, 2));
    }

    game1T.move(2, 0, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1T.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1T.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1T.getSlotAt(1, 0));

    game1T.move(3, 2, 1, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1T.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1T.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1T.getSlotAt(2, 1));

    game1T.move(4, 1, 2, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1T.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1T.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1T.getSlotAt(3, 1));

    game1T.move(4, 3, 4, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1T.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1T.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1T.getSlotAt(4, 2));

    try {
      game2T.move(3, 0, 3, 1);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1T.getSlotAt(3, 0));
    }
    try {
      game2T.move(1, 0, 3, 1);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1T.getSlotAt(1, 0));
    }
    try {
      game2T.move(5, 3, 3, 3);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game2T.getSlotAt(3, 3));
    }
    try {
      game2T.move(3, 1, 3, 3);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2T.getSlotAt(3, 1));
    }
    try {
      game2T.move(3, 2, 3, 4);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game2T.getSlotAt(3, 4));
    }
  }

  @Test
  public void testIsGameOver() {
    MarbleSolitaireModel modelEWin = new EuropeanSolitaireModel(3, 6, 2);
    assertEquals(false, modelEWin.isGameOver());
    modelEWin.move(4, 2, 6, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEWin.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelEWin.getSlotAt(6, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelEWin.getSlotAt(5, 2));
    modelEWin.move(4, 4, 4, 2);
    modelEWin.move(6, 4, 4, 4);
    modelEWin.move(6, 3, 4, 3);
    modelEWin.move(3, 2, 5, 2);
    modelEWin.move(6, 2, 4, 2);
    modelEWin.move(3, 0, 3, 2);
    modelEWin.move(5, 1, 3, 1);
    modelEWin.move(4, 3, 4, 1);
    modelEWin.move(4, 0, 4, 2);
    modelEWin.move(2, 1, 4, 1);
    modelEWin.move(4, 1, 4, 3);
    modelEWin.move(2, 3, 2, 1);
    modelEWin.move(2, 0, 2, 2);
    modelEWin.move(0, 3, 2, 3);
    modelEWin.move(1, 1, 1, 3);
    modelEWin.move(3, 2, 1, 2);
    modelEWin.move(0, 2, 2, 2);
    modelEWin.move(1, 4, 1, 2);
    modelEWin.move(3, 4, 1, 4);
    modelEWin.move(2, 6, 2, 4);
    modelEWin.move(3, 6, 3, 4);
    modelEWin.move(5, 5, 3, 5);
    modelEWin.move(4, 3, 4, 5);
    modelEWin.move(4, 6, 4, 4);
    modelEWin.move(2, 3, 2, 5);
    modelEWin.move(2, 5, 4, 5);
    modelEWin.move(4, 5, 4, 3);
    modelEWin.move(4, 3, 2, 3);
    modelEWin.move(2, 2, 2, 4);
    modelEWin.move(1, 5, 1, 3);
    modelEWin.move(3, 4, 1, 4);
    modelEWin.move(0, 4, 2, 4);
    modelEWin.move(1, 2, 1, 4);
    modelEWin.move(2, 4, 0, 4);
    assertEquals(true, modelEWin.isGameOver());

    MarbleSolitaireModel modelTWin = new TriangleSolitaireModel(4, 1, 0);
    assertEquals(false, modelTWin.isGameOver());
    modelTWin.move(3, 0, 1, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTWin.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelTWin.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelTWin.getSlotAt(2, 0));
    modelTWin.move(0, 0, 2, 0);
    modelTWin.move(2, 2, 0, 0);
    modelTWin.move(3, 2, 3, 0);
    modelTWin.move(3, 0, 1, 0);
    modelTWin.move(0, 0, 2, 0);
    modelTWin.move(2, 0, 2, 2);
    modelTWin.move(3, 3, 1, 1);
    assertEquals(true, modelTWin.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, game1E.getBoardSize());
    assertEquals(7, game2E.getBoardSize());
    assertEquals(13, game3E.getBoardSize());
    assertEquals(7, game4E.getBoardSize());
    assertEquals(19, game5E.getBoardSize());

    assertEquals(5, game1T.getBoardSize());
    assertEquals(5, game2T.getBoardSize());
    assertEquals(6, game3T.getBoardSize());
    assertEquals(6, game4T.getBoardSize());
    assertEquals(7, game5T.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1E.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game2E.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game3E.getSlotAt(11, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game4E.getSlotAt(6, 2));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game1E.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game2E.getSlotAt(5, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game3E.getSlotAt(10, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game4E.getSlotAt(0, 5));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1E.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2E.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game3E.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game4E.getSlotAt(3, 5));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1T.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game2T.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game3T.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game4T.getSlotAt(2, 2));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game1T.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game2T.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game3T.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game4T.getSlotAt(0, 5));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1T.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2T.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game3T.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game4T.getSlotAt(4, 2));

    try {
      game1E.getSlotAt(-1, 2);
      fail("Given row or column cannot be beyond board dimensions");
    } catch (IllegalArgumentException e) {
      assertEquals("Given row or column cannot be beyond board dimensions", e.getMessage());
    }
    try {
      game1E.getSlotAt(8, 2);
      fail("Given row or column cannot be beyond board dimensions");
    } catch (IllegalArgumentException e) {
      assertEquals("Given row or column cannot be beyond board dimensions", e.getMessage());
    }
    try {
      game3T.getSlotAt(3, -3);
      fail("Given row or column cannot be beyond board dimensions");
    } catch (IllegalArgumentException e) {
      assertEquals("Given row or column cannot be beyond board dimensions", e.getMessage());
    }
    try {
      game3T.getSlotAt(3, 18);
      fail("Given row or column cannot be beyond board dimensions");
    } catch (IllegalArgumentException e) {
      assertEquals("Given row or column cannot be beyond board dimensions", e.getMessage());
    }
  }

  @Test
  public void testGetScore() {
    assertEquals(36, game1E.getScore());
    game1E.move(5, 3, 3, 3);
    assertEquals(35, game1E.getScore());
    game1E.move(4, 5, 4, 3);
    assertEquals(34, game1E.getScore());

    assertEquals(14, game1T.getScore());
    game1T.move(2, 0, 0, 0);
    assertEquals(13, game1T.getScore());
    game1T.move(2, 2, 2, 0);
    assertEquals(12, game1T.getScore());
  }

  @Test
  public void testIsInvalidSlot()  {
    assertTrue(game3E.isInvalidSlot(1, 1));
    assertTrue(game3E.isInvalidSlot(3, 12));
    assertTrue(game3E.isInvalidSlot(12, 1));
    assertFalse(game3E.isInvalidSlot(3, 3));
    assertFalse(game3E.isInvalidSlot(4, 2));

    assertFalse(game4E.isInvalidSlot(3, 1));
    assertFalse(game4E.isInvalidSlot(5, 2));
    assertTrue(game4E.isInvalidSlot(6, 6));
    assertTrue(game4E.isInvalidSlot(5, 0));

    assertFalse(game5E.isInvalidSlot(11, 7));
    assertFalse(game5E.isInvalidSlot(7, 2));
    assertTrue(game5E.isInvalidSlot(19, 1));
    assertTrue(game5E.isInvalidSlot(2, 2));

    assertTrue(game3T.isInvalidSlot(1, 2));
    assertTrue(game3T.isInvalidSlot(3, 4));
    assertTrue(game3T.isInvalidSlot(0, 3));
    assertFalse(game3T.isInvalidSlot(3, 3));
    assertFalse(game3T.isInvalidSlot(2, 2));

    assertFalse(game4T.isInvalidSlot(3, 1));
    assertFalse(game4T.isInvalidSlot(5, 2));
    assertTrue(game4T.isInvalidSlot(5, 6));
    assertTrue(game4T.isInvalidSlot(2, 5));

    assertFalse(game5T.isInvalidSlot(3, 3));
    assertFalse(game5T.isInvalidSlot(0, 0));
    assertTrue(game5T.isInvalidSlot(1, 2));
    assertTrue(game5T.isInvalidSlot(2, 3));
  }
}
