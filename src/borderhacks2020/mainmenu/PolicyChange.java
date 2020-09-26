package borderhacks2020.mainmenu;

import borderhacks2020.EventBasedState;
import borderhacks2020.Main;
import borderhacks2020.ui.Button;
import borderhacks2020.ui.Label;
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
        Slot socialPolicyBox = new Slot(gameContainer, Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.decode("0xDB3131"), Color.lightGray);
        components.add(socialPolicyBox);
        Slot internationalPolicyBox = new Slot(gameContainer, 2 * Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.decode("0x62AFF4"), Color.lightGray);
        components.add(internationalPolicyBox);
        Slot economicPolicyBox = new Slot(gameContainer, 3 * Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.decode("0xEAE84B"), Color.lightGray);
        components.add(economicPolicyBox);
        Slot otherPolicyBox = new Slot(gameContainer, 4 * Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.decode("0x69D15B"), Color.lightGray);
        components.add(otherPolicyBox);
        Button[] buttons = {
                new Policy(gameContainer, 250, 700, 150, 150, new Image("assets/policies/regular.png"), socialPolicyBox),
                new Policy(gameContainer, 450, 700, 150, 150, new Image("assets/policies/mandatorymasks.png"), socialPolicyBox),
                new Policy(gameContainer, 650, 700, 150, 150, new Image("assets/policies/nogatherings.png"), socialPolicyBox),
                new Policy(gameContainer, 850, 700, 150, 150, new Image("assets/policies/lockdown.png"), socialPolicyBox),
                new Policy(gameContainer, 1050, 700, 150, 150, new Image("assets/policies/openborders.png"), internationalPolicyBox),
                new Policy(gameContainer, 1250, 700, 150, 150, new Image("assets/policies/restrictedtravel.png"), internationalPolicyBox),
                new Policy(gameContainer, 1450, 700, 150, 150, new Image("assets/policies/minimalcontact.png"), internationalPolicyBox),
                new Policy(gameContainer, 1650, 700, 150, 150, new Image("assets/policies/isolation.png"), internationalPolicyBox),
                new Policy(gameContainer, 250, 900, 150, 150, new Image("assets/policies/sociallydistance.png"), economicPolicyBox),
                new Policy(gameContainer, 450, 900, 150, 150, new Image("assets/policies/normaloperation.png"), economicPolicyBox),
                new Policy(gameContainer, 650, 900, 150, 150, new Image("assets/policies/essentialsonly.png"), economicPolicyBox),
                new Policy(gameContainer, 850, 900, 150, 150, new Image("assets/policies/completeclosure.png"), economicPolicyBox),
                new Policy(gameContainer, 1050, 900, 150, 150, new Image("assets/policies/stimuluscheck.png"), otherPolicyBox),
                new Policy(gameContainer, 1250, 900, 150, 150, new Image("assets/policies/construction.png"), otherPolicyBox),
                new Policy(gameContainer, 1450, 900, 150, 150, new Image("assets/policies/acceleratedresearch.png"), otherPolicyBox),
                new Policy(gameContainer, 1650, 900, 150, 150, new Image("assets/testpolicy.png"), otherPolicyBox)
        };
        components.add(new Button(gameContainer, Main.WIDTH/2, Main.HEIGHT/2 - 50, 150, 75, new Image("assets/title_screen/playbtn.png")));
        for (int i = 0; i < buttons.length; i++) {
            components.add(buttons[i]);
        }
        components.add(new Label(gameContainer, "Social Gatherings",  Main.WIDTH / 5,  150));
        components.add(new Label(gameContainer, "International Policy",  2*Main.WIDTH / 5,  150));
        components.add(new Label(gameContainer, "Economic",  3*Main.WIDTH / 5,  150));
        components.add(new Label(gameContainer, "Miscellaneous",  4*Main.WIDTH / 5,  150));

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    @Override
    public int getID() {
        return 1;
    }

}
