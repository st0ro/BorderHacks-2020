package borderhacks2020.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

public class Graph extends ScreenComponent{

    private int[] data = new int[100];

    public Graph(GUIContext container, int x, int y, int width, int height) {
        super(container);
        screenBox = new Rectangle(0, 0, width, height);
        setLocation(x, y);

    }

    @Override
    public void render(GUIContext guiContext, Graphics graphics) throws SlickException {

    }
}
