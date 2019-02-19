package com.liyan.bird;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel{
	BufferedImage background;
	public Test() throws Exception {
	background=ImageIO.read( getClass().getResource("/images/bg.png"));
	}
	public static void main(String[] args) throws Exception {
		JFrame frame=new JFrame("����----FlyBird!");
		Test game=new Test();
		frame.add(game);
		frame.setSize(440, 710);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	public  void paint(Graphics g) {
		g.drawImage(background,0,0,null);
	}
	public void palyMusic() {
		java.net.URL file1 = getClass().getResource("love.wav"); 
		  AudioClip sound1 = java.applet.Applet.newAudioClip(file1); 
		  sound1.play();
	}

}
