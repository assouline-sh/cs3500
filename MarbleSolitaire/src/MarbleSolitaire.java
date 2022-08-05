package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Class to run the EnglishSolitaireProgram in the console.
 */
public final class MarbleSolitaire {
  /**
   * Run any version of the Marble Solitaire game.
   */
  public static void main(String[] args) {
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    int size = 0;
    int row = 0;
    int col = 0;
    boolean givenSize = false;
    boolean givenHole = false;

    int i = 1;
    while (i < args.length) {
      if (args[i].equalsIgnoreCase("-size")) {
        size = Integer.parseInt(args[i + 1]);
        i += 2;
        givenSize = true;
      } else if (args[i].equalsIgnoreCase("-hole")) {
        row = Integer.parseInt(args[i + 1]);
        col = Integer.parseInt(args[i + 2]);
        i += 3;
        givenHole = true;
      }
      else {
        i++;
      }
    }

    switch (args[0]) {
      case "english":
        MarbleSolitaireModel modelEN = null;
        if (!givenSize && !givenHole) {
          modelEN = new EnglishSolitaireModel();
        }
        else if (givenSize && !givenHole) {
          modelEN = new EnglishSolitaireModel(size);
        }
        else if (!givenSize && givenHole) {
          modelEN = new EnglishSolitaireModel(row, col);
        }
        else if (givenSize && givenHole) {
          modelEN = new EnglishSolitaireModel(size, row, col);
        }
        MarbleSolitaireTextView viewEN = new MarbleSolitaireTextView(modelEN);
        MarbleSolitaireControllerImpl controllerEN =
                new MarbleSolitaireControllerImpl(modelEN, viewEN, rd);
        controllerEN.playGame();
        break;
      case "european":
        MarbleSolitaireModel modelEU = null;
        if (!givenSize && !givenHole) {
          modelEU = new EuropeanSolitaireModel();
        }
        else if (givenSize && !givenHole) {
          modelEU = new EuropeanSolitaireModel(size);
        }
        else if (!givenSize && givenHole) {
          modelEU = new EuropeanSolitaireModel(row, col);
        }
        else if (givenSize && givenHole) {
          modelEU = new EuropeanSolitaireModel(size, row, col);
        }
        MarbleSolitaireTextView viewEU = new MarbleSolitaireTextView(modelEU);
        MarbleSolitaireControllerImpl controllerEU =
                new MarbleSolitaireControllerImpl(modelEU, viewEU, rd);
        controllerEU.playGame();
        break;
      case "triangle" :
        MarbleSolitaireModel modelTri = null;
        if (!givenSize && !givenHole) {
          modelTri = new TriangleSolitaireModel();
        }
        else if (givenSize && !givenHole) {
          modelTri = new TriangleSolitaireModel(size);
        }
        else if (!givenSize && givenHole) {
          modelTri = new TriangleSolitaireModel(row, col);
        }
        else if (givenSize && givenHole) {
          modelTri = new TriangleSolitaireModel(size, row, col);
        }
        MarbleSolitaireView viewTri = new TriangleSolitaireTextView(modelTri, ap);
        MarbleSolitaireControllerImpl controllerTri =
                new MarbleSolitaireControllerImpl(modelTri, viewTri, rd);
        controllerTri.playGame();
        break;
      default:
        throw new RuntimeException("Cannot start game");
    }
  }
}
