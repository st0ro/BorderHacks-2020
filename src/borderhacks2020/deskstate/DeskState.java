package borderhacks2020.deskstate;

import borderhacks2020.EventBasedState;
import borderhacks2020.Main;
import borderhacks2020.ui.Button;
import borderhacks2020.ui.ImageComponent;
import borderhacks2020.ui.Label;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DeskState extends EventBasedState {
    private ImageComponent tabSelect;
    private int selectedTab = 0;
    private int time;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ImageComponent(gameContainer, "assets/deskstate/background.png", Main.WIDTH/2, Main.HEIGHT/2, Main.WIDTH, Main.HEIGHT));
        tabSelect = new ImageComponent(gameContainer, "assets/deskstate/tabopen.png", 1104, 84, 304, 88);
        components.add(tabSelect);
        components.add(new Button(gameContainer, 1104, 84, 304, 88){
            @Override
            public void onLeftClick(){
                selectedTab = 0;
                tabSelect.setLocation(1104, 84);
            }
        });
        components.add(new Button(gameContainer, 1408, 84, 304, 88){
            @Override
            public void onLeftClick(){
                selectedTab = 1;
                tabSelect.setLocation(1408, 84);
            }
        });
        components.add(new Button(gameContainer, 1712, 84, 304, 88){
            @Override
            public void onLeftClick(){
                selectedTab = 2;
                tabSelect.setLocation(1712, 84);
            }
        });
        components.add(new Label(gameContainer, "Stats", 1104, 74));
        components.add(new Label(gameContainer, "News", 1408, 74));
        components.add(new Label(gameContainer, "Map", 1712, 74));
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        time += delta;
    }
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        super.render(container, game, g);
    }

    @Override
    public int getID() {
        return 2;
    }
}
