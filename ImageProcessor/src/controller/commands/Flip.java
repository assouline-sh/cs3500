package controller.commands;

import java.util.ArrayList;

import model.IModel;

/**
 * Class that parses the given arguments to vertical flip an image, and calls the correct
 * method to vertical flip the given image.
 */
public class Flip implements Commands {
  private final String version;
  private final String name;
  private final String direction;

  /**
   * Instantiate a new VerticalFlip class and parse the given arguments to correctly vertical
   * flip an image.
   *
   * @param args the name of the image to apply a vertical flip to, and the name of the new
   *             vertically flipped image
   */
  public Flip(ArrayList<String> args) {
    this.version = args.get(1);
    this.name = args.get(2);
    this.direction = args.get(0).substring(0, args.get(0).indexOf("-")).toLowerCase();
  }

  /**
   * Flip the given image vertically with the arguments passed in by the user.
   *
   * @param m the image we want to apply the command on
   */
  @Override
  public void performCommand(IModel m) {
    m.flip(direction, version, name);
  }
}
