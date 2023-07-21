package ImageProcessing.model;


import java.io.FileNotFoundException;

/**
 * The ImageModel interface represents the model component in the Model-View-Controller.
 * This interface contains methods for manipulating images.
 */
public interface ImageModel {
  /**
   * Load an image from an ASCII PPM file.
   *
   * @param filename the path and name of the file to read.
   */
  void readPPM(String filename) throws FileNotFoundException;

  /**
   * Extract the red color channel from the image.
   */
  void extractRedChannel();

  /**
   * Extract the green color channel from the image.
   */
  void extractGreenChannel();

  /**
   * Extract the blue color channel from the image.
   */
  void extractBlueChannel();

  /**
   * Extract the value component from the image.
   */
  void extractValue();

  /**
   * Extract the intensity component from the image.
   */
  void extractIntensity();

  /**
   * Extract the luma component from the image.
   */
  void extractLuma();

  /**
   * Convert the image to greyscale using the specified method (value, intensity, or luma).
   *
   * @param method the method to use for the greyscale conversion.
   */
  void convertToGreyscale(String method);

  /**
   * Modify the brightness of the image.
   *
   * @param amount the amount to adjust the brightness by.
   */
  void modifyBrightness(int amount);

  /**
   * Flips the current image horizontally.
   */
  void flipImage();

  /**
   * Get the pixels of the current image.
   *
   * @return a 2D array of Pixel objects representing the image.
   */
  Pixel[][] getPixels();

  /**
   * Save the current image to an ASCII PPM file.
   *
   * @param filename the path and name of the file to write.
   */
  void writePPM(String filename);
}

