import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import model.IModel;
import model.ModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the GUI of the image processor program.
 */
public class MockGUITest {

  Appendable appendable;
  MockSwingFrame frame;
  IModel model;

  @Before
  public void setUp() {
    appendable = new StringBuilder();
    this.model = new ModelImpl(new ArrayList<>());
    this.frame = new MockSwingFrame(model, appendable);
  }

  @Test
  public void testGUICommand() {
    this.frame.callCommand("flip");
    assertEquals(appendable.toString(), "flip chosen ");
    this.frame.callCommand("brighten");
    assertEquals(appendable.toString(), "flip chosen brighten chosen ");
    this.frame.callCommand("component");
    assertEquals(appendable.toString(), "flip chosen brighten chosen component chosen ");
    this.frame.callCommand("load");
    assertEquals(appendable.toString(), "flip chosen brighten chosen component chosen "
            + "load chosen ");
    this.frame.callCommand("save");
    assertEquals(appendable.toString(), "flip chosen brighten chosen component chosen "
            + "load chosen save chosen ");
    this.frame.callCommand("sharpen");
    assertEquals(appendable.toString(), "flip chosen brighten chosen component chosen "
            + "load chosen save chosen other chosen ");
    this.frame.callCommand("blur");
    assertEquals(appendable.toString(), "flip chosen brighten chosen component chosen "
            + "load chosen save chosen other chosen other chosen ");
    this.frame.callCommand("luma");
    assertEquals(appendable.toString(), "flip chosen brighten chosen component chosen "
            + "load chosen save chosen other chosen other chosen other chosen ");
    this.frame.callCommand("sepia");
    assertEquals(appendable.toString(), "flip chosen brighten chosen component chosen "
            + "load chosen save chosen other chosen other chosen other chosen other chosen ");
    this.frame.callCommand("");
    assertEquals(appendable.toString(), "flip chosen brighten chosen component chosen "
            + "load chosen save chosen other chosen other chosen other chosen other chosen "
            + "N/A chosen ");
  }

  @Test
  public void testGUIButton() {
    this.frame.actionPerformedTest("get file");
    assertEquals(appendable.toString(), "get / save clicked ");
    this.frame.actionPerformedTest("save file");
    assertEquals(appendable.toString(), "get / save clicked get / save clicked ");
    this.frame.actionPerformedTest("submit");
    assertEquals(appendable.toString(), "get / save clicked get / save clicked "
            + "submit clicked ");
  }

}
