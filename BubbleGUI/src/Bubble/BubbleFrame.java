package Bubble;

import java.awt.Color;

import javax.swing.JFrame;

public class BubbleFrame extends  JFrame {
	
	public static void main(String[] args) {
		
	BubbleFrame frame = new BubbleFrame();
	frame.setVisible(true);
	frame.setTitle("Bubble Time ");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new BubbleJPannel());
	frame.setBounds(300, 100, 700, 600);
	
	
}
}
