/**
 * This interface represents all methods that a features implementation is expected to support.
 * A features implementation is typically utilized by a controller, inbetween a model and a view.
 */
public interface Features {

  /**
   * Opens the Jfilechooser window to access and load a local stored image. Upon selecting an
   * image, the image is stored in the model, and the view is updated to display the new image.
   */
  void loadImage();

  /**
   * Opens the Jfile chooser, and allows the user to type their filename, and save their image
   * to the directory of their choice. If there is no image to save, an error message pops up.
   */
  void saveImage();

  /**
   * Applies a greyscale transformation to the image, and updates the view to display the
   * transformed image. If there is no image to apply the transformation to, pops up an
   * error message.
   */
  void greyscale();


  /**
   * Applies a sepia transformation to the image, and updates the view to display the
   * transformed image. If there is no image to apply the transformation to, pops up an
   * error message.
   */
  void sepia();

  /**
   * Applies a blur transformation to the image, and updates the view to display the
   * transformed image. If there is no image to apply the transformation to, pops up an
   * error message.
   */
  void blur();

  /**
   * Applies a sharpen transformation to the image, and updates the view to display the
   * transformed image. If there is no image to apply the transformation to, pops up an
   * error message.
   */
  void sharpen();

  /**
   * Applies a dither transformation to the image, and updates the view to display the
   * transformed image. If there is no image to apply the transformation to, pops up an
   * error message.
   */
  void dither();

  /**
   * Applies a mosaic transformation to the image with the user provided seed count,
   * and updates the view to display the transformed image. If there is no image to
   * apply the transformation to, pops up an error message.
   */
  void mosaic();

  /**
   * Generates a checkerboard image with the user provided height, width, and square size.
   * If a field is left blank, an error pops up.
   */
  void generateCheckerboard();

  /**
   * Generates a Greek flag with the user provided height. If a field is left
   * blank, an error pops up.
   */
  void generateGreece();

  /**
   * Generates a Swiss flag with the user provided height. If a field is left
   * blank, an error pops up.
   */
  void generateSwiss();

  /**
   * Generates a French flag with the user provided height. If a field is left
   * blank, an error pops up.
   */
  void generateFrance();

  /**
   * Generates a horizontal rainbow with the user provided height and width. If a
   * field is left blank, an error pops up.
   */
  void generateHorizontalRainbow();

  /**
   * Generates a vertical rainbow with the user provided height and width. If a
   * field is left blank, an error pops up.
   */
  void generateVerticalRainbow();

  /**
   * Builds and displays the options panel for the user to enter the mosaic seed number.
   */
  void setMosaicVisible();

  /**
   * Builds and displays the options panel for the user to enter the height, width,
   * and square size.
   */
  void setCheckerboardVisible();

  /**
   * Builds and displays the options panel for the user to enter the height of the flag.
   */
  void setSwissVisible();

  /**
   * Builds and displays the options panel for the user to enter the height of the flag.
   */
  void setFranceVisible();

  /**
   * Builds and displays the options panel for the user to enter the height of the flag.
   */
  void setGreeceVisible();

  /**
   * Builds and displays the options panel for the user to enter the height and width.
   */
  void setHorizontalRainbowVisible();

  /**
   * Builds and displays the options panel for the user to enter the height and width.
   */
  void setVerticalRainbowVisible();

  /**
   * Undoes the most recent operation performed, and updates the view. If there
   * is no operation to undo, an error pops up.
   */
  void undoImage();

  /**
   * Redoes the most recent operation performed, and updates the view. If there
   * is no operation to undo, an error pops up.
   */
  void redoImage();

  /**
   * Deletes all images and operations performed since the last image load, and clears
   * the view.
   */
  void deleteAll();

  /**
   * This method gets the user-typed commands from the GUI batch script
   * text box, and processes the commands. If the user types an incorrect command,
   * an error pops up.
   */
  void executeScript();



}
