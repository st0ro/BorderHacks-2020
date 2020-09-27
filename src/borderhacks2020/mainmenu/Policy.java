package borderhacks2020.mainmenu;

import borderhacks2020.ui.Button;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

public class Policy extends Button {

    private Slot slot;
    private int baseX, baseY;

    private float cureRate;

    private float infectionModifier, economyModifier, happinessModifier;

    public float getInfectionModifier() {
        return infectionModifier;
    }

    public float getEconomyModifier() {
        return economyModifier;
    }

    public float getHappinessModifier() {
        return happinessModifier;
    }

    public float getCureRate() {
        return cureRate;
    }


    public Policy(GUIContext container, int x, int y, int width, int height, Image image, Slot s){
        super(container, x, y, width, height, image);
        baseX = x;
        baseY = y;
        slot = s;
        infectionModifier = 0;
        economyModifier = 0;
        happinessModifier = 0;
    }

    public Policy(GUIContext container, int x, int y, int width, int height, Image image, Slot s, float infectionModifier, float economyModifier, float happinessModifier){
        super(container, x, y, width, height, image);
        baseX = x;
        baseY = y;
        slot = s;
        this.infectionModifier = infectionModifier;
        this.economyModifier = economyModifier;
        this.happinessModifier = happinessModifier;
        cureRate = 0;
    }

    public Policy(GUIContext container, int x, int y, int width, int height, Image image, Slot s, float infectionModifier, float economyModifier, float happinessModifier, float cureRate){
        super(container, x, y, width, height, image);
        baseX = x;
        baseY = y;
        slot = s;
        this.infectionModifier = infectionModifier;
        this.economyModifier = economyModifier;
        this.happinessModifier = happinessModifier;
        this.cureRate = cureRate;
    }

    public void switchLocation(){
        if(!slot.isOccupied()){
            screenBox.setCenterX(slot.getX());
            screenBox.setCenterY(slot.getY());
            slot.setPolicy(this);
        }
        else if(slot.isOccupied()&&this==slot.getImplemented()){
            screenBox.setCenterX(baseX);
            screenBox.setCenterY(baseY);
            slot.setPolicy(null);
        }
    }

    @Override
    public void onLeftClick() {
        switchLocation();

    }
}
