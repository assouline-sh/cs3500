package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.image.Image;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import model.image.Pixel;

/**
 * This class creates a Histogram of the red, green, and blue components of an image.
 */
public class Histogram extends JPanel {
  private static final int BARWIDTH = 1;
  private Color red = new Color(255, 0, 0, 60);
  private Color green = new Color(0, 255, 0, 60);
  private Color blue = new Color(0, 0, 255, 60);
  private Image image;

  /**
   * Instantiate a new Histogram with the given image and create HashMaps of the number of pixels
   * at each value for red, green, and blue components.
   * @param image the image to create a histogram for
   */
  public Histogram(List<List<Pixel>> image) {
    this.image = new Image(image, "new");
  }

  /**
   * Draw the Histogram on the JPanel, and make the red, green, and blue bars of the Histogram
   * overlap.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graph = (Graphics2D) g.create();
    graph.setColor(Color.WHITE);

    HashMap<Integer, Integer> reds = this.image.countRed();
    HashMap<Integer, Integer> greens = this.image.countGreen();
    HashMap<Integer, Integer> blues = this.image.countBlue();

    for (Integer key : reds.keySet()) {
      Rectangle2D bar = new Rectangle2D.Float((float) (key * (this.getWidth() / 255)),
              this.getHeight() - (float) Math.min(255, reds.get(key) * 0.05),
              BARWIDTH,
              (float) Math.min(255, reds.get(key) * 0.05));
      graph.setPaint(red);
      graph.fill(bar);
      graph.draw(bar);
    }

    for (Integer key : greens.keySet()) {
      Rectangle2D bar = new Rectangle2D.Float((float) (key * (this.getWidth() / 255)),
              this.getHeight() - (float) Math.min(255, greens.get(key) * 0.05),
              BARWIDTH,
              (float) Math.min(255, greens.get(key) * 0.05));
      graph.setPaint(green);
      graph.fill(bar);
      graph.draw(bar);
    }

    for (Integer key : blues.keySet()) {
      Rectangle2D bar = new Rectangle2D.Float((float) (key * (this.getWidth() / 255)),
              this.getHeight() - (float) Math.min(255, blues.get(key) * 0.05),
              BARWIDTH,
              (float) Math.min(255, blues.get(key) * 0.05));
      graph.setPaint(blue);
      graph.fill(bar);
      graph.draw(bar);
    }
  }
}
