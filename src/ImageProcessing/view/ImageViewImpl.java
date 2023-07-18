package ImageProcessing.view;

import ImageProcessing.model.Pixel;

public class ImageViewImpl implements ImageView{

  @Override
  public void displayUI() {
    System.out.println("UI is displayed.");
  }

  @Override
  public void updateImage(Pixel[][] pixels) {
    System.out.println("Image updated with size: " + pixels.length + "x" + pixels[0].length);
    System.out.println("First few pixels:");
    for (int i = 0; i < Math.min(5, pixels.length); i++) {
      for (int j = 0; j < Math.min(5, pixels[i].length); j++) {
        System.out.println("Pixel at (" + i + "," + j + "): "
                + pixels[i][j].getRed() + ","
                + pixels[i][j].getGreen() + ","
                + pixels[i][j].getBlue());
      }
    }
  }

  @Override
  public void displayMessage(String message) {
    System.out.println("Message: " + message);
  }

  @Override
  public void displayError(String error) {
    System.err.println("Error: " + error);
  }
}
