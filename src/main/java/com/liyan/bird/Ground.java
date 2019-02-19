package com.liyan.bird;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * create by YanL on 2019/2/19
 */
public class Ground {
    BufferedImage image;
    int x;
    int y;
    private int width;
    private int height;

    Ground() throws IOException {
        image = ImageIO.read(getClass().getResource("/images/ground.png"));
        width = image.getWidth();
        height = image.getHeight();
        x = 0;
        y = 540;
    }

    public void step() {
        x--;
        if (x == -109) {
            x = 0;
        }

    }
}
