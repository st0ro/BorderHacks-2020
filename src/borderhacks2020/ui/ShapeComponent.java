package borderhacks2020.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;

/**
 * ScreenComponent made up of a single shape to be rendered. The outline and fill of the shape can be different colors.
 * Gradient ShapeFills are not supported.
 * @author Alex Price
 *
 */
public class ShapeComponent extends ScreenComponent {

    private Color fill, outline;
    private int outlineWidth;
    /**
     * Creates a ShapeComponent of a Rectangle with the given parameters
     * @param container container of the component
     * @param x centered x position
     * @param y centered y position
     * @param width width of the shape
     * @param height height of the shape
     * @param fill color to fill with
     * @param outline color to outline with
     */
    public ShapeComponent(GUIContext container, int x, int y, int width, int height, Color fill, Color outline) {
        super(container);
        screenBox = new Rectangle(0, 0, width, height);
        setLocation(x, y);
        setFill(fill);
        setOutline(outline);
        setOutlineWidth(5);
    }

    public ShapeComponent(GUIContext container, Shape shape, Color fill, Color outline) {
        super(container);
        screenBox = shape;
        setFill(fill);
        setOutline(outline);
        setOutlineWidth(5);
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        g.setLineWidth(getOutlineWidth());
        g.setColor(fill);
        g.fill(screenBox);
        g.setColor(outline);
        g.draw(screenBox);
    }

    /**
     * Gets the current fill Color
     * @return current fill Color
     */
    public Color getFill() {
        return fill;
    }

    /**
     * Sets the current fill Color
     * @param fill new fill Color
     */
    public void setFill(Color fill) {
        this.fill = fill;
    }

    /**
     * Gets the current outline Color
     * @return current outline Color
     */
    public Color getOutline() {
        return outline;
    }

    /**
     * Sets the current outline Color
     * @param outline new outline Color
     */
    public void setOutline(Color outline) {
        this.outline = outline;
    }

    /**
     * Gets the current outline thickness
     * @return current outline thickness
     */
    public int getOutlineWidth() {
        return outlineWidth;
    }

    /**
     * Sets the current outline thickness
     * @param outlineWidth new outline thickness
     */
    public void setOutlineWidth(int outlineWidth) {
        this.outlineWidth = outlineWidth;
    }

    public Shape getScreenBox() {return screenBox;}

    public void setScreenBox(Shape shape) {screenBox = shape;}

}