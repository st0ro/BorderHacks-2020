package borderhacks2020.mainmenu;

import borderhacks2020.ui.Button;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

public class Policy extends Button {

    private Slot slot;

    public Policy(GUIContext container, int x, int y, int width, int height, Image image){
        super(container, x, y, width, height, image);
    }

    public Policy(GUIContext container, int x, int y, int width, int height, Image image, Slot s){
        super(container, x, y, width, height, image);
        slot = s;
    }

    public void switchLocation(int currentX, int currentY){
        if(slot.isOccupied()){

        }
        else{

        }
    }

    @Override
    public void onLeftClick() {
        switchLocation(getX(), getY());

    }
}
