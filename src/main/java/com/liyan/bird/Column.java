package com.liyan.bird;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * create by YanL on 2019/2/19
 */
public class Column {

    BufferedImage image;
    int x, y;
    int width;
    int height;
    int gap;
    private int distance;

    private Random random = new Random();

    Column(int n) throws IOException {
        image = ImageIO.read(getClass().getResource("/images/column.png"));
        width = image.getWidth();
        height = image.getHeight();
        gap = 144;
        distance = 245;
        x = 550 + (n - 1) * distance;
        y = random.nextInt(288) + 132;
    }

    public void step() {
        x--;
        if (x == -width / 2) {
            x = distance * 2 - width / 2;
            y = random.nextInt(288) + 132;
        }
    }
}
