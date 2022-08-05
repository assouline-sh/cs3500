package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TextViews;

/**
 * This class renders the Marble Solitaire game for viewing.
 * Refactored class to extend TextViews instead of implementing MarbleSolitaireView directly in
 * order to abstract repeated code in TriangleSolitaireTextView.
 */
public class MarbleSolitaireTextView extends TextViews {

  /**
   * Instantiate the view with the given game board as input and print in System.out.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    super(game);
  }

  /**
   * Instantiate the view with the given game board as input and append the results to
   * the Appendable object.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game, Appendable appendable)
          throws IllegalArgumentException {
    super(game, appendable);
  }

  /**
   * Refactored code to remove drawCell(SlotState), renderBoard(), and renderMessage(String)
   * from this class because it is repeated code in TriangleSolitaireTextView as well,
   * so code for this method was moved to the abstract class to avoid repetition.
   * TextViews
   */

  @Override
  public String toString() {
    String renderedBoard = "";
    for (int i = 0; i < this.game.getBoardSize(); i++) {
      for (int j = 0; j < this.game.getBoardSize(); j++) {
        if (j == 0) {
          renderedBoard += this.drawCell(this.game.getSlotAt(i, j));
        } else if (j > this.game.getBoardSize() / 2
                  && this.game.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          break;
        }
        else {
          renderedBoard += " " + this.drawCell(this.game.getSlotAt(i, j));
        }
      }
      if (i == this.game.getBoardSize() - 1) {
        return renderedBoard;
      } else {
        renderedBoard += "\n";
      }
    }
    return renderedBoard;
  }
}
