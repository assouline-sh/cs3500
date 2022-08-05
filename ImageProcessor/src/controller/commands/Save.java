package controller.commands;

import java.util.ArrayList;
import model.IModel;

/**
 * Class to save an image from its file path and save it as the given version name.
 */
public class Save implements Commands {
  private final String filePath;
  private final String version;

  /**
   * Instantiate a new Save class and parse the given arguments to correctly get the file from
   * the given file path and save it under the given name.
   * @param args the name of the file path of the image and the new name the image will be
   *             saved under
   */
  public Save(ArrayList<String> args) {
    this.filePath = args.get(1);
    this.version = args.get(2);
  }

  /**
   * Save the given image using the arguments passed in by the user.
   * @param m the image we want to apply the command on
   */
  @Override
  public void performCommand(IModel m) {
    m.save(filePath, version);
  }
}