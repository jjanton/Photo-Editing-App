package transformations;

import image.Image;


/**
 * This interface represents all methods that Transformation is expected to support.
 * Transformations can be color transformations or filters, and each implementation of
 * Transformation has its own implementation of the apply method.
 */
public interface Transformation {
  /**
   * This method takes the given image, and compiles a new image which is a copy of the original
   * image, but with the the ransformation applied.
   * @param originalImage         The original image to be modified.
   * @return                      A new image, which is a copy of the original image,
   *                              with the transformation applied.
   */
  Image apply(Image originalImage);
}
