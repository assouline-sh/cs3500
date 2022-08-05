package model;

import java.util.List;

import model.image.Image;

/**
 * This interface includes the methods that an image should support. It includes the transformations
 * that can occur on an image and the data these methods must take in to perform those
 * transformations on an image.
 */
public interface IModel {

  /**
   * Save the image at the given path as a ppm image under the given version name.
   * @param path the file path of this image to save
   * @param version the name the image should be saved as
   */
  void save(String path, String version);

  /**
   * Load the image at the given path as an image under the given version name.
   * @param path the file path of this image to load
   * @param name the name the image should be loaded as
   */
  void load(String path, String name);

  /**
   * Greyscale the image by the given component, which can be of red, green, or blue component,
   * or by value, luma, or intensity. Can also apply a sepia filter.
   * @param component the component to greyscale the image to; can be a red, green, or blue
   *                  component or can greyscale by value, luma, or intensity
   * @param version the image to greyscale
   * @param name the name of the new greyscaled image
   */
  void greyscale(String component, String version, String name);

  /**
   * Flip the image vertically.
   * @param direction the direction of the flip
   * @param version the image to flip vertically
   * @param name the name of the new vertically flipped image
   */
  void flip(String direction, String version, String name);

  /**
   * Brighten or darken the given image by the given factor. If given a positive factor, the image
   * will be brightened by that factor. If given a negative factor, the image will be darkened
   * by that factor.
   * @param factor the factor (how much) by which the image should be brightened or darkened
   * @param version the image to brighten or darken
   * @param name the name of the new brightened or darkened image
   */
  void brightenOrDarken(int factor, String version, String name);

  /**
   * Blur or sharpen a given image.
   * @param type the type of filter to perform, either blurring or sharpening an image
   * @param version the image to apply the filter to
   * @param name the name of the new filtered image
   */
  void filter(String type, String version, String name);

  /**
   * Apply a luma or sepia filter on the image.
   * @param type the type of color transformation to perform, either luma or sepia
   * @param version the image to apply the color transformation to
   * @param name the name of the new color transformed image
   */
  void transformColor(String type, String version, String name);

  /**
   * Produce a deep copy of a given image.
   *
   * @param version the image version to make a copy of
   * @param name    name to give the deep copy
   * @return the deep copy of the given image
   */
  Image getImageCopy(String version, String name);

  /**
   * Get the versions of this image. Versions are the new images that result from manipulating an
   * image.
   * @return a new ArrayList of Images which contain the versions of this image
   */
  List<Image> getVersions();
}