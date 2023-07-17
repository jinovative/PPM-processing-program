package ImageProcessing.view;

import ImageProcessing.model.Pixel;


public interface ImageView {
  void displayUI();
  void updateImage(Pixel[][] pixels);
  void displayMessage(String message);
  void displayError(String error);
}

