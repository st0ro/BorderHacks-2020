package borderhacks2020;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import borderhacks2020.ui.ScreenComponent;

public abstract class EventBasedState implements GameState {

    protected List<ScreenComponent> components;

    protected EventBasedState() {
        components = new ArrayList<ScreenComponent>();
    }

    @Override
    public void mouseWheelMoved(int change) {}

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {}

    @Override
    public void mousePressed(int button, int x, int y) {}

    @Override
    public void mouseReleased(int button, int x, int y) {}

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {}

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {}

    @Override
    public void setInput(Input input) {}

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {}

    @Override
    public void inputStarted() {}

    @Override
    public void keyPressed(int key, char c) {}

    @Override
    public void keyReleased(int key, char c) {}

    @Override
    public void controllerLeftPressed(int controller) {}

    @Override
    public void controllerLeftReleased(int controller) {}

    @Override
    public void controllerRightPressed(int controller) {}

    @Override
    public void controllerRightReleased(int controller) {}

    @Override
    public void controllerUpPressed(int controller) {}

    @Override
    public void controllerUpReleased(int controller) {}

    @Override
    public void controllerDownPressed(int controller) {}

    @Override
    public void controllerDownReleased(int controller) {}

    @Override
    public void controllerButtonPressed(int controller, int button) {}

    @Override
    public void controllerButtonReleased(int controller, int button) {}

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        for(ScreenComponent sc:components) {
            sc.render(container, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        for(ScreenComponent sc:components) {
            sc.setEnabled(true);
        }
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        for(ScreenComponent sc:components) {
            sc.setEnabled(false);
        }
    }
}