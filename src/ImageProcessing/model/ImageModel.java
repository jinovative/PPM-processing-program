package ImageProcessing.model;

public interface ImageModel {
  // Load an image from an ASCII PPM file.
  void readPPM(String filename);

  // Create images that visualize individual R, G, B components of an image.
  void extractRedChannel();
  void extractGreenChannel();
  void extractBlueChannel();

  // Create images that visualize the value, intensity or luma of an image.
  void extractValue();
  void extractIntensity();
  void extractLuma();

  // Convert image to greyscale using value, intensity or luma.
  void convertToGreyscale(String method);

  // Brighten or darken an image.
  void modifyBrightness(int amount);

  // Get the pixels of the current image.
  Pixel[][] getPixels();

  // Save an image to an ASCII PPM file.
  void writePPM(String filename);
}

