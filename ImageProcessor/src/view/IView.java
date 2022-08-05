package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

import controller.commands.Commands;

/**
 * This interface promises that subclasses will support rendering a message to a user, as well
 * as showing a user the commands available to them.
 */
public interface IView {

  /**
   * Render the given message for the user.
   * @param s the message to render
   * @throws IOException if transmission of the message to the data destination fails
   */
  void renderMessage(String s) throws IOException;

  /**
   * Render the commands available; these are the possibilities for manipulations done to an image.
   * @param knownCommands the commands that are supported for manipulating an image
   * @throws IOException if transmission of the commands to the data destination fails
   */
  void showCommands(Map<String, Function<ArrayList<String>, Commands>> knownCommands)
          throws IOException;

  /**
   * Render the previous versions of an image.
   * @throws IOException if transmission of the commands to the data destination fails
   */
  void showVersions() throws IOException;
}