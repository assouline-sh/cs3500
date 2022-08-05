package controller.commands;

import model.IModel;

/**
 * Interface to take the model object and execute a set of operations on the model. All
 * transformations that can be done to an image are unified under this single interface that
 * has a method of the same signature. This represents a high-level command.
 */
public interface Commands {

  /**
   * Call the appropriate method that we want to apply to the given image.
   * @param m the image we want to apply the command on
   */
  void performCommand(IModel m);
}
