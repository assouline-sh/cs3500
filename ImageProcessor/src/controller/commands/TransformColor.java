package controller.commands;

import java.util.ArrayList;

import model.IModel;

/**
 * Class that parses the given arguments to transform the colors of an image, either apply luma
 * or a sepia filter, and call the correct method to apply the filter to the image.
 */
public class TransformColor implements Commands {
  private final String type;
  private final String version;
  private final String name;

  /**
   * Instantiate a new TransformColor class and correctly parse its arguments to get the type of
   * filter, luma or sepia, the file from the given file path and load it under the given name.
   * @param args the type of filter to apply, the file path to get the image from, and the new file
   *             name to save the image to
   */
  public TransformColor(ArrayList<String> args) {
    this.type = args.get(0).toLowerCase();
    this.version = args.get(1);
    this.name = args.get(2);
  }

  @Override
  public void performCommand(IModel m) {
    m.transformColor(type, version, name);
  }
}
