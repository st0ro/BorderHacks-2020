package borderhacks2020.mainmenu;

import borderhacks2020.ui.ShapeComponent;
import org.newdawn.slick.Color;
import org.newdawn.slick.gui.GUIContext;

public class Slot extends ShapeComponent{

    private Policy implemented;

    public Slot(GUIContext container, int x, int y, int width, int height, Color fill, Color outline) {
        super(container, x, y, width, height, fill, outline);
    }

    public boolean isOccupied() {
        if(implemented==null){
            return false;
        }
        return true;
    }

    public Policy getImplemented() {
        return implemented;
    }

    public void setPolicy(Policy p){
        implemented = p;
    }
}
