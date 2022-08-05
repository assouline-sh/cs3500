import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test methods for an English Solitaire Model game.
 */
public class EnglishSolitaireModelTest {
  private EnglishSolitaireModel game1;
  private EnglishSolitaireModel game10;
  private EnglishSolitaireModel game2;
  private EnglishSolitaireModel game3;
  private EnglishSolitaireModel game4;
  private EnglishSolitaireModel game5;

  @Before
  public void setUp() {
    game1 = new EnglishSolitaireModel();
    game10 = new EnglishSolitaireModel();
    game2 = new EnglishSolitaireModel(3, 1);
    game3 = new EnglishSolitaireModel(5);
    game4 = new EnglishSolitaireModel(3, 3, 5);
    game5 = new EnglishSolitaireModel(7);
  }

  @Test
  public void testConstructorTwo() {
    try {
      EnglishSolitaireModel game6 = new EnglishSolitaireModel(-1, 2);
      fail("Empty cell (-1,2) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (-1,2) must be on board", e.getMessage());
    }
    try {
      EnglishSolitaireModel game7 = new EnglishSolitaireModel(0, 10);
      fail("Empty cell (0,10) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (0,10) must be on board", e.getMessage());
    }
    try {
      EnglishSolitaireModel game8 = new EnglishSolitaireModel(9, 2);
      fail("Empty cell (9,2) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (9,2) must be on board", e.getMessage());
    }
    try {
      EnglishSolitaireModel game9 = new EnglishSolitaireModel(5, -3);
      fail("Empty cell (5,-3) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (5,-3) must be on board", e.getMessage());
    }
    try {
      EnglishSolitaireModel game10 = new EnglishSolitaireModel(0, 1);
      fail("Invalid empty cell position (0,1)");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1)", e.getMessage());
    }
    try {
      EnglishSolitaireModel game11 = new EnglishSolitaireModel(5, 6);
      fail("Invalid empty cell position (5,6)");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,6)", e.getMessage());
    }
  }

  @Test
  public void testConstructorThree() {
    try {
      EnglishSolitaireModel game12 = new EnglishSolitaireModel(2);
      fail("Board size must be a positive odd number at least 3");
    } catch (IllegalArgumentException e) {
      assertEquals("Board size must be a positive odd number at least 3", e.getMessage());
    }
    try {
      EnglishSolitaireModel game13 = new EnglishSolitaireModel(-6);
      fail("Length cannot be negative or zero.");
    } catch (IllegalArgumentException e) {
      assertEquals("Length cannot be negative or zero.", e.getMessage());
    }
    try {
      EnglishSolitaireModel game14 = new EnglishSolitaireModel(10);
      fail("Board size must be a positive odd number at least 3");
    } catch (IllegalArgumentException e) {
      assertEquals("Board size must be a positive odd number at least 3", e.getMessage());
    }
  }

  @Test
  public void testConstructorFour() {
    try {
      EnglishSolitaireModel game15 = new EnglishSolitaireModel(2, 0, 1);
      fail("Board size must be a positive odd number at least 3");
    } catch (IllegalArgumentException e) {
      assertEquals("Board size must be a positive odd number at least 3", e.getMessage());
    }
    try {
      EnglishSolitaireModel game16 = new EnglishSolitaireModel(8, 5, 3);
      fail("Invalid empty cell position (5,3)");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,3)", e.getMessage());
    }
    try {
      EnglishSolitaireModel game16 = new EnglishSolitaireModel(-5, 5, 3);
      fail("Length cannot be negative or zero.");
    } catch (IllegalArgumentException e) {
      assertEquals("Length cannot be negative or zero.", e.getMessage());
    }
    try {
      EnglishSolitaireModel game17 = new EnglishSolitaireModel(5, -2, 4);
      fail("Empty cell (-2,4) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (-2,4) must be on board", e.getMessage());
    }
    try {
      EnglishSolitaireModel game18 = new EnglishSolitaireModel(7, 5, 32);
      fail("Empty cell (5,32) must be on board");
    } catch (IllegalArgumentException e) {
      assertEquals("Empty cell (5,32) must be on board", e.getMessage());
    }
    try {
      EnglishSolitaireModel game19 = new EnglishSolitaireModel(3, 6, 5);
      fail("Invalid empty cell position (6,5)");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,5)", e.getMessage());
    }
    try {
      EnglishSolitaireModel game20 = new EnglishSolitaireModel(5, 11, 11);
      fail("Invalid empty cell position (11,11)");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (11,11)", e.getMessage());
    }
  }

  @Test
  public void testInitializeBoard() {
    assertEquals(game1.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1.getSlotAt(0, 1), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1.getSlotAt(0, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1.getSlotAt(0, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1.getSlotAt(0, 5), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1.getSlotAt(1, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1.getSlotAt(2, 6), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game1.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1.getSlotAt(4, 6), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game1.getSlotAt(5, 1), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game1.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Invalid);

    assertEquals(game2.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game2.getSlotAt(5, 5), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game2.getSlotAt(1, 1), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game2.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game2.getSlotAt(2, 4), MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(game3.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game3.getSlotAt(2, 2), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game3.getSlotAt(11, 9), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game3.getSlotAt(12, 12), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game3.getSlotAt(3, 8), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game3.getSlotAt(11, 5), MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(game4.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game4.getSlotAt(1, 0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game4.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game4.getSlotAt(1, 5), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game4.getSlotAt(3, 6), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game4.getSlotAt(5, 4), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testMove() {
    game1.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(3, 2));
    game1.move(5, 2, 3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(4, 2));
    try {
      game1.move(1, 2, 3, 3);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    }
    catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game10.getSlotAt(1, 2));
    }
    try {
      game1.move(6, 2, 4, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(4, 2));
    }
    try {
      game1.move(2, 2, 5, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1.getSlotAt(2, 2));
    }
    try {
      game1.move(1, 2, 4, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(4, 2));
    }
    try {
      game1.move(3, 2, 4, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(4, 2));
    }
    try {
      game1.move(-1, -1, 4, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(4, 2));
    }
    try {
      game1.move(1, 4, 1, 6);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(4, 2));
    }
    try {
      game1.move(1, 4, 3, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1.getSlotAt(1, 4));
    }

    game2.move(3, 3, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game2.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2.getSlotAt(3, 2));
    try {
      game2.move(2, 5, 2, 3);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game2.getSlotAt(2, 3));
    }
    try {
      game2.move(3, 2, 1, 2);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2.getSlotAt(3, 2));
    }

    game3.move(4, 6, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game3.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game3.getSlotAt(4, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game3.getSlotAt(5, 6));
    try {
      game3.move(12, 5, 12, 3);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game3.getSlotAt(0, 5));
    }
    try {
      game3.move(1, 6, -1, 6);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game3.getSlotAt(1, 6));
    }
    try {
      game3.move(1, 6, -1, 6);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Marble, game3.getSlotAt(1, 6));
    }
    try {
      game3.move(3, 2, 3, 4);
      fail("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    } catch (IllegalArgumentException e) {
      assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game3.getSlotAt(3, 2));
    }
  }

  @Test
  public void testIsGameOver() {
    assertEquals(false, game1.isGameOver());
    game1.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1.getSlotAt(3, 3));
    game1.move(5, 2, 3, 2);
    game1.move(4, 0, 4, 2);
    game1.move(4, 3, 4, 1);
    game1.move(4, 5, 4, 3);
    game1.move(6, 4, 4, 4);
    game1.move(3, 4, 5, 4);
    game1.move(6, 2, 6, 4);
    game1.move(6, 4, 4, 4);
    game1.move(2, 2, 4, 2);
    game1.move(0, 2, 2, 2);
    game1.move(1, 4, 3, 4);
    game1.move(3, 4, 5, 4);
    game1.move(5, 4, 5, 2);
    game1.move(5, 2, 3, 2);
    game1.move(3, 2, 1, 2);
    game1.move(2, 0, 4, 0);
    game1.move(4, 0, 4, 2);
    game1.move(4, 2, 4, 4);
    game1.move(2, 6, 2, 4);
    game1.move(2, 3, 2, 5);
    game1.move(4, 6, 2, 6);
    game1.move(2, 6, 2, 4);
    game1.move(0, 4, 0, 2);
    game1.move(0, 2, 2, 2);
    game1.move(2, 1, 2, 3);
    game1.move(2, 3, 2, 5);
    game1.move(2, 5, 4, 5);
    game1.move(4, 5, 4, 3);
    game1.move(4, 3, 2, 3);
    game1.move(1, 3, 3, 3);
    assertEquals(true, game1.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, game1.getBoardSize());
    assertEquals(7, game2.getBoardSize());
    assertEquals(13, game3.getBoardSize());
    assertEquals(7, game4.getBoardSize());
    assertEquals(19, game5.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game2.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game3.getSlotAt(11, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game4.getSlotAt(6, 2));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game2.getSlotAt(5, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game3.getSlotAt(10, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game4.getSlotAt(1, 5));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game3.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game4.getSlotAt(3, 5));

    try {
      game1.getSlotAt(-1, 2);
      fail("Given row or column cannot be beyond board dimensions");
    } catch (IllegalArgumentException e) {
      assertEquals("Given row or column cannot be beyond board dimensions", e.getMessage());
    }
    try {
      game1.getSlotAt(8, 2);
      fail("Given row or column cannot be beyond board dimensions");
    } catch (IllegalArgumentException e) {
      assertEquals("Given row or column cannot be beyond board dimensions", e.getMessage());
    }
    try {
      game3.getSlotAt(3, -3);
      fail("Given row or column cannot be beyond board dimensions");
    } catch (IllegalArgumentException e) {
      assertEquals("Given row or column cannot be beyond board dimensions", e.getMessage());
    }
    try {
      game3.getSlotAt(3, 18);
      fail("Given row or column cannot be beyond board dimensions");
    } catch (IllegalArgumentException e) {
      assertEquals("Given row or column cannot be beyond board dimensions", e.getMessage());
    }
  }

  @Test
  public void testGetScore() {
    assertEquals(32, game1.getScore());
    game1.move(5, 3, 3, 3);
    assertEquals(31, game1.getScore());
    game1.move(4, 5, 4, 3);
    assertEquals(30, game1.getScore());

    assertEquals(104, game3.getScore());
    game3.move(4, 6, 6, 6);
    assertEquals(103, game3.getScore());
    game3.move(4, 4, 4, 6);
    assertEquals(102, game3.getScore());
  }

  @Test
  public void testIsInvalidSlot()  {
    assertTrue(game1.isInvalidSlot(1, 1));
    assertTrue(game1.isInvalidSlot(5, 5));
    assertTrue(game1.isInvalidSlot(6, 5));
    assertFalse(game1.isInvalidSlot(3, 3));
    assertFalse(game1.isInvalidSlot(4, 2));

    assertFalse(game2.isInvalidSlot(3, 1));
    assertFalse(game2.isInvalidSlot(5, 2));
    assertTrue(game2.isInvalidSlot(6, 6));
    assertTrue(game2.isInvalidSlot(5, 0));

    assertFalse(game3.isInvalidSlot(11, 7));
    assertFalse(game3.isInvalidSlot(7, 2));
    assertTrue(game3.isInvalidSlot(12, 9));
    assertTrue(game3.isInvalidSlot(3, 3));
  }
}