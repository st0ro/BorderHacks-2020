package borderhacks2020.mainmenu;

import borderhacks2020.EventBasedState;
import borderhacks2020.Main;
import borderhacks2020.ui.Button;
import borderhacks2020.ui.ImageComponent;
import borderhacks2020.ui.ShapeComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Color;


public class PolicyChange extends EventBasedState {

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ShapeComponent(gameContainer, Main.WIDTH / 2, 3 * Main.HEIGHT / 4, 9 * Main.WIDTH / 10, 5 * Main.HEIGHT / 12, Color.lightGray, Color.darkGray));
        Slot economicPolicyBox = new Slot(gameContainer, Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.red, Color.lightGray);
        components.add(economicPolicyBox);
        Slot socialPolicyBox = new Slot(gameContainer, 2 * Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.blue, Color.lightGray);
        components.add(socialPolicyBox);
        Slot internationalPolicyBox = new Slot(gameContainer, 3 * Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.yellow, Color.lightGray);
        components.add(internationalPolicyBox);
        Slot otherPolicyBox = new Slot(gameContainer, 4 * Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.green, Color.lightGray);
        components.add(otherPolicyBox);
        Button[] buttons = {new Policy(gameContainer, 250, 700, 150, 150, new Image("assets/testpolicy.png"), economicPolicyBox),
                new Policy(gameContainer, 450, 700, 150, 150, new Image("assets/testpolicy.png"), socialPolicyBox),
                new Policy(gameContainer, 650, 700, 150, 150, new Image("assets/testpolicy.png"), internationalPolicyBox),
                new Policy(gameContainer, 850, 700, 150, 150, new Image("assets/testpolicy.png"), otherPolicyBox),
                new Policy(gameContainer, 1050, 700, 150, 150, new Image("assets/testpolicy.png"), otherPolicyBox),
                new Policy(gameContainer, 1250, 700, 150, 150, new Image("assets/testpolicy.png"), otherPolicyBox),
                new Policy(gameContainer, 1450, 700, 150, 150, new Image("assets/testpolicy.png"), otherPolicyBox),
                new Policy(gameContainer, 1650, 700, 150, 150, new Image("assets/testpolicy.png"), otherPolicyBox),
                new Button(gameContainer, 450, 900, 150, 150, new Image("assets/testpolicy.png")),
                new Button(gameContainer, 650, 900, 150, 150, new Image("assets/testpolicy.png"))};
        for (int i = 0; i < buttons.length; i++) {
            components.add(buttons[i]);
        }


    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    @Override
    public int getID() {
        return 1;
    }

}
