package image;

/**
 * This class represents a single image.Pixel. A image.Pixel is represented by its color,
 * given in RGB values between 0-255.
 */
public class Pixel {

  private int r;
  private int g;
  private int b;

  /**
   * Constructs a single image.Pixel. A image.Pixel has a given red, green, and blue
   * channel value, which determines the color of the image. Upon construction, any
   * channel values that are outside the range of 0-255 are clamped to be within the range.
   *
   * @param r         the red channel of the image.
   * @param g         the green channel of the image.
   * @param b         the blue channel of the image.
   */
  public Pixel(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
    this.clampPixel();
  }

  /**
   * Gets and returns the image's red channel value.
   *
   * @return        The image's red channel value.
   */
  public int getR() {
    return r;
  }

  /**
   * Gets and returns the image's green channel value.
   *
   * @return        The image's green channel value.
   */
  public int getG() {
    return g;
  }

  /**
   * Gets and returns the image's blue channel value.
   *
   * @return        The image's blue channel value.
   */
  public int getB() {
    return b;
  }

  /**
   * This method determines if a image's channel value is outside of the range 0-255.
   * If so, the value is clamped to the maximum or minimum value, depending.
   */
  private void clampPixel() {
    if (r > 255) {
      r = 255;
    } else if (r < 0) {
      r = 0;
    }
    if (g > 255) {
      g = 255;
    } else if (g < 0) {
      g = 0;
    }
    if (b > 255) {
      b = 255;
    } else if (b < 0) {
      b = 0;
    }
  }

}
