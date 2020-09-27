package borderhacks2020.ui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public class GuiGroup extends ScreenComponent {

    private List<ScreenComponent> components;

    public GuiGroup(GUIContext container) {
        super(container);
        components = new ArrayList<ScreenComponent>();
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        for(ScreenComponent sc:components) {
            sc.render(container, g);
        }
    }

    public void addComponent(ScreenComponent sc) {
        components.add(sc);
    }

    public ScreenComponent getComponent(int id) {
        return components.get(id);
    }

    @Override
    public void move(int dx, int dy){
        for(ScreenComponent sc:components){
            sc.move(dx, dy);
        }
    }
}