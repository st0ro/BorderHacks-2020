package borderhacks2020.gamestate;

import borderhacks2020.EventBasedState;
import borderhacks2020.Main;
import borderhacks2020.MapManager;
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
import org.newdawn.slick.util.FontUtils;
import sun.font.FontUtilities;

import javax.swing.plaf.FontUIResource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PlayState extends EventBasedState {

    private Image mapImg, graphImg;
    private ImageComponent selector, bigMap, playbutton;
    private boolean mapSelected = true;
    private ShapeComponent[] bars = new ShapeComponent[3];
    private Calendar calendar;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
    private Label lblDate, lblTotal, lblActive, lblDeaths, lblRecovered;
    private int timer;
    private boolean gameTicking = true;
    private MapManager manager;

    private int activeCases;
    private float infectionRate;
    private float happiness;
    private float happinessRate;
    private float economy;
    private float economyRate;
    private float recoveryRate;
    private int deaths;
    private int totalCases;


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
                bigMap.setLocation(465, 619);
            }
        });
        components.add(new Button(gameContainer, 1186, 941, 165, 128){
            @Override
            public void onLeftClick(){
                mapSelected = false;
                selector.setImage(graphImg);
                bigMap.setLocation(10000, 10000);
            }
        });
        bigMap = new ImageComponent(gameContainer, "assets/map.png", 465, 619, 780, 728);
        components.add(bigMap);
        playbutton = new ImageComponent(gameContainer, "assets/gamestate/playbtn.png", 10000, 10000, 90, 83);
        components.add(playbutton);
        components.add(new Button(gameContainer, 705, 78, 90, 83){
            @Override
            public void onLeftClick(){
                gameTicking = !gameTicking;
            }
        });
        bars[0] = new ShapeComponent(gameContainer, 1192, 468, 0, 53, new Color(0x47bc4f), Color.transparent);
        bars[1] = new ShapeComponent(gameContainer, 1192, 610, 0, 53, new Color(0x47bc4f), Color.transparent);
        bars[2] = new ShapeComponent(gameContainer, 1192, 753, 0, 53, new Color(0x47bc4f), Color.transparent);
        for (ShapeComponent s: bars) {
            components.add(s);
        }
        activeCases = 100;
        happiness = 1;
        economy = 1;
        infectionRate = .25f;
        happinessRate = 0.99f;
        economyRate = 1.01f;
        recoveryRate = 0.18f;
        deaths = 0;
        totalCases = 100;
        calendar = Calendar.getInstance();
        calendar.set(2020, 0,1);
        lblDate = new Label(gameContainer, dateFormat.format(calendar.getTime()), 451, 60, Main.pixelFontBlack);
        components.add(lblDate);

        lblTotal = new Label(gameContainer, Integer.toString(totalCases), 1342, 125, Main.pixelFontBlack, FontUtils.Alignment.RIGHT);
        components.add(lblTotal);
        lblActive = new Label(gameContainer, Integer.toString(activeCases), 1860, 125, Main.pixelFontBlack, FontUtils.Alignment.RIGHT);
        components.add(lblActive);
        lblDeaths = new Label(gameContainer, Integer.toString(deaths), 1342, 290, Main.pixelFontBlack, FontUtils.Alignment.RIGHT);
        components.add(lblDeaths);
        lblRecovered = new Label(gameContainer, Integer.toString(totalCases-activeCases-deaths), 1860, 290, Main.pixelFontBlack, FontUtils.Alignment.RIGHT);
        components.add(lblRecovered);

        updateBar(gameContainer, 0, happiness);
        updateBar(gameContainer, 1, economy);
        updateBar(gameContainer, 2, infectionRate - recoveryRate);
        manager = new MapManager();
    }

    public void updateBar(GameContainer gameContainer, int barNum, float progress) throws SlickException {
        Rectangle rect = (Rectangle)(bars[barNum].getScreenBox());
        rect.setWidth((int)(630 * progress));
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gameContainer, game, g);
        if(mapSelected){
            manager.render(gameContainer, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(gameTicking){
            timer += delta;
            if(timer > 1000){
                timer -= 1000;
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                lblDate.setText(dateFormat.format(calendar.getTime()));
                deaths += activeCases *0.05;
                int newCases = Math.round(activeCases *(infectionRate-recoveryRate));
                activeCases += newCases;
                manager.add(container, newCases/10);
                totalCases += activeCases * infectionRate;
                happiness *= happinessRate;
                economy *= economyRate;

                lblTotal.setText(Integer.toString(totalCases));
                lblActive.setText(Integer.toString(activeCases));
                lblDeaths.setText(Integer.toString(deaths));
                lblRecovered.setText(Integer.toString(totalCases-activeCases-deaths));

                updateBar(container, 0, happiness);
                updateBar(container, 1, economy);
                updateBar(container, 2, infectionRate - recoveryRate);
            }
            playbutton.setLocation(10000, 10000);
        }
        else {
            playbutton.setLocation(705, 78);
        }
    }

    @Override
    public int getID() {
        return 2;
    }
}
