import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

import javax.swing.JFrame;

import controller.commands.Commands;
import model.IModel;
import view.IView;

/**
 * This class provides a Graphical User Interface for a user to interact with. The user can see
 * the image they are processing, the available commands to use, the saved versions so far, and
 * a histogram of the red, green, and blue components of the 'before' and 'after' image.
 */
public class MockSwingFrame extends JFrame implements ActionListener, IView {
  private Appendable appendable;

  /**
   * Instantiate a new SwingFrame and format it accordingly.
   * @param model the model we want to use for the GUI
   */
  public MockSwingFrame(IModel model, Appendable appendable) {
    super();
    this.appendable = appendable;
  }

  /**
   * Calls the appropriate command according to the given input.
   * @param command command from the user (as text, would be from an actionlistener in the GUI)
   */
  public void callCommand(String command) {
    switch (command) {
      case "flip":
        try {
          appendable.append("flip chosen ");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "brighten":
        try {
          appendable.append("brighten chosen ");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "component":
        try {
          appendable.append("component chosen ");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "load":
        try {
          appendable.append("load chosen ");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "save":
        try {
          appendable.append("save chosen ");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "sharpen":
      case "blur":
      case "luma":
      case "sepia":
        try {
          appendable.append("other chosen ");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      default:
        try {
          appendable.append("N/A chosen ");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
    }
  }

  /**
   * Define what the GUI should do depending on the user's actions.
   * @param e the event to be processed
   */
  public void actionPerformedTest(String e) {
    int retvalue;
    switch (e) {
      case "get file":
      case "save file":
        try {
          appendable.append("get / save clicked ");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "submit":
        try {
          appendable.append("submit clicked ");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      default:
        try {
          appendable.append("none ");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
    }
  }

  @Override
  public void renderMessage(String s) throws IOException {
    appendable.append("render message called with String:" + s);
  }

  @Override
  public void showCommands(Map<String, Function<ArrayList<String>, Commands>> knownCommands)
          throws IOException {
    appendable.append("showCommands() called.");
  }

  @Override
  public void showVersions() throws IOException {
    appendable.append("showVersions() called.");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    return;
  }
}
