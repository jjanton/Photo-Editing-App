package transformations;

/**
 * This class represents transformations.Blur filter object. This class takes in an
 * image, and returns a blurred copy of the image.
 */
public class Blur extends AbstractFilter {

  /**
   * This constructs a Blur filter object, and takes no parameters. Upon construction,
   * the Abstract class's constructor is called, and given a 2d array representing
   * the blur calculations.
   */
  public Blur() {
    super(new double[][]{{1.0 / 16, 1.0 / 8, 1.0 / 16}
    ,{1.0 / 8, 1.0 / 4, 1.0 / 8}
    ,{1.0 / 16, 1.0 / 8, 1.0 / 16}});
  }
}
