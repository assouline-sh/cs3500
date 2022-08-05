package cs3500.marblesolitaire.model.hw02;

import java.io.InputStreamReader;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Class to run the EnglishSolitaireProgram in the console.
 */
public class EnglishSolitaireProgram {
  /**
   * Run the EnglishSolitaireProgram to play.
   */
  public static void main(String[] args) {
    Appendable ap = System.out;
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, ap);
    Readable rd = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
  }
}
