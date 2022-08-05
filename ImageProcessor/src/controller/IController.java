package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import controller.commands.BrightenOrDarken;
import controller.commands.Commands;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.Greyscale;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.TransformColor;


/**
 * This interface represents the controller for the image manipulation. The controller
 * takes input from the user and decides what to do next with it. It also initializes
 * all the commands available.
 */
public interface IController {

  Map<String, Function<ArrayList<String>, Commands>> knownCommands = new HashMap<>();

  /**
   * Initialize the list of commands available to the user.
   */
  private static void initCommands() {
    knownCommands.put("load", args -> new Load(args));
    knownCommands.put("save", args -> new Save(args));
    knownCommands.put("flip", args -> new Flip(args));
    knownCommands.put("component", args -> new Greyscale(args));
    knownCommands.put("brighten", args -> new BrightenOrDarken(args));
    knownCommands.put("blur", args -> new Filter(args));
    knownCommands.put("sharpen", args -> new Filter(args));
    knownCommands.put("sepia", args -> new TransformColor(args));
    knownCommands.put("luma", args -> new TransformColor(args));
  }

  /**
   * Run the image manipulation program and perform the appropriate command.
   */
  void performCommand();

  /**
   * Get the list of commands the user can use.
   *
   * @return the list of commands available
   */
  static Map<String, Function<ArrayList<String>, Commands>> getCommandList() {
    IController.initCommands();
    return new HashMap<>(knownCommands);
  }

}
