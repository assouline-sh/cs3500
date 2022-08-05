package cs3500.marblesolitaire.model.hw04;

import java.io.IOException;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Abstract class to represent the different models for variations of the Marble Solitaire game.
 */
public abstract class TextViews implements MarbleSolitaireView {
  protected MarbleSolitaireModelState game;
  protected Appendable appendable;

  /**
   * Instantiate the view with the given game board as input and print in System.out.
   */
  public TextViews(MarbleSolitaireModelState game) throws IllegalArgumentException {
    if (game == null) {
      throw new IllegalArgumentException("Provided model is null.");
    }
    this.game = game;
    this.appendable = System.out;
  }

  /**
   * Instantiate the view with the given game board as input and append the results to
   * the Appendable object.
   */
  public TextViews(MarbleSolitaireModelState game, Appendable appendable)
          throws IllegalArgumentException {
    if ((game == null) || (appendable == null)) {
      throw new IllegalArgumentException("Provided model or Appendable object is null.");
    }
    this.game = game;
    this.appendable = appendable;
  }

  /**
   * Render a single cell on the game board depending on its state.
   *
   * @param ss the given SlotState of the current cell
   * @return String the corresponding String representation of the current state
   * @throws RuntimeException if the SlotState cannot be resolved to a string
   */
  protected String drawCell(MarbleSolitaireModelState.SlotState ss) {
    switch (ss) {
      case Invalid:
        return " ";
      case Empty:
        return "_";
      case Marble:
        return "O";
      default:
        throw new RuntimeException("SlotState can't be resolved");
    }
  }

  @Override
  public void renderBoard() throws IOException {
    this.appendable.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}
