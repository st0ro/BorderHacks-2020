package borderhacks2020.mainmenu;

import borderhacks2020.EventBasedState;
import borderhacks2020.Main;
import borderhacks2020.ui.Button;
import borderhacks2020.ui.ImageComponent;
import borderhacks2020.ui.Label;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends EventBasedState {

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ImageComponent(gameContainer, "assets/title_screen/background.png", Main.WIDTH/2, Main.HEIGHT/2, Main.WIDTH, Main.HEIGHT));
        components.add(new Label(gameContainer, "Sample Text", Main.WIDTH/2, Main.HEIGHT/4));
        Button playBtn = new Button(gameContainer, Main.WIDTH/2, Main.HEIGHT/2, 200, 100, new Image("assets/title_screen/playbtn.png")) {
            @Override
            public void onLeftClick() {
                stateBasedGame.enterState(1);

            }
        };
        components.add(playBtn);
    }

    @Override
    public int getID() {
        return 0;
    }
}
