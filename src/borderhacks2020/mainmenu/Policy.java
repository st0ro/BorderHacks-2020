package borderhacks2020.mainmenu;

import borderhacks2020.ui.Button;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

public class Policy extends Button {

    private Slot slot;
    private int baseX, baseY;

    public Policy(GUIContext container, int x, int y, int width, int height, Image image){
        super(container, x, y, width, height, image);
    }

    public Policy(GUIContext container, int x, int y, int width, int height, Image image, Slot s){
        super(container, x, y, width, height, image);
        baseX = x;
        baseY = y;
        slot = s;
    }

    public void switchLocation(){
        if(!slot.isOccupied()){
            screenBox.setCenterX(slot.getX());
            screenBox.setCenterY(slot.getY());
            slot.changeOccupied();
        }
        else if(slot.isOccupied()&&getX()==slot.getX()){
            screenBox.setCenterX(baseX);
            screenBox.setCenterY(baseY);
            slot.changeOccupied();
        }
    }

    @Override
    public void onLeftClick() {
        switchLocation();

    }
}
