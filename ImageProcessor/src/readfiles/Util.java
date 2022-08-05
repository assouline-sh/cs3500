package readfiles;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.IModel;
import model.image.Pixel;
import model.image.Image;

/**
 * This class offers methods to read the given image file depending on its format and parse its
 * information into an ArrayList of an ArrayList of Pixels to later be able to manipulate the
 * image's pixels. It also offers methods to save and load images of all file formats.
 */
public class Util {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @return image as a 2D array of pixels
   */
  public static List<List<Pixel>> readPPM(String filename)
          throws FileNotFoundException, IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException(e.getMessage());
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    List<List<Pixel>> image = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      image.add(new ArrayList<Pixel>());
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        image.get(i).add(new Pixel(j, i, new Color(r, g, b)));
      }
    }
    return image;
  }

  /**
   * Read an any image file.
   *
   * @param filename the path of the file
   * @return a read buffered image
   */
  public static List<List<Pixel>> readFile(String filename) {
    if (filename.endsWith("ppm")) {
      try {
        return readPPM(filename);
      } catch (IOException e) {
        throw new IllegalArgumentException("IOException");
      }
    }
    BufferedImage read;
    try {
      read = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException("IOException");
    }
    return writeToArrayList(read);
  }

  /**
   * Convert the image pixels into a buffered image.
   *
   * @param image all the pixels of the image
   * @return a buffered image
   */
  public static BufferedImage writeToBuffered(List<List<Pixel>> image) {
    BufferedImage bufferedImage = new BufferedImage(image.get(0).size(), image.size(),
            BufferedImage.TYPE_INT_RGB);

    for (int i = 0 ; i < image.size(); i++) {
      for (int j = 0; j < image.get(0).size(); j ++) {
        bufferedImage.setRGB(j, i, image.get(i).get(j).getColor().getRGB());
      }
    }

    return bufferedImage;
  }

  /**
   * Convert the given image into an ArrayList of an ArrayList of Pixels.
   * @param image buffered image to translate into an ArrayList of an ArrayList of Pixels
   * @return All pixels of the given image in the appropriate position
   */
  public static List<List<Pixel>> writeToArrayList(BufferedImage image) {
    List<List<Pixel>> toReturn = new ArrayList<>();
    for (int i = 0 ; i < image.getHeight(); i++) {
      toReturn.add(new ArrayList<>());
      for (int j = 0; j < image.getWidth(); j ++) {
        toReturn.get(i).add(new Pixel(i, j, new Color(image.getRGB(j, i))));
      }
    }
    return toReturn;
  }

  /**
   * Save the given ppm image to the given path.
   * @param path where to save the given ppm image to
   * @param image the image to save
   */
  public static void savePPM(String path, Image image) throws IllegalArgumentException {
    PrintWriter output;
    try {
      output = new PrintWriter(path);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }

    output.write("P3\n");

    output.write(image.getWidth() + " " + image.getHeight() + "\n");
    output.write(255 + "\n");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel current = image.getPixel(i, j);
        output.write(current.getColor().getRed() + "\n");
        output.write(current.getColor().getGreen() + "\n");
        output.write(current.getColor().getBlue() + "\n");
      }
    }
    output.close();
  }

  /**
   * Save the given image to the given path. The image is of any format other than ppm,
   * including jpeg, png, and bmp
   * @param path where to save the given image to
   * @param image the image to save
   */
  public static void saveBuffer(String path, Image image) throws IllegalArgumentException {
    try {
      ImageIO.write(Util.writeToBuffered(image.getArrayCopy()),
              path.substring(path.lastIndexOf(".") + 1), new File(path));
    } catch (IOException e) {
      throw new IllegalArgumentException("IOException");
    }
  }

  /**
   * Copy of save method that takes in a StringBuilder to write to. For testing purposes.
   *
   * @param path    path the file path of this image to save
   * @param version version the name the image should be saved as
   * @param builder StringBuilder to append to
   * @throws IllegalArgumentException if the file is not found
   */
  public static void testSave(String path, String version, StringBuilder builder, IModel model)
          throws IllegalArgumentException {
    Image image = model.getImageCopy(version, version);

    PrintWriter output;
    try {
      output = new PrintWriter(path);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }

    output.write("P3\n");
    builder.append("P3\n");

    output.write(image.getWidth() + " " + image.getHeight() + "\n");
    output.write(255 + "\n");
    builder.append(image.getWidth() + " " + image.getHeight() + "\n");
    builder.append(255 + "\n");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel current = image.getPixel(i, j);
        output.write(current.getColor().getRed() + "\n");
        output.write(current.getColor().getGreen() + "\n");
        output.write(current.getColor().getBlue() + "\n");
        builder.append(current.getColor().getRed() + "\n");
        builder.append(current.getColor().getGreen() + "\n");
        builder.append(current.getColor().getBlue() + "\n");
      }
    }
    output.close();
  }

  /**
   * Load the image at the given filepath to the given name.
   * @param filepath where the file to load is located
   * @param name the name to load the image under
   * @return a new image at the given file path and with the given name
   */
  public static Image loadImage(String filepath, String name) {
    return new Image(Util.readFile(filepath), name);
  }
}

