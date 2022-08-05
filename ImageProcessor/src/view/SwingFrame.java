package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ControllerImpl;
import controller.IController;
import controller.commands.Commands;
import model.IModel;
import model.image.Image;
import model.image.Pixel;
import readfiles.Util;

/**
 * This class provides a Graphical User Interface for a user to interact with. The user can see
 * the image they are processing, the available commands to use, the saved versions so far, and
 * a histogram of the red, green, and blue components of the 'before' and 'after' image.
 */
public class SwingFrame extends JFrame implements ActionListener, IView {

  private final Dimension panelSize = new Dimension(300, 300);
  private IModel model;
  private final List<Image> versions;
  private List<String> versionStrings;
  private ImageIcon beforeImageIcon;
  private List<List<Pixel>> beforeImage;
  private ImageIcon afterImageIcon;
  private List<List<Pixel>> afterImage;
  private StringBuilder commandsText;
  private final JLabel rightPanel;
  private final JPanel bottomPanel;
  private final JPanel bottomInput;
  private final JComboBox<String> bottomInputCommand;
  private JSpinner bottomInputValue;
  private JComboBox<String> bottomInputClarify;
  private JComboBox<String> bottomInputSaves;
  private JTextField bottomInputTo;
  private JButton bottomInputFile;
  private final FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "Images", "jpg", "gif", "png", "jpeg", "ppm");

  private final JFileChooser fchooser = new JFileChooser(".");
  private File file;

  private final StringBuilder outputText;
  private final JLabel bottomOutput;

  private JPanel beforeHistogram;
  private Histogram afterHistogram;

  /**
   * Define what the GUI should do depending on the user's actions.
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    int retvalue;
    switch (e.getActionCommand()) {
      case "get file":
      case "save file":
        fchooser.setFileFilter(filter);
        retvalue = fchooser.showOpenDialog(SwingFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          file = fchooser.getSelectedFile();
          bottomInputFile.setText(file.getPath().substring(file.getPath().lastIndexOf('\\') + 1));
        }
        break;
      case "Submit":
        boolean validFile = true;
        //if N/A
        if (bottomInputCommand.getSelectedIndex() == 0) {
          try {
            this.renderMessage("Must choose a Command");
            validFile = false;
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }

        //Submitting any commands without a version to update
        else if (!Objects.equals(bottomInputCommand.getSelectedItem(), "load")
                && bottomInputSaves.getSelectedItem() == null) {
          try {
            this.renderMessage("Must Load an image first");
            validFile = false;
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }


        if ((Objects.equals(bottomInputCommand.getSelectedItem(), "load")
                && bottomInputFile.getText().equals("Load a file"))
                || (Objects.equals(bottomInputCommand.getSelectedItem(), "save")
                && bottomInputFile.getText().equals("Save to file"))) {
          try {
            this.renderMessage("Must choose a file path");
            validFile = false;
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }

        if (validFile) {
          StringBuilder currentCommand = new StringBuilder();
          Component[] components = bottomInput.getComponents();
          for (Component current : components) {
            if (current instanceof JButton) {
              currentCommand.append(file.getPath()).append(" ");
            } else if (current instanceof JComboBox) {
              currentCommand.append(((JComboBox<?>) current).getSelectedItem()).append(" ");
            } else if (current instanceof JSpinner) {
              currentCommand.append(((JSpinner) current).getValue()).append(" ");
            } else if (current instanceof JTextField) {
              currentCommand.append(((JTextField) current).getText()).append(" ");
            }
          }

          // other commands
          if ((bottomInput.getComponent(bottomInput.getComponentCount() - 1) instanceof JTextField
                  && (!bottomInputTo.getText().equals(""))
                  || Objects.equals(bottomInputCommand.getSelectedItem(), "save"))) {
            try {
              this.renderMessage(currentCommand.toString());
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }

            if (Objects.equals(bottomInputCommand.getSelectedItem(), "component")
                    || Objects.equals(bottomInputCommand.getSelectedItem(), "flip")) {
              String[] revised = currentCommand.toString().split(" ");
              revised[1] = revised[1] + "-" + revised[0];
              revised[0] = "";
              currentCommand = new StringBuilder(String.join(" ", revised));
            }

            IController controller = new ControllerImpl(model,
                    this,
                    new StringReader(currentCommand.toString()));
            controller.performCommand();

            //updates the before image only when loaded
            if (Objects.equals(bottomInputCommand.getSelectedItem(), "load")) {
              bottomPanel.remove(1);
              beforeImage = versions.get(versions.size() - 1).getArrayCopy();
              beforeImageIcon.setImage(
                      Util.writeToBuffered(beforeImage));
              beforeHistogram = new Histogram(beforeImage);
              beforeHistogram.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              bottomPanel.add(beforeHistogram, 1);
            }
            bottomPanel.remove(2);
            afterImage = versions.get(versions.size() - 1).getArrayCopy();
            afterImageIcon.setImage(
                    Util.writeToBuffered(afterImage));
            afterHistogram = new Histogram(afterImage);
            afterHistogram.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            bottomPanel.add(afterHistogram);
            bottomInputCommand.setSelectedIndex(0);
          } else {
            try {
              this.renderMessage("Invalid Command, Not enough arguments");
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
          }
        }
        break;
      default:
        try {
          this.renderMessage("Invalid Command, Not enough arguments");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
    }
    revalidate();
  }

  /**
   * Instantiate a new SwingFrame and format it accordingly.
   * @param model the model we want to use for the GUI
   */
  public SwingFrame(IModel model) {
    super();
    this.model = model;
    String[] commands = new String[IController.getCommandList().size() + 1];
    versions = model.getVersions();
    List<List<Pixel>> defaultImageArray = new ArrayList<>();

    for (int i = 0; i < panelSize.height; i++) {
      defaultImageArray.add(new ArrayList<>());
      for (int j = 0; j < panelSize.width; j++) {
        defaultImageArray.get(i).add(new Pixel(i, j, Color.WHITE));
      }
    }

    java.awt.Image defaultImage = Util.writeToBuffered(defaultImageArray);


    int i = 0;
    commands[i] = "N/A";
    i++;
    for (Map.Entry<String, Function<ArrayList<String>, Commands>> command
            : IController.getCommandList().entrySet()) {
      commands[i] = command.getKey();
      i++;
    }


    setTitle("CS3500 - Image Processor by Nelson and Shannon");
    afterImage = defaultImageArray;
    beforeImage = defaultImageArray;
    afterImageIcon = new ImageIcon(Util.writeToBuffered(afterImage));
    beforeImageIcon = new ImageIcon(Util.writeToBuffered(beforeImage));
    outputText = new StringBuilder("<HTML>");
    //main panel holds everything
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    //Center panel will hold the images before and after the changes
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout());

    JLabel centerBefore = new JLabel();
    JScrollPane centerBeforeScroll = new JScrollPane(centerBefore);
    centerBeforeScroll.setBorder(BorderFactory.createTitledBorder("Before"));
    centerBefore.setIcon(beforeImageIcon);
    centerPanel.add(centerBeforeScroll);

    JLabel centerAfter = new JLabel();
    JScrollPane centerAfterScroll = new JScrollPane(centerAfter);
    centerAfterScroll.setBorder(BorderFactory.createTitledBorder("After"));
    centerAfter.setIcon(afterImageIcon);
    centerPanel.add(centerAfterScroll);

    mainPanel.add(centerPanel, BorderLayout.CENTER);


    //Bottom will contain the script text field
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new GridLayout());

    //console portion
    JPanel bottomConsole = new JPanel();
    bottomConsole.setLayout(new BorderLayout());
    bottomOutput = new JLabel();
    bottomOutput.setBorder(BorderFactory.createTitledBorder("Console"));
    bottomOutput.setVerticalAlignment(SwingConstants.BOTTOM);

    bottomOutput.setText(outputText.toString());
    JScrollPane bottomScrollPane = new JScrollPane(bottomOutput);
    bottomScrollPane.setPreferredSize(new Dimension(30, 200));
    bottomScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        e.getAdjustable().setValue(e.getAdjustable().getMaximum());
      }
    });
    bottomConsole.add(bottomScrollPane, BorderLayout.NORTH);


    //enter the script here
    bottomInput = new JPanel();
    bottomInput.setLayout(new GridLayout());
    bottomInput.setSize(new Dimension(50, 1));
    bottomInputCommand = new JComboBox<>(commands);
    bottomInputCommand.setSelectedIndex(0);
    bottomInput.add(bottomInputCommand);

    bottomInputCommand.addItemListener(e -> {
      String command = (String) e.getItem();
      Object[] versionsObj;
      String[] versionsStr;
      switch (command) {
        case "flip":
          bottomInput.removeAll();
          bottomInput.add(bottomInputCommand);

          bottomInputClarify = new JComboBox<>(new String[]{"horizontal", "vertical"});
          bottomInput.add(bottomInputClarify);

          this.initVersionAsStrings();
          versionsObj = versionStrings.toArray(); // ArrayList to object array conversion
          versionsStr = Arrays.copyOf(versionsObj, versionsObj.length, String[].class);
          bottomInputSaves = new JComboBox<>(versionsStr);
          bottomInputSaves.setSelectedIndex(bottomInputSaves.getItemCount() - 1);
          bottomInput.add(bottomInputSaves);
          bottomInputTo = new JTextField();
          bottomInput.add(bottomInputTo);
          break;
        case "brighten":
          bottomInput.removeAll();
          bottomInput.add(bottomInputCommand);

          bottomInputValue = new JSpinner();
          bottomInput.add(bottomInputValue);

          this.initVersionAsStrings();
          versionsObj = versionStrings.toArray(); // ArrayList to object array conversion
          versionsStr = Arrays.copyOf(versionsObj, versionsObj.length, String[].class);
          bottomInputSaves = new JComboBox<>(versionsStr);
          bottomInputSaves.setSelectedIndex(bottomInputSaves.getItemCount() - 1);

          bottomInput.add(bottomInputSaves);
          bottomInputTo = new JTextField();
          bottomInput.add(bottomInputTo);
          break;
        case "component":
          bottomInput.removeAll();
          bottomInput.add(bottomInputCommand);

          bottomInputClarify = new
                  JComboBox<>(new String[]{"red", "blue", "green", "value", "intensity"});
          bottomInput.add(bottomInputClarify);

          this.initVersionAsStrings();
          versionsObj = versionStrings.toArray(); // ArrayList to object array conversion
          versionsStr = Arrays.copyOf(versionsObj, versionsObj.length, String[].class);
          bottomInputSaves = new JComboBox<>(versionsStr);
          bottomInputSaves.setSelectedIndex(bottomInputSaves.getItemCount() - 1);

          bottomInput.add(bottomInputSaves);
          bottomInputTo = new JTextField();
          bottomInput.add(bottomInputTo);
          break;
        case "load":
          bottomInput.removeAll();
          bottomInput.add(bottomInputCommand);

          bottomInputFile = new JButton("Load a file");
          bottomInputFile.setActionCommand("get file");
          bottomInputFile.addActionListener(this);
          bottomInput.add(bottomInputFile);

          bottomInputTo = new JTextField();
          bottomInput.add(bottomInputTo);
          break;

        case "save":
          bottomInput.removeAll();
          bottomInput.add(bottomInputCommand);

          bottomInputFile = new JButton("Save to file");
          bottomInputFile.setActionCommand("save file");
          bottomInputFile.addActionListener(this);
          bottomInput.add(bottomInputFile);

          this.initVersionAsStrings();
          versionsObj = versionStrings.toArray(); // ArrayList to object array conversion
          versionsStr = Arrays.copyOf(versionsObj, versionsObj.length, String[].class);

          bottomInputSaves = new JComboBox<>(versionsStr);
          bottomInputSaves.setSelectedIndex(bottomInputSaves.getItemCount() - 1);
          bottomInput.add(bottomInputSaves);
          break;

        case "sharpen":
        case "blur":
        case "luma":
        case "sepia":
          bottomInput.removeAll();
          bottomInput.add(bottomInputCommand);

          this.initVersionAsStrings();
          versionsObj = versionStrings.toArray(); // ArrayList to object array conversion
          versionsStr = Arrays.copyOf(versionsObj, versionsObj.length, String[].class);
          bottomInputSaves = new JComboBox<>(versionsStr);
          bottomInputSaves.setSelectedIndex(bottomInputSaves.getItemCount() - 1);

          bottomInput.add(bottomInputSaves);
          bottomInputTo = new JTextField();
          bottomInput.add(bottomInputTo);
          break;
        default:
          bottomInput.removeAll();
          bottomInput.add(bottomInputCommand);
      }
      revalidate();
      repaint();
    });

    //put saves into this bottomInputFrom to choose from the saves

    bottomConsole.add(bottomInput, BorderLayout.CENTER);


    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(this);
    submitButton.setActionCommand("Submit");
    bottomConsole.add(submitButton, BorderLayout.EAST);
    bottomPanel.add(bottomConsole);


    beforeHistogram = new Histogram(new ArrayList<>());
    beforeHistogram.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    afterHistogram = new Histogram(new ArrayList<>());
    afterHistogram.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    bottomPanel.add(beforeHistogram);
    bottomPanel.add(afterHistogram);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);


    JLabel leftPanel = new JLabel();
    showCommands(IController.getCommandList());
    leftPanel.setText(commandsText.toString());
    JScrollPane leftScrollPane = new JScrollPane(leftPanel);
    leftScrollPane.setBorder(BorderFactory.createTitledBorder("Allowed Commands"));
    leftScrollPane.setPreferredSize(new Dimension(250, 50));
    mainPanel.add(leftScrollPane, BorderLayout.WEST);

    rightPanel = new JLabel();
    rightPanel.setText("<HTML>");
    JScrollPane rightScrollPane = new JScrollPane(rightPanel);
    rightScrollPane.setBorder(BorderFactory.createTitledBorder("Saved Versions"));
    rightScrollPane.setPreferredSize(new Dimension(200, 50));
    rightScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        e.getAdjustable().setValue(e.getAdjustable().getMaximum());
      }
    });

    mainPanel.add(rightScrollPane, BorderLayout.EAST);

    add(mainPanel);
    pack();

  }

  @Override
  public void renderMessage(String s) throws IOException {
    String[] split = s.split(" ");

    outputText.append("<br> ");
    for (int i = 0; i < split.length; i++) {
      if (split[i].contains("\\")) {
        outputText.append(split[i].substring(split[i].lastIndexOf("\\") + 1))
                .append(" ");
      } else {
        outputText.append(split[i]).append(" ");
      }
    }
    bottomOutput.setText(outputText.toString());
  }

  @Override
  public void showCommands(Map<String, Function<ArrayList<String>, Commands>> knownCommands) {
    commandsText = new StringBuilder("<HTML>");
    for (Map.Entry<String, Function<ArrayList<String>, Commands>> e : knownCommands.entrySet()) {
      String command = e.getKey();
      if (command.equals("flip")) {
        commandsText.append("<br>horizontal-" + e.getKey() + "<br>");
        commandsText.append("[image-name] [dest-image-name]<br>");
        commandsText.append("<br>vertical-" + e.getKey() + "<br>");
        commandsText.append("[image-name] [dest-image-name]<br>");
        continue;
      } else if (command.equals("component")) {
        commandsText.append("<br>red-" + e.getKey() + "<br>");
        commandsText.append("[image-name] [dest-image-name]<br>");
        commandsText.append("<br>green-" + e.getKey() + "<br>");
        commandsText.append("[image-name] [dest-image-name]<br>");
        commandsText.append("<br>blue-" + e.getKey() + "<br>");
        commandsText.append("[image-name] [dest-image-name]<br>");
        commandsText.append("<br>value-" + e.getKey() + "<br>");
        commandsText.append("[image-name] [dest-image-name]<br>");
        commandsText.append("<br>intensity-" + e.getKey() + "<br>");
        commandsText.append("[image-name] [dest-image-name]<br>");
        continue;
      }

      commandsText.append("<br>" + command + "<br>");
      if (command.equals("save") || command.equals("load")) {
        commandsText.append("[image-path] [image-name]<br>");
        continue;
      } else if (command.equals("brighten")) {
        commandsText.append(" [increment] ");
      }
      commandsText.append("[image-name] [dest-image-name]<br>");
    }
  }

  @Override
  public void showVersions() throws IOException {
    StringBuilder versionText = new StringBuilder("<HTML>");
    for (int i = 0; i < versions.size(); i++) {
      versionText.append(versions.get(i).getName()).append("<br>");
    }
    rightPanel.setText(versionText.toString());
    revalidate();
    repaint();
  }

  /**
   * Convert the array of images to a string that will later be printed out in the GUI.
   */
  private void initVersionAsStrings() {
    this.versionStrings = new ArrayList<>();
    for (int i = 0; i < versions.size(); i++) {
      this.versionStrings.add(versions.get(i).getName());
    }
  }
}
