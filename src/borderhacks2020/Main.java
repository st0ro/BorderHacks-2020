package borderhacks2020;

import borderhacks2020.gamestate.PlayState;
import borderhacks2020.mainmenu.MainMenu;
import borderhacks2020.mainmenu.PolicyChange;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;

public class Main extends StateBasedGame {

    public static final int WIDTH = 1920, HEIGHT = 1080;
    public static UnicodeFont fontSegoe14;
    public static SpriteSheetFont pixelFontBlack;
    public static PlayState playState;

    public Main() {
        super("Pandemic!!!");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new MainMenu());
        playState = new PlayState();
        addState(playState);
        addState(new PolicyChange());
        fontSegoe14 = new UnicodeFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        fontSegoe14.addAsciiGlyphs();
        fontSegoe14.getEffects().add(new ColorEffect(java.awt.Color.BLACK));
        fontSegoe14.loadGlyphs();
        pixelFontBlack = new SpriteSheetFont(new SpriteSheet(new Image("assets/fontblack.png", false, Image.FILTER_NEAREST).getScaledCopy(7f), 35, 35), ' ');
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
