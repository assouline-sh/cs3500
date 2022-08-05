package controller.commands;

import java.util.ArrayList;
import model.IModel;

/**
 * Class that parses the given arguments to greyscale an image by the given component, and calls
 * the correct method to greyscale the image. An image can be greyscaled by the red, green, or blue
 * component, or by value, luma, or intensity.
 */
public class Greyscale implements Commands {
  private final String color;
  private final String version;
  private final String name;

  /**
   * Instantiate a new Greyscale class and parse the given arguments to correctly greyscale
   * the image.
   * @param args the component to greyscale the image to, the name of the image to greyscale,
   *             and the name of the new greyscaled image
   */
  public Greyscale(ArrayList<String> args) {
    this.color = args.get(0).substring(0, args.get(0).indexOf("-")).toLowerCase();
    this.version = args.get(1);
    this.name = args.get(2);
  }

  /**
   * Greyscale the given image with the arguments passed in by the user.
   * @param m the image we want to apply the command on
   */
  @Override
  public void performCommand(IModel m) {
    if (this.color.equals("luma")) {
      m.transformColor(color, version, name);
    }
    else {
      m.greyscale(color, version, name);
    }
  }
}
