package cs3500.marblesolitaire.model.hw02;

/**
 * A mock MarbleSolitaireModel to test that methods are appropriately called with the
 * correct parameters.
 */
public class MockModel implements MarbleSolitaireModel {
  private final StringBuilder log;

  /**
   * Instantiate the mock controller with the given output.
   *
   * @param output the output of methods called
   */
  public MockModel(StringBuilder output) {
    this.log = output;
  }

  public int getBoardSize() {
    log.append("getBoardSize() called\n");
    return 0;
  }

  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    log.append("row, col:" + row + col + "\n");
    return SlotState.Invalid;
  }

  public int getScore() {
    log.append("getScore() called\n");
    return 0;
  }

  public boolean isInvalidSlot(int row, int col) {
    log.append(" isInvalidSlot: row: " + row + " col: " + col);
    return false;
  }

  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(" MOVE: fromRow: " + fromRow + " fromCol: " + fromCol
            + " toRow: " + toRow + " toCol: " + toCol + "\n");
  }

  public boolean isGameOver() {
    log.append("isGameOver() called\n");
    return false;
  }
}
