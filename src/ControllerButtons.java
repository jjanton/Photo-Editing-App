import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * This class represents a controller that operates the GUI. This controller contains a model and a
 * view, which allow it to control the flow of data into the model, and determines what operations
 * to perform when. The controller also updates the view whenever there is a change of data.
 */
class ControllerButtons {

  /**
   * Constructs a controller object, creates instance variables for a
   * model and a view, and instantiates a Features implementation class for
   * abstraction purposes.
   */
  ControllerButtons() {

    View view = new View("Image processor");
    Model model = new Model();
    view.setFeatures(new FeatureImpl(model, view));

  }


  /**
   * This is a private controller impl class, which adds anther layer of abstraction.
   * This prevents a features object from being cast as a controller, and
   * gaining access to the Controller methods.
   */
  private static class FeatureImpl implements Features {

    private final Model model;
    private final View view;

    private FeatureImpl(Model model, View view) {
      this.model = model;
      this.view = view;
    }


    /**
     * Opens the Jfilechooser window to access and load a local stored image. Upon selecting an
     * image, the image is stored in the model, and the view is updated to display the new image.
     */
    @Override
    public void loadImage() {
      final JFileChooser fileChooser = new JFileChooser(".");

      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG, GIF, PNG Images", "jpg", "gif", "png");
      fileChooser.setFileFilter(filter);

      int returnValue = fileChooser.showOpenDialog(view);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        model.load(file.getName());
        updateDisplay();
        view.setAllInvisible();
      }
    }

    /**
     * Opens the Jfile chooser, and allows the user to type their filename, and save their image
     * to the directory of their choice. If there is no image to save, an error message pops up.
     */
    @Override
    public void saveImage() {
      try {
        final JFileChooser fileChooser = new JFileChooser(".");

        int returnValue = fileChooser.showSaveDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          model.save(file.getName() + ".png");
        }
      } catch (IllegalStateException e) {
        view.displayError(e.getMessage());
      }
    }

    /**
     * Applies a greyscale transformation to the image, and updates the view to display the
     * transformed image. If there is no image to apply the transformation to, pops up an
     * error message.
     */
    @Override
    public void greyscale() {
      try {
        model.greyScale();
        updateDisplay();
      } catch (IllegalStateException e) {
        view.displayError(e.getMessage());
      }
    }


    /**
     * Applies a sepia transformation to the image, and updates the view to display the
     * transformed image. If there is no image to apply the transformation to, pops up an
     * error message.
     */
    @Override
    public void sepia() {
      try {
        model.sepia();
        updateDisplay();
      } catch (IllegalStateException e) {
        view.displayError(e.getMessage());
      }
    }

    /**
     * Applies a blur transformation to the image, and updates the view to display the
     * transformed image. If there is no image to apply the transformation to, pops up an
     * error message.
     */
    @Override
    public void blur() {
      try {
        model.blur();
        updateDisplay();
      } catch (IllegalStateException e) {
        view.displayError(e.getMessage());
      }
    }

    /**
     * Applies a sharpen transformation to the image, and updates the view to display the
     * transformed image. If there is no image to apply the transformation to, pops up an
     * error message.
     */
    @Override
    public void sharpen() {
      try {
        model.sharpen();
        updateDisplay();
      } catch (IllegalStateException e) {
        view.displayError(e.getMessage());
      }
    }

    /**
     * Applies a dither transformation to the image, and updates the view to display the
     * transformed image. If there is no image to apply the transformation to, pops up an
     * error message.
     */
    @Override
    public void dither() {
      try {
        model.dither();
        updateDisplay();
      } catch (IllegalStateException e) {
        view.displayError(e.getMessage());
      }
    }

    /**
     * Applies a mosaic transformation to the image with the user provided seed count,
     * and updates the view to display the transformed image. If there is no image to
     * apply the transformation to, pops up an error message.
     */
    @Override
    public void mosaic() {
      try {
        model.mosaic(view.getSeedNum());
        updateDisplay();
        view.setAllInvisible();
      } catch (IllegalStateException e) {
        view.displayError(e.getMessage());
      } catch (IllegalArgumentException a) {
        view.displayError(a.getMessage());
      }
    }

    /**
     * Generates a checkerboard image with the user provided height, width, and square size.
     * If a field is left blank, an error pops up.
     */
    @Override
    public void generateCheckerboard() {
      try {
        model.generateCheckerBoard(view.getWidthField(), view.getHeightField()
                , view.getSquareField());
        updateDisplay();
        view.setAllInvisible();
      } catch (IllegalArgumentException e) {
        view.displayError(e.getMessage());
      }
    }


    /**
     * Generates a Greek flag with the user provided height. If a field is left
     * blank, an error pops up.
     */
    @Override
    public void generateGreece() {
      try {
        model.generateGreece(view.getHeightField());
        updateDisplay();
        view.setAllInvisible();
      } catch (IllegalArgumentException e) {
        view.displayError(e.getMessage());
      }
    }

    /**
     * Generates a Swiss flag with the user provided height. If a field is left
     * blank, an error pops up.
     */
    @Override
    public void generateSwiss() {
      try {
        model.generateSwiss(view.getHeightField());
        updateDisplay();
        view.setAllInvisible();
      } catch (IllegalArgumentException e) {
        view.displayError(e.getMessage());
      }
    }

    /**
     * Generates a French flag with the user provided height. If a field is left
     * blank, an error pops up.
     */
    @Override
    public void generateFrance() {
      try {
        model.generateFrance(view.getHeightField());
        updateDisplay();
        view.setAllInvisible();
      } catch (IllegalArgumentException e) {
        view.displayError(e.getMessage());
      }
    }

    /**
     * Generates a horizontal rainbow with the user provided height and width. If a
     * field is left blank, an error pops up.
     */
    @Override
    public void generateHorizontalRainbow() {
      try {
        model.generateHorizontalRainbow(view.getWidthField(), view.getHeightField());
        updateDisplay();
      } catch (IllegalArgumentException e) {
        view.displayError(e.getMessage());
      }
    }

    /**
     * Generates a vertical rainbow with the user provided height and width. If a
     * field is left blank, an error pops up.
     */
    @Override
    public void generateVerticalRainbow() {
      try {
        model.generateVerticalRainbow(view.getWidthField(), view.getHeightField());
        updateDisplay();
      } catch (IllegalArgumentException e) {
        view.displayError(e.getMessage());
      }
    }

    /**
     * Builds and displays the options panel for the user to enter the mosaic seed number.
     */
    @Override
    public void setMosaicVisible() {
      view.setMosaicVisible();
    }

    /**
     * Builds and displays the options panel for the user to enter the height, width,
     * and square size.
     */
    @Override
    public void setCheckerboardVisible() {
      view.setCheckerboardVisible();
    }

    /**
     * Builds and displays the options panel for the user to enter the height of the flag.
     */
    @Override
    public void setSwissVisible() {
      view.setSwissOptionsVisible();
    }

    /**
     * Builds and displays the options panel for the user to enter the height of the flag.
     */
    @Override
    public void setFranceVisible() {
      view.setFranceOptionsVisible();
    }

    /**
     * Builds and displays the options panel for the user to enter the height of the flag.
     */
    @Override
    public void setGreeceVisible() {
      view.setGreeceOptionsVisible();
    }

    /**
     * Builds and displays the options panel for the user to enter the height and width.
     */
    @Override
    public void setHorizontalRainbowVisible() {
      view.setHorizontalRainbowOptionsVisible();
    }

    /**
     * Builds and displays the options panel for the user to enter the height and width.
     */
    @Override
    public void setVerticalRainbowVisible() {
      view.setVerticalRainbowOptionsVisible();
    }

    /**
     * Undoes the most recent operation performed, and updates the view. If there
     * is no operation to undo, an error pops up.
     */
    @Override
    public void undoImage() {
      try {
        model.undoImage();
        updateDisplay();
      } catch (IllegalStateException e) {
        view.displayError(e.getMessage());
      }

    }

    /**
     * Redoes the most recent operation performed, and updates the view. If there
     * is no operation to undo, an error pops up.
     */
    @Override
    public void redoImage() {
      try {
        model.redoImage();
        updateDisplay();
      } catch (IllegalStateException e) {
        view.displayError(e.getMessage());
      }

    }

    /**
     * Deletes all images and operations performed since the last image load, and clears
     * the view.
     */
    @Override
    public void deleteAll() {
      model.deleteAll();
      view.clearImage();
      view.setAllInvisible();
    }

    /**
     * Updates the view to display the most recent image. If there is no image to display,
     * an error pops up.
     */
    private void updateDisplay() {
      BufferedImage bufferedImage = model.convertImage();
      view.loadImage(bufferedImage);
    }

    /**
     * This method gets the user-typed commands from the GUI batch script
     * text box, and processes the commands. If the user types an incorrect command,
     * an error pops up.
     *
     * @throws IllegalArgumentException if the script command is not valid.
     */
    public void executeScript() throws IllegalArgumentException {
      String batchScript = view.getBatchScript();
      Scanner scan = new Scanner(batchScript);

      try {
        while (scan.hasNext()) {
          String token = scan.next().toLowerCase();

          switch (token) {
            case "open":
              String filename = scan.next();
              model.load(filename);
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
              throw new IllegalArgumentException("Script command not recognized, check for typos");
          }
        }

        updateDisplay();
        view.clearScriptTextBox();
      }
      catch (IllegalArgumentException e) {
        view.displayError(e.getMessage());
      }
      catch (IllegalStateException a) {
        view.displayError(a.getMessage());
      }



    }


  }
}




