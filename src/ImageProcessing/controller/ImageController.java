package ImageProcessing.controller;


import java.io.FileNotFoundException;

/**
 * The ImageController interface represents the controller component in the Model-View-Controller (MVC)
 * design pattern. It defines the operations that can be performed on the ImageModel. These operations
 * include loading an image from a file, extracting color channels from an image, modifying the brightness
 * of an image, and saving an image to a file. It also includes a method to overwrite an existing image
 * with another image.
 */
public interface ImageController {
  /**
   * Load an image from an ASCII PPM file.
   *
   * @param filename the path to the file to load.
   */
  void loadImage(String filename) throws FileNotFoundException;

  /**
   * Create an image that visualizes the red channel of the current image.
   */
  void extractRedChannel();

  /**
   * Create an image that visualizes the green channel of the current image.
   */
  void extractGreenChannel();

  /**
   * Create an image that visualizes the blue channel of the current image.
   */
  void extractBlueChannel();

  /**
   * Create an image that visualizes the value of the current image.
   */
  void extractValue();

  /**
   * Create an image that visualizes the intensity of the current image.
   */
  void extractIntensity();

  /**
   * Create an image that visualizes the luma of the current image.
   */
  void extractLuma();

  /**
   * Converts the current image to greyscale using the specified method.
   *
   * @param method the method to use for greyscale conversion.
   *               Must be one of "value", "intensity", or "luma".
   * @throws IllegalArgumentException if an unknown method is provided.
   */
  void convertToGreyscale(String method);

  /**
   * Brighten or darken the current image by a specified amount.
   *
   * @param amount the amount to change the brightness by, can be negative to darken the image.
   */
  void changeBrightness(int amount);

  /**
   * Flips the current image horizontally.
   */
  void flipImage();

  /**
   * Save the current image to an ASCII PPM file.
   *
   * @param filename the path to the file to save to.
   */
  void saveImage(String filename);

  /**
   * Overwrite an existing image with another image.
   *
   * @param filename the path to the image to overwrite with.
   */
  void overwrite(String filename) throws FileNotFoundException;

}
