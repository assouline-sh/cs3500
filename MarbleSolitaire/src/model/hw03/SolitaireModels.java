package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstract class to represent the different models for variations of the Marble Solitaire game.
 */
public abstract class SolitaireModels implements MarbleSolitaireModel {
  protected int length;
  protected int sRow;
  protected int sCol;
  protected SlotState[][] board;

  /**
   * Create a new instance of a SolitaireModel.
   */
  public SolitaireModels(int length, int sRow, int sCol, int boardSize)
          throws IllegalArgumentException {
    if (length <= 0) {
      throw new IllegalArgumentException("Length cannot be negative or zero.");
    }
    this.length = length;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = new SlotState[boardSize][boardSize];
    this.initializeBoard();

    if (sRow < 0 || sRow > this.getBoardSize() || sCol < 0 || sCol > this.getBoardSize()) {
      throw new IllegalArgumentException("Empty cell (" + sRow + "," + sCol + ") must be on board");
    }

    if (this.getSlotAt(sRow, sCol).equals(SlotState.Invalid)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
  }

  /**
   * Initialize the game board with the correct values of which slots should be invalid, a marble,
   * or empty.
   */
  private void initializeBoard() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.isInvalidSlot(i, j)) {
          this.board[i][j] = SlotState.Invalid;
        } else if (i == this.sRow && j == this.sCol) {
          this.board[i][j] = SlotState.Empty;
        } else {
          this.board[i][j] = SlotState.Marble;
        }
      }
    }
  }

  /**
   * Determine if the slot at the given row and column is an invalid slot for the game.
   * @param row row of the slot that we are testing for if it is invalid
   * @param col col of the slot that we are testing for if it is invalid
   * @return true if the slot should be invalid, false if not
   */
  public abstract boolean isInvalidSlot(int row, int col);


  /**
   * Moves a single marble from the given position to the given position. Consequently, it removes
   * a marble from the originating position, puts a marble at the designated destination, and
   * removes the marble that is in between these two marbles
   *
   * @param fromRow the starting position's row
   * @param fromCol the starting position's column
   * @param toRow   the ending position's row
   * @param toCol   the ending position's row
   * @throws IllegalArgumentException throws an error: if any of the parameters given are off the
   *                                  board (below than 0 or greater than the board size); if the
   *                                  originating position does not contain a marble; if the
   *                                  designated destination is not empty; if the slot that is
   *                                  jumped over by the marble does not have a marble; if the
   *                                  originating position and designated destination are not two
   *                                  slots away (up, down, left, or right, not diagonally)
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (validMove(fromRow, fromCol, toRow, toCol)) {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
      this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
    } else {
      throw new IllegalArgumentException("Must move a valid Marble to an empty slot on the board "
              + "that jumps over one valid Marble.");
    }
  }

  /**
   * Determine if there is a valid move from the given position.
   *
   * @param fromRow the row of the starting position
   * @param fromCol the column of the starting position
   * @param toRow   the row of the destination position
   * @param toCol   the column of the destination position
   * @return boolean true if there is a valid move from the given position, false if not
   */
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (toRow > 0 || toRow < this.getBoardSize() || toCol > 0 || toCol < this.getBoardSize()) {
      if (this.getSlotAt(fromRow, fromCol).equals(SlotState.Marble)) {
        if (this.getSlotAt(toRow, toCol).equals(SlotState.Empty)) {
          if (Math.abs(fromRow - toRow) == 2 || Math.abs(fromCol - toCol) == 2) {
            if (this.getSlotAt((fromRow + toRow) / 2,
                    (fromCol + toCol) / 2).equals(SlotState.Marble)) {
              if (fromRow == toRow || fromCol == toCol) {
                return true;
              }
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
          if ((i - 2 >= 0 && this.validMove(i, j, i - 2, j))
                  || (i + 2 < this.getBoardSize() && this.validMove(i, j, i + 2, j))
                  || (j - 2 >= 0 && this.validMove(i, j, i, j - 2))
                  || (j + 2 < this.getBoardSize() && this.validMove(i, j, i, j + 2))) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return (this.length * 3) - 2;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row > this.getBoardSize() - 1
            || col < 0 || col > this.getBoardSize() - 1) {
      throw new IllegalArgumentException("Given row or column cannot be beyond board dimensions");
    } else {
      return this.board[row][col];
    }
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j).equals(SlotState.Marble)) {
          score++;
        }
      }
    }
    return score;
  }
}
