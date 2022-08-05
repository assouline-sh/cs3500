import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import controller.commands.BrightenOrDarken;
import controller.commands.Commands;
import controller.commands.Flip;
import controller.commands.Greyscale;
import controller.commands.Load;
import controller.commands.Save;
import model.IModel;
import model.image.Image;
import model.ModelImpl;
import view.IView;
import view.ViewImpl;

import static readfiles.Util.readPPM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test methods to view the image model.
 */
public class ImageViewTest {

  private IModel model;

  @Before
  public void setUp() throws FileNotFoundException {
    ArrayList<Image> tomato1 = new ArrayList<Image>();
    Image image = new Image(readPPM("./res/tomato/tomato.ppm"),
            "tomato1");
    tomato1.add(image);
    model = new ModelImpl(tomato1);
  }

  @Test
  public void testViewConstructor() {
    try {
      IModel nullModel = new ModelImpl(new ArrayList<Image>());
      IView invalidView1 = new ViewImpl(nullModel);
    }
    catch (IllegalArgumentException e) {
      assertEquals("model cannot be null", e.getMessage());
    }

    try {
      IModel nullModel = new ModelImpl(new ArrayList<Image>());
      StringBuilder appendable = new StringBuilder();
      IView invalidView1 = new ViewImpl(nullModel, appendable);
    }
    catch (IllegalArgumentException e) {
      assertEquals("model cannot be null", e.getMessage());
    }
  }

  @Test
  public void testIOException() {
    StringBuilder log = new StringBuilder();
    IView mockView = new CorruptView(log);
    try {
      mockView.renderMessage("random message");
      fail("from renderMessage()");
    } catch (IOException e) {
      assertEquals("from renderMessage()", e.getMessage());
      assertTrue(log.toString().contains("renderMessage() IO exception"));
    }
    Map<String, Function<ArrayList<String>, Commands>> knownCommands = null;
    StringBuilder log1 = new StringBuilder();
    IView mockView1 = new CorruptView(log1);
    try {
      mockView1.showCommands(knownCommands);
      fail("from showCommands()");
    } catch (IOException e) {
      assertEquals("from showCommands()", e.getMessage());
      assertTrue(log1.toString().contains("showCommands() IO exception"));
    }
  }

  @Test
  public void testRenderMessage() throws FileNotFoundException {
    StringBuilder appendable = new StringBuilder();
    IView view1 = new ViewImpl(model, appendable);
    try {
      view1.renderMessage("Render this.\n");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    String[] result = appendable.toString().split("\n");
    assertEquals("Render this.", result[0]);

    try {
      view1.renderMessage("Now render this.\n");
      view1.renderMessage("Oh and this too.\n");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    String[] result2 = appendable.toString().split("\n");
    assertEquals("Render this.", result2[0]);
    assertEquals("Now render this.", result2[1]);
    assertEquals("Oh and this too.", result2[2]);

    StringBuilder appendable1 = new StringBuilder();
    IView view2 = new MockView(appendable1);
    try {
      view2.renderMessage("This is a test.");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    assertEquals(appendable1.toString(), "renderMessage() called s:This is a test.\n");
  }

  @Test
  public void testShowCommands() throws FileNotFoundException {
    Map<String, Function<ArrayList<String>, Commands>> knownCommands =
            new HashMap<String, Function<ArrayList<String>, Commands>>();
    knownCommands.put("load", args -> new Load(args));
    knownCommands.put("save", args -> new Save(args));
    knownCommands.put("flip", args -> new Flip(args));

    StringBuilder appendable = new StringBuilder();
    IView view1 = new ViewImpl(model, appendable);
    try {
      view1.showCommands(knownCommands);
    }
    catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    assertEquals(appendable.toString(), "\n"
            + "Commands: \n"
            + "load image-path image-name\n"
            + "save image-path image-name\n"
            + "horizontal-flip image-name dest-image-name\n"
            + "vertical-flip image-name dest-image-name\n");

    knownCommands.put("component", args -> new Greyscale(args));
    knownCommands.put("brighten", args -> new BrightenOrDarken(args));

    StringBuilder appendable1 = new StringBuilder();
    IView view2 = new ViewImpl(model, appendable1);
    try {
      view2.showCommands(knownCommands);
    }
    catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    assertEquals(appendable1.toString(), "\n"
            + "Commands: \n"
            + "brighten increment image-name dest-image-name\n"
            + "red-component image-name dest-image-name\n"
            + "green-component image-name dest-image-name\n"
            + "blue-component image-name dest-image-name\n"
            + "value-component image-name dest-image-name\n"
            + "intensity-component image-name dest-image-name\n"
            + "load image-path image-name\n"
            + "save image-path image-name\n"
            + "horizontal-flip image-name dest-image-name\n"
            + "vertical-flip image-name dest-image-name\n");

    StringBuilder appendable2 = new StringBuilder();
    IView view3 = new MockView(appendable2);
    try {
      view3.showCommands(knownCommands);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    assertTrue(appendable2.toString().contains("showCommands() called Commands:"));
  }
}
