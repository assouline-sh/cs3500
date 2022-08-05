package cs3500.marblesolitaire.model.hw04;

/**
 * A class to represent a European Solitaire model of the game.
 */
public class EuropeanSolitaireModel extends SolitaireModels {
  /**
   * Instantiate the model of a new European Marble Solitaire game with default parameters.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3, 7);
  }

  /**
   * Instantiate a model of a new European Marble Solitaire game with the given side length
   * and the empty slot at the center of the board, as default.
   * @param sideLength the length of the side of the board
   * @throws IllegalArgumentException if given side length is not a valid positive number
   */
  public EuropeanSolitaireModel(int sideLength) throws IllegalArgumentException {
    super(sideLength, ((sideLength - 3) + (sideLength * 2)) / 2,
            ((sideLength - 3) + (sideLength * 2)) / 2,
            (sideLength - 2) + (sideLength * 2));
    if (sideLength % 2 == 0 || sideLength < 3) {
      throw new IllegalArgumentException("Board size must be a positive odd number at least 3");
    }
  }

  /**
   * Instantiate a model of a new European Marble Solitaire game with the starting empty slot set
   * to the given row and column, and the board of size 3, as default.
   * @param sRow initial row position of empty slot.
   * @param sCol initial column position of empty slot.
   * @throws IllegalArgumentException if given initial empty slot position is invalid
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(3, sRow, sCol, 7);
  }

  /**
   * Instantiate a model of a new European Marble Solitaire game with the starting empty slot set.
   * to the given row and column, and the given board size
   * @param sideLength the length of the side of the board
   * @param sRow initial row position of empty slot.
   * @param sCol initial column position of empty slot.
   * @throws IllegalArgumentException if given initial empty slot position is invalid
   */
  public EuropeanSolitaireModel(int sideLength, int sRow, int sCol)
          throws IllegalArgumentException {
    super(sideLength, sRow, sCol, (sideLength - 2) + (sideLength * 2));
    if (sideLength % 2 == 0 || sideLength < 3) {
      throw new IllegalArgumentException("Board size must be a positive odd number at least 3");
    }
  }

  @Override
  public boolean isInvalidSlot(int row, int col) {
    return ((row <= this.length - 2 && col <= this.length - (row + 2))
            || (row <= this.length - 2 && col >= ((this.length * 2) - 1) + row)
            || (row >= this.getBoardSize() - this.length - 1
            && col <= row - (this.length * 2 - 1))
            || (row >= this.getBoardSize() - this.length - 1
            && col >= this.getBoardSize() - 1 - (row - ((this.length * 2) - 1))));
  }
}

