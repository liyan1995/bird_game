package com.liyan.bird;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * create by YanL on 2019/2/19
 */
public class Bird {
    private BufferedImage[] images;
    private int index;

    BufferedImage image;
    int x, y;
    int width;
    int height;

    private double s, g, t, v0, speed;


    public Bird() throws Exception {

        image = ImageIO.read(getClass().getResource("/images/0.png"));
        width = image.getWidth() - 45;
        height = image.getHeight();
        x = 132;
        y = 280;

        g = 4;
        v0 = 21;
        t = 0.5;
        speed = v0;
        s = 0;

        images = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            images[i] = ImageIO.read(getClass().getResource("/images/" + i + ".png"));
        }
        index = 0;
    }

    public boolean hint(Ground ground) {
        boolean hit = y >= ground.y - height / 2;
        if (hit) {
            y = ground.y - height / 2;

        }
        return hit;
    }

    public boolean hint(Column column) {
        return x > column.x - column.width / 2 - width / 2 - 40 && x < column.x + column.width / 2 + width / 2 && (y <= column.y - column.gap / 2 + height / 2 || y >= column.y + column.gap / 2 - height / 2);
    }

    public void flappy() {
        speed = v0;
    }

    public void fly() {
        index++;
        image = images[(index / 5) % 3];
    }

    public void step() {
        double v0 = speed;
        s = v0 * t + g * t * t * 0.5;
        y = y - (int) s;
        speed = v0 - (g * t);
    }
}
