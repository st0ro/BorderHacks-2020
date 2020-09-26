package borderhacks2020;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

    public static final int WIDTH = 1000, HEIGHT = 600;

    public Main() {
        super("Pandemic!!!");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {

    }

    public static void main(String args[]){
        try {
            AppGameContainer app = new AppGameContainer(new Main()); //create game in container
            app.setDisplayMode(WIDTH, HEIGHT, false); //set window size and if fullscreen
            app.setVSync(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace(); //in case of failure to start game, will print error
        }
    }
}
