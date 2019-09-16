package transformations;

import image.Image;
import image.ImageRGB;
import image.Pixel;

/**
 * This class represents a Dither transformation object. A dither object contains one public method,
 * apply, which accepts an image, applies a dither transformation, and returns the dithered version
 * of the image.
 */
public class Dither implements Transformation {

  /**
   * Constructs a Dither object, and takes no parameters.
   */
  public Dither() {
    // Does nothing upon construction.
  }

  /**
   * This method takes the given image, and compiles a new image which is a copy of the original
   * image, but with the dither transformation applied.
   *
   * @param originalImage The original image that is being filtered.
   * @return A new image, which is a copy of the original image, with the dither applied.
   */
  public Image apply(Image originalImage) {
    // Convert the original image to greyscale image
    AbstractColorTransformation greyscale = new Greyscale();
    Image originalGrey = greyscale.apply(originalImage);

    // Get data of greyscale image. Since data is a copy, I'm also using data as the 3d array
    // to be dithered, and returned.
    int[][][] data = originalGrey.getData();

    // Loop through grey image
    for (int i = 0; i < originalGrey.getHeight(); i++) {
      for (int j = 0; j < originalGrey.getWidth(); j++) {


        int oldColor = data[i][j][0];
        int newColor = determineNewColor(oldColor);
        int error = oldColor - newColor;

        try {
          // Create pixels for each of the 4 surrounding pixels. so that we can use the image
          // constructor's clamping.

          // image.Pixel at (r,c+1)
          Pixel pixel1 = new Pixel((data[i + 1][j][0] += (int) (error * (7. / 16)))
                  , (data[i + 1][j][1] += (int) (error * (7. / 16)))
                  , (data[i + 1][j][2] += (int) (error * (7. / 16))));

          // image.Pixel at (r+1,c-1)
          Pixel pixel2 = new Pixel((data[i - 1][j + 1][0] += (int) (error * (7. / 16))),
                  (data[i - 1][j + 1][1] += (int) (error * (7. / 16))),
                  (data[i - 1][j + 1][2] += (int) (error * (7. / 16))));

          // image.Pixel at (r+1,c)
          Pixel pixel3 = new Pixel((data[i][j + 1][0] += (int) (error * (5. / 16))),
                  (data[i][j + 1][1] += (int) (error * (5. / 16))),
                  (data[i][j + 1][2] += (int) (error * (5. / 16))));

          // image.Pixel at (r+1,c+1)
          Pixel pixel4 = new Pixel((data[i + 1][j + 1][0] += (int) (error * (1. / 16))),
                  (data[i + 1][j + 1][1] += (int) (error * (1. / 16))),
                  (data[i + 1][j + 1][2] += (int) (error * (1. / 16))));


          // Modify the color of the target image at [i][j]
          data[i][j][0] = newColor;
          data[i][j][1] = newColor;
          data[i][j][2] = newColor;

          // Modify the image color for (r,c+1)
          data[i + 1][j][0] = pixel1.getR();
          data[i + 1][j][1] = pixel1.getG();
          data[i + 1][j][2] = pixel1.getB();

          // Modify image for (r+1, c-1)
          data[i - 1][j + 1][0] = pixel2.getR();
          data[i - 1][j + 1][1] = pixel2.getG();
          data[i - 1][j + 1][2] = pixel2.getB();

          // Modify image for (r+1, c)
          data[i][j + 1][0] = pixel3.getR();
          data[i][j + 1][1] = pixel3.getG();
          data[i][j + 1][2] = pixel3.getB();

          // Modify image for (r+1, c+1)
          data[i + 1][j + 1][0] = pixel4.getR();
          data[i + 1][j + 1][1] = pixel4.getG();
          data[i + 1][j + 1][2] = pixel4.getB();
        } catch (ArrayIndexOutOfBoundsException e) {
          continue;
        }
      }
    }
    return new ImageRGB(data);
  }

  /**
   * This is a helper method for applying a dither. This method determines if the given number is
   * closer to black or white, and returns either 0 or 255.
   *
   * @param num The color being compared.
   * @return 0 or 255, depending on which number num is closer to.
   */
  private int determineNewColor(int num) {
    if (Math.abs(255 - num) < Math.abs(0 - num)) {
      return 255;
    } else {
      return 0;
    }
  }

}