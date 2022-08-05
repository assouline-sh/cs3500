package model;

import java.awt.Color;
import java.util.List;

import model.image.Image;
import model.image.Pixel;

import static readfiles.Util.loadImage;
import static readfiles.Util.saveBuffer;
import static readfiles.Util.savePPM;
import static readfiles.Util.testSave;

/**
 * This class manipulates images and saves those images to an ArrayList of versions of that
 * image. It also saves and loads images.
 */
public class ModelImpl implements IModel {
  protected final List<Image> versions;

  /**
   * Instantiate a new model with all versions of the image. New versions of an image are created
   * and added to the ArrayList of versions when an image is manipulated.
   *
   * @param versions all versions of the image
   * @throws IllegalArgumentException if versions is null
   */
  public ModelImpl(List<Image> versions) throws IllegalArgumentException {
    if (versions == null) {
      throw new IllegalArgumentException("Versions cannot be null.");
    }
    this.versions = versions;
  }

  @Override
  public void save(String path, String version) {
    Image image = this.getImageCopy(version, version);
    if (path.endsWith("ppm")) {
      savePPM(path, image);
    } else {
      saveBuffer(path, image);
    }
  }

  /**
   * Method that calls the testSave() method in the Util class. Meant to test the save method
   * by printing the results of the method to the given log.
   * @param path path of where to save the given image
   * @param version the image to save
   * @param log the contents of the saved image after it has been saved
   */
  public void callTestSave(String path, String version, StringBuilder log) {
    Image image = this.getImageCopy(version, version);
    testSave(path, version, log, this);
  }

  @Override
  public void load(String filepath, String name) {
    this.versions.add(loadImage(filepath, name));
  }

  // Reworked method to use a switch statement instead of a lot of if-else statements.
  // Also moved "luma" greyscale method to the transformColor() method because the process to
  // apply a luma transformation is more similar to applying a sepia filter, because both involve
  // the same matrix mathematical calculations. The similar calculations meant similar code, and
  // so it made more sense to place both transformations in the same method to reduce code
  // duplication.
  @Override
  public void greyscale(String component, String version, String name)
          throws IllegalArgumentException {
    Image image = this.getImageCopy(version, name);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel current = image.getPixel(i, j);
        Color colors = current.getColor();
        switch (component) {
          case "red":
            colors = new Color(colors.getRed(), colors.getRed(), colors.getRed());
            break;
          case "green":
            colors = new Color(colors.getGreen(), colors.getGreen(), colors.getGreen());
            break;
          case "blue":
            colors = new Color(colors.getBlue(), colors.getBlue(), colors.getBlue());
            break;
          case "value":
            colors = new Color(current.maxValue(), current.maxValue(),
                    current.maxValue());
            break;
          case "intensity":
            colors = new Color(current.intensity(), current.intensity(),
                    current.intensity());
            break;
          default:
            throw new IllegalArgumentException("Please give a valid component.");
        }
        image.getPixel(i, j).setColor(colors);
      }
    }
    versions.add(image);
  }

  @Override
  public void flip(String direction, String version, String name) throws IllegalArgumentException {
    Image image = this.getImageCopy(version, name);
    Image copy = this.getImageCopy(version, "copy");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        switch (direction) {
          case "vertical":
            image.getPixel(i, j).setY(image.getHeight() - i - 1);
            image.getPixel(i, j).setColor(copy.getPixel(image.getHeight() - i - 1, j).getColor());
            break;
          case "horizontal":
            image.getPixel(i, j).setX(image.getWidth() - j - 1);
            image.getPixel(i, j).setColor(copy.getPixel(i, image.getWidth() - j - 1).getColor());
            break;
          default: throw new IllegalArgumentException("Give a supported type of flip.");
        }
      }
    }
    versions.add(image);
  }

  @Override
  public void brightenOrDarken(int factor, String version, String name) {
    Image image = this.getImageCopy(version, name);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Color newColors = image.getPixel(i, j).getColor();
        newColors = new Color(clamp(newColors.getRed() + factor),
                clamp(newColors.getGreen() + factor),
                clamp(newColors.getBlue() + factor));
        image.getPixel(i, j).setColor(newColors);
      }
    }
    versions.add(image);
  }

  @Override
  public void filter(String type, String version, String name) throws IllegalArgumentException {
    double[][] kernel;
    int halfKernelSize;
    Image image = this.getImageCopy(version, name);
    switch (type) {
      case "blur":
        kernel = new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
          {0.0625, 0.125, 0.0625}};
        halfKernelSize = (int) kernel.length / 2;
        break;
      case "sharpen":
        kernel = new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, 0.25, 1, 0.25, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, -0.125, -0.125, -0.125, -0.125}};
        halfKernelSize = (int) kernel.length / 2;
        break;
      default: throw new IllegalArgumentException("Give a supported filter type.");
    }

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        double[][][] newKernel = assignKernel(i, j, image, halfKernelSize, kernel.length);
        int[] appliedFilter = filterColor(newKernel, kernel);
        Color newColor = new Color(clamp(appliedFilter[0]), clamp(appliedFilter[1]),
                clamp(appliedFilter[2]));
        image.getPixel(i, j).setColor(newColor);
      }
    }
    versions.add(image);
  }

  /**
   * Assign surrounding pixels of the given pixel (at position (i, j) in the image) to a new double
   * 3d array which holds the red, green, and blue components of each pixel that the filter should
   * be applied to.
   * @param i the row of the pixel that should be at the center of the kernel
   * @param j the column of the pixel that should be at the center of the kernel
   * @param image the image we are applying the filter on
   * @param halfKernelSize half the size of the kernel
   * @param size the size of the kernel
   * @return the red, green, and blue components of each pixel that the filter will be applied to
   */
  private double[][][] assignKernel(int i, int j, Image image, int halfKernelSize, int size) {
    double[][][] newKernel = new double[size][size][3];
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {
        if (r + (i - halfKernelSize) >= 0 && c + (j - halfKernelSize) >= 0
                && r + (i - halfKernelSize) < image.getHeight()
                && c + (j - halfKernelSize) < image.getWidth()) {
          newKernel[r][c][0] = image.getPixel(r + (i - halfKernelSize),
                  (c + (j - halfKernelSize))).getColor().getRed();
          newKernel[r][c][1] = image.getPixel(r + (i - halfKernelSize),
                  (c + (j - halfKernelSize))).getColor().getGreen();
          newKernel[r][c][2] = image.getPixel(r + (i - halfKernelSize),
                  (c + (j - halfKernelSize))).getColor().getBlue();
        }
        else {
          newKernel[r][c][0] = 0;
          newKernel[r][c][1] = 0;
          newKernel[r][c][2] = 0;
        }
      }
    }
    return newKernel;
  }

  /**
   * Apply the kernel filter to all the pixels that should be filtered.
   * @param newKernel the red, green, and blue components of all the pixels to apply the filter to
   * @param kernel the kernel that provides what number each pixel should be multiplied by to
   *               produce the correct filter
   * @return the filtered color that the new pixel with the filter applied should be
   */
  private int[] filterColor(double[][][] newKernel, double[][] kernel) {
    int[] newColor = new int[3];
    for (int r = 0; r <  newKernel.length; r++) {
      for (int c = 0; c <  newKernel.length; c++) {
        newColor[0] += newKernel[r][c][0] * kernel[r][c];
        newColor[1] += newKernel[r][c][1] * kernel[r][c];
        newColor[2] += newKernel[r][c][2] * kernel[r][c];
      }
    }
    Math.round(newColor[0]);
    Math.round(newColor[1]);
    Math.round(newColor[2]);
    return newColor;
  }

  /**
   * Ensure that the red, green, or blue color component is not below 0 or above 255, as these
   * are invalid component values.
   *
   * @param newRGB red, green, and blue components of a new color
   * @return the clamped red, green, or blue component, or the original value if no clamping
   */
  private int clamp(int newRGB) {
    if (newRGB > 255) {
      return 255;
    } else {
      return Math.max(newRGB, 0);
    }
  }

  // The new method in the Pixel class colorTransformation easily allows future extension to be
  // made with different filters. To implement different filters, simply define the values of the
  // filter matrix here, and then call colorTransformation, passing in that filter.
  @Override
  public void transformColor(String type, String version, String name)
          throws IllegalArgumentException {
    Image image = this.getImageCopy(version, name);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel current = image.getPixel(i, j);
        double[][] filter;
        switch (type) {
          case "luma":
            filter = new double[][]{{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
              {0.2126, 0.7152, 0.0722}};
            break;
          case "sepia":
            filter = new double[][]{{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168},
              {0.272, 0.534, 0.131}};
            break;
          default:
            throw new IllegalArgumentException("Give a supported filter type.");
        }
        int[] newColor = current.colorTransformation(filter);
        Color colors = new Color(clamp(newColor[0]), clamp(newColor[1]), clamp(newColor[2]));
        image.getPixel(i, j).setColor(colors);
      }
    }
    versions.add(image);
  }

  @Override
  public Image getImageCopy(String version, String name) throws IllegalArgumentException {
    for (Image i : versions) {
      if (i.getName().equals(version)) {
        return new Image(i.getArrayCopy(), name);
      }
    }
    throw new IllegalArgumentException("Version not real");
  }

  @Override
  public List<Image> getVersions() {
    return this.versions;
  }
}