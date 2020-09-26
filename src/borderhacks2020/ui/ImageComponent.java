package borderhacks2020.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

/**
 * ScreenComponent made up of a single image to be displayed.
 * @author Alex Price
 *
 */
public class ImageComponent extends ScreenComponent {

    private Image image;

    /**
     * Creates an ImageComponent from the given path and position, with no scaling.
     * @param container container to use
     * @param path path of the Image file
     * @param x centered x position to render at
     * @param y centered y position to render at
     */
    public ImageComponent(GUIContext container, String path, int x, int y) {
        super(container);
        try {
            image = new Image(path);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        screenBox = new Rectangle(0, 0, image.getWidth(), image.getHeight());
        setLocation(x, y);
    }

    /**
     * Creates an ImageComponent from the given path and position, with scaling to match the given width and height.
     * @param container container to use
     * @param path path of the Image file
     * @param x centered x position to render at
     * @param y centered y position to render at
     * @param width width of the Image
     * @param height height of the Image
     */
    public ImageComponent(GUIContext container, String path, int x, int y, int width, int height) {
        super(container);
        try {
            image = new Image(path);
            image.setFilter(Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        screenBox = new Rectangle(0, 0, width, height);
        setLocation(x, y);
    }

    /**
     * Creates an ImageComponent from the given Image and position, with no scaling.
     * @param container container to use
     * @param src Image to draw
     * @param x centered x position to render at
     * @param y centered y position to render at
     */
    public ImageComponent(GUIContext container, Image src, int x, int y) {
        super(container);
        image = src;
        image.setFilter(Image.FILTER_NEAREST);
        screenBox = new Rectangle(0, 0, image.getWidth(), image.getHeight());
        setLocation(x, y);
    }

    public ImageComponent(GUIContext container, Image src, int x, int y, int width, int height) {
        super(container);
        image = src;
        image.setFilter(Image.FILTER_NEAREST);
        screenBox = new Rectangle(0, 0, width, height);
        setLocation(x, y);
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        image.draw(getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
    }

    public void setImage(Image image) {
        this.image = image;
    }

}