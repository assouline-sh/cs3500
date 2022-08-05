package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;


import controller.commands.Commands;
import model.IModel;
import view.IView;

/**
 * This class is the controller for manipulating images program. This controller works with a
 * Readable to read inputs from the user.
 */
public class ControllerImpl implements IController {
  private final IModel model;
  private final IView view;
  private final Scanner sc;

  /**
   * Instantiate a new controller with the given model, view, and input from user.
   * @param model current image model
   * @param view current view of the image
   * @param readable readable input from the user
   * @throws IllegalArgumentException if any of the model, view, or readable object are null
   */
  public ControllerImpl(IModel model, IView view, Readable readable)
          throws IllegalArgumentException {
    if (view == null || model == null || readable == null) {
      throw new IllegalArgumentException("Null Parameters");
    }
    this.model = model;
    this.view = view;
    sc = new Scanner(readable);
  }


  @Override
  public void performCommand() {

    ArrayList<String> args = new ArrayList<>();

    while (sc.hasNext()) {
      args.add((sc.next()));
    }

    Commands c;
    String in = args.get(0);

    //abstracts the flips and components
    if (args.get(0).contains("-")) {
      in = in.substring(args.get(0).indexOf("-") + 1);
    }

    Function<ArrayList<String>, Commands> cmd =
            knownCommands.getOrDefault(in, null);
    if (cmd == null) {
      try {
        view.renderMessage("Not a command\n");
        view.showCommands(knownCommands);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      c = cmd.apply(args);
      c.performCommand(model);

      try {
        view.showVersions();
        view.showCommands(knownCommands);
      } catch (IOException e) {
        throw new IllegalArgumentException("IOException when rendering messages");
      }
    }
  }
}