package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TextViews;

/**
 * A class to represent the view of a Triangle Marble Solitaire game.
 */
public class TriangleSolitaireTextView extends TextViews {

  /**
   * Instantiate the view with the given game board as input and print in System.out.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState game) {
    super(game);
  }

  /**
   * Instantiate the view with the given game board as input and append the results to
   * the Appendable object.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState game, Appendable appendable) {
    super(game, appendable);
  }

  @Override
  public String toString() {
    String renderedBoard = "";
    for (int i = 0; i < this.game.getBoardSize(); i++) {
      int spaces = i;
      while (spaces < this.game.getBoardSize() - 1) {
        renderedBoard += " ";
        spaces++;
      }
      for (int j = 0; j <= i; j++) {
        if (j == 0) {
          renderedBoard += this.drawCell(this.game.getSlotAt(i, j));
        } else {
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
