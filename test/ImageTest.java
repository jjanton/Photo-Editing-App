//import org.junit.Before;
//import org.junit.Test;
//
//public class ImageTest {
//
//  private image.Image image;
//  private ColorTransformation grey;
//  private Filter blur;
//  private Filter sharpen;
//  private ColorTransformation sepia;
//  private RainbowGenerator rainbow;
//  private CheckerboardGenerator checkerboard;
//  private generator.GeneratorInterface flags;
//  private generator.GeneratorInterface GreekFlag;
//
//  private double[][] g = {{0.2126, 0.7152, 0.0722},
//          {0.2126, 0.7152, 0.0722},
//          {0.2126, 0.7152, 0.0722}};
//
//  private double[][] b = {{1.0 / 16, 1.0 / 8, 1.0 / 16},
//          {1.0 / 8, 1.0 / 4, 1.0 / 8},
//          {1.0 / 16, 1.0 / 8, 1.0 / 16}};
//
//  private double[][] sharpenMatrix = {{-(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0)},
//          {-(1.0 / 8.0), (1.0 / 4.0), (1.0 / 4.0), (1.0 / 4.0), -(1.0 / 8.0)},
//          {-(1.0 / 8.0), (1.0 / 4.0), (1.0), (1.0 / 4.0), -(1.0 / 8.0)},
//          {-(1.0 / 8.0), (1.0 / 4.0), (1.0 / 4.0), (1.0 / 4.0), -(1.0 / 8.0)},
//          {-(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0), -(1.0 / 8.0)}};
//
//  private double[][] s = {{.393, .769, .189},
//          {.349, .686, .168},
//          {.272, .534, .131}};
//
//  @Before
//  public void setUp() {
//    image = new image.ImageRGB("sharper.png");
//    grey = new ColorTransformation(g);
//    blur = new Filter(b);
//    sharpen = new Filter(sharpenMatrix);
//    sepia = new ColorTransformation(s);
//    rainbow = new RainbowGenerator(700, 500, true);
//    checkerboard = new CheckerboardGenerator(80, 80, 10);
//    flags = new SwissGenerator(200);
//    GreekFlag = new generator.GreeceGenerator(100);
//  }
//
//  @Test
//  public void testWidthAndHeight() {
//    System.out.printf("Width:" + image.getWidth() + " Height: " + image.getHeight());
//  }
//
//  @Test
//  public void testToString() {
//    System.out.println(image);
//  }
//
//  @Test
//  public void testWriteImage() {
//    image.write("newsprite.png");
//  }
//
//  @Test
//  public void testChunk() {
//    image.Image aChunk = blur.makeChunk(image.getData(), 1, 1, 3);
//    System.out.println(aChunk);
//  }
//
//  @Test
//  public void calculatePixelValueTest() {
//    image.Image aChunk2 = blur.makeChunk(image.getData(), 1, 1, 3);
//    System.out.println(blur.calculatePixelValue(aChunk2).getR());
//    System.out.println(blur.calculatePixelValue(aChunk2).getG());
//    System.out.println(blur.calculatePixelValue(aChunk2).getB());
//  }
//
//  @Test
//  public void blurTest() {
//    grey.apply(image).write("grey.png");
//    blur.applyFilter(image).write("blur.png");
//  }
//
//  @Test
//  public void sharpenTest() {
//    sharpen.applyFilter(image).write("sharper.png");
//  }
//
//
//  @Test
//  public void sepiaTest() {
//    sepia.apply(image).write("sepia.png");
//  }
//
//  @Test
//  public void HorizontalRainbowTest() {
//    rainbow.generate().write("horizontalRainbow.png");
//  }
//
//  @Test
//  public void VerticalRainbowTest() {
//    rainbow.generate().write("verticalRainbow.png");
//  }
//
//  @Test
//  public void CheckerboardTest() {
//    checkerboard.generate().write("Checkerboard.png");
//  }
//
//  @Test
//  public void GreeceTest() {
//    flags.generate().write("greece.png");
//  }
//
//  @Test
//  public void SwissTest() {
//    GreekFlag.generate().write("switzerland.png");
//  }
//
//}
