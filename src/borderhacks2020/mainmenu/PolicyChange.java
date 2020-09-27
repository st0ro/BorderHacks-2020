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
    private Slot socialPolicyBox;
    private Slot internationalPolicyBox;
    private Slot economicPolicyBox;
    private Slot otherPolicyBox;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ShapeComponent(gameContainer, Main.WIDTH / 2, 3 * Main.HEIGHT / 4, 9 * Main.WIDTH / 10, 5 * Main.HEIGHT / 12, Color.lightGray, Color.darkGray));
        socialPolicyBox = new Slot(gameContainer, Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.decode("0xDB3131"), Color.lightGray);
        components.add(socialPolicyBox);
        internationalPolicyBox = new Slot(gameContainer, 2 * Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.decode("0x62AFF4"), Color.lightGray);
        components.add(internationalPolicyBox);
        economicPolicyBox = new Slot(gameContainer, 3 * Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.decode("0xEAE84B"), Color.lightGray);
        components.add(economicPolicyBox);
        otherPolicyBox = new Slot(gameContainer, 4 * Main.WIDTH / 5, Main.HEIGHT / 4, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.decode("0x69D15B"), Color.lightGray);
        components.add(otherPolicyBox);
        Policy[] policies = {
                new Policy(gameContainer, 250, 700, 150, 150, new Image("assets/policies/regular.png"), socialPolicyBox, 0.03f, 0.3f, 0.2f),
                new Policy(gameContainer, 450, 700, 150, 150, new Image("assets/policies/mandatorymasks.png"), socialPolicyBox, 0f, -0.1f, -0.1f),
                new Policy(gameContainer, 650, 700, 150, 150, new Image("assets/policies/nogatherings.png"), socialPolicyBox, -0.01f, -0.2f, -0.3f),
                new Policy(gameContainer, 850, 700, 150, 150, new Image("assets/policies/lockdown.png"), socialPolicyBox,-0.02f, -0.4f, -0.5f),
                new Policy(gameContainer, 1050, 700, 150, 150, new Image("assets/policies/openborders.png"), internationalPolicyBox, 0.02f, 0.1f, 0.1f),
                new Policy(gameContainer, 1250, 700, 150, 150, new Image("assets/policies/restrictedtravel.png"), internationalPolicyBox, 0f, 0f, 0),
                new Policy(gameContainer, 1450, 700, 150, 150, new Image("assets/policies/minimalcontact.png"), internationalPolicyBox, -0.01f, -0.1f, -0.1f),
                new Policy(gameContainer, 1650, 700, 150, 150, new Image("assets/policies/isolation.png"), internationalPolicyBox, -0.02f, -0.3f, -0.3f),
                new Policy(gameContainer, 250, 900, 150, 150, new Image("assets/policies/normaloperation.png"), economicPolicyBox, 0.03f, 0.1f, 0.2f),
                new Policy(gameContainer, 450, 900, 150, 150, new Image("assets/policies/sociallydistance.png"), economicPolicyBox, 0f, 0f, -0.1f),
                new Policy(gameContainer, 650, 900, 150, 150, new Image("assets/policies/essentialsonly.png"), economicPolicyBox, -0.01f, -0.2f, -0.3f),
                new Policy(gameContainer, 850, 900, 150, 150, new Image("assets/policies/completeclosure.png"), economicPolicyBox, -0.03f, -0.6f, -0.4f),
                new Policy(gameContainer, 1050, 900, 150, 150, new Image("assets/policies/stimuluscheck.png"), otherPolicyBox, 0f, -0.3f, 0.2f),
                new Policy(gameContainer, 1250, 900, 150, 150, new Image("assets/policies/construction.png"), otherPolicyBox, 0.01f, 0.3f, 0.01f),
                new Policy(gameContainer, 1450, 900, 150, 150, new Image("assets/policies/acceleratedresearch.png"), otherPolicyBox, 0f, -0.2f, 0f, (float)1/366),
                new Policy(gameContainer, 1650, 900, 150, 150, new Image("assets/policies/deceleratedresearch.png"), otherPolicyBox, 0f, 0.2f, 0f, -(float)1/732),
        };
        policies[0].switchLocation();
        policies[4].switchLocation();
        policies[8].switchLocation();
        components.add(new Button(gameContainer, Main.WIDTH/2, Main.HEIGHT/2 - 50, 150, 75, new Image("assets/title_screen/playbtn.png")){
            @Override
            public void onLeftClick(){
                apply(stateBasedGame);
            }
        });
        for (int i = 0; i < policies.length; i++) {
            components.add(policies[i]);
        }
        components.add(new Label(gameContainer, "SOCIAL GATHERINGS",  Main.WIDTH / 5,  150, Main.pixelFontBlack));
        components.add(new Label(gameContainer, "INTERNATIONAL POLICY",  2*Main.WIDTH / 5,  150, Main.pixelFontBlack));
        components.add(new Label(gameContainer, "ECONOMIC",  3*Main.WIDTH / 5,  150, Main.pixelFontBlack));
        components.add(new Label(gameContainer, "MISCELLANEOUS",  4*Main.WIDTH / 5,  150, Main.pixelFontBlack));

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    public void apply(StateBasedGame stateBasedGame){
        float em=0;
        float hm=0;
        float im=0;
        if(socialPolicyBox.isOccupied()){
            em+=socialPolicyBox.getImplemented().getEconomyModifier();
            hm+=socialPolicyBox.getImplemented().getHappinessModifier();
            im+=socialPolicyBox.getImplemented().getInfectionModifier();
        }
        if (economicPolicyBox.isOccupied()){
            em+=economicPolicyBox.getImplemented().getEconomyModifier();
            hm+=economicPolicyBox.getImplemented().getHappinessModifier();
            im+=economicPolicyBox.getImplemented().getInfectionModifier();
        }
        if(internationalPolicyBox.isOccupied()){
            em+=internationalPolicyBox.getImplemented().getEconomyModifier();
            hm+=internationalPolicyBox.getImplemented().getHappinessModifier();
            im+=internationalPolicyBox.getImplemented().getInfectionModifier();
        }
        if(otherPolicyBox.isOccupied()){
            em+=otherPolicyBox.getImplemented().getEconomyModifier();
            hm+=otherPolicyBox.getImplemented().getHappinessModifier();
            im+=otherPolicyBox.getImplemented().getInfectionModifier();
            Main.playState.setCureModifier(otherPolicyBox.getImplemented().getCureModifier());
        }
        Main.playState.setEconomyRate(em);
        Main.playState.setHappinessRate(hm);
        Main.playState.setInfectionModifier(im);

        stateBasedGame.enterState(2);
    }

    @Override
    public int getID() {
        return 1;
    }

}
