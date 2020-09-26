package borderhacks2020.gamestate;

import borderhacks2020.EventBasedState;
import borderhacks2020.Main;
import borderhacks2020.ui.Button;
import borderhacks2020.ui.ImageComponent;
import borderhacks2020.ui.ShapeComponent;
import borderhacks2020.ui.Label;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PlayState extends EventBasedState {

    private Image mapImg, graphImg;
    private ImageComponent selector, bigMap;
    private boolean mapSelected = true;
    private ShapeComponent[] bars = new ShapeComponent[3];
    private Calendar calendar;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
    private Label lblDate;
    private int timer;

    private int cases;
    private float infectionRate;
    private float happiness;
    private float happinessRate;
    private float economy;
    private float economyRate;


    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ImageComponent(gameContainer, "assets/gamestate/gameScreen.png", Main.WIDTH/2, Main.HEIGHT/2, Main.WIDTH, Main.HEIGHT));
        mapImg = new Image("assets/gamestate/selectorMap.png", false, Image.FILTER_NEAREST);
        graphImg = new Image("assets/gamestate/selectorGraph.png", false, Image.FILTER_NEAREST);
        selector = new ImageComponent(gameContainer, mapImg, 960, 540, 1920, 1080);
        components.add(selector);
        components.add(new Button(gameContainer, 1021, 941, 165, 128){
            @Override
            public void onLeftClick(){
                mapSelected = true;
                selector.setImage(mapImg);
                bigMap.setEnabled(true);
            }
        });
        components.add(new Button(gameContainer, 1186, 941, 165, 128){
            @Override
            public void onLeftClick(){
                mapSelected = false;
                selector.setImage(graphImg);
                bigMap.setEnabled(false);
            }
        });
        bars[0] = new ShapeComponent(gameContainer, 1192, 468, 0, 53, new Color(0x47bc4f), Color.transparent);
        bars[1] = new ShapeComponent(gameContainer, 1192, 610, 0, 53, new Color(0x47bc4f), Color.transparent);
        bars[2] = new ShapeComponent(gameContainer, 1192, 753, 0, 53, new Color(0x47bc4f), Color.transparent);
        for (ShapeComponent s: bars) {
            components.add(s);
        }
        updateBar(gameContainer, 1, 0.34f);
    }

    public void updateBar(GameContainer gameContainer, int barNum, float progress) throws SlickException {
        Rectangle rect = (Rectangle)(bars[barNum].getScreenBox());
        rect.setWidth((int)(630 * progress));
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gameContainer, game, g);
        lblDate = new Label(gameContainer, "date", 451, 60, Main.pixelFontBlack);
        components.add(lblDate);
        calendar = Calendar.getInstance();
        calendar.set(2020, 0,1);
        lblDate.setText(dateFormat.format(calendar.getTime()));

        bigMap = new ImageComponent(gameContainer, "assets/map.png", 465, 619, 780, 728);
        components.add(bigMap);
        cases = 100;
        happiness = 1;
        economy = 1;
        infectionRate = 2;
        happinessRate = 0.99f;
        economyRate = 1.01f;

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        timer += delta;
        if(timer > 1000){
            timer -= 1000;
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            lblDate.setText(dateFormat.format(calendar.getTime()));
            cases *= infectionRate;
            happiness *= happinessRate;
            economy *= economyRate;
        }
    }

    @Override
    public int getID() {
        return 2;
    }
}
