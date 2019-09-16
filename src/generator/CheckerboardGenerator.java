package generator;

import image.Image;
import image.ImageRGB;
import image.Pixel;

/**
 * This class generates a checkerboard image with the given proportions. A checkerboard consists of
 * a pattern of 2 alternating colors.
 */
public class CheckerboardGenerator extends AbstractGenerator {

  private Pixel white = new Pixel(255, 255, 255);
  private Pixel black = new Pixel(0, 0, 0);
  private int squareSize;

  /**
   * Constructs a checkerboard generator object sized with the given dimensions, width and height.
   *
   * @param width      The width of the checkerboard.
   * @param height     The height of the checkerboard.
   * @param squareSize The size of an individual square on the checkerboard.
   */
  public CheckerboardGenerator(int width, int height, int squareSize) {
    super(width, height);
    this.squareSize = squareSize;
  }

  /**
   * Generates a checkerboard with the object's square size.
   *
   * @return The checkerboard.
   */
  public Image generate() {
    for (int i = 0; i < this.height / squareSize; i++) {
      for (int j = 0; j < this.width / squareSize; j++) {
        if ((i + j) % 2 == 0) {
          generateRectangle(squareSize, squareSize, j * squareSize, i * squareSize, white);
        } else {
          generateRectangle(squareSize, squareSize, j * squareSize, i * squareSize, black);
        }
      }
    }
    return new ImageRGB(data);
  }
}
