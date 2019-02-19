package com.liyan.bird;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BirdGame extends JPanel {
    private Bird bird;
    private Column column1, column2;
    private Ground ground;
    private BufferedImage background;
    private int score;

    private BufferedImage gameoverImage;
    private BufferedImage startImage;
    private int state;
    private static final int START = 0;
    private static final int RUNNING = 1;
    private static final int GAME_OVER = 2;

    private BirdGame() throws Exception {
        state = START;
        gameoverImage = ImageIO.read(getClass().getResource("/images/gameover.png"));
        startImage = ImageIO.read(getClass().getResource("/images/start0.png"));

        bird = new Bird();
        column1 = new Column(1);
        column2 = new Column(2);
        ground = new Ground();
        background = ImageIO.read(getClass().getResource("/images/bg.png"));
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("FlyBird!");
        BirdGame game = new BirdGame();
        frame.add(game);
        frame.setSize(440, 710);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        game.playMusic(1);

        game.action();
    }

    private void playMusic(int n) {
        java.net.URL file1 = getClass().getResource("/voices/music" + n + ".wav");
        AudioClip sound1 = java.applet.Applet.newAudioClip(file1);
        sound1.loop();
    }

    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(column1.image, column1.x - column1.width / 2,
                column1.y - column1.height / 2, null);
        g.drawImage(column2.image, column2.x - column2.width / 2,
                column2.y - column2.height / 2, null);
        g.drawImage(ground.image, ground.x, 0, null);

        g.drawImage(bird.image, bird.x - bird.width / 2, bird.y - bird.height / 2, null);

        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        g.setFont(f);
        g.drawString(" " + score, 30, 60);
        g.setColor(Color.WHITE);
        g.drawString(" " + score, 30 - 3, 60 - 3);

        switch (state) {
            case GAME_OVER:
                g.drawImage(gameoverImage, 0, 0, null);
                break;
            case START:
                g.drawImage(startImage, 60, 320, null);
                break;
        }
    }

    private void action() throws Exception {
        MouseListener l = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                try {
                    switch (state) {
                        case GAME_OVER:
                            column1 = new Column(1);
                            column2 = new Column(2);
                            bird = new Bird();
                            Thread.sleep(1000 / 30);
                            score = 0;
                            state = START;
                            break;
                        case START:
                            state = RUNNING;
                        case RUNNING:
                            bird.flappy();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        };
        addMouseListener(l);
        while (true) {
            switch (state) {
                case START:
                    bird.fly();
                    ground.step();
                    break;
                case RUNNING:
                    column1.step();
                    column2.step();
                    bird.fly();
                    bird.step();
                    ground.step();
                    if (bird.x == column1.x || bird.x == column2.x) {
                        score++;
                    }
                    if (bird.hint(ground) || bird.hint(column1) || bird.hint(column2)) {
                        state = GAME_OVER;
                    }
                    break;
            }
            repaint();
            Thread.sleep(1000 / 33);
        }
    }
}

