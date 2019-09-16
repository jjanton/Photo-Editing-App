package generator;

import image.Image;
import image.ImageRGB;
import image.Pixel;

/**
 * This class generates a Greek flag with standard proportions, or 3:2 proportions.
 */
public class GreeceGenerator extends AbstractGenerator {

  private Pixel[] greece = {blue, white};

  /**
   * Constructs a greeceGenerator object, with the provided height. A greecegenerator
   * is constructed with the super constructor from the abstract class, using the given height,
   * and the width calculated using the height.
   * @param height          The height of the flag.
   */
  public GreeceGenerator(int height) {
    //start with no width, as this is variable by flag shape.
    super(height, height * 2 / 3);
  }


  /**
   * This method generates the flag of Greece. The Greek flag is composed of 3 layers.
   *
   * @return                The Greek flag.
   */
  public Image generate() {
    generateStripes(greece, 9, false);
    int cornerWidth = (int) Math.ceil(width * 10.0 / 27.0);
    int crossWidth = (int) Math.ceil(width * 2.0 / 27.0);
    generateRectangle(cornerWidth, cornerWidth, 0, 0, blue);
    generateRectangle(crossWidth, cornerWidth, 2 * crossWidth, 0, white);
    generateRectangle(cornerWidth, crossWidth, 0, 2 * crossWidth, white);

    return new ImageRGB(data);
  }

}

