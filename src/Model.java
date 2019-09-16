import java.awt.image.BufferedImage;
import java.util.EmptyStackException;
import java.util.Stack;

import generator.CheckerboardGenerator;
import generator.FranceGenerator;
import generator.GreeceGenerator;
import generator.RainbowGenerator;
import generator.SwissGenerator;
import image.Image;
import image.ImageRGB;
import transformations.Blur;
import transformations.Dither;
import transformations.Greyscale;
import transformations.Mosaic;
import transformations.Sepia;
import transformations.Sharpen;


/**
 * Model holds a single image to perform operations upon, which it replaces after using each filter,
 * generator or transformation. Every operation in the model is performed by first instantiating an
 * object of the relevant class, and then delegating the work to that object.
 */
class Model {
  private Stack<Image> undoStack;
  private Stack<Image> redoStack;

  /**
   * Constructs a model object, and instantiates the undoStack and redoStack instance
   * variables.
   */
  Model() {
    undoStack = new Stack<>();
    redoStack = new Stack<>();
  }


  /**
   * loads an image into the model, and stores it in the undoStack.
   *
   * @param filename                  The name of the file containing the image.
   */
  void load(String filename) {
    undoStack.push(new ImageRGB(filename));
  }

  /**
   * Saves the image to a file.
   *
   * @param filename                  The name of the file to be saved.
   * @throws IllegalStateException    if there is no imaged to be saved.
   */
  void save(String filename) throws IllegalStateException {
    try {
      undoStack.peek().write(filename);
    } catch (EmptyStackException e) {
      throw new IllegalStateException("There is no image to save");
    }
  }

  /**
   * Applies a grescale transformation to an image.
   * @throws IllegalStateException       if there is no file to apply the transformation to.
   */
  void greyScale() throws IllegalStateException {
    try {
      undoStack.push(new Greyscale().apply(undoStack.peek()));
    } catch (EmptyStackException e) {
      throw new IllegalStateException("There is no image to apply a filter to");
    }
  }

  /**
   * Applies a sepia transformation to an image.
   * @throws IllegalStateException       if there is no file to apply the transformation to.
   */
  void sepia() throws IllegalStateException {
    try {
      undoStack.push(new Sepia().apply(undoStack.peek()));
    } catch (EmptyStackException e) {
      throw new IllegalStateException("There is no image to apply a filter to");
    }
  }

  /**
   * Applies a blur transformation to an image.
   * @throws IllegalStateException       if there is no file to apply the transformation to.
   */
  void blur() throws IllegalStateException {
    try {
      undoStack.push(new Blur().apply(undoStack.peek()));
    } catch (EmptyStackException e) {
      throw new IllegalStateException("There is no image to apply a filter to");
    }
  }

  /**
   * Applies a dither transformation to an image.
   * @throws IllegalStateException       if there is no file to apply the transformation to.
   */
  void dither() throws IllegalStateException {
    try {
      undoStack.push(new Dither().apply(undoStack.peek()));
    } catch (EmptyStackException e) {
      throw new IllegalStateException("There is no image to apply a filter to");
    }
  }

  /**
   * Applies a mosaic transformation to an image.
   *
   *
   * @param seed           The number of seeds to create the mosaic with.
   * @throws IllegalStateException       if there is no file to apply the transformation to.
   */
  void mosaic(int seed) throws IllegalStateException {
    try {
      undoStack.push(new Mosaic(seed).apply(undoStack.peek()));
    } catch (EmptyStackException e) {
      throw new IllegalStateException("There is no image to apply a filter to");
    }
  }

  /**
   * Applies a sharpen transformation to an image.
   * @throws IllegalStateException      if there is no file to apply the transformation to.
   */
  void sharpen() {
    try {
      undoStack.push(new Sharpen().apply(undoStack.peek()));
    } catch (EmptyStackException e) {
      throw new IllegalStateException("There is no image to apply a filter to");
    }
  }

  /**
   * generates a swiss flag with the user provided height.
   *
   * @param height                       The height of the flag.
   */
  void generateSwiss(int height) {
    undoStack.push(new SwissGenerator(height).generate());
  }

  /**
   * Generates a french flag with the user provided height.
   *
   * @param height          The height of the flag.
   */
  void generateFrance(int height) {
    undoStack.push(new FranceGenerator(height).generate());
  }

  /**
   * Generates a Greece flag with the user provided height.
   *
   * @param height          The height of the flag.
   */
  void generateGreece(int height) {
    undoStack.push(new GreeceGenerator(height).generate());
  }

  /**
   * Generates a horizontal rainbow flag with the user provided height and width.
   *
   * @param width           The width of the flag.
   * @param height          The height of the flag.
   */
  void generateHorizontalRainbow(int width, int height) {
    undoStack.push(new RainbowGenerator(width, height, false).generate());
  }

  /**
   * Generates a vertical rainbow flag with the user provided height and width.
   *
   * @param width           The width of the flag.
   * @param height          The height of the flag.
   */
  void generateVerticalRainbow(int width, int height) {
    undoStack.push(new RainbowGenerator(width, height, true).generate());
  }

  /**
   * Generates a checkerboard flag with the user provided width, height, and squaresize.
   *
   * @param width           The width of the flag.
   * @param height          The height of the flag.
   * @param squareSize      The size of an individual checker on the flag.
   */
  void generateCheckerBoard(int width, int height, int squareSize) {
    undoStack.push(new CheckerboardGenerator(width, height, squareSize).generate());
  }

  /**
   * Converts the most recent image into a BufferedImage.
   * @return The converted BufferedImage.
   */
  BufferedImage convertImage() throws IllegalStateException {
    try {
      int[][][] rgb = undoStack.peek().getData();
      int width = undoStack.peek().getWidth();
      int height = undoStack.peek().getHeight();

      BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = rgb[i][j][0];
          int g = rgb[i][j][1];
          int b = rgb[i][j][2];

          int color = (r << 16) + (g << 8) + b;
          output.setRGB(j, i, color);
        }
      }

      return output;
    }
    catch (EmptyStackException e) {
      throw new IllegalStateException("Script is empty, enter commands to execute script");
    }
  }


  /**
   * Undoes the most recent operation performed, and updates the view.
   *
   * @throws IllegalStateException if there is no operation to undo.
   */
  void undoImage() throws IllegalStateException {
    if (undoStack.size() <= 1) {
      throw new IllegalStateException("There are no operations to undo");
    }
    redoStack.push(undoStack.pop());

  }

  /**
   * Redoes the most recent operation performed, and updates the view.
   *
   * @throws IllegalStateException if there is no operation to redo.
   */
  void redoImage() throws IllegalStateException {
    if (redoStack.size() < 1) {
      throw new IllegalStateException("There are no operations to redo");
    }
    undoStack.push(redoStack.pop());
  }

  /**
   * Deletes all images and operations performed since the last image load, and clears
   * the view.
   */
  void deleteAll() {
    while (!undoStack.empty()) {
      undoStack.pop();
    }
    while (!redoStack.empty()) {
      redoStack.pop();
    }
  }
}


