package controller.commands;

import java.util.ArrayList;
import model.IModel;

/**
 * Class that parses the given arguments to brighten or darken an image, and calls the correct
 * method to brighten (if given a positive factor) or darken (if given a negative factor)
 * the given image.
 */
public class BrightenOrDarken implements Commands {
  private final int factor;
  private final String version;
  private final String name;

  /**
   * Instantiate a new BrightenOrDarken class and parse the given arguments to correctly brighten
   * or darken an image.
   * @param args the factor to brighten or darken the image by, the image to apply brightening
   *             or darkening to, and the name of the new brightened or darkened image
   */
  public BrightenOrDarken(ArrayList<String> args) {
    try {
      this.factor = Integer.parseInt(args.get(1));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("not a number");
    }
    this.version = args.get(2);
    this.name = args.get(3);
  }

  /**
   * Brighten or darken the given image with the arguments passed in by the user.
   * @param m the image we want to apply the command on
   */
  @Override
  public void performCommand(IModel m) {
    m.brightenOrDarken(factor, version, name);
  }
}
