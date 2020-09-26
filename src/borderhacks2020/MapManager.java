package borderhacks2020;

import borderhacks2020.ui.ShapeComponent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class MapManager {
    public static final int lfBound = 0, rtBound = 400, upBound = 0, lwBound = 400;
    private ArrayList<ShapeComponent> cases = new ArrayList<ShapeComponent>();

    public void add (GameContainer gameContainer, int numOfCases) {
        int width = rtBound - lfBound;
        int height = lwBound - upBound;
        for (int i = 0; i < numOfCases; i++) {
            cases.add(new ShapeComponent(gameContainer, (int) (Math.random() * width) + lfBound, (int) (Math.random() * height) + upBound, 16, 16, Color.red, Color.transparent));
        }
    }

    public void render (GameContainer gameContainer, Graphics g) throws SlickException {
        for (ShapeComponent s: cases) {
            s.render(gameContainer, g);
        }
    }
}
