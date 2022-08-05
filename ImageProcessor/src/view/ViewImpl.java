package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

import controller.commands.Commands;
import model.IModel;
import model.image.Image;

/**
 * This class renders messages and commands for the user when using the program to apply
 * manipulations to an image.
 */
public class ViewImpl implements IView {
  private final IModel model;
  private final Appendable appendable;

  /**
   * Instantiate the view with the given model image as input and print the results in
   * Sytem.out.
   *
   * @param model the model image we want to view
   */
  public ViewImpl(IModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    this.model = model;
    this.appendable = System.out;
  }

  /**
   * Instantiate the view with the given model image as input and append the results to
   * the given Appendable object.
   *
   * @param model      the model image we want to view
   * @param appendable the appendable object we want to append the view to
   */
  public ViewImpl(IModel model, Appendable appendable) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    this.model = model;
    this.appendable = appendable;
  }


  @Override
  public void renderMessage(String message) throws IOException {
    appendable.append(message);
  }

  @Override
  public void showCommands(Map<String, Function<ArrayList<String>, Commands>> knownCommands)
          throws IOException {
    this.renderMessage("\nCommands: \n");
    for (Map.Entry<String, Function<ArrayList<String>, Commands>> e : knownCommands.entrySet()) {
      String command = e.getKey();

      if (command.equals("flip")) {
        renderMessage("horizontal-" + e.getKey());
        renderMessage(" image-name dest-image-name\n");
        renderMessage("vertical-" + e.getKey());
        renderMessage(" image-name dest-image-name\n");
        continue;
      } else if (command.equals("component")) {
        renderMessage("red-" + e.getKey());
        renderMessage(" image-name dest-image-name\n");
        renderMessage("green-" + e.getKey());
        renderMessage(" image-name dest-image-name\n");
        renderMessage("blue-" + e.getKey());
        renderMessage(" image-name dest-image-name\n");
        renderMessage("value-" + e.getKey());
        renderMessage(" image-name dest-image-name\n");
        renderMessage("intensity-" + e.getKey());
        renderMessage(" image-name dest-image-name\n");
        continue;
      }

      renderMessage(command);
      if (command.equals("save") || command.equals("load")) {
        renderMessage(" image-path image-name\n");
        continue;
      } else if (command.equals("brighten")) {
        renderMessage(" increment");
      }
      renderMessage(" image-name dest-image-name\n");
    }
  }

  @Override
  public void showVersions() throws IOException {
    renderMessage("\nCurrent Versions Saved: \n");
    for (Image i : this.model.getVersions()) {
      renderMessage(i.getName() + "\n");
    }
  }
}

