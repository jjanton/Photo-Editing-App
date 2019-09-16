import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents a controller. A controller contains a model and a view, controls the flow
 * of data into the model, and determines what operations to perform when. The controller also
 * updates the view whenever there is a change of data.
 */
class ControllerScript {

  private Model model;
  //private View view;
  private String script;
  private Scanner scan;

  /**
   * Constructs a ControllerScript object with the given script.
   *
   * @param script A script containing a list of operations to be performed on an image.
   */
  ControllerScript(String script) {
    this.model = new Model();
    this.script = script;
  }

  /**
   * This method analyzes and performs the commands in a script.
   */
  void run() {

    File file = new File(script);
    try {
      scan = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found");
    }

    while (scan.hasNext()) {
      String token = scan.next().toLowerCase();
      processToken(token);
    }
  }


  /**
   * This is a helper method for analyzing commands in a script. Ths method takes a command, and
   * delegates the appropriate work to the model.
   *
   * @param token The next command in the script.
   * @throws IllegalArgumentException if a command is not recognized, if a number is not positive.
   */
  void processToken(String token) throws IllegalArgumentException {
    switch (token) {
      case "load":
        String fileName = scan.next();
        model.load(fileName);
        break;

      case "save":
        String writeName = scan.next();
        model.save(writeName);
        break;

      case "greyscale":
        model.greyScale();
        break;

      case "sepia":
        model.sepia();
        break;

      case "blur":
        model.sepia();
        break;

      case "dither":
        model.dither();
        break;

      case "mosaic":
        try {
          String next = scan.next();

          int seed = Integer.parseInt(next);
          model.mosaic(seed);

        } catch (NumberFormatException e) {
          throw new IllegalStateException("Mosaic needs a seed number");
        }
        break;

      case "sharpen":
        model.sharpen();
        break;

      case "generategreece":
        try {
          String next = scan.next();

          int height = Integer.parseInt(next);
          model.generateGreece(height);

        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Height must be a positive integer");
        }
        break;

      case "generatefrance":
        try {
          String next = scan.next();

          int height = Integer.parseInt(next);
          model.generateFrance(height);

        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Height must be a positive integer");
        }
        break;

      case "generateswiss":
        try {
          String next = scan.next();

          int height = Integer.parseInt(next);
          model.generateSwiss(height);

        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Height must be a positive integer");
        }
        break;

      case "generateverticalrainbow":
        try {
          String next = scan.next();
          String secondNext = scan.next();

          int width = Integer.parseInt(next);
          int height = Integer.parseInt(secondNext);
          model.generateVerticalRainbow(width, height);

        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Height must be a positive integer");
        }
        break;

      case "generatehorizontalrainbow":
        try {
          String next = scan.next();
          String secondNext = scan.next();

          int width = Integer.parseInt(next);
          int height = Integer.parseInt(secondNext);
          model.generateHorizontalRainbow(width, height);

        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Height must be a positive integer");
        }
        break;

      case "generatecheckerboard":
        try {
          String next = scan.next();
          String secondNext = scan.next();
          String thirdNext = scan.next();

          int width = Integer.parseInt(next);
          int height = Integer.parseInt(secondNext);
          int squareSize = Integer.parseInt(thirdNext);
          model.generateCheckerBoard(width, height, squareSize);

        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Height must be a positive integer");
        }
        break;

      default:
        throw new IllegalArgumentException("Command not recognized");
    }
  }


}

