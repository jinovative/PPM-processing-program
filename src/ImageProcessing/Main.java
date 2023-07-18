package ImageProcessing;

import ImageProcessing.controller.ImageController;
import ImageProcessing.controller.ImageControllerImpl;
import ImageProcessing.model.ImageModel;
import ImageProcessing.model.ImageModelImpl;
import ImageProcessing.view.ImageView;
import ImageProcessing.view.ImageViewImpl;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // Create an instance of the model, view, and controller.
    ImageModel model = new ImageModelImpl();
    ImageView view = new ImageViewImpl();
    ImageController controller = new ImageControllerImpl(model, view);

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter the name of the image file:");
    String imageName = scanner.nextLine();

    System.out.println("Select an operation:");
    System.out.println("1. Greyscale");
    System.out.println("2. Brighter");
    System.out.println("3. Darker");
    System.out.println("4. Flip");

    int selection = scanner.nextInt();

    try {
      controller.loadImage(imageName + ".ppm");

      switch (selection) {
        case 1:
          controller.convertToGreyscale("luma");
          controller.saveImage(imageName + "-greyscale.ppm");
          break;
        case 2:
          controller.changeBrightness(10);
          controller.saveImage(imageName + "-brighter.ppm");
          break;
        case 3:
          controller.changeBrightness(-10);
          controller.saveImage(imageName + "-darker.ppm");
          break;
        case 4:
          // Assuming there is a method in the controller to flip the image
          controller.flipImage();
          controller.saveImage(imageName + "-flipped.ppm");
          break;
        default:
          System.out.println("Invalid selection.");
          break;
      }
    } catch (Exception e) {
      view.displayError(e.getMessage());
    }

    scanner.close();
  }
}


