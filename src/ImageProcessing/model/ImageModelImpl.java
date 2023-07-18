package ImageProcessing.model;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImageModelImpl implements ImageModel{
  private Pixel[][] pixels;

  @Override
  public void readPPM(String filename) throws FileNotFoundException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine().trim();  // Remove leading and trailing whitespace
      if (!s.isEmpty() && s.charAt(0) != '#') {  // Check if line is not empty and not a comment
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token = sc.next();
    if (!token.equals("P3")) {
      if (token.equals("P6")) {
        // If the file is in P6 format, convert it to P3
        convertP6ToP3(filename, filename);
        // Close the current file scanner
        sc.close();
        // Re-open the file scanner
        sc = new Scanner(new FileInputStream(filename));
        // Re-read the token after conversion
        token = sc.next();
      } else {
        throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
      }
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    this.pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        this.pixels[i][j] = new Pixel(r, g, b);
      }
    }
  }

  /**
   * Converts an image from P6 (binary) PPM format to P3 (ASCII) PPM format.
   *
   * @param inputFile  The file path of the input image in P6 PPM format.
   * @param outputFile The file path where the output image in P3 PPM format will be saved.
   * @throws IllegalArgumentException If an I/O error occurs during the conversion process.
   */
  private void convertP6ToP3(String inputFile, String outputFile) {
    try {
      // Read the P6 file
      FileInputStream fis = new FileInputStream(inputFile);

      // Read the header information
      BufferedReader br = new BufferedReader(new InputStreamReader(fis));
      String magicNumber = br.readLine();
      String widthHeight;
      do {
        widthHeight = br.readLine();
      } while (widthHeight.startsWith("#")); // Skip comment lines
      String maxColorValue;
      do {
        maxColorValue = br.readLine();
      } while (maxColorValue.startsWith("#")); // Skip comment lines

      // Extract width, height, and maximum color value
      String[] dimensions = widthHeight.split(" ");
      int width = Integer.parseInt(dimensions[0]);
      int height = Integer.parseInt(dimensions[1]);
      int maxValue = Integer.parseInt(maxColorValue);

      // Create a FileWriter to write the P3 file
      FileWriter fw = new FileWriter(outputFile);
      BufferedWriter bw = new BufferedWriter(fw);

      // Write the P3 header information
      bw.write("P3");
      bw.newLine();
      bw.write(width + " " + height);
      bw.newLine();
      bw.write(maxValue + "");
      bw.newLine();

      // Convert and write the pixel data
      int pixelCount = width * height;
      for (int i = 0; i < pixelCount; i++) {
        int red = fis.read();
        int green = fis.read();
        int blue = fis.read();
        bw.write(red + " " + green + " " + blue + " ");
      }

      // Close the file streams
      fis.close();
      bw.close();

      System.out.println("P6 to P3 conversion completed successfully!");
    } catch (IOException e) {
      throw new IllegalArgumentException("Error occurred during P6 to P3 conversion");
    }
  }

  @Override
  public void extractRedChannel() {
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        int red = this.pixels[i][j].getRed();
        this.pixels[i][j] = new Pixel(red, 0, 0);
      }
    }
  }

  @Override
  public void extractGreenChannel() {
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        int green = this.pixels[i][j].getGreen();
        this.pixels[i][j] = new Pixel(0, green, 0);
      }
    }
  }

  @Override
  public void extractBlueChannel() {
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        int blue = this.pixels[i][j].getBlue();
        this.pixels[i][j] = new Pixel(0, 0, blue);
      }
    }
  }

  @Override
  public void extractValue() {
    // Value is the maximum of the three color channels.
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        int value = Math.max(Math.max(this.pixels[i][j].getRed(), this.pixels[i][j].getGreen()), this.pixels[i][j].getBlue());
        this.pixels[i][j] = new Pixel(value, value, value);
      }
    }
  }

  @Override
  public void extractIntensity() {
    // Intensity is the average of the three color channels.
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        int intensity = (this.pixels[i][j].getRed() + this.pixels[i][j].getGreen() + this.pixels[i][j].getBlue()) / 3;
        this.pixels[i][j] = new Pixel(intensity, intensity, intensity);
      }
    }
  }

  @Override
  public void extractLuma() {
    // Luma is a weighted average of the three color channels.
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        int luma = (int) (0.2126 * this.pixels[i][j].getRed()
                + 0.7152 * this.pixels[i][j].getGreen()
                + 0.0722 * this.pixels[i][j].getBlue());
        this.pixels[i][j] = new Pixel(luma, luma, luma);
      }
    }
  }

  @Override
  public void convertToGreyscale(String method) {
    switch (method) {
      case "value":
        this.extractValue();
        break;
      case "intensity":
        this.extractIntensity();
        break;
      case "luma":
        this.extractLuma();
        break;
      default:
        throw new IllegalArgumentException("Unknown greyscale conversion method: " + method);
    }
  }

  @Override
  public void modifyBrightness(int amount) {
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        int r = Math.max(0, Math.min(255, this.pixels[i][j].getRed() + amount));
        int g = Math.max(0, Math.min(255, this.pixels[i][j].getGreen() + amount));
        int b = Math.max(0, Math.min(255, this.pixels[i][j].getBlue() + amount));
        this.pixels[i][j] = new Pixel(r, g, b);
      }
    }
  }

  @Override
  public void flipImage() {
    int width = this.pixels[0].length;
    int height = this.pixels.length;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        Pixel temp = this.pixels[i][j];
        this.pixels[i][j] = this.pixels[i][width - j - 1];
        this.pixels[i][width - j - 1] = temp;
      }
    }
  }

  @Override
  public Pixel[][] getPixels() {
    return this.pixels;
  }

  @Override
  public void writePPM(String filename) {
    try {
      FileWriter writer = new FileWriter(filename);

      // Write the PPM image header
      writer.write("P3\n");
      writer.write(pixels[0].length + " " + pixels.length + "\n");
      writer.write("255\n");

      // Write the pixel data
      for (int i = 0; i < pixels.length; i++) {
        for (int j = 0; j < pixels[i].length; j++) {
          writer.write(pixels[i][j].getRed() + " "
                  + pixels[i][j].getGreen()
                  + " " + pixels[i][j].getBlue()
                  + "\n");
        }
      }

      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Error occurred while writing to file: " + filename);
    }
  }
}

