package ImageProcessing.controller;

public interface ImageController {
  // Load an image from an ASCII PPM file.
  void loadImage(String filename);

  // Create images that visualize individual R, G, B components of an image.
  void extractRedChannel();
  void extractGreenChannel();
  void extractBlueChannel();

  // Create images that visualize the value, intensity or luma of an image.
  void extractValue();
  void extractIntensity();
  void extractLuma();

  // Brighten or darken an image.
  void changeBrightness(int amount);

  // Save an image to an ASCII PPM file.
  void saveImage(String filename);

  // Overwrite an existing image with another image.
  void overwrite(String filename);
}
