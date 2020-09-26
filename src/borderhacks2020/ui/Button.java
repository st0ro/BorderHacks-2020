package borderhacks2020.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

public class Button extends ScreenComponent {

    private Image image, hovered, held;
    private boolean pressed;

    public Button(GUIContext container, int x, int y, int width, int height, Image image) {
        super(container);
        screenBox = new Rectangle(0, 0, width, height);
        setLocation(x, y);
        setImage(image);
        setHovered(image);
        setHeld(image);
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        if(pressed) {
            held.draw(screenBox.getMinX(), screenBox.getMinY(), getWidth(), getHeight());
        }
        else {
            int mX = container.getInput().getMouseX();
            int mY = container.getInput().getMouseY();
            if(mX <= screenBox.getMaxX() && mX >= screenBox.getMinX() && mY <= screenBox.getMaxY() && mY >= screenBox.getMinY()) {
                hovered.draw(screenBox.getMinX(), screenBox.getMinY(), getWidth(), getHeight());
            }
            else {
                image.draw(screenBox.getMinX(), screenBox.getMinY(), getWidth(), getHeight());
            }
        }
    }

    @Override
    public void mousePressed(int btn, int x, int y) {
        if(enabled && btn == 0 && x <= screenBox.getMaxX() && x >= screenBox.getMinX() && y <= screenBox.getMaxY() && y >= screenBox.getMinY()) {
            pressed = true;
        }
    }

    @Override
    public void mouseReleased(int btn, int x, int y) {
        if(enabled && btn == 0 && x <= screenBox.getMaxX() && x >= screenBox.getMinX() && y <= screenBox.getMaxY() && y >= screenBox.getMinY()) {
            onLeftClick();
            pressed = false;
        }
    }

    public void onLeftClick() {

    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        image.setFilter(Image.FILTER_NEAREST);
    }

    public Image getHovered() {
        return hovered;
    }

    public void setHovered(Image hovered) {
        this.hovered = hovered;
        hovered.setFilter(Image.FILTER_NEAREST);
    }

    public Image getHeld() {
        return held;
    }

    public void setHeld(Image held) {
        this.held = held;
        held.setFilter(Image.FILTER_NEAREST);
    }

}