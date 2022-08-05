package controller.commands;

import java.util.ArrayList;
import model.IModel;

/**
 * Class to load an image from its file path and load it as the given version name.
 */
public class Load implements Commands {
  private final String filePath;
  private final String version;

  /**
   * Instantiate a new Load class and parse the given arguments to correctly get the file from
   * the given file path and load it under the given name.
   * @param args the name of the file path of the image and the new name the image will be
   *             loaded under
   */
  public Load(ArrayList<String> args) {
    this.filePath = args.get(1);
    this.version = args.get(2);
  }

  /**
   * Load the given image with the arguments passed in by the user.
   * @param m the image we want to apply the command on
   */
  @Override
  public void performCommand(IModel m) {
    m.load(filePath, version);
  }
}

