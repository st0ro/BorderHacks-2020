package borderhacks2020.mainmenu;

import borderhacks2020.EventBasedState;
import borderhacks2020.Main;
import borderhacks2020.ui.Button;
import borderhacks2020.ui.ImageComponent;
import borderhacks2020.ui.Label;
import borderhacks2020.ui.ShapeComponent;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class PolicyChange extends EventBasedState {
    private Slot socialPolicyBox;
    private Slot internationalPolicyBox;
    private Slot economicPolicyBox;
    private Slot otherPolicyBox;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ImageComponent(gameContainer, "assets/policyBack.png", 960, 540, 1920, 1080));
        socialPolicyBox = new Slot(gameContainer, 442, 180, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.transparent, Color.transparent);
        components.add(socialPolicyBox);
        internationalPolicyBox = new Slot(gameContainer, 442, 450, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.transparent, Color.transparent);
        components.add(internationalPolicyBox);
        economicPolicyBox = new Slot(gameContainer, 442, 720, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.transparent, Color.transparent);
        components.add(economicPolicyBox);
        otherPolicyBox = new Slot(gameContainer, 442, 990, 1 * Main.WIDTH / 10, Main.HEIGHT / 4, Color.transparent, Color.transparent);
        components.add(otherPolicyBox);
        Policy[] policies = {
                new Policy(gameContainer, 995, 180, 200, 200, new Image("assets/policies/regular.png"), socialPolicyBox, 0.03f, 0.3f, 0.2f),
                new Policy(gameContainer, 1225, 180, 200, 200, new Image("assets/policies/mandatorymasks.png"), socialPolicyBox, 0f, -0.1f, -0.1f),
                new Policy(gameContainer, 1455, 180, 200, 200, new Image("assets/policies/nogatherings.png"), socialPolicyBox, -0.01f, -0.2f, -0.3f),
                new Policy(gameContainer, 1685, 180, 200, 200, new Image("assets/policies/lockdown.png"), socialPolicyBox,-0.02f, -0.4f, -0.5f),
                new Policy(gameContainer, 995, 450, 200, 200, new Image("assets/policies/openborders.png"), internationalPolicyBox, 0.02f, 0.1f, 0.1f),
                new Policy(gameContainer, 1225, 450, 200, 200, new Image("assets/policies/restrictedtravel.png"), internationalPolicyBox, 0f, 0f, 0),
                new Policy(gameContainer, 1455, 450, 200, 200, new Image("assets/policies/minimalcontact.png"), internationalPolicyBox, -0.01f, -0.1f, -0.1f),
                new Policy(gameContainer, 1685, 450, 200, 200, new Image("assets/policies/isolation.png"), internationalPolicyBox, -0.02f, -0.3f, -0.3f),
                new Policy(gameContainer, 995, 720, 200, 200, new Image("assets/policies/normaloperation.png"), economicPolicyBox, 0.03f, 0.1f, 0.2f),
                new Policy(gameContainer, 1225, 720, 200, 200, new Image("assets/policies/sociallydistance.png"), economicPolicyBox, 0f, 0f, -0.1f),
                new Policy(gameContainer, 1445, 720, 200, 200, new Image("assets/policies/essentialsonly.png"), economicPolicyBox, -0.01f, -0.2f, -0.3f),
                new Policy(gameContainer, 1685, 720, 200, 200, new Image("assets/policies/completeclosure.png"), economicPolicyBox, -0.03f, -0.6f, -0.4f),
                new Policy(gameContainer, 995, 990, 200, 200, new Image("assets/policies/stimuluscheck.png"), otherPolicyBox, 0f, -0.3f, 0.2f),
                new Policy(gameContainer, 1225, 990, 200, 200, new Image("assets/policies/construction.png"), otherPolicyBox, 0.01f, 0.3f, 0.01f),
                new Policy(gameContainer, 1455, 990, 200, 200, new Image("assets/policies/acceleratedresearch.png"), otherPolicyBox, 0f, -0.2f, 0f, (float)1/366),
                new Policy(gameContainer, 1685, 990, 200, 200, new Image("assets/policies/deceleratedresearch.png"), otherPolicyBox, 0f, 0.2f, 0f, -(float)1/732),
        };
        policies[0].switchLocation();
        policies[4].switchLocation();
        policies[8].switchLocation();
        components.add(new Button(gameContainer, 1845, 65, 90, 90, new Image("assets/btnBack.png")){
            @Override
            public void onLeftClick(){
                apply(stateBasedGame);
            }
        });
        for (int i = 0; i < policies.length; i++) {
            components.add(policies[i]);
        }

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

        stateBasedGame.enterState(2, new FadeOutTransition(Color.black, Main.fadeTime), new FadeInTransition(Color.black, Main.fadeTime));
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void keyPressed(int key, char c){
        if(key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }

}
