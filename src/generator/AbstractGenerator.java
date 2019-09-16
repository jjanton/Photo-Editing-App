package generator;

import image.Pixel;

/**
 * This is an abstract class with methods that all generator classes implement. The constructor
 * takes a width and a height, sets up a space of that size, and by default fills it with zeros,
 * which equates to a black canvas. It includes methods to draw rectangles and stripes.
 */
abstract class AbstractGenerator implements GeneratorInterface {

  int width;
  int height;
  int[][][] data;

  Pixel red = new Pixel(255, 0, 0);
  Pixel orange = new Pixel(255, 165, 0);
  Pixel yellow = new Pixel(255, 255, 0);
  Pixel green = new Pixel(0, 128, 0);
  Pixel blue = new Pixel(0, 0, 255);
  Pixel indigo = new Pixel(75, 0, 130);
  Pixel violet = new Pixel(238, 130, 238);
  Pixel white = new Pixel(255, 255, 255);

  /**
   * Constructs a Generator object. A generator object has a given width and height, which
   * represent the width and height of the image to be generated. The Generator is also
   * initialized with a 3d array of data, representing the data that comprises an image -
   * height, width, and color (R/G/B channels).
   *
   * @param width         The width of the images to be generated.
   * @param height        The height of the images to be generated.
   * @throws IllegalArgumentException if the height or width is less than 1.
   */
  AbstractGenerator(int width, int height) throws IllegalArgumentException {
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Height and width cannot be less than 1");
    }
    this.width = width;
    this.height = height;
    this.data = new int[height][width][3];
  }

  /**
   * This method creates a rectangle and adds it to a specified drawing space. The rectangle is
   * added to the object's data array in the generator.
   *
   * @param rectWidth          The width of the rectangle to be added.
   * @param rectHeight         The height of the rectangle to be added.
   * @param x                  The x position of the top left corner of the rectangle.
   * @param y                  The y position of the top left corner of the rectangle.
   * @param color              The color of the rectangle, a image.Pixel object.
   * @throws IllegalArgumentException if the height or width is less than 1.
   */
  void generateRectangle(int rectWidth, int rectHeight, int x, int y, Pixel color)
          throws IllegalArgumentException {

    if (rectHeight < 1 || rectWidth < 1) {
      throw new IllegalArgumentException("height and width cannot be less than 1");
    }
    for (int i = 0; i < rectHeight; i++) {
      for (int j = 0; j < rectWidth; j++) {

        try {
          data[i + y][j + x][0] = color.getR();
          data[i + y][j + x][1] = color.getG();
          data[i + y][j + x][2] = color.getB();
        } catch (ArrayIndexOutOfBoundsException e) {
          continue;
        }
      }
    }
  }

  /**
   * This method generates stripes on a given image, using the generateRectangle method.
   * Each stripe on a complete image can be thought of as composed of individual rectangles.
   * @param colors            An array of colors.
   * @param numOfStripes      The number of stripes in the image.
   * @param vertical          True for vertical stripes, false for horizontal stripes.
   * @throws IllegalArgumentException        If the number of stripes is less than 1,
   *                                         or there isn't at least 1 color.
   */
  void generateStripes(Pixel[] colors, int numOfStripes, boolean vertical)
          throws IllegalArgumentException {

    if (numOfStripes < 1) {
      throw new IllegalArgumentException("Number of stripes cannot be less than 1");
    }
    if (colors.length < 1) {
      throw new IllegalArgumentException("must have at least 1 color");
    }

    if (vertical) {
      int stripeWidth = (int) Math.ceil(width / (double) numOfStripes);
      for (int i = 0; i < numOfStripes; i++) {
        generateRectangle(stripeWidth, height, stripeWidth * i,
                0, colors[i % colors.length]);
      }
    } else {
      int stripeHeight = (int) Math.ceil(height / (double) numOfStripes);
      for (int i = 0; i < numOfStripes; i++) {
        generateRectangle(width, stripeHeight, 0,
                stripeHeight * i, colors[i % colors.length]);
      }
    }
  }

}
