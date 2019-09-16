package transformations;


import image.Image;
import image.ImageRGB;
import image.Pixel;

/**
 * This class constructs a color transformation object with the ability to apply a given
 * transformation to an image. A color transformation is represented by a 2d array of 3x3
 * data.
 */
public abstract class AbstractColorTransformation implements Transformation {

  private double[][] matrix;


  /**
   * Constructs a filter with the given kernel matrix. A kernel is a 2d array that is used to
   * perform operations on a specified number of pixels surrounding a target image, in order to
   * determine a new value for the target image.
   *
   * @param matrix A 2d array used to perform color transformation operations on every image in an
   *               image.
   */
  public AbstractColorTransformation(double[][] matrix) {
    this.matrix = matrix;
  }

  /**
   * Performs color calculations on a single image, and returns the modified image.
   *
   * @param initialRGB The initial RGB values of the image.
   * @return The modified image.
   */
  private Pixel calculateRGB(Pixel initialRGB) {
    double resultR;
    double resultG;
    double resultB;

    //this is the calc from step 5 of assignment
    resultR = matrix[0][0] * initialRGB.getR() + matrix[0][1] * initialRGB.getG()
            + matrix[0][2] * initialRGB.getB();
    resultG = matrix[1][0] * initialRGB.getR() + matrix[1][1] * initialRGB.getG()
            + matrix[1][2] * initialRGB.getB();
    resultB = matrix[2][0] * initialRGB.getR() + matrix[2][1] * initialRGB.getG()
            + matrix[2][2] * initialRGB.getB();

    return new Pixel((int) resultR, (int) resultG, (int) resultB);
  }


  /**
   * Applies a transformation to every pixel in a given image.
   *
   * @param originalImage The original image to be modified.
   * @return A copy of the original image, with the color transformation applied.
   */
  public Image apply(Image originalImage) {
    int[][][] newImageData = new int[originalImage.getHeight()][originalImage.getWidth()][3];
    int[][][] data = originalImage.getData();

    for (int i = 0; i < originalImage.getHeight(); i++) {
      for (int j = 0; j < originalImage.getWidth(); j++) {
        Pixel original = new Pixel(data[i][j][0], data[i][j][1], data[i][j][2]);
        Pixel newPix = calculateRGB(original);
        newImageData[i][j][0] = newPix.getR();
        newImageData[i][j][1] = newPix.getG();
        newImageData[i][j][2] = newPix.getB();
      }

    }
    return new ImageRGB(newImageData);
  }
}

