package GameConfig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick {
    public int x, y;
    public int width = 60;
    public int height = 20;
    public boolean isVisible = true;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }
}
