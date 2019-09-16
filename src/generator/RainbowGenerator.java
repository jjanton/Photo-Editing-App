package generator;

import image.Image;
import image.ImageRGB;
import image.Pixel;

/**
 * This class generates a flag with the colors of the rainbow. The rainbow flag is of the given
 * height and width, and contains 7 stripes, one for each color of the rainbow.
 */
public class RainbowGenerator extends AbstractGenerator {


  private boolean vertical;
  private Pixel[] rainbow = {red, orange, yellow, green, blue, indigo, violet};

  /**
   * Constructs a rainbow generator object, with the provided width, height, and boolean.
   * A rainbow generator calls the super constructor, and provides the width and height.
   * @param width         The width of the rainbow.
   * @param height        The height of the rainbow.
   * @param vertical      True if vertical stripes, false if horizontal stripes.
   */
  public RainbowGenerator(int width, int height, boolean vertical) {
    super(width, height);
    this.vertical = vertical;
  }

  /**
   * Generates a rainbow flag with 7 vertical stripes, one for each color of the rainbow.
   *
   * @return             The vertical rainbow.
   */
  public Image generate() {
    generateStripes(rainbow, 7, vertical);
    return new ImageRGB(data);
  }

}
