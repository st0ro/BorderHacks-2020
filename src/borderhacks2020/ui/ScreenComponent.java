package borderhacks2020.ui;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 * GUI component to be rendered on to the screen.
 * Components are centered on their given position parameters.
 * @author Alex Price
 *
 */
public abstract class ScreenComponent extends AbstractComponent {

    /**
     * Shape representing the area the component exists in.
     */
    protected Shape screenBox;

    /**
     * Whether or not this element is enabled for input or other actions.
     */
    protected boolean enabled;

    /**
     * Creates a ScreenComponent that is default disabled.
     * @param container container that this component is in
     */
    public ScreenComponent(GUIContext container) {
        super(container);
        setEnabled(false);
    }

    @Override
    public void setLocation(int x, int y) {
        if(screenBox != null) {
            screenBox.setCenterX(x);
            screenBox.setCenterY(y);
        }
    }

    @Override
    public int getX() {
        if(screenBox != null) {
            return (int) screenBox.getCenterX();
        }
        return 0;
    }

    @Override
    public int getY() {
        if(screenBox != null) {
            return (int) screenBox.getCenterY();
        }
        return 0;
    }

    @Override
    public int getWidth() {
        if(screenBox != null) {
            return (int) screenBox.getWidth();
        }
        return 0;
    }

    @Override
    public int getHeight() {
        if(screenBox != null) {
            return (int) screenBox.getHeight();
        }
        return 0;
    }

    /**
     * Sets the enabled parameter.
     * @param set value to set to
     */
    public void setEnabled(boolean set) {
        enabled = set;
    }

    /**
     * Returns the current enabled status.
     * @return current enabled status
     */
    public boolean getEnabled() {
        return enabled;
    }

    public void move(int dx, int dy){
        screenBox.setLocation(screenBox.getCenterX() + dx, screenBox.getCenterY() + dy);
    }

}