package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class is the controller for the Marble Solitaire game. This controller works with a
 * Readable to read its inputs and an Appendable to transmit output.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable input;

  /**
   * Instantiate a controller with the current model, view, and a Readable object from which
   * to read input.
   *
   * @param model the current model of the game
   * @param view  the current view of the game
   * @param input a readable input object
   * @throws IllegalArgumentException if the model, view, or Readable object are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if ((model == null) || (view == null) || (input == null)) {
      throw new IllegalArgumentException("Model, view, and input cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  /**
   * Play a game with the given model. Render the game board and the score while the game is in
   * play. If 'q' or 'Q' is pressed, quit the game, and if any other input is not a 'q', 'Q', or
   * a positive number, tell player to re-enter value. If all inputs are valid, make the move on
   * the board, else ask the player to input a valid move. When the game is no longer playable,
   * end it.
   *
   * @throws IllegalStateException whenever the controller cannot successfully read the input
   *                               or transmit the output
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner sc = new Scanner(this.input);
    boolean gameQuit = false;

    while (!model.isGameOver()) {
      getGameAndScore();

      ArrayList<Integer> values = new ArrayList<Integer>();
      while (values.size() != 4) {
        String next;
        try {
          next = sc.next();
        } catch (NoSuchElementException e) {
          try {
            next = sc.nextLine();
          } catch (NoSuchElementException ee) {
            throw new IllegalStateException("Not enough inputs given.");
          }
          throw new IllegalStateException("Not enough inputs given.");
        }

        try {
          if (next.equalsIgnoreCase("q")) {
            this.quitGame();
            gameQuit = true;
            break;
          } else if (Integer.parseInt(next) > 0) {
            values.add(Integer.parseInt(next) - 1);
            continue;
          }
        } catch (NumberFormatException ne) {
          try {
            view.renderMessage("An unexpected value was encountered.\n");
          } catch (IOException e) {
            throw new IllegalStateException("Cannot read input or transmit output.");
          }
        }
      }


      if (!gameQuit) {
        try {
          model.move(values.get(0), values.get(1), values.get(2), values.get(3));
        } catch (IllegalArgumentException e) {
          try {
            view.renderMessage("Invalid move. Play again. Move must follow the"
                    + " rules of the game and be on the board.\n");
          } catch (IOException ee) {
            throw new IllegalStateException("Cannot read input or transmit output.");
          }
        }
      } else {
        break;
      }
    }

    if (model.isGameOver()) {
      this.endGame();
    }
  }

  /**
   * Render the current state of the game and the current score.
   *
   * @throws IllegalStateException if the controller cannot transmit the game and score
   */
  private void getGameAndScore() throws IllegalStateException {
    try {
      view.renderBoard();
      view.renderMessage("\n");
      view.renderMessage("Score: " + model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Error in transmitting final game state.");
    }
  }

  /**
   * Render the end game message and ending state of the game and the ending score.
   *
   * @throws IllegalStateException if the controller cannot transmit the message, game, or score
   */
  private void endGame() throws IllegalStateException {
    try {
      view.renderMessage("Game over!\n");
      this.getGameAndScore();
    } catch (IOException e) {
      throw new IllegalStateException("Error in transmitting final game state.");
    }
  }

  /**
   * Render the quit game message and quit state of the game and the quit score.
   *
   * @throws IllegalStateException if the controller cannot transmit the messages, game, or score
   */
  private void quitGame() throws IllegalStateException {
    try {
      view.renderMessage("Game quit!\n");
      view.renderMessage("State of game when quit:\n");
      this.getGameAndScore();
    } catch (IOException e) {
      throw new IllegalStateException("Error in transmitting quit game state.");
    }
  }
}