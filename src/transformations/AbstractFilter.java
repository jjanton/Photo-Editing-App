package transformations;

import image.Image;
import image.ImageRGB;
import image.Pixel;

/**
 * This class constructs a filter object with the ability to apply a given filter
 * to an image. A filter is represented by a kernel, which is a 2d array.
 */
public abstract class AbstractFilter implements Transformation {

  private double[][] kernel;

  /**
   * Constructs a filter with the given kernel matrix. A kernel is a 2d array that is used to
   * perform operations on a specified number of pixels surrounding a target image,
   * in order to determine a new value for the target image.
   *
   * @param kernel                          A 2d array, also referred to as an x by x matrix.
   *                                        X must be odd (eg. 3x3, 5x5).
   * @throws IllegalArgumentException       if the kernel does not have square dimensions,
   *                                        or the kernel is not an odd number.
   */
  public AbstractFilter(double[][] kernel) throws IllegalArgumentException {
    if (kernel.length != kernel[0].length) {
      throw new IllegalArgumentException("Kernel must be a square");
    }
    if (kernel.length % 2 == 0) {
      throw new IllegalArgumentException("Kernel length must be an odd number");
    }
    this.kernel = kernel;
  }

  /**
   * This method creates a chunk from an image. A chunk is created by utilizing a chunk size,
   * which is the desired amount of pixels around the target image to use.
   * @param data                 The image's 3d array.
   * @param x                    The x value of the target image.
   * @param y                    The y value of the target image.
   * @param chunkSize            The size of the chunk. A chunk will be represented
   *                             by a chunksize x chunksize 2d array.
   * @return                     A chunk, which is an exact copy of a chunk from the orignial
   *                             image.
   * @throws IllegalArgumentException     If the chunk size is not odd, or less than 1.
   */
  private Image makeChunk(int[][][] data, int x, int y, int chunkSize)
          throws IllegalArgumentException {

    if (chunkSize % 2 == 0 || chunkSize < 1) {
      throw new IllegalArgumentException("Chunk size must be an odd number, greater than 0");
    }

    int[][][] chunk = new int[chunkSize][chunkSize][3];
    int spread = chunkSize / 2;

    for (int i = 0; i < chunkSize; i++) {
      for (int j = 0; j < chunkSize; j++) {
        try {
          chunk[i][j][0] = data[x + i - spread][y + j - spread][0];
          chunk[i][j][1] = data[x + i - spread][y + j - spread][1];
          chunk[i][j][2] = data[x + i - spread][y + j - spread][2];
        } catch (IndexOutOfBoundsException e) {
          chunk[i][j][0] = 0;
          chunk[i][j][1] = 0;
          chunk[i][j][2] = 0;
        }
      }
    }
    return new ImageRGB(chunk);
  }

  /**
   * Calculates the RBG values for a specific image at the center of a given chunk,
   * by multiplying each item in the chunk matrix by its corresponding item in the
   * filter matrix for each of the 3 channels and then summing them. After the image
   * value is calculated, any necessary clamping is handled by the image object when
   * a new image is created.
   *
   * @param chunk         an image (3D , representing a portion of the larger image.
   * @return              The new image value, as an array of 3 ints, all between 0 and 255
   *                      representing the three channels of the image.
   */
  private Pixel calculatePixelValue(Image chunk) {
    int resultR = 0;
    int resultG = 0;
    int resultB = 0;

    //Gets the data from the chunk image. Then calculates each channel of the image.
    int[][][] data = chunk.getData();

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        resultR += kernel[i][j] * data[i][j][0];
        resultG += kernel[i][j] * data[i][j][1];
        resultB += kernel[i][j] * data[i][j][2];
      }
    }
    int[] resultArray = {(int) resultR, (int) resultG, (int) resultB};
    return new Pixel(resultR, resultG, resultB);//resultArray;
  }

  /**
   * This method takes the given image, and compiles a new image which is a copy of the original
   * image, but with the filter applied.
   *
   * @param originalImage        The original image that is being filtered.
   * @return                     A new image, which is a copy of the original image,
   *                             with the filter applied.
   */
  public Image apply(Image originalImage) {
    int[][][] originalData = originalImage.getData();
    int[][][] newImageData = new int[originalImage.getHeight()][originalImage.getWidth()][3];


    for (int i = 0; i < originalImage.getHeight(); i++) {
      for (int j = 0; j < originalImage.getWidth(); j++) {
        Image chunk = makeChunk(originalData, i, j, kernel.length);
        Pixel pix = calculatePixelValue(chunk);
        newImageData[i][j][0] = pix.getR();
        newImageData[i][j][1] = pix.getG();
        newImageData[i][j][2] = pix.getB();
      }

    }
    return new ImageRGB(newImageData);
  }


}