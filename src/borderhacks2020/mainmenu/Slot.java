package borderhacks2020.mainmenu;

import borderhacks2020.ui.ShapeComponent;
import org.newdawn.slick.Color;
import org.newdawn.slick.gui.GUIContext;

public class Slot extends ShapeComponent{

    private boolean isOccupied;

    public Slot(GUIContext container, int x, int y, int width, int height, Color fill, Color outline) {
        super(container, x, y, width, height, fill, outline);
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void changeOccupied(){
        isOccupied = !isOccupied;
    }
}
