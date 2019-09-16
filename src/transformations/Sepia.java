package transformations;

/**
 * This class represents a transformations.Sepia transformation object.
 * This class takes in an image, and returns a sepia copy of the image.
 */
public class Sepia extends AbstractColorTransformation {

  /**
   * This constructs a transformations.Sepia filter object, and takes no parameters.
   * Upon construction, the Abstract class's constructor is called, and given a 2d array
   * representing the sepia calculations.
   */
  public Sepia() {
    super(new double[][]{{.393, .769, .189}
    ,{.349, .686, .168}
    ,{.272, .534, .131}});
  }
}
