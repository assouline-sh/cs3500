package cs3500.marblesolitaire.view;

import java.io.IOException;

/**
 * Class to create a mock view of the game to verify IOExceptions were thrown correctly.
 */
public class MockView implements MarbleSolitaireView {
  final StringBuilder log;

  /**
   * Instantiate a new mock view with a log.
   *
   * @param output the output of methods called
   */
  public MockView(StringBuilder output) {
    this.log = output;
  }

  @Override
  public void renderBoard() throws IOException {
    throw new IOException("from renderBoard()");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    throw new IOException("from renderMessage()");
  }
}
