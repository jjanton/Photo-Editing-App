package image;

/**
 * This interface represents all methods that an image implementation is expected to support.
 * Images can be loaded in from a filepath, and its width, height, and a 3d array of pixels
 * representing the image's data can be built.
 */
public interface Image {

  /**
   * This method writes and outputs an image, using the provided filename.
   * @param filename                           The name of the file to be outputted.
   * @throws IllegalArgumentException          If the file cannot be written to the provided path.
   */
  void write(String filename) throws IllegalArgumentException;

  /**
   * Gets and returns the width of the image.
   * @return           The width of the image.
   */
  int getWidth();

  /**
   * Gets and returns the height of the image.
   * @return           The height of the image.
   */
  int getHeight();

  /**
   * This method creates a copy of the image's data, and returns the copy to
   * the calling object.
   * @return           A copy of the image's data.
   */
  int[][][] getData();

}
