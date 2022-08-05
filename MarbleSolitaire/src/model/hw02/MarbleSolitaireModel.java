package cs3500.marblesolitaire.model.hw02;

/**
 * This interface represents the operations offered by the marble solitaire
 * model. One object of the model represents one game of marble solitaire
 */
public interface MarbleSolitaireModel extends MarbleSolitaireModelState {

  /**
   * Determines if a slot at the given row and column should be invalid based on the board's
   * constraints.
   * @param row row of the slot that we are testing for if it is invalid
   * @param col col of the slot that we are testing for if it is invalid
   * @return true if the slot should be invalid, false if not
   */
  boolean isInvalidSlot(int row, int col);

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  void move(int fromRow, int fromCol, int toRow, int toCol) throws
          IllegalArgumentException;

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  boolean isGameOver();
}