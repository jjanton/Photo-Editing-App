import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



/**
 * This class is responsible for creating a GUI which can facilitate loading,
 * displaying, transforming, generating, and saving images.
 */
public class View extends JFrame {

  private JPanel imagePanel;

  // Transformation menu buttons
  private JMenuItem blurMenuButton;
  private JMenuItem ditherMenuButton;
  private JMenuItem greyscaleMenuButton;
  private JMenuItem sepiaMenuButton;
  private JMenuItem mosaicMenuButton;
  private JMenuItem sharpenMenuButton;

  // Load and save menu buttons
  private JMenuItem openMenuButton;
  private JMenuItem saveMenuButton;
  private JMenuItem undoMenuButton;
  private JMenuItem redoMenuButton;
  private JMenuItem deleteMenuButton;


  // Generator buttons
  private JMenuItem checkerboardMenuButton;
  private JMenuItem franceMenuButton;
  private JMenuItem greeceMenuButton;
  private JMenuItem swissMenuButton;
  private JMenuItem horizontalRainbowMenuButton;
  private JMenuItem verticalRainbowMenuButton;


  // Script text box
  private JTextArea scriptTextBox;

  // Execute script button
  private JMenuItem executeScriptMenuButton;

  // Mosaic input field
  private JPanel optionsPanel;
  private JTextField mosaicSeedCount;
  private JButton mosaicGenerateButton;
  private  JLabel mosaicLabel;

  // Checkerboard input field
  private JLabel widthLabel;
  private JLabel heightLabel;
  private JTextField widthField;
  private JTextField heightField;
  private JTextField checkerboardSquareSizeField;
  private JButton checkerboardGenerateButton;
  private JLabel squareSizeLabel;

  private JButton swissGenerateButton;
  private JButton franceGenerateButton;
  private JButton greeceGenerateButton;
  private JButton verticalRainbowGenerateButton;
  private JButton horizontalRainbowGenerateButton;


  /**
   * Constructs a view object with the given caption, instantiates all instance variables,
   * and sets up and makes the GUI visible.
   */
  public View(String caption) {
    super(caption);

    // Set up window
    setSize(800, 600);
    setLocation(100, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Set up main panel
    // Main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane);


    // Add panel and image to main screen
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Your image"));
    imagePanel.setLayout(new FlowLayout());
    mainPanel.add(imagePanel);


    //Set up Menu:
    //Menus
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenu transformationMenu = new JMenu("Transformations");
    JMenu generatorMenu = new JMenu("Generators");
    JMenu scriptMenu = new JMenu("Script");
    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(transformationMenu);
    menuBar.add(generatorMenu);
    menuBar.add(scriptMenu);


    // Add menu bar
    this.setJMenuBar(menuBar);

    // Function to display/refresh image. Constructed as blank
    loadImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB));

    // Load menu button
    openMenuButton = new JMenuItem("Open");
    fileMenu.add(openMenuButton);


    // Save menu button
    saveMenuButton = new JMenuItem("Save");
    fileMenu.add(saveMenuButton);


    // Undo menu button
    undoMenuButton = new JMenuItem("Undo");
    editMenu.add(undoMenuButton);


    // Redo menu button
    redoMenuButton = new JMenuItem("Redo");
    editMenu.add(redoMenuButton);


    // Clear menu button
    deleteMenuButton = new JMenuItem("Delete all");
    editMenu.add(deleteMenuButton);


    // Blur button
    blurMenuButton = new JMenuItem("Blur");
    blurMenuButton.setActionCommand("Blur");
    transformationMenu.add(blurMenuButton);

    // Dither button
    ditherMenuButton = new JMenuItem("Dither");
    ditherMenuButton.setActionCommand("Dither");
    transformationMenu.add(ditherMenuButton);

    // Greyscale button
    greyscaleMenuButton = new JMenuItem("Greyscale");
    greyscaleMenuButton.setActionCommand("Greyscale");
    transformationMenu.add(greyscaleMenuButton);

    // Sepia button
    sepiaMenuButton = new JMenuItem("Sepia");
    sepiaMenuButton.setActionCommand("Sepia");
    transformationMenu.add(sepiaMenuButton);

    // Mosaic button
    mosaicMenuButton = new JMenuItem("Mosaic");
    mosaicMenuButton.setActionCommand("Mosaic");
    transformationMenu.add(mosaicMenuButton);

    //Mosaic Menu
    mosaicSeedCount = new JTextField(5);
    mosaicGenerateButton = new JButton("Run Mosaic");
    mosaicLabel = new JLabel("Seed Count:");

    //adding options panel
    optionsPanel = new JPanel();
    mainPanel.add(optionsPanel);

    // Sharpen button
    sharpenMenuButton = new JMenuItem("Sharpen");
    sharpenMenuButton.setActionCommand("Sharpen");
    transformationMenu.add(sharpenMenuButton);


    // Checkerboard button
    checkerboardMenuButton = new JMenuItem("Checkerboard");
    checkerboardMenuButton.setActionCommand("Checkerboard");
    generatorMenu.add(checkerboardMenuButton);

    //checkerboard menu
    checkerboardGenerateButton = new JButton("Generate Checkerboard");
    squareSizeLabel = new JLabel("Square Size:");
    checkerboardSquareSizeField = new JTextField("", 4);


    //height and widths used several locations
    widthLabel = new JLabel("Width:");
    heightLabel = new JLabel("Height: ");
    widthField = new JTextField(4);
    heightField = new JTextField(4);

    // France buttons
    franceMenuButton = new JMenuItem("France");
    franceMenuButton.setActionCommand("France");
    generatorMenu.add(franceMenuButton);
    franceGenerateButton = new JButton("Generate French Flag");

    // Greece buttons
    greeceMenuButton = new JMenuItem("Greek Flag");
    greeceMenuButton.setActionCommand("Greek Flag");
    generatorMenu.add(greeceMenuButton);
    greeceGenerateButton = new JButton("Generate Greek Flag");

    // Rainbow buttons
    horizontalRainbowGenerateButton = new JButton("Generate Horizontal Rainbow");
    verticalRainbowGenerateButton = new JButton("Generate Vertical Rainbow");

    // Swiss menu button
    swissMenuButton = new JMenuItem("Swiss Flag");
    swissMenuButton.setActionCommand("Swiss Flag");
    swissGenerateButton = new JButton("Generate Swiss Flag");
    generatorMenu.add(swissMenuButton);

    // V rainbow menu button
    verticalRainbowMenuButton = new JMenuItem("Vertical Rainbow");
    verticalRainbowMenuButton.setActionCommand("Vertical Rainbow");
    generatorMenu.add(verticalRainbowMenuButton);

    // H rainbow menu button
    horizontalRainbowMenuButton = new JMenuItem("Horizontal Rainbow");
    horizontalRainbowMenuButton.setActionCommand("Horizontal Rainbow");
    generatorMenu.add(horizontalRainbowMenuButton);


    // Script generator panel
    JPanel generatorPanel = new JPanel();
    generatorPanel.setBorder(BorderFactory.createTitledBorder("Write a script"));
    generatorPanel.setLayout(new FlowLayout());
    mainPanel.add(generatorPanel);

    executeScriptMenuButton = new JMenuItem("Execute Script");
    executeScriptMenuButton.setActionCommand("Execute");
    scriptMenu.add(executeScriptMenuButton);

    // Script generator text box
    scriptTextBox = new JTextArea(10, 40);
    JScrollPane scriptScrollPane = new JScrollPane(scriptTextBox);
    scriptTextBox.setLineWrap(true);
    scriptScrollPane.setBorder(BorderFactory.createTitledBorder("Type your script here"));
    generatorPanel.add(scriptTextBox);


    pack();
    clearImage();
    setAllInvisible();
    setVisible(true);
  }

  /**
   * SetFeatures A method to set up all the action listeners in the View. It takes f, a feature,
   * which is an interface that holds all the actions that taking and action in the view
   * can be expected to do.
   * @param f a Feature, which sets the action listener and delegates the work to the controller.
   */
  void setFeatures(Features f) {
    openMenuButton.addActionListener(l -> f.loadImage());
    saveMenuButton.addActionListener(l -> f.saveImage());

    undoMenuButton.addActionListener(l -> f.undoImage());
    redoMenuButton.addActionListener(l -> f.redoImage());
    deleteMenuButton.addActionListener(l -> f.deleteAll());

    blurMenuButton.addActionListener(l -> f.blur());
    sharpenMenuButton.addActionListener(l -> f.sharpen());
    sepiaMenuButton.addActionListener(l -> f.sepia());
    greyscaleMenuButton.addActionListener(l -> f.greyscale());
    ditherMenuButton.addActionListener(l -> f.dither());

    mosaicMenuButton.addActionListener(l -> f.setMosaicVisible());
    mosaicGenerateButton.addActionListener(l -> f.mosaic());

    checkerboardMenuButton.addActionListener(l -> f.setCheckerboardVisible());
    checkerboardGenerateButton.addActionListener(l -> f.generateCheckerboard());
    greeceMenuButton.addActionListener(l -> f.setGreeceVisible());
    greeceGenerateButton.addActionListener(l -> f.generateGreece());

    swissMenuButton.addActionListener(l -> f.setSwissVisible());
    swissGenerateButton.addActionListener(l -> f.generateSwiss());

    franceMenuButton.addActionListener(l -> f.setFranceVisible());
    franceGenerateButton.addActionListener(l -> f.generateFrance());

    horizontalRainbowMenuButton.addActionListener(l -> f.setHorizontalRainbowVisible());
    horizontalRainbowGenerateButton.addActionListener(l -> f.generateHorizontalRainbow());

    verticalRainbowMenuButton.addActionListener(l -> f.setVerticalRainbowVisible());
    verticalRainbowGenerateButton.addActionListener(l -> f.generateVerticalRainbow());

    executeScriptMenuButton.addActionListener(l -> f.executeScript());
  }


  /**
   * Sets up and adds an Image (in the form of a buffered image) to the view.
   * @param bufferedImage     The image to be displayed on the panel.
   */
  void loadImage(BufferedImage bufferedImage) {
    JLabel imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageLabel.setIcon(new ImageIcon(bufferedImage));
    imageScrollPane.setPreferredSize(new Dimension(600, 400));
    imagePanel.removeAll();
    imagePanel.add(imageScrollPane);
    revalidate();
  }

  /**
   * Clears any image on the panel, leaving an empty grey space.
   */
  void clearImage() {
    imagePanel.removeAll();
    revalidate();
  }

  /**
   * Empties the text box of text, indicating that the script has been processed.
   */
  void clearScriptTextBox() {
    scriptTextBox.selectAll();
    scriptTextBox.replaceSelection("");
  }

  /**
   * Gets and returns the information contained in the script text box.
   * @return    A String, the text in the batch script box.
   */
  String getBatchScript() {
    return scriptTextBox.getText();
  }


  /**
   * Removes all items from the optionsPanel and makes it invisible. Used to clear the options
   * when opening a new set of options.
   */
  void setAllInvisible() {
    optionsPanel.removeAll();
    optionsPanel.setVisible(false);
    revalidate();
  }


  /**
   * Sets up and makes visible the options panel for the checkerboard generator.
   */
  void setCheckerboardVisible() {
    setAllInvisible();
    addHeightAndWidthField();
    optionsPanel.add(squareSizeLabel);
    optionsPanel.add(checkerboardSquareSizeField);
    optionsPanel.add(checkerboardGenerateButton);
    optionsPanel.setVisible(true);
    revalidate();
  }

  /**
   * Sets up and makes visible the options panel for mosaic transform.
   */
  void setMosaicVisible() {
    setAllInvisible();
    optionsPanel.add(mosaicLabel);
    optionsPanel.add(mosaicSeedCount);
    optionsPanel.add(mosaicGenerateButton);
    optionsPanel.setVisible(true);
    revalidate();
  }


  /**
   * Sets up and makes visible the options panel for generating a Swiss flag.
   */
  void setSwissOptionsVisible() {
    setAllInvisible();
    addHeightField();
    optionsPanel.add(swissGenerateButton);
    optionsPanel.setVisible(true);
    revalidate();
  }

  /**
   * Sets up and makes visible the options panel for generating a French flag.
   */
  void setFranceOptionsVisible() {
    setAllInvisible();
    addHeightField();
    optionsPanel.add(franceGenerateButton);
    optionsPanel.setVisible(true);
    revalidate();
  }

  /**
   * Sets up and makes visible the options panel for generating a Greek flag.
   */
  void setGreeceOptionsVisible() {
    setAllInvisible();
    addHeightField();
    optionsPanel.add(greeceGenerateButton);
    optionsPanel.setVisible(true);
    revalidate();
  }

  /**
   * Sets up and makes visible the options panel for generating a horizontal rainbow.
   */
  void setHorizontalRainbowOptionsVisible() {
    setAllInvisible();
    addHeightAndWidthField();
    optionsPanel.add(horizontalRainbowGenerateButton);
    optionsPanel.setVisible(true);
    revalidate();
  }

  /**
   * Sets up and makes visible the options panel for generating a vertical rainbow.
   */
  void setVerticalRainbowOptionsVisible() {
    setAllInvisible();
    addHeightAndWidthField();
    optionsPanel.add(verticalRainbowGenerateButton);
    optionsPanel.setVisible(true);
    revalidate();
  }

  /**
   * Gets the number of seeds required for the mosaic function from the seedCount text box.
   * @return    int, a number of seeds.
   * @throws    IllegalArgumentException if a non number is entered.
   */
  int getSeedNum() {
    String seedNum = mosaicSeedCount.getText();
    try {
      return Integer.parseInt(seedNum);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Must enter a number for # of seeds");
    }
  }

  /**
   * Gets the desired width of an image from the widthField text box.
   * @return    An int, the width in pixels.
   * @throws IllegalArgumentException    If a non number is entered for width.
   */
  int getWidthField() {
    String widthString = widthField.getText();
    try {
      return Integer.parseInt(widthString);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Must enter a number for width");
    }
  }

  /**
   * Gets the desired height of an image from the widthField text box.
   * @return    An int, the height in pixels.
   * @throws IllegalArgumentException    If a non number is entered for width.
   */
  int getHeightField() {
    String heightString = heightField.getText();
    try {
      return Integer.parseInt(heightString);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Must enter a number for height");
    }
  }

  /**
   * Gets the desired size of a checkerboard square.
   * @return    An int, the size in pixels.
   * @throws IllegalArgumentException    If a non number is entered for .
   */
  int getSquareField() {
    String squareSize = checkerboardSquareSizeField.getText();
    try {
      return Integer.parseInt(squareSize);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Must enter a number for square size");
    }
  }

  /**
   * Displays an en error from a caught exception and shows it to the user on a popup window.
   * @param error   The error as a String, usually from a caught exception.
   */
  void displayError(String error) {
    JOptionPane.showMessageDialog(null, error);
  }

  /**
   * Helper method to add items to the optionsPanel, adds height text box and its label.
   */
  private void addHeightField() {
    optionsPanel.add(heightLabel);
    optionsPanel.add(heightField);
  }

  /**
   * Helper method to add items to the optionsPanel, adds height text box and its label, and a
   * width text box and its label.
   */
  private void addHeightAndWidthField() {
    addHeightField();
    optionsPanel.add(widthLabel);
    optionsPanel.add(widthField);
  }
}


