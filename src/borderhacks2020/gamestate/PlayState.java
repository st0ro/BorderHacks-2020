package borderhacks2020.gamestate;

import borderhacks2020.EventBasedState;
import borderhacks2020.Main;
import borderhacks2020.MapManager;
import borderhacks2020.ui.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.FontUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PlayState extends EventBasedState {

    private Image mapImg, graphImg;
    private ImageComponent selector, bigMap, playButton, graphBack;
    private boolean mapSelected = true;
    private ShapeComponent[] bars = new ShapeComponent[3];
    private Calendar calendar;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
    private Label lblDate, lblTotal, lblActive, lblDeaths, lblRecovered;
    private int timer;
    private boolean gameTicking = true, ableToPlay = true;
    private MapManager manager;
    private Button btnNews1, btnNews2;
    private Graph graphTotal, graphNew;
    private Button lossScreen1, lossScreen2, lossScreen3;

    private int activeCases;
    private float infectionRate;
    private float happiness;
    private float happinessRate;
    private float economy;
    private float economyRate;
    private float recoveryRate;
    private int deaths;
    private int totalCases;
    private float infectionModifier;

    private float cureModifier;

    private Calendar firstEvent, secondEvent;


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
        playButton = new ImageComponent(gameContainer, "assets/gamestate/playbtn.png", 10000, 10000, 90, 83);
        components.add(playButton);
        components.add(new Button(gameContainer, 705, 78, 90, 83){
            @Override
            public void onLeftClick(){
                if(ableToPlay){
                    gameTicking = !gameTicking;
                }
            }
        });
        bars[0] = new ShapeComponent(gameContainer, 1192, 468, 0, 53, new Color(0x47bc4f), Color.transparent);
        bars[1] = new ShapeComponent(gameContainer, 1192, 610, 0, 53, new Color(0x47bc4f), Color.transparent);
        bars[2] = new ShapeComponent(gameContainer, 1192, 753, 0, 53, new Color(0x47bc4f), Color.transparent);
        for (ShapeComponent s: bars) {
            components.add(s);
        }

        activeCases = 100;
        happiness = 50;
        economy = 50;
        infectionRate = 0.2f;
        happinessRate = 0f;
        economyRate = 0f;
        recoveryRate = 0.15f;
        deaths = 0;
        totalCases = 100;
        calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY,20, 0, 0, 0);
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

        updateBar(0, happiness/100);
        updateBar(1, economy/100);
        updateBar(2, infectionRate - recoveryRate);
        manager = new MapManager();

        firstEvent = Calendar.getInstance();
        firstEvent.set(2020, Calendar.JANUARY, 26, 0, 0, 0);
        secondEvent = Calendar.getInstance();
        secondEvent.set(2020, Calendar.MAY, 13, 0, 0, 0);
        btnNews1 = new Button(gameContainer, 10000, 10000, 780, 680, new Image("assets/News/News1.png")){
            @Override
            public void onLeftClick(){
                gameTicking = true;
                ableToPlay = true;
                setLocation(10000, 10000);
            }
        };
        components.add(btnNews1);
        btnNews2 = new Button(gameContainer, 10000, 10000, 780, 680, new Image("assets/News/News2.png")){
            @Override
            public void onLeftClick(){
                gameTicking = true;
                ableToPlay = true;
                setLocation(10000, 10000);
            }
        };
        components.add(btnNews2);
        components.add(new Button(gameContainer, 1586, 941, 578, 128){
            @Override
            public void onLeftClick(){
                if(ableToPlay)
                stateBasedGame.enterState(1);
            }
        });
        graphBack = new ImageComponent(gameContainer, new Image("assets/gamestate/graphBack.png"),465, 619, 780, 728);
        components.add(graphBack);
        graphTotal = new Graph(gameContainer, 458, 550);
        components.add(graphTotal);
        graphNew = new Graph(gameContainer, 458, 910);
        components.add(graphNew);
        lossScreen1 = new Button(gameContainer, 10000, 10000, 1500, 1500, new Image("assets/LossImageCases.png"));
        components.add(lossScreen1);
        lossScreen2 = new Button(gameContainer, 10000, 10000, 1500, 1500, new Image("assets/LossImageEconomy.png"));
        components.add(lossScreen2);
        lossScreen3 = new Button(gameContainer, 10000, 10000, 1500, 1500, new Image("assets/LossImageRevolt.png"));
        components.add(lossScreen3);

    }

    public void updateBar(int barNum, float progress){
        Rectangle rect = (Rectangle)(bars[barNum].getScreenBox());
        rect.setWidth((int)(630 * progress));
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics g) throws SlickException {
        super.render(gameContainer, game, g);
        if(mapSelected){
            manager.render(gameContainer, g);
        }
        btnNews1.render(gameContainer, g);
        btnNews2.render(gameContainer, g);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(gameTicking){
            timer += delta;
            if(timer > 1000){
                timer -= 1000;
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                lblDate.setText(dateFormat.format(calendar.getTime()));


                int newDeaths = (int) Math.floor(activeCases *0.03);
                deaths += newDeaths;
                int newCases;
                if((infectionRate+infectionModifier)-recoveryRate>0)
                    newCases = (int) Math.ceil(activeCases * ((infectionRate + infectionModifier) - recoveryRate));
                else
                    newCases = (int) Math.floor(activeCases * ((infectionRate + infectionModifier) - recoveryRate));
                activeCases += newCases;
                manager.add(container, activeCases);
                totalCases += activeCases * (infectionRate+infectionModifier);
                happiness += happinessRate;
                economy += economyRate;

                lblTotal.setText(Integer.toString(totalCases));
                lblActive.setText(Integer.toString(activeCases));
                lblDeaths.setText(Integer.toString(deaths));
                lblRecovered.setText(Integer.toString(totalCases-activeCases-deaths));

                updateBar(0, happiness/100);
                updateBar(1, economy/100);
                updateBar(2, infectionRate - recoveryRate);

                if(calendar.get(Calendar.MONTH) == firstEvent.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_MONTH) == firstEvent.get(Calendar.DAY_OF_MONTH)){
                    gameTicking = false;
                    ableToPlay = false;
                    btnNews1.setLocation(960, 540);
                }
                if(calendar.get(Calendar.MONTH) == secondEvent.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_MONTH) == secondEvent.get(Calendar.DAY_OF_MONTH)){
                    gameTicking = false;
                    ableToPlay = false;
                    btnNews2.setLocation(960, 540);
                }
                graphTotal.addData(totalCases);
                graphNew.addData(newCases);

                if(totalCases >= 1000000) {
                    gameTicking = false;
                    ableToPlay = false;
                    lossScreen1.setLocation(960, 540);
                }

                else if (economy <= 0) {
                    gameTicking = false;
                    ableToPlay = false;
                    lossScreen2.setLocation(960, 540);
                }

                else if (happiness <= 0) {
                    gameTicking = false;
                    ableToPlay = false;
                    lossScreen3.setLocation(960, 540);
                }
            }
            playButton.setLocation(10000, 10000);
        }
        else {
            playButton.setLocation(705, 78);
        }
    }

    public void setInfectionModifier(float infectionModifier) {
        this.infectionModifier = infectionModifier;
    }

    public void setEconomyRate(float economyRate) {
        this.economyRate = economyRate;
    }

    public void setHappinessRate(float happinessRate) {
        this.happinessRate = happinessRate;
    }

    public void setCureModifier(float cureModifier){
        this.cureModifier = cureModifier;
    }

    @Override
    public int getID() {
        return 2;
    }
}
