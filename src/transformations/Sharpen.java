package transformations;

/**
 * This class represents a transformations.Sepia transformation object.
 * This class takes in an image, and returns a sepia copy of the image.
 */
public class Sharpen extends AbstractFilter {

  /**
   * This constructs a transformations.Sharpen filter object, and takes no parameters.
   * Upon construction, the Abstract class's constructor is called, and given a 2d array
   * representing the sharpen calculations.
   */
  public Sharpen() {
    super(new double[][]{{-(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0)}
    ,{-(1.0 / 8.0), (1.0 / 4.0), (1.0 / 4.0), (1.0 / 4.0), -(1.0 / 8.0)}
    ,{-(1.0 / 8.0), (1.0 / 4.0), (1.0), (1.0 / 4.0), -(1.0 / 8.0)}
    ,{-(1.0 / 8.0), (1.0 / 4.0), (1.0 / 4.0), (1.0 / 4.0), -(1.0 / 8.0)}
    ,{-(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0)}});
  }
}

