Overview
This application provides basic image processing operations such as converting an image to greyscale, adjusting brightness, flipping the image, and saving the modified image.

Components
The application is based on the MVC (Model-View-Controller) design pattern, with several classes and interfaces forming the structure of the application.

Model
The ImageModel interface defines methods for the various image processing operations. The ImageModelImpl class implements this interface and provides the logic for each operation.

View
The ImageView interface defines methods for updating the displayed image and displaying error messages. The ImageViewImpl class implements this interface and provides a simple text-based view in the console.

Controller
The ImageController interface defines methods for loading an image, performing image processing operations, and saving the image. The ImageControllerImpl class implements this interface and coordinates the actions between the model and the view.

Main
The Main class contains the main method that starts the application. It creates instances of the model, view, and controller, and provides a simple text-based user interface for performing image processing operations.

Operation
The application operates by reading an image file in PPM format, performing one or more image processing operations on the image, and then saving the modified image to a new PPM file. The user can choose the operation from a menu, and the application will display the modified image and any error messages in the console.

Getting Started
To use the ImageProcessing application, follow these steps:

Place your PPM image files in the ImageProcessing directory.
Open a terminal or command prompt and navigate to the project directory.
Compile the Java source files by running the command: javac ImageProcessing/*.java.
Run the application by executing the command: java ImageProcessing.Main.

Usage
Once the application is running, follow the on-screen prompts to perform image processing operations. Here's an overview of the available options:

Convert to Greyscale: This option converts the image to greyscale using the Luma method.
Adjust Brightness: This option increases or decreases the brightness of the image.
Flip Image: This option flips the image horizontally.
Save Image: This option saves the modified image in the images directory with a new filename.
Save Pixels as Text: This option saves the pixel values of the image as a text file in the res directory.

Future Enhancements
Possible future enhancements include adding more image processing operations, improving the user interface, and adding support for more image file formats.


Image Source: https://people.sc.fsu.edu/~jburkardt/data/ppma/ppma.html

