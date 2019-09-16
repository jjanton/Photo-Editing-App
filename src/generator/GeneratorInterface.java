package generator;

import image.Image;

/**
 * This interface represents an image generator, and the single method that any generator is
 * expected to implement. The generate method creates an image file determined by the
 * specific implementation of the interface.
 */
public interface GeneratorInterface {

  /**
   * This method generates and outputs an image file, determined by a classes specific
   * implementation.
   *
   * @return          An image file.
   */
  Image generate();

}
