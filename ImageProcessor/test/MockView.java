import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

import controller.commands.Commands;
import view.IView;

/**
 * Class to create a mock view of the image to verify methods in view are called correctly
 * with the correct parameters.
 */
public class MockView implements IView {

  private final StringBuilder log;

  /**
   * Instantiate a new mock view with a log.
   *
   * @param output the output of methods called
   */
  public MockView(StringBuilder output) {
    this.log = output;
  }

  @Override
  public void renderMessage(String s) throws IOException {
    log.append("renderMessage() called " + "s:" + s + "\n");
  }

  @Override
  public void showCommands(Map<String, Function<ArrayList<String>, Commands>> knownCommands)
          throws IOException {
    log.append("showCommands() called " + "Commands: " + knownCommands + "\n");
  }

  @Override
  public void showVersions() throws IOException {
    log.append("showVersions() called");
  }
}
