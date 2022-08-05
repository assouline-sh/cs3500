package controller.commands;

import java.util.ArrayList;

import model.IModel;

/**
 * Class that parses the given arguments to filter an image, either blur or sharpen it, and calls
 * the correct method to blur or sharpen the image.
 */
public class Filter implements Commands {
  private final String type;
  private final String version;
  private final String name;

  /**
   * Instantiate a new Filter class and correctly parse its arguments to get the type of filter,
   * blur or sharpen, the file from the given file path and load it under the given name.
   * @param args the type of filter to apply, the file path to get the image from and the new file
   *             name to save the image to
   */
  public Filter(ArrayList<String> args) {
    this.type = args.get(0).toLowerCase();
    this.version = args.get(1);
    this.name = args.get(2);
  }

  @Override
  public void performCommand(IModel m) {
    m.filter(type, version, name);
  }
}
