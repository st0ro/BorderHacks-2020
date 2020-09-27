package borderhacks2020.mainmenu;

import borderhacks2020.EventBasedState;
import borderhacks2020.Main;
import borderhacks2020.ui.Button;
import borderhacks2020.ui.ImageComponent;
import borderhacks2020.ui.Label;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends EventBasedState {

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ImageComponent(gameContainer, "assets/title_screen/mainmenu.png", Main.WIDTH/2, Main.HEIGHT/2, Main.WIDTH, Main.HEIGHT));
        Button playBtn = new Button(gameContainer, 1400, 900, 330, 120, new Image("assets/title_screen/playbtn.png")) {
            @Override
            public void onLeftClick() {
                stateBasedGame.enterState(2, new FadeOutTransition(Color.black, Main.fadeTime), new FadeInTransition(Color.black, Main.fadeTime));

            }
        };
        components.add(playBtn);
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void keyPressed(int key, char c){
        if(key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }
}
