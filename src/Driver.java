/**
 * This class is a driver, which runs runs every color transformation, filter, and generator
 * created.
 */
public class Driver {

  /**
   * Main method to run the generators, filters, and generators.
   *
   * @param args   The name of the script containing commands.
   */
  public static void main(String[] args) {

    if (args[0].equals("-interactive")) {
      ControllerButtons controllerButtons = new ControllerButtons();
    } else if (args[0].equals("-script")) {
      new ControllerScript(args[1]).run();
    }
  }

}
