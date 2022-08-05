import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import model.ModelImpl;
import view.IView;
import view.SwingFrame;
import view.ViewImpl;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * It checks that inputs are valid and runs the program.
 */
public class ImageUtil {
  //demo main

  /**
   * Run the program to manipulate an image.
   *
   * @param args the commands to run on an image, and which image to run it on
   */
  public static void main(String[] args) throws IOException {

    IModel model = new ModelImpl(new ArrayList<>());
    IView view;
    Readable justToShowCommands = new StringReader("");

    // for either text or script
    if (args.length != 0) {
      Scanner sc = chooseInput(args);

      try {
        view = new ViewImpl(model);
        view.showCommands(IController.getCommandList());
      } catch (IOException e) {
        throw new IllegalArgumentException();
      }
      while (sc.hasNext()) {
        Readable passToController = new StringReader(validInput(sc).toString());

        IController controller = new ControllerImpl(model, view, passToController);
        controller.performCommand();
      }
    }

    // for GUI
    else {
      SwingFrame.setDefaultLookAndFeelDecorated(false);
      view = new SwingFrame(model);

      ((SwingFrame) view).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ((SwingFrame) view).setVisible(true);

      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
               | IllegalAccessException ignored) {
      }
    }
  }

  /**
   * Validate that the values inputted by the user are valid.
   *
   * @param sc the inputs from the user
   * @return the current arguments
   * @throws NoSuchElementException if there are not enough arguments provided by the user
   */
  private static StringBuilder validInput(Scanner sc) throws NoSuchElementException {
    StringBuilder currentArgs = new StringBuilder();
    String filepath;
    String toName;
    String commandName;
    commandName = sc.next();

    currentArgs.append(commandName + " ");

    if (commandName.equalsIgnoreCase("brighten")) {
      int brightenFactor;
      brightenFactor = sc.nextInt();

      currentArgs.append(brightenFactor + " ");
    }
    filepath = sc.next();
    currentArgs.append(filepath + " ");
    toName = sc.next();
    currentArgs.append(toName + " ");
    return currentArgs;
  }

  private static Scanner chooseInput(String[] args) {
    Scanner sc = null;
    //if not given a file run through System.in
    if (args.length == 1 && args[0].equals("-text")) {
      sc = new Scanner(System.in);
    } else if (args.length == 2 && args[0].equals("-file")) {
      try (BufferedReader br = new BufferedReader(new FileReader(args[1]))) {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
          sb.append(line);
          sb.append(System.lineSeparator());
          line = br.readLine();
        }
        sc = new Scanner(sb.toString());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    return sc;
  }
}