package model.image;

import java.awt.Color;

/**
 * This class represents a singular pixel in an image. It has a position in the image (x and y
 * coordinates) and a color broken into red, green, blue components (RGB), stored in this order.
 */
public class Pixel {
  private int y;
  private int x;
  /* Changed the representation of a color from an integer array int[] to Java's Color class. In
  doing so, the readability of our code drastically improved because instead of calling the index
  of an integer array to access the red, green, and blue components, it is clear what color
  component we are calling with specific methods of getRed(), getGreen(), and getBlue(). It also
  made our code more succinct.
   */
  private Color color;

  /**
   * Instantiate a new pixel with the given x and y coordinates, and given color.
   * @param x the x position of the pixel in the image
   * @param y the y position of the pixel in the image
   * @param color the color of the pixel in the image
   * @throws IllegalArgumentException if x or y coordinate of pixel is less than 0
   */
  public Pixel(int y, int x, Color color) throws IllegalArgumentException {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Invalid pixel coordinates");
    }
    this.y = y;
    this.x = x;
    this.color = color;
  }

  /**
   * Get the color of this pixel.
   * @return a new integer array with the same values as the colors of this pixel
   */
  public Color getColor() {
    return new Color(color.getRed(), color.getGreen(), color.getBlue());
  }

  /**
   * Set the red, green, and blue component colors of this pixel to the given colors.
   * @param colors the new red, green, and blue components to set the color of this pixel
   * @throws IllegalArgumentException if given red, green, blue colors aren't valid
   */
  public void setColor(Color colors) throws IllegalArgumentException {
    if (colors.getRed() >= 0 && colors.getRed() <= 255
            && colors.getGreen() >= 0 && colors.getGreen() <= 255
            && colors.getBlue() >= 0 && colors.getBlue() <= 255) {
      this.color = new Color(colors.getRed(), colors.getGreen(), colors.getBlue());
    }
    else {
      throw new IllegalArgumentException("Must give valid color.");
    }
  }

  /**
   * Get the pixel's x position. Used in testing to test that setX() works.
   * @return pixel's x position
   */
  public int getX() {
    return this.x;
  }

  /**
   * Get the pixel's y position. Used in testing to test that setY() works.
   * @return pixel's y position
   */
  public int getY() {
    return this.y;
  }

  /**
   * Set the x value of this pixel to the given x.
   * @param x The x value to set this pixel to
   * @throws IllegalArgumentException if given negative x value
   */
  public void setX(int x) throws IllegalArgumentException {
    if (x >= 0) {
      this.x = x;
    }
    else {
      throw new IllegalArgumentException("x cannot be negative.");
    }
  }

  /**
   * Set the y value of this pixel to the given y.
   * @param y The y value to set this pixel to
   * @throws IllegalArgumentException if given negative y value
   */
  public void setY(int y) throws IllegalArgumentException {
    if (y >= 0) {
      this.y = y;
    }
    else {
      throw new IllegalArgumentException("y cannot be negative.");
    }
  }

  /**
   * Get the maximum value of all three red, green, and blue components of this pixel's color.
   * @return the maximum value of the red, green, or blue component of this pixel's color
   */
  public int maxValue() {
    return Math.max(Math.max(color.getRed(), color.getGreen()), color.getBlue());
  }

  /**
   * Calculate average of the red, green, and blue components of this pixel's color.
   * @return the average of the red, green, and blue components of this pixel's color
   */
  public int intensity() {
    return (int) ((double) color.getRed() + color.getGreen() + color.getBlue()) / 3;
  }

  // Deleted the luma method. Now luma is calculated with the colorTransformation() method where
  // the luma matrix needed to perform the mathematical calculations is passed in.

  // This new method was added to apply the given filter matrix to the color of an individual pixel.
  // This method will allow extension in the future because it will work for any given filter.
  /**
   * Apply the given filter the red, green, and blue components of this pixel's color.
   * @param filter the filter values to be applied to the image
   * @return the red, green, and blue components of the new color after the filter has been applied
   */
  public int[] colorTransformation(double[][] filter) {
    int[] oldColor = new int[]{this.color.getRed(), this.color.getGreen(), this.color.getBlue()};
    int[] newColor = new int[3];
    for (int c = 0; c < filter.length; c++) {
      for (int r = 0; r < filter[0].length; r++) {
        newColor[r] += filter[r][c] * oldColor[c];
      }
    }
    Math.round(newColor[0]);
    Math.round(newColor[1]);
    Math.round(newColor[2]);
    return newColor;
  }
}
