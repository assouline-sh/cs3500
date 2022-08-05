package cs3500.marblesolitaire.controller;

/**
 * This interface represents the controller for the marble solitaire game. The controller
 * takes input from the user and decides what to do next with it.
 */
public interface MarbleSolitaireController {
  /**
   * Play a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if the controller is unable to successfully
   *                               read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
