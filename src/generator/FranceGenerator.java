package generator;

import image.Image;
import image.ImageRGB;
import image.Pixel;

/**
 * This class generates a French flag with standard proportions, or 3:2 proportions.
 */
public class FranceGenerator extends AbstractGenerator {

  private Pixel[] france = {red, white, blue};

  /**
   * This constructs a standard flag generator object, which creates a generator object with the
   * provided height value sized to 2:3 proportions.
   *
   * @param height          the height of the flag, sized proportionally.
   */
  public FranceGenerator(int height) {
    //start with no width, as this is variable by flag shape.
    super(height, height * 2 / 3);
  }

  /**
   * This method generates the French flag.
   *
   * @return                The french flag.
   */
  public Image generate() {
    generateStripes(france, 3, true);
    return new ImageRGB(data);
  }


}
