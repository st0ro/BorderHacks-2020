package borderhacks2020.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.util.FontUtils;

public class Label extends ScreenComponent {

    private String text;
    private Font font;
    private int alignment;

    public Label(GUIContext container, String text, int x, int y) {
        super(container);
        this.text = text;
        font = container.getDefaultFont();
        screenBox = new Rectangle(0, 0, font.getWidth(text), font.getHeight(text));
        setLocation(x, y);
        alignment = FontUtils.Alignment.CENTER;
    }

    public Label(GUIContext container, String text, int x, int y, int align) {
        super(container);
        this.text = text;
        font = container.getDefaultFont();
        screenBox = new Rectangle(0, 0, font.getWidth(text), font.getHeight(text));
        setLocation(x, y);
        alignment = align;
    }

    public Label(GUIContext container, String text, int x, int y, Font font) {
        super(container);
        this.text = text;
        this.font = font;
        screenBox = new Rectangle(0, 0, font.getWidth(text), font.getHeight(text));
        setLocation(x, y);
        alignment = FontUtils.Alignment.CENTER;
    }

    public Label(GUIContext container, String text, int x, int y, Font font, int align) {
        super(container);
        this.text = text;
        this.font = font;
        screenBox = new Rectangle(0, 0, font.getWidth(text), font.getHeight(text));
        setLocation(x, y);
        alignment = align;
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        FontUtils.drawString(font, text, alignment, getX(), getY(), getWidth(), Color.white);
        // TODO line wrapping
    }
}