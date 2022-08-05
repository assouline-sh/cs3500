import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import controller.ControllerImpl;
import controller.IController;
import controller.commands.BrightenOrDarken;
import controller.commands.Commands;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.Greyscale;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.TransformColor;
import model.IModel;
import model.image.Image;
import model.ModelImpl;
import view.IView;
import view.ViewImpl;
import readfiles.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class to test the Image Controller.
 */
public class ImageControllerTest {
  Image koala;
  ArrayList<Image> versionList1 = new ArrayList<>();
  ArrayList<Image> versionList2 = new ArrayList<>();
  ArrayList<Image> versionList3 = new ArrayList<>();
  IModel koalaModel1;
  IModel koalaModel2;
  IModel koalaModel3;

  StringBuilder viewLog1;
  StringBuilder viewLog2;
  StringBuilder viewLog3;

  IView koalaView1;
  IView koalaView2;
  IView koalaView3;

  IController controller1;
  IController controller2;
  IController controller3;

  Readable readable1;
  Readable readable2;
  Readable readable3;

  StringBuilder mockModelLog1;
  StringBuilder mockModelLog2;
  StringBuilder mockModelLog3;

  IModel mockModel1;
  IModel mockModel2;
  IModel mockModel3;

  StringBuilder mockViewLog1;
  StringBuilder mockViewLog2;
  StringBuilder mockViewLog3;

  IView mockView1;
  IView mockView2;
  IView mockView3;

  @Before
  public void init() {

    //should read it correctly from our file
    try {
      koala = new Image(Util.readPPM("./res/koala/koala.ppm"), "koala");
    } catch (FileNotFoundException e) {
      fail("Should not throw FileNotFoundException on given koala.ppm");
    }

    koalaModel1 = new ModelImpl(versionList1);
    koalaModel2 = new ModelImpl(versionList2);
    koalaModel3 = new ModelImpl(versionList3);

    viewLog1 = new StringBuilder();
    viewLog2 = new StringBuilder();
    viewLog3 = new StringBuilder();

    koalaView1 = new ViewImpl(koalaModel1, viewLog1);
    koalaView2 = new ViewImpl(koalaModel2, viewLog2);
    koalaView3 = new ViewImpl(koalaModel3, viewLog2);

    mockModelLog1 = new StringBuilder();
    mockModelLog2 = new StringBuilder();
    mockModelLog3 = new StringBuilder();

    mockModel1 = new MockModel(mockModelLog1);
    mockModel2 = new MockModel(mockModelLog2);
    mockModel3 = new MockModel(mockModelLog3);

    mockViewLog1 = new StringBuilder();
    mockViewLog2 = new StringBuilder();
    mockViewLog3 = new StringBuilder();

    mockView1 = new ViewImpl(mockModel1, mockViewLog1);
    mockView2 = new ViewImpl(mockModel2, mockViewLog2);
    mockView3 = new ViewImpl(mockModel3, mockViewLog3);
  }

  @Test
  public void TestControllerConstructor() {
    readable1 = new StringReader("load ./res/koala/koala.ppm koala");
    readable2 = new StringReader("load ./res/koala/koala.jpg koala");
    readable3 = new StringReader("load ./res/koala/koala.bmp koala");

    //valid controller
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);

    //tests each param for null
    try {
      controller1 = new ControllerImpl(null, koalaView1, readable1);
    } catch (IllegalArgumentException e) {
      assertEquals("Null Parameters", e.getMessage());
    }

    try {
      controller2 = new ControllerImpl(koalaModel1, null, readable1);
    } catch (IllegalArgumentException e) {
      assertEquals("Null Parameters", e.getMessage());
    }

    try {
      controller3 = new ControllerImpl(koalaModel1, koalaView1, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Null Parameters", e.getMessage());
    }
  }

  @Test
  public void TestControllerGetCommandList() {
    readable1 = new StringReader("save ./res/koala/koala.ppm koala");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);

    Map<String, Function<ArrayList<String>, Commands>> knownCommands = new HashMap<>();
    knownCommands.put("load", args -> new Load(args));
    knownCommands.put("save", args -> new Save(args));
    knownCommands.put("flip", args -> new Flip(args));
    knownCommands.put("component", args -> new Greyscale(args));
    knownCommands.put("brighten", args -> new BrightenOrDarken(args));
    knownCommands.put("blur", args -> new Filter(args));
    knownCommands.put("sharpen", args -> new Filter(args));
    knownCommands.put("sepia", args -> new TransformColor(args));

    //for every command shown here, test that controller has it
    for (Map.Entry<String, Function<ArrayList<String>, Commands>> e
            : knownCommands.entrySet()) {
      assertTrue(IController.getCommandList().containsKey(e.getKey()));
    }
  }

  @Test
  public void TestControllerInvalidInput() {
    readable1 = new StringReader("foo ./res/koala/koala.ppm koala");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Not a command", e.getMessage());
    }

    init();
    readable1 = new StringReader("save ./res/koala/koala.ppm blahblah");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    init();
    readable1 = new StringReader("load blahblah koala");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("IOException", e.getMessage());
    }

    readable2 = new StringReader("foo-component koala foo");
    versionList2.add(koala);
    controller2 = new ControllerImpl(koalaModel2, koalaView2, readable2);
    try {
      controller2.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Please give a valid component.", e.getMessage());
    }

    readable1 = new StringReader("red-component koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("blue-component koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("green-component koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("intensity-component koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("luma-component koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("lum koala koala2");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Not a command", e.getMessage());
    }

    readable1 = new StringReader("value-component koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("value-component koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("value-component koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("foo-flip koala foo");
    versionList1.add(koala);
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Give a supported type of flip.", e.getMessage());
    }
    versionList1 = new ArrayList<>();

    readable1 = new StringReader("horizontal-flip koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("flipped koala koala2");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Not a command", e.getMessage());
    }

    readable1 = new StringReader("vertical-flip koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("brighten 50 koala foo");
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }

    readable1 = new StringReader("brighten koala foo");
    versionList1.add(koala);
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("not a number", e.getMessage());
    }
    versionList1 = new ArrayList<>();

    readable1 = new StringReader("blur koala blah");
    versionList1.add(koala);
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }
    versionList1 = new ArrayList<>();

    readable1 = new StringReader("sharpen blah sharpened-koala");
    versionList1.add(koala);
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }
    versionList1 = new ArrayList<>();

    readable1 = new StringReader("sharpened blah sharpened-koala");
    versionList1.add(koala);
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Not a command", e.getMessage());
    }
    versionList1 = new ArrayList<>();

    readable1 = new StringReader("blur foo koala-blurred");
    versionList1.add(koala);
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }
    versionList1 = new ArrayList<>();

    readable1 = new StringReader("sepia koala foo");
    versionList1.add(koala);
    controller1 = new ControllerImpl(koalaModel1, koalaView1, readable1);
    try {
      controller1.performCommand();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not real", e.getMessage());
    }
    versionList1 = new ArrayList<>();


  }

  @Test
  public void TestControllerCommands() {

    init();
    readable2 = new StringReader("save ./res/koala/koala.ppm koala");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("save() called path: ./res/koala/koala.ppm version: koala"));


    init();
    readable2 = new StringReader("load ./res/koala/koala.ppm koala");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("load() called path: ./res/koala/koala.ppm name: koala"));

    init();
    readable2 = new StringReader("red-component koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("greyscale() called component: red version: koala name: koala2"));

    init();
    readable2 = new StringReader("blue-component koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("greyscale() called component: blue version: koala name: koala2"));

    init();
    readable2 = new StringReader("green-component koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("greyscale() called component: green version: koala name: koala2"));

    init();
    readable2 = new StringReader("intensity-component koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("greyscale() called component: intensity version: koala name: koala2"));

    init();
    readable2 = new StringReader("value-component koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("greyscale() called component: value version: koala name: koala2"));

    init();
    readable2 = new StringReader("luma-component koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("transformColor() called type: luma path: koala name: koala2"));

    init();
    readable2 = new StringReader("vertical-flip koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("vertical : flip() called version: koala name: koala2\n"));

    init();
    readable2 = new StringReader("horizontal-flip koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("horizontal : flip() called version: koala name: koala2"));

    init();
    readable2 = new StringReader("brighten 50 koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("brightenOrDarken() factor: 50 version: koala name: koala2"));

    init();
    readable2 = new StringReader("brighten -50 koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("brightenOrDarken() factor: -50 version: koala name: koala2"));

    init();
    readable2 = new StringReader("blur ./res/koala/koala.ppm koala");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("filter() called type: blur path: ./res/koala/koala.ppm name: koala"));

    init();
    readable2 = new StringReader("sharpen ./res/koala/koala.ppm koala");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("filter() called type: sharpen path: ./res/koala/koala.ppm name: koala"));

    init();
    readable2 = new StringReader("sepia koala koala2");
    controller2 = new ControllerImpl(mockModel2, mockView2, readable2);
    controller2.performCommand();
    assertTrue(mockModelLog2.toString()
            .contains("transformColor() called type: sepia path: koala name: koala2"));
  }
}

