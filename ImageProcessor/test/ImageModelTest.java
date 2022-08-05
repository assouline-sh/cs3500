import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import model.IModel;
import model.image.Image;
import model.image.Pixel;
import model.ModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static readfiles.Util.readFile;
import static readfiles.Util.readPPM;

/**
 * Test methods for the image model.
 */
public class ImageModelTest {
  private Pixel pixel1;
  private Pixel pixel2;
  private Pixel pixel3;
  private Image image1;
  private Image image2;
  private Image image3;
  private Image image4;
  private Image image5;
  private ModelImpl model1;


  @Before
  public void setUp() throws FileNotFoundException {
    pixel1 = new Pixel(0,0, new Color(50, 100, 75));
    pixel2 = new Pixel(10,20, new Color(10, 210, 80));
    pixel3 = new Pixel(40,15, new Color(75, 20, 175));

    List<Pixel> array1 = new ArrayList<Pixel>();
    List<Pixel> array2 = new ArrayList<Pixel>();
    List<Pixel> array3 = new ArrayList<Pixel>();
    List<Pixel> array4 = new ArrayList<Pixel>();
    List<Pixel> array5 = new ArrayList<Pixel>();
    List<List<Pixel>> pixelArray1 = new ArrayList<List<Pixel>>();
    List<List<Pixel>> pixelArray2 = new ArrayList<List<Pixel>>();
    List<List<Pixel>> pixelArray3 = new ArrayList<List<Pixel>>();
    List<List<Pixel>> pixelArray4 = new ArrayList<List<Pixel>>();
    List<List<Pixel>> pixelArray5 = new ArrayList<List<Pixel>>();
    array1.add(new Pixel(0, 0, new Color(10, 30, 50)));
    array1.add(new Pixel(0, 1, new Color(10, 30, 50)));
    array1.add(new Pixel(0, 2, new Color(10, 30, 50)));
    array2.add(new Pixel(1, 0, new Color(100, 200, 150)));
    array2.add(new Pixel(1, 1, new Color(100, 200, 150)));
    array2.add(new Pixel(1, 2, new Color(100, 200, 150)));
    array3.add(new Pixel(2, 0, new Color(40, 70, 175)));
    array3.add(new Pixel(2, 1, new Color(40, 70, 175)));
    array3.add(new Pixel(2, 2, new Color(40, 70, 175)));
    array4.add(new Pixel(0, 0, new Color(10, 30, 50)));
    array5.add(new Pixel(0, 0, new Color(10, 30, 50)));
    array5.add(new Pixel(0, 1, new Color(10, 30, 50)));
    pixelArray1.add(array1);
    pixelArray1.add(array2);
    pixelArray1.add(array3);
    pixelArray2.add(array1);
    pixelArray2.add(array2);
    pixelArray3.add(array1);
    pixelArray4.add(array4);
    pixelArray4.add(array5);
    pixelArray5.add(array5);
    image1 = new Image(pixelArray1, "testImage1");
    image2 = new Image(pixelArray2, "testImage2");
    image3 = new Image(pixelArray3, "testImage3");
    image4 = new Image(pixelArray4, "testImage4");
    image5 = new Image(pixelArray5, "testImage5");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelConstructor() {
    Pixel invalidPixel1 = new Pixel(-3,0, new Color(50, 100, 75));
    Pixel invalidPixel2 = new Pixel(-7,-2, new Color(25, 150, 35));
    Pixel invalidPixel3 = new Pixel(4,-11, new Color(50, 100, 175));
  }

  @Test
  public void testGetColor() {
    assertEquals(pixel1.getColor(), new Color(50, 100, 75));
    assertEquals(pixel2.getColor(), new Color(10, 210, 80));
    assertEquals(pixel3.getColor(), new Color(75, 20, 175));
  }

  @Test
  public void testSetColor() {
    Color setColor1 = new Color(0, 10, 20);
    Color setColor2 = new Color(190, 250, 30);
    Color setColor3 = new Color( 1, 40, 219);
    pixel1.setColor(setColor1);
    pixel2.setColor(setColor1);
    pixel3.setColor(setColor2);
    assertEquals(pixel1.getColor(), setColor1);
    assertEquals(pixel2.getColor(), setColor1);
    assertEquals(pixel3.getColor(), setColor2);

    try {
      pixel1.setColor(setColor3);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Must give valid color.", e.getMessage());
    }
  }

  @Test
  public void testSetX() {
    pixel1.setX(10);
    pixel2.setX(0);
    assertEquals(pixel1.getX(), 10);
    assertEquals(pixel2.getX(), 0);
    try {
      pixel3.setX(-9);
    }
    catch (IllegalArgumentException e) {
      assertEquals("x cannot be negative.", e.getMessage());
    }
  }

  @Test
  public void testSetY() {
    pixel1.setY(10);
    pixel2.setY(0);
    assertEquals(pixel1.getY(), 10);
    assertEquals(pixel2.getY(), 0);
    try {
      pixel3.setY(-1);
    }
    catch (IllegalArgumentException e) {
      assertEquals("y cannot be negative.", e.getMessage());
    }
  }

  @Test
  public void testMaxValue() {
    assertEquals(pixel1.maxValue(), 100);
    assertEquals(pixel2.maxValue(), 210);
    assertEquals(pixel3.maxValue(), 175);
  }

  @Test
  public void testIntensity() {
    assertEquals(pixel1.intensity(), 75);
    assertEquals(pixel2.intensity(), 100);
    assertEquals(pixel3.intensity(), 90);
  }

  @Test
  public void testColorTransformation() {
    double[][] filter = new double[][]{{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
    double[][] filter2 =
            new double[][]{{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    assertEquals(pixel1.colorTransformation(filter)[0], 86.0, 0.1);
    assertEquals(pixel1.colorTransformation(filter)[1], 86.0, 0.1);
    assertEquals(pixel1.colorTransformation(filter)[2], 86.0, 0.1);
    assertEquals(pixel2.colorTransformation(filter)[0], 157.0, 0.1);
    assertEquals(pixel2.colorTransformation(filter)[1], 157.0, 0.1);
    assertEquals(pixel2.colorTransformation(filter)[2], 157.0, 0.1);
    assertEquals(pixel3.colorTransformation(filter2)[0], 77.0, 0.1);
    assertEquals(pixel3.colorTransformation(filter2)[1], 68.0, 0.1);
    assertEquals(pixel3.colorTransformation(filter2)[2], 52.0, 0.1);
  }





  @Test
  public void testImageController() {
    ArrayList<List<Pixel>> array = new ArrayList<List<Pixel>>();
    List<Pixel> innerArray1 = new ArrayList<Pixel>();
    List<Pixel> innerArray2 = new ArrayList<Pixel>();
    innerArray1.add(pixel1);
    innerArray2.add(pixel2);
    innerArray2.add(pixel3);
    array.add(innerArray1);
    array.add(innerArray2);
    try {
      Image invalidImage1 = new Image(new ArrayList<List<Pixel>>(), "name");
    }
    catch (IllegalArgumentException e) {
      assertEquals("Pixels or image name cannot be null.", e.getMessage());
    }

    try {
      Image invalidImage2 = new Image(array, "");
    }
    catch (IllegalArgumentException e) {
      assertEquals("Pixels or image name cannot be null.", e.getMessage());
    }

    try {
      Image invalidImage3 = new Image(new ArrayList<List<Pixel>>(), "");
    }
    catch (IllegalArgumentException e) {
      assertEquals("Pixels or image name cannot be null.", e.getMessage());
    }
  }

  @Test
  public void testGetArrayCopy() {
    List<List<Pixel>> copy1 = new ArrayList<List<Pixel>>();
    copy1 = image1.getArrayCopy();
    assertEquals(copy1.get(0).get(0).getColor(), new Color(10, 30, 50));
    assertEquals(copy1.get(1).get(0).getColor(), new Color(100, 200, 150));
    assertEquals(copy1.get(1).get(2).getColor(), new Color(100, 200, 150));
    assertEquals(copy1.get(2).get(0).getColor(), new Color(40, 70, 175));
  }

  @Test
  public void testGetPixel() {
    assertEquals(image1.getPixel(0,0).getColor(), new Color(10, 30, 50));
    assertEquals(image1.getPixel(1,0).getColor(), new Color(100, 200, 150));
    assertEquals(image1.getPixel(1,2).getColor(), new Color(100, 200, 150));
    assertEquals(image1.getPixel(2,2).getColor(), new Color(40, 70, 175));
  }

  @Test
  public void testGetHeight() {
    assertEquals(image1.getHeight(), 3);
    assertEquals(image2.getHeight(), 2);
    assertEquals(image3.getHeight(), 1);
  }

  @Test
  public void testGetWidth() {
    assertEquals(image3.getWidth(), 3);
    assertEquals(image4.getWidth(), 1);
    assertEquals(image5.getWidth(), 2);
  }

  @Test
  public void testGetName() {
    assertEquals(image1.getName(), "testImage1");
    assertEquals(image2.getName(), "testImage2");
    assertEquals(image3.getName(), "testImage3");
  }





  @Test
  public void testModelController() {
    ArrayList<Image> versions = new ArrayList<Image>();
    try {
      IModel model1 = new ModelImpl(versions);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Versions cannot be null.", e.getMessage());
    }
  }

  @Test
  public void testSave() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    ArrayList<Image> koala1 = new ArrayList<Image>();
    Image image = new Image(readPPM("./res/koala/Koala.ppm"), "koala");
    koala1.add(image);
    model1 = new ModelImpl(koala1);
    model1.callTestSave("./res/koala/Koala-new-save.ppm", "koala", log);
    assertTrue(log.toString().contains("P3"));
    assertTrue(log.toString().contains("320 240"));
    assertTrue(log.toString().contains("255\n103\n91\n63\n103"));
  }

  @Test
  public void testLoad() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    ArrayList<Image> koala1 = new ArrayList<Image>();
    Image image = new Image(readPPM("./res/koala/Koala.ppm"),
            "koala");
    koala1.add(image);
    model1 = new ModelImpl(koala1);
    model1.load("./res/koala/Koala.ppm", "koala");
    model1.callTestSave("./res/koala/Koala.ppm",  "koala", log);
    assertEquals(model1.getVersions().size(), 2);
    assertEquals(model1.getVersions().get(0).getName(), "koala");
    model1.load("./res/tomato/tomato.ppm", "tomato");
    model1.save("./res/tomato/tomato.jpg", "tomato");
    model1.save("./res/tomato/tomato.bmp", "tomato");
    model1.save("./res/tomato/tomato.png", "tomato");
    model1.callTestSave("./res/tomato/tomato.png", "tomato", log);
    assertEquals(model1.getVersions().size(), 3);
    assertEquals(model1.getVersions().get(2).getName(), "tomato");

    ArrayList<Image> tomato1 = new ArrayList<Image>();
    Image image2 = new Image(readFile("./res/tomato/tomato.jpg"), "tomatoj");
    tomato1.add(image2);
    model1 = new ModelImpl(tomato1);
    model1.load("./res/tomato/tomato.jpg", "tomatoj");
    model1.callTestSave("./res/tomato/tomato.png", "tomatoj", log);
    assertEquals(model1.getVersions().size(), 2);
    assertEquals(model1.getVersions().get(1).getName(), "tomatoj");
    model1.load("./res/tomato/tomato.ppm", "tomatop");
    model1.callTestSave("./res/tomato/tomato.bmp", "tomatop", log);
    assertEquals(model1.getVersions().size(), 3);
    assertEquals(model1.getVersions().get(2).getName(), "tomatop");

    ArrayList<Image> tomato2 = new ArrayList<Image>();
    Image image3 = new Image(readFile("./res/tomato/tomato.ppm"), "tomatop");
    tomato2.add(image3);
    model1 = new ModelImpl(tomato2);
    model1.load("./res/tomato/tomato.jpg", "tomatop");
    model1.callTestSave("./res/tomato/tomato.bmp", "tomatop", log);
    assertEquals(model1.getVersions().size(), 2);
    assertEquals(model1.getVersions().get(1).getName(), "tomatop");
    model1.load("./res/tomato/tomato.ppm", "tomatopp");
    model1.callTestSave("./res/tomato/tomato.jpg", "tomatopp", log);
    assertEquals(model1.getVersions().size(), 3);
    assertEquals(model1.getVersions().get(2).getName(), "tomatopp");

    try {
      model1.load("./res/koala/panda.ppm", "panda");
    }
    catch (IllegalArgumentException e ) {
      assertEquals("IOException", e.getMessage());
    }
  }

  @Test
  public void testGreyscale() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    ArrayList<Image> tomato1 = new ArrayList<Image>();
    Image image = new Image(readPPM("./res/tomato/tomato.ppm"),
            "tomato1");
    tomato1.add(image);
    model1 = new ModelImpl(tomato1);
    model1.greyscale("red", "tomato1", "red-tomato1");
    model1.callTestSave("./res/tomato/red-tomato.ppm", "red-tomato1", log);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "230\n"
            + "230\n"
            + "230\n"
            + "230\n"
            + "230\n"
            + "230\n"
            + "230\n"
            + "230\n"
            + "230\n"
            + "229\n"
            + "229\n"
            + "229\n"
            + "229\n"
            + "229", log.toString().substring(0, 70));
    assertEquals(model1.getVersions().size(), 2);

    StringBuilder log1 = new StringBuilder();
    model1.greyscale("blue", "tomato1", "blue-tomato1");
    model1.callTestSave("./res/tomato/blue-tomato1.ppm", "blue-tomato1", log1);
    assertEquals("P3\n"
                    + "500 333\n"
                    + "255\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "240\n"
                    + "240\n"
                    + "240\n"
                    + "240\n"
                    + "240",
            log1.toString().substring(0, 70));
    assertEquals(model1.getVersions().size(), 3);

    StringBuilder log2 = new StringBuilder();
    model1.greyscale("green", "tomato1", "green-tomato1");
    model1.callTestSave("./res/tomato/green-tomato1.ppm", "green-tomato1", log2);
    assertEquals( "P3\n"
                    + "500 333\n"
                    + "255\n"
                    + "238\n"
                    + "238\n"
                    + "238\n"
                    + "238\n"
                    + "238\n"
                    + "238\n"
                    + "238\n"
                    + "238\n"
                    + "238\n"
                    + "237\n"
                    + "237\n"
                    + "237\n"
                    + "237\n"
                    + "237",
            log2.toString().substring(0, 70));
    assertEquals(model1.getVersions().size(), 4);


    StringBuilder log3 = new StringBuilder();
    model1.greyscale("value", "tomato1", "value-tomato1");
    model1.callTestSave("./res/tomato/value-tomato1.ppm", "value-tomato1", log3);
    assertEquals("P3\n"
                    + "500 333\n"
                    + "255\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "241\n"
                    + "240\n"
                    + "240\n"
                    + "240\n"
                    + "240\n"
                    + "240",
            log3.toString().substring(0, 70));
    assertEquals(model1.getVersions().size(), 5);


    StringBuilder log4 = new StringBuilder();
    model1.greyscale("intensity", "tomato1", "intensity-tomato1");
    model1.callTestSave("./res/tomato/intensity-tomato1.ppm", "intensity-tomato1", log4);
    assertEquals( "P3\n"
                    + "500 333\n"
                    + "255\n"
                    + "236\n"
                    + "236\n"
                    + "236\n"
                    + "236\n"
                    + "236\n"
                    + "236\n"
                    + "236\n"
                    + "236\n"
                    + "236\n"
                    + "235\n"
                    + "235\n"
                    + "235\n"
                    + "235\n"
                    + "235",
            log4.toString().substring(0, 70));
    assertEquals(model1.getVersions().size(), 6);
  }

  @Test
  public void testVerticalFlip() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    ArrayList<Image> tomato1 = new ArrayList<Image>();
    Image image = new Image(readPPM("./res/tomato/tomato.ppm"),
            "tomato1");
    tomato1.add(image);
    model1 = new ModelImpl(tomato1);
    model1.flip("vertical", "tomato1", "vertical-tomato1");
    model1.callTestSave("./res/tomato/vertical-tomato1.ppm", "vertical-tomato1", log);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "239\n"
            + "243\n"
            + "252\n"
            + "239\n"
            + "243\n"
            + "252\n"
            + "239\n"
            + "243\n"
            + "252\n"
            + "239\n"
            + "243\n"
            + "252\n"
            + "239\n"
            + "243", log.toString().substring(0, 70));
    assertEquals(model1.getVersions().size(), 2);

    StringBuilder log1 = new StringBuilder();
    model1.flip("vertical", "vertical-tomato1", "vertical2-tomato1");
    model1.callTestSave("./res/tomato/vertical2-tomato1.ppm", "vertical2-tomato1", log1);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "230\n"
            + "238\n"
            + "241\n"
            + "230\n"
            + "238\n"
            + "241\n"
            + "230\n"
            + "238\n"
            + "241\n"
            + "229\n"
            + "237\n"
            + "240\n"
            + "229\n"
            + "237", log1.toString().substring(0, 70));
    assertEquals(model1.getVersions().size(), 3);

    StringBuilder log2 = new StringBuilder();
    model1.flip("vertical", "vertical2-tomato1", "vertical3-tomato1");
    model1.callTestSave("./res/tomato/vertical3-tomato1.ppm", "vertical3-tomato1", log2);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "239\n"
            + "243\n"
            + "252\n"
            + "239\n"
            + "243\n"
            + "252\n"
            + "239\n"
            + "243\n"
            + "252\n"
            + "239\n"
            + "243\n"
            + "252\n"
            + "239\n"
            + "243", log2.toString().substring(0, 70));
    assertEquals(model1.getVersions().size(), 4);
  }

  @Test
  public void testHorizontalFlip() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    ArrayList<Image> tomato1 = new ArrayList<Image>();
    Image image = new Image(readPPM("./res/tomato/tomato.ppm"),
            "tomato1");
    tomato1.add(image);
    model1 = new ModelImpl(tomato1);
    model1.flip("horizontal", "tomato1", "horizontal-tomato1");
    model1.callTestSave("./res/tomato/horizontal-tomato1.ppm", "horizontal-tomato1", log);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "214\n"
            + "225\n"
            + "231\n"
            + "214\n"
            + "225\n"
            + "231\n"
            + "214\n"
            + "225\n"
            + "231\n"
            + "214\n"
            + "225\n"
            + "231\n"
            + "214\n"
            + "225\n"
            + "231\n"
            + "214", log.toString().substring(0, 78));
    assertEquals(model1.getVersions().size(), 2);

    StringBuilder log1 = new StringBuilder();
    model1.flip("horizontal", "horizontal-tomato1", "horizontal2-tomato1");
    model1.callTestSave("./res/tomato/horizontal2-tomato1.ppm", "horizontal2-tomato1", log1);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "230\n"
            + "238\n"
            + "241\n"
            + "230\n"
            + "238\n"
            + "241\n"
            + "230\n"
            + "238\n"
            + "241\n"
            + "229\n"
            + "237\n"
            + "240\n"
            + "229\n"
            + "237", log1.toString().substring(0, 70));
    assertEquals(model1.getVersions().size(), 3);

    StringBuilder log2 = new StringBuilder();
    model1.flip("horizontal", "horizontal2-tomato1", "horizontal3-tomato1");
    model1.callTestSave("./res/tomato/horizontal2-tomato1.ppm", "horizontal3-tomato1", log2);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "214\n"
            + "225\n"
            + "231\n"
            + "214\n"
            + "225\n"
            + "231\n"
            + "214\n"
            + "225\n"
            + "231\n"
            + "214\n"
            + "225\n"
            + "231\n"
            + "214\n"
            + "225\n"
            + "231\n"
            + "214", log2.toString().substring(0, 78));
    assertEquals(model1.getVersions().size(), 4);
  }


  @Test
  public void testFilter() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    ArrayList<Image> tomato1 = new ArrayList<Image>();
    Image image = new Image(readPPM("./res/tomato/tomato.ppm"),
            "tomato1");
    tomato1.add(image);
    model1 = new ModelImpl(tomato1);
    model1.filter("blur", "tomato1", "blur-tomato1");
    model1.callTestSave("./res/tomato/blur-tomato.ppm", "blur-tomato1", log);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "127\n"
            + "131\n"
            + "135\n"
            + "156\n"
            + "161\n"
            + "166\n"
            + "160\n"
            + "165\n"
            + "170\n"
            + "161\n"
            + "165\n"
            + "171\n"
            + "161\n"
            + "165", log.toString().substring(0, 70));

    StringBuilder log2 = new StringBuilder();
    model1.filter("sharpen", "tomato1", "sharp-tomato1");
    model1.callTestSave("./res/tomato/sharp-tomato1.ppm", "sharp-tomato1", log2);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "255", log2.toString().substring(0, 78));

    try {
      model1.filter("foo", "tomato1", "foo-tomato1");
    } catch (IllegalArgumentException e) {
      assertEquals("Give a supported filter type.", e.getMessage());
    }

  }

  @Test
  public void testTransformColor() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    ArrayList<Image> tomato1 = new ArrayList<Image>();
    Image image = new Image(readPPM("./res/tomato/tomato.ppm"),
            "tomato1");
    tomato1.add(image);
    model1 = new ModelImpl(tomato1);
    model1.transformColor("sepia", "tomato1", "sepia-tomato1");
    model1.callTestSave("./res/tomato/sepia-tomato.ppm", "sepia-tomato1", log);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "255\n"
            + "255\n"
            + "220\n"
            + "255\n"
            + "255\n"
            + "220\n"
            + "255\n"
            + "255\n"
            + "220\n"
            + "255\n"
            + "255\n"
            + "219\n"
            + "255\n"
            + "255", log.toString().substring(0, 70));

    StringBuilder log2 = new StringBuilder();
    model1.transformColor("luma", "tomato1", "luma-tomato1");
    model1.callTestSave("./res/tomato/luma-tomato1.ppm", "luma-tomato1", log2);
    assertEquals("P3\n"
            + "500 333\n"
            + "255\n"
            + "235\n"
            + "235\n"
            + "235\n"
            + "235\n"
            + "235\n"
            + "235\n"
            + "235\n"
            + "235\n"
            + "235\n"
            + "234\n"
            + "234\n"
            + "234\n"
            + "234\n"
            + "234\n"
            + "234\n"
            + "233", log2.toString().substring(0, 78));

    try {
      model1.transformColor("foo", "tomato1", "foo-tomato1");
    } catch (IllegalArgumentException e) {
      assertEquals("Give a supported filter type.", e.getMessage());
    }
  }
}
