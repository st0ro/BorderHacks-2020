package borderhacks2020;

import borderhacks2020.ui.ShapeComponent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class MapManager {
    private ArrayList<ShapeComponent> cases = new ArrayList<ShapeComponent>();

    public void add (GameContainer gameContainer, int activeCases) {
        activeCases /= 50;
        activeCases -= cases.size();
        if (activeCases < 0) {
            activeCases = Math.abs(activeCases);
            for (int i = 0; i < activeCases; i++) {
                cases.remove((int) Math.round(Math.random() * (cases.size() - 1)));
            }
        }
        else {
            int zone = 0;
            float weight = 0;
            int x = 0;
            int y = 0;
            for (int i = 0; i < activeCases; i++) {
                weight = (float) Math.random();
                if (weight > 0.45)
                    zone = 0;
                else if (weight > 0.30)
                    zone = 1;
                else if (weight > 0.05)
                    zone = 2;
                else if (weight > 0.025)
                    zone = 3;
                else
                    zone = 4;
                if (zone == 0) {        // residential zone
                    x = (int) (Math.random() * (615 - 390)) + 390;
                    y = (int) (Math.random() * (848 - 413)) + 413;
                }
                else if (zone == 1) {   // airport zone
                    x = (int) (Math.random() * (390 - 150)) + 150;
                    y = (int) (Math.random() * (615 - 533)) + 533;
                }
                else if (zone == 2) {   // university zone
                    x = (int) (Math.random() * (773 - 637)) + 637;
                    y = (int) (Math.random() * (428 - 322)) + 322;
                }
                else if (zone == 3) {   // boat 1
                    x = (int) (Math.random() * (195 - 165)) + 165;
                    y = (int) (Math.random() * (758 - 727)) + 727;
                }
                else if (zone == 4) {   // boat 2
                    x = (int) (Math.random() * (818 - 788)) + 788;
                    y = (int) (Math.random() * (600 - 570)) + 570;
                }
                cases.add(new ShapeComponent(gameContainer, x, y, 4, 4, Color.red, Color.transparent));
            }
        }
    }

    public void render (GameContainer gameContainer, Graphics g) throws SlickException {
        for (ShapeComponent s: cases) {
            s.render(gameContainer, g);
        }
    }
}
