package transformations;

/**
 * This class represents a Grayscale transformation object. This class takes in an image, and
 * returns a grayscale copy of the image.
 */
public class Greyscale extends AbstractColorTransformation {

  /**
   * This constructs a Greyscale filter object, and takes no parameters. Upon construction, the
   * Abstract class's constructor is called, and given a 2d array representing the Grayscale
   * calculations.
   */
  public Greyscale() {
    super(new double[][]{{0.2126, 0.7152, 0.0722}
    ,{0.2126, 0.7152, 0.0722}
    ,{0.2126, 0.7152, 0.0722}});
  }
}

