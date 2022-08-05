package model.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represent an image, including its name and individual pixels that compose the image.
 */
public class Image {

  /**
   * We chose to represent an image as an ArrayList of an ArrayList of pixels because ArrayLists
   * give us the flexibility to support an image of any size with any number of pixels. It also
   * makes it easy to get a pixel at a particular location, iterate through the rows and columns
   * of pixels in an image, and change the value of a pixel if needed.
   */
  private final List<List<Pixel>> image;
  private final String name;

  /**
   * Instantiate a new image with the given pixels and with the given name.
   * @param image all the pixels that compose an image
   * @param name the name of the image
   * @throws IllegalArgumentException if not given pixels or name
   */
  public Image(List<List<Pixel>> image, String name) throws IllegalArgumentException {
    if (image == null || name == null) {
      throw new IllegalArgumentException("Pixels or image name cannot be null.");
    }
    this.image = image;
    this.name = name;
  }

  /**
   * Instantiate a new image with the given Buffered Image and with the given name.
   * @param image given buffered image
   * @param name the name of the image
   * @throws IllegalArgumentException if not given image or name
   */
  public Image(BufferedImage image, String name) throws IllegalArgumentException {
    if (image == null || name == null) {
      throw new IllegalArgumentException("Image or name cannot be null.");
    }
    this.image = bufferedToArray(image);
    this.name = name;
  }

  /**
   * Convert the given buffered image to an ArrayList of an ArrayList of pixels. This is how
   * we store information about an image (all its pixels) in our program.
   * @param image the image to convert into an ArrayList of an ArrayList of pixels
   * @return an ArrayList of an ArrayList of pixels of all pixels in the image
   */
  private List<List<Pixel>> bufferedToArray(BufferedImage image) {
    List<List<Pixel>> toAdd = new ArrayList<List<Pixel>>();
    for (int i = 0; i < image.getHeight(); i++) {
      toAdd.add(new ArrayList<>());
      for (int j = 0; j < image.getWidth(); j++) {
        int clr = image.getRGB(j, i);
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;
        toAdd.get(i).add(new Pixel(i, j, new Color(red, green, blue)));
      }
    }
    return toAdd;
  }

  /**
   * Copy all the pixels of this image into a new ArrayList of an ArrayList of pixels. This
   * ensures that when an image is manipulated, it does not manipulate the pixels of the original
   * image.
   * @return all the pixels of the image in a new ArrayList of an ArrayList of pixels
   */
  public List<List<Pixel>> getArrayCopy() {
    List<List<Pixel>> newArray = new ArrayList<List<Pixel>>();
    for (int i = 0; i < image.size(); i++) {
      newArray.add(new ArrayList<Pixel>());
      for (int j = 0; j < image.get(0).size(); j++) {
        newArray.get(i).add(new Pixel(i, j, image.get(i).get(j).getColor()));
      }
    }
    return newArray;
  }

  /**
   * Get the pixel at the specified position in the image.
   * @param y the y position of the pixel to retrieve
   * @param x the x position of the pixel to retrieve
   * @return the pixel at the specified x and y position
   * @throws IllegalArgumentException if given an invalid pixel position
   */
  public Pixel getPixel(int y, int x) throws IllegalArgumentException {
    if (y >= 0 && y < getHeight() && x >= 0 && x < getWidth()) {
      return this.image.get(y).get(x);
    }
    else {
      throw new IllegalArgumentException("invalid pixel position.");
    }
  }

  /**
   * Get the height of the image.
   * @return the height of the image
   */
  public int getHeight() {
    return image.size();
  }

  /**
   * Get the width of the image.
   * @return the width of the image
   */
  public int getWidth() {
    return image.get(0).size();
  }

  /**
   * Get the name of this image.
   * @return the name of the image
   */
  public String getName() {
    return new String(name);
  }

  /**
   * Count the instances of a red channel at a particular value.
   * @return a HashMap of the range of possible red values and the number of pixels that have that
   *         red value
   */
  public HashMap<Integer, Integer> countRed() {
    HashMap<Integer, Integer> reds = new HashMap<>();
    for (int i = 0; i < 256; i++) {
      reds.put(i, 0);
    }
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        reds.put(getPixel(i, j).getColor().getRed(),
                reds.get(getPixel(i, j).getColor().getRed()) + 1);
      }
    }
    return reds;
  }

  /**
   * Count the instances of a green channel at a particular value.
   * @return a HashMap of the range of possible green values and the number of pixels that have that
   *         red value
   */
  public HashMap<Integer, Integer> countGreen() {
    HashMap<Integer, Integer> greens = new HashMap<>();
    for (int i = 0; i < 256; i++) {
      greens.put(i, 0);
    }
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        greens.put(getPixel(i, j).getColor().getGreen(),
                greens.get(getPixel(i, j).getColor().getGreen()) + 1);
      }
    }
    return greens;
  }

  /**
   * Count the instances of a blue channel at a particular value.
   * @return a HashMap of the range of possible blue values and the number of pixels that have that
   *         red value
   */
  public HashMap<Integer, Integer> countBlue() {
    HashMap<Integer, Integer> blues = new HashMap<>();
    for (int i = 0; i < 256; i++) {
      blues.put(i, 0);
    }
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        blues.put(getPixel(i, j).getColor().getBlue(),
                blues.get(getPixel(i, j).getColor().getBlue()) + 1);
      }
    }
    return blues;
  }

}