package generator;

import image.Image;
import image.ImageRGB;

/**
 * This class generates a Swiss flag with square proportions.
 */
public class SwissGenerator extends AbstractGenerator {

  /**
   * This constructs a square flag generator object, which creates a generator object with the
   * provided height value, and its width value equal to its height.
   *
   * @param height the height of the flag.
   */
  public SwissGenerator(int height) {
    super(height, height);
  }

  /**
   * This method generates the Swiss flag.
   *
   * @return the Swiss flag.
   */
  public Image generate() {
    int crossHeight = height * 5 / 8;
    int crossThickness = height / 6;

    this.generateRectangle(height, height, 0, 0, red);
    this.generateRectangle(crossThickness, crossHeight,
            (int) (height * 13.0 / 32.0), height / 6, white);
    this.generateRectangle(crossHeight, crossThickness, height / 6,
            (int) (height * 13.0 / 32.0), white);

    return new ImageRGB(data);
  }
}
