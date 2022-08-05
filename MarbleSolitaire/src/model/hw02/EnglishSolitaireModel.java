package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.SolitaireModels;

/**
 * This class represents the English Model of the Marble Solitaire game. It specifies which
 * slots should be empty, invalid, or have a marble to correctly render and play the game.
 * Refactored this class to extend SolitaireModels instead of implementing MarbleSolitaireModel
 * directly in order to abstract repeated code in Model classes.
 */
public class EnglishSolitaireModel extends SolitaireModels {

  /**
   * Instantiate the game to its default settings.
   * Refactored constructor to call super constructor of SolitaireModels.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3, 7);
  }

  /**
   * Instantiate the game with the empty slot at the given row and column location.
   * Refactored constructor to call super constructor of SolitaireModels.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol, 7);
  }

  /**
   * Instantiate the game with the given board dimensions.
   * Refactored constructor to call super constructor of SolitaireModels.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, ((armThickness - 3) + (armThickness * 2)) / 2,
            ((armThickness - 3) + (armThickness * 2)) / 2,
            (armThickness - 2) + (armThickness * 2));
    if (armThickness % 2 == 0 || armThickness < 3) {
      throw new IllegalArgumentException("Board size must be a positive odd number at least 3");
    }
  }

  /**
   * Instantiate the game with the empty slot at the given row and column location, and with the
   * given board dimensions.
   * Refactored constructor to call super constructor of SolitaireModels.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol, (armThickness - 2) + (armThickness * 2));
    if (armThickness % 2 == 0 || armThickness < 3) {
      throw new IllegalArgumentException("Board size must be a positive odd number at least 3");
    }
  }

  /*
   * All other methods that were previously in this class (initializeBoard(), move(int, int, int,
   * int), validMove(int, int, int, int), isGameOver(), getBoardSize(), getSlotAt(int, int),
   * getScore()) have been moved to the abstract class SolitaireModels, since EuropeanSolitaireModel
   * would have an identical implementation of these methods
   */

  /**
   * Added a method isInvalidSlot to determine if a slot at the given row and column should be
   * invalid. This method was added so that the initializeBoard() method could be abstracted in
   * SolitaireModels. This allows there to be only one initializeBoard() method in SolitaireModels
   * which uses this helper method (that each class that extends SolitaireModels has) to determine
   * which slots should be Invalid.
   * @param row row of the slot that we are testing for if it is invalid
   * @param col col of the slot that we are testing for if it is invalid
   * @return true if the slot should be invalid, false if not
   */
  @Override
  public boolean isInvalidSlot(int row, int col) {
    return ((row <= length - 2 || row >= this.getBoardSize() - (length - 1))
            && (col <= length - 2
            || col >= this.getBoardSize() - (length - 1)));
  }
}
