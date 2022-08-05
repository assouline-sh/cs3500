package cs3500.marblesolitaire.model.hw04;

/**
 * A class to represent a Triangle Solitaire model of the game.
 */
public class TriangleSolitaireModel extends SolitaireModels {
  /**
   * Instantiate the model of a new Triangle Marble Solitaire game with default parameters.
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0, 5);
  }

  /**
   * Instantiate a model of a new Triangle Marble Solitaire game with the given dimensions.
   * and the empty slot at the center of the board, as default.
   * @param dimensions the size of the board; number of slots in bottom-most row
   * @throws IllegalArgumentException if given dimension is not a valid positive number
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    super(dimensions, 0, 0, dimensions);
  }

  /**
   * Instantiate a model of a new Triangle Marble Solitaire game with the starting empty slot set
   * to the given row and column, and the board of size 5, as default.
   * @param sRow initial row position of empty slot.
   * @param sCol initial column position of empty slot.
   * @throws IllegalArgumentException if given initial empty slot position is invalid
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(5, sRow, sCol, 5);
  }

  /**
   * Instantiate a model of a new Triangle Marble Solitaire game with the starting empty slot set
   * to the given row and column, and the given board size.
   * @param dimensions the size of the board; number of slots in the bottom-most row
   * @param sRow initial row position of empty slot.
   * @param sCol initial column position of empty slot.
   * @throws IllegalArgumentException if given initial empty slot position is invalid
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol)
          throws IllegalArgumentException {
    super(dimensions, sRow, sCol, dimensions);
  }

  @Override
  public boolean isInvalidSlot(int row, int col) {
    return (col > row);
  }

  @Override
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (toRow >= 0 && toRow < this.getBoardSize() && toCol >= 0 && toCol < this.getBoardSize()) {
      if (this.getSlotAt(fromRow, fromCol).equals(SlotState.Marble)) {
        if (this.getSlotAt(toRow, toCol).equals(SlotState.Empty)) {
          if (this.getSlotAt((fromRow + toRow) / 2,
                  (fromCol + toCol) / 2).equals(SlotState.Marble)) {
            if ((fromRow == toRow && (Math.abs(fromCol - toCol) == 2))
                    || (fromRow - 2 == toRow && (fromCol - 2 == toCol || fromCol == toCol))
                    || (fromRow + 2 == toRow && (fromCol + 2 == toCol || fromCol == toCol))) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j).equals(SlotState.Marble)) {
          if (this.validMove(i, j, i, j - 2)
                  || this.validMove(i, j, i, j + 2)
                  || this.validMove(i, j, i - 2, j - 2)
                  || this.validMove(i, j, i - 2, j)
                  || this.validMove(i, j, i + 2, j)
                  || this.validMove(i, j, i + 2, j + 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return this.length;
  }
}
