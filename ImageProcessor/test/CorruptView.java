import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

import controller.commands.Commands;
import view.IView;

/**
 * Class to create a mock view of the image to verify IOExceptions were thrown correctly.
 */
public class CorruptView implements IView {
  private final StringBuilder log;

  /**
   * Instantiate a new corrupt mock view with a log.
   *
   * @param output the output of methods called
   */
  public CorruptView(StringBuilder output) {
    this.log = output;
  }

  @Override
  public void renderMessage(String s) throws IOException {
    log.append("renderMessage() IO exception");
    throw new IOException("from renderMessage()");
  }

  @Override
  public void showCommands(Map<String, Function<ArrayList<String>, Commands>> knownCommands)
          throws IOException {
    log.append("showCommands() IO exception");
    throw new IOException("from showCommands()");
  }

  @Override
  public void showVersions() throws IOException {
    log.append("showVersions() IO exception");
    throw new IOException("from showVersions()");
  }
}
