package borderhacks2020.ui;

import borderhacks2020.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.util.FontUtils;

public class Graph extends GuiGroup{

    private int[] data = new int[60];
    private int yMax = 100;
    private Label lblAxis;
    private Color barColor;

    public Graph(GUIContext container, int x, int y) {
        super(container);
        screenBox = new Rectangle(0, 0, 100, 100);
        setLocation(x, y);
        lblAxis = new Label(container, "100", x - 230, y - 220, Main.pixelFontBlack, FontUtils.Alignment.RIGHT);
        components.add(lblAxis);
        barColor = new Color(0x439CAD);
    }

    @Override
    public void render(GUIContext guiContext, Graphics graphics) throws SlickException {
        for(ScreenComponent sc:components) {
            sc.render(container, graphics);
        }
        for(int i = 0; i < 60; i++){
            if(data[i] != 0){
                graphics.setColor(barColor);
                graphics.fillRect(getX() - 218 + i * 7.5f, getY() + 28 - data[i] * 255/yMax, 7.5f, data[i] * 255/yMax);
            }
        }
    }

    public void addData(int newData){
        int max = newData;
        for(int i = 1; i < 60; i++){
            int val = data[i];
            if(val > max){
                max = val;
            }
            data[i - 1] = val;
        }
        data[59] = newData;
        switch((int)Math.log10(max)){
            case 0:
            case 1:
                yMax = 100;
                lblAxis.setText("100");
                break;
            case 2:
                yMax = 1000;
                lblAxis.setText("1K");
                break;
            case 3:
                yMax = 10000;
                lblAxis.setText("10K");
                break;
            case 4:
                yMax = 100000;
                lblAxis.setText("100K");
                break;
            case 5:
                yMax = 1000000;
                lblAxis.setText("1M");
        }
    }
}
