package borderhacks2020.gamestate;

import borderhacks2020.EventBasedState;
import borderhacks2020.Main;
import borderhacks2020.ui.Button;
import borderhacks2020.ui.ImageComponent;
import borderhacks2020.ui.ScreenComponent;
import borderhacks2020.ui.ShapeComponent;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class PlayState extends EventBasedState {

    private Image mapImg, graphImg;
    private ImageComponent selector;
    private boolean mapSelected = true;
    private ShapeComponent[] bars = new ShapeComponent[3];

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ImageComponent(gameContainer, "assets/gamestate/gameScreen.png", Main.WIDTH/2, Main.HEIGHT/2, Main.WIDTH, Main.HEIGHT));
        mapImg = new Image("assets/gamestate/selectorMap.png", false, Image.FILTER_NEAREST);
        graphImg = new Image("assets/gamestate/selectorGraph.png", false, Image.FILTER_NEAREST);
        selector = new ImageComponent(gameContainer, mapImg, 960, 540, 1920, 1080);
        components.add(selector);
        components.add(new Button(gameContainer, 1021, 941, 165, 128){
            @Override
            public void onLeftClick(){
                mapSelected = true;
                selector.setImage(mapImg);
            }
        });
        components.add(new Button(gameContainer, 1186, 941, 165, 128){
            @Override
            public void onLeftClick(){
                mapSelected = false;
                selector.setImage(graphImg);
            }
        });
        bars[0] = new ShapeComponent(gameContainer, 1192, 468, 0, 53, new Color(0x47bc4f), Color.transparent);
        bars[1] = new ShapeComponent(gameContainer, 1192, 610, 0, 53, new Color(0x47bc4f), Color.transparent);
        bars[2] = new ShapeComponent(gameContainer, 1192, 753, 0, 53, new Color(0x47bc4f), Color.transparent);
    }

    public void updateBar(GameContainer gameContainer, int barNum, float progress) throws SlickException {
        Rectangle rect = (Rectangle)(bars[barNum].getScreenBox());
        rect.setWidth((int)(630 * progress));
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gameContainer, game, g);
        for (ShapeComponent s: bars) {
            s.render(gameContainer, g);
        }
    }

    @Override
    public int getID() {
        return 2;
    }
}
