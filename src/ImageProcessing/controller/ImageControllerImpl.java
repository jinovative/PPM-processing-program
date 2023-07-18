package ImageProcessing.controller;

import java.io.FileNotFoundException;

import ImageProcessing.model.ImageModel;
import ImageProcessing.view.ImageView;

public class ImageControllerImpl implements ImageController {
  private ImageModel model;
  private ImageView view;

  public ImageControllerImpl(ImageModel model, ImageView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void loadImage(String filename) throws FileNotFoundException {
    model.readPPM(filename);
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractRedChannel() {
    model.extractRedChannel();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractGreenChannel() {
    model.extractGreenChannel();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractBlueChannel() {
    model.extractBlueChannel();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractValue() {
    model.extractValue();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractIntensity() {
    model.extractIntensity();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractLuma() {
    model.extractLuma();
    view.updateImage(model.getPixels());
  }

  @Override
  public void convertToGreyscale(String method) {
    model.convertToGreyscale(method);
    view.updateImage(model.getPixels());
  }

  @Override
  public void changeBrightness(int amount) {
    model.modifyBrightness(amount);
    view.updateImage(model.getPixels());
  }

  @Override
  public void flipImage() {
    model.flipImage();
    view.updateImage(model.getPixels());
  }

  @Override
  public void saveImage(String filename) {
    model.writePPM(filename);
  }

  @Override
  public void overwrite(String filename) throws FileNotFoundException {
    model.readPPM(filename);
    view.updateImage(model.getPixels());
  }
}
