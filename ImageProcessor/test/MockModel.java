import java.util.ArrayList;
import java.util.List;

import model.IModel;
import model.image.Image;
import model.image.Pixel;

/** A mock image model to test that methods are appropriately called with the correct parameters.
 */
public class MockModel implements IModel {
  private final StringBuilder log;

  /**
   * Instantiate the mock image model with the given output.
   *
   * @param output the output of methods called
   */
  public MockModel(StringBuilder output) {
    this.log = output;
  }

  @Override
  public void save(String path, String version) {
    log.append("save() called "  + "path: " + path + " version: " + version + "\n");
  }

  @Override
  public void load(String path, String name) {
    log.append("load() called "  + "path: " + path + " name: " + name + "\n");
  }

  @Override
  public void greyscale(String component, String version, String name) {
    log.append("greyscale() called "  + "component: " + component + " version: " + version
            + " name: " + name + "\n");
  }

  @Override
  public void flip(String direction, String version, String name) {
    log.append(direction + " : flip() called "  + "version: " + version + " name: " + name + "\n");
  }

  @Override
  public void brightenOrDarken(int factor, String version, String name) {
    log.append("brightenOrDarken() "  + "factor: " + factor + " version: " + version
            + " name: " + name + "\n");
  }

  @Override
  public void filter(String type, String version, String name) {
    log.append("filter() called "  + "type: " + type + " path: " + version
            + " name: " + name + "\n");
  }

  @Override
  public void transformColor(String type, String version, String name) {
    log.append("transformColor() called "  + "type: " + type + " path: " + version
            + " name: " + name + "\n");
  }

  @Override
  public Image getImageCopy(String version, String name) {
    log.append("getImageCopy() called "  + "version: " + version + " name: " + name + "\n");
    return new Image(new ArrayList<List<Pixel>>(), "name");
  }

  @Override
  public ArrayList<Image> getVersions() {
    log.append("getVersions() called\n");
    return new ArrayList<>();
  }
}
