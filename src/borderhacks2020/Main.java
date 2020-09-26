package borderhacks2020;

import borderhacks2020.gamestate.PlayState;
import borderhacks2020.mainmenu.MainMenu;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

public class Main extends StateBasedGame {

    public static final int WIDTH = 1920, HEIGHT = 1080;
    public static UnicodeFont fontSegoe14;

    public Main() {
        super("Pandemic!!!");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new MainMenu());
        addState(new PlayState());
        fontSegoe14 = new UnicodeFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        fontSegoe14.addAsciiGlyphs();
        fontSegoe14.getEffects().add(new ColorEffect(java.awt.Color.BLACK));
        fontSegoe14.loadGlyphs();
        gameContainer.setDefaultFont(fontSegoe14);
    }

    public static void main(String args[]){
        try {
            AppGameContainer app = new AppGameContainer(new Main()); //create game in container
            app.setDisplayMode(WIDTH, HEIGHT, true); //set window size and if fullscreen
            //app.setVSync(true);
            app.setTargetFrameRate(120);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace(); //in case of failure to start game, will print error
        }
    }
}
