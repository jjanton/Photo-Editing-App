package image;

import java.io.IOException;

/**
 * This class stores image data as a 3D array where dimension 0 represents x position
 * of a image, 1 represents the y position of the image, and dimension 2 represents
 * a color chanel, R, B or G. The height and width of the image is also stored.
 */
public class ImageRGB implements Image {

  private int[][][] data;
  private int width;
  private int height;


  /**
   * Constructs an image.ImageRGB object using the given filename. An image object
   * is loaded, and the width, height, and 3d array of the image are determined and stores
   * as height, width, and data.
   *
   * @param filename                        the path of the image file to be loaded.
   * @throws IllegalArgumentException       if the given filename cannot be found.
   */
  public ImageRGB(String filename) throws IllegalArgumentException {
    try {
      this.data = ImageUtil.readImage(filename);
      this.width = ImageUtil.getWidth(filename);
      this.height = ImageUtil.getHeight(filename);
    } catch (IOException e) {
      throw new IllegalArgumentException("File not found");
    }
  }

  /**
   * This class sets the value of the data.
   *
   * @param data                            the data of the image.
   */
  public ImageRGB(int[][][] data) {
    this.data = data;
    this.width = data[0].length;
    this.height = data.length;
  }

  /**
   * This method writes and outputs an image, using the provided filename.
   *
   * @param filename                        The name of the file to be outputted.
   * @throws IllegalArgumentException       If the file cannot be written to the provided path.
   */
  public void write(String filename) throws IllegalArgumentException {
    try {
      ImageUtil.writeImage(data, width, height, filename);

    } catch (IOException e) {
      throw new IllegalArgumentException("File cannot be written to provided path");
    }
  }

  /**
   * This method creates a copy of the image's data, and returns the copy to the calling
   * object.
   *
   * @return                                A copy of the image's data.
   */
  public int[][][] getData() {
    int[][][] dataCopy = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        dataCopy[i][j][0] = data[i][j][0];
        dataCopy[i][j][1] = data[i][j][1];
        dataCopy[i][j][2] = data[i][j][2];
      }
    }
    return dataCopy;
  }

  /**
   * Gets and returns the width of the image.
   *
   * @return                               The width of the image.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Gets and returns the height of the image.
   *
   * @return                               The height of the image.
   */
  public int getHeight() {
    return height;
  }

  /**
   * This builds a string representation of each image (x position, y position, 3 channels
   * per pixel) and returns the string.
   *
   * @return The string representation of an image.
   */
  public String toString() {
    StringBuilder str = new StringBuilder("");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        str.append("(" + data[i][j][0] + "," + data[i][j][1] + "," + data[i][j][2] + ")");
      }
      str.append("\n");
    }
    return str.toString();
  }


}
