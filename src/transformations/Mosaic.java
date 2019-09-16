package transformations;

import image.Image;
import image.ImageRGB;

/**
 * This class represents a Mosaic transformation object. This class takes in an image, and
 * returns a grayscale copy of the image.
 */
public class Mosaic implements Transformation {

  private int seedNumber;
  private int[] randomPixelsX;
  private int[] randomPixelsY;

  /**
   * This constructs a Mosaic filter object with the given seed number. Upon construction, two
   * instance variables are created to hold the set of random pixels, or seeds.
   * @param seedNumber                  The number of seeds the mosaic should be created with.
   * @throws IllegalArgumentException   if the seed number is less than 1.
   */
  public Mosaic(int seedNumber) throws IllegalArgumentException {
    if (seedNumber < 1) {
      throw new IllegalArgumentException("SeedNumber must be greater than 0");
    }
    this.seedNumber = seedNumber;
    randomPixelsX = new int[seedNumber];
    randomPixelsY = new int[seedNumber];
  }


  /**
   * This method takes the given image, and compiles a new image which is a copy of the original
   * image, but with the mosaic transformation applied.
   *
   * @param original        The original image that is being filtered.
   * @return                A new image, which is a copy of the original image,
   */
  public Image apply(Image original) {
    //Select seedNumber random pixels
    //assuming duplicate image values rare enough to not be a concern
    int width = original.getWidth();
    int height = original.getHeight();
    //int[] randomPixelsX = new int[seedNumber];
    //int[] randomPixelsY = new int[seedNumber];
    for (int i = 0; i < seedNumber; i++) {
      randomPixelsX[i] = (int) (Math.random() * width);
      randomPixelsY[i] = (int) (Math.random() * height);
    }

    int[][][] data = original.getData();

    //assign each x/y to a cluster, and average the pixels at the same time.
    int[][] clusterAssignments = new int[height][width];
    //temporarily use the "averages" value to store the total, then divide after total number of
    //cluster pixels is known. This is a weird array in that its first value is for the seed, but it
    //has 3 depth to represent the RGB colors. Its initialized with all 0's so it can be added to as
    //needed and then divided later.
    int[][] clusterPreAverages = new int[seedNumber][3];
    //used for calculating the average:
    int[] quantityPerCluster = new int[seedNumber];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int clusterValue = findClosestCluster(j, i);
        clusterAssignments[i][j] = findClosestCluster(j, i);
        //System.out.print(clusterAssignments[i][j] + " ");

        quantityPerCluster[clusterValue] += 1;
        clusterPreAverages[clusterValue][0] += data[i][j][0];
        clusterPreAverages[clusterValue][1] += data[i][j][1];
        clusterPreAverages[clusterValue][2] += data[i][j][2];
      }
      //System.out.println("\n");
    }
    //perform the averaging calculation:
    for (int i = 0; i < seedNumber; i++) {
      if (quantityPerCluster[i] != 0) {
        clusterPreAverages[i][0] = clusterPreAverages[i][0] / quantityPerCluster[i];
        clusterPreAverages[i][1] = clusterPreAverages[i][1] / quantityPerCluster[i];
        clusterPreAverages[i][2] = clusterPreAverages[i][2] / quantityPerCluster[i];
      }
    }
    //now clusterPreAverages are the actual averages
    //reusing data value:
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int cluster = clusterAssignments[i][j];
        data[i][j][0] = clusterPreAverages[cluster][0];
        data[i][j][1] = clusterPreAverages[cluster][1];
        data[i][j][2] = clusterPreAverages[cluster][2];
      }
    }

    //set image to new value.
    return new ImageRGB(data);
  }

  private double getDistance(int x1, int y1, int x2, int y2) {
    return Math.pow(x1 - x2, 2) + Math.pow((y1 - y2), 2);
  }

  /**
   * This is a helper method for applying a mosaic to an image. After creating seeds from the
   * original image, this method finds the closest seed to a pixel using the euclidean distance
   * formula.
   * @param locationX         The x location of the pixel.
   * @param locationY         The y location of the pixel.
   * @return                  The value of the cluster.
   */
  private int findClosestCluster(int locationX, int locationY) {

    double minDistance = Double.POSITIVE_INFINITY;
    int clusterValue = -1;
    for (int i = 0; i < seedNumber; i++) {
      double currentDistance = getDistance(randomPixelsX[i], randomPixelsY[i]
              ,locationX, locationY);
      if (currentDistance < minDistance) {
        minDistance = currentDistance;
        clusterValue = i;
      }
    }
    return clusterValue;
  }
}
