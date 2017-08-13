package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

public class PongBall {
	public Random random;
	public boolean ballTouchThePaddle;
	public int x, y, width = 25, height = 25, xSpeed, ySpeed, whichSide = 0;
	public Rectangle ball, paddle1, paddle2;
	public PongJPanel pong;
	public Thread soundThread;
	public Clip clip;

	public PongBall(PongJPanel pong) {
		this.pong = pong;
		this.x = 313;
		this.y = 250;
		ball = new Rectangle(x, y, width, height);
		paddle1 = new Rectangle();
		paddle2 = new Rectangle();
		random = new Random();
		xSpeed = random.nextInt(3) + 1;
		ySpeed = random.nextInt(3) + 1;
		SoundThread();
		soundThread.start();
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(ball.x, ball.y, ball.width, ball.height);
	}

	public void move() {

		if (whichSide == 1 || whichSide == 2){
			xSpeed = -xSpeed;
		}
		whichSide = 0;

		ball.y += ySpeed;
		ball.x += xSpeed;

		if (ball.y+5 < 0 || ball.y + 20 > 550) {

			if (ySpeed > 0) {
				ySpeed = random.nextInt(3) + 1;

			} else if (ySpeed < 0) {
				ySpeed = random.nextInt(3) - 3;
			}
			ySpeed = -ySpeed;

		}
		CheckforIntersection();
		BallVanished();

	}

	public void CheckforIntersection() {

		if (ball.intersects(paddle1) || ball.intersects(paddle2)) {
			ballTouchThePaddle =true;
			if (xSpeed > 0) {
				xSpeed = random.nextInt(3) + 1;

			} else if (xSpeed < 0) {
				xSpeed = random.nextInt(3) - 3;
			}
			xSpeed = -xSpeed;
		}
	}

	public void BallVanished(){
		if(ball.x <-25){
			
			pong.player2Score++;
			pong.repaint();
			if(pong.scoreLimit == pong.player2Score ){
				pong.timer.stop();
				int getInput1 = JOptionPane.showConfirmDialog(null, "Player 2 Wins The game By " +(pong.player2Score - pong.player1Score) + " Points"   , " Restart The Game ",
						JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, pong.restartIcon);
				if (getInput1 == 0) {
					pong.Restart();
				}
				else{
					System.exit(0);
				}
			}
			ball.x= 313;
			ball.y=250;
			whichSide = 1;
			
		}
		else if(ball.x >675){
			pong.player1Score++;
			pong.repaint();
			if(pong.scoreLimit == pong.player1Score ){
				pong.timer.stop();
				int getInput2 = JOptionPane.showConfirmDialog(null, "Player 1 Wins The game By " +(pong.player1Score - pong.player2Score) + " Points"   , " Restart The Game ",
						JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, pong.restartIcon);
				if (getInput2 == 0) {
					pong.Restart();
				}
				else{
					System.exit(0);
				}
			}
			ball.x= 313;
			ball.y=250;
			whichSide =2;
			
		}
		
		
	}
	
	public void GetPaddleInfo(int x, int y, int width, int height, int whichPaddle) {

		if (whichPaddle == 1) {
			this.paddle1.setBounds(x, y, width, height);
		} else {
			this.paddle2.setBounds(x, y, width, height);
		}
	}

	public void SoundThread(){
		soundThread= new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						if(ballTouchThePaddle){
					try {
						Thread.sleep(300);
					File file = new File("/home/sudoajay/Documents/workspace/PongGui/src/Images/Sound.wav");

					AudioInputStream ais = AudioSystem.getAudioInputStream(file);
					AudioFormat format = ais.getFormat();
					DataLine.Info info = new DataLine.Info(Clip.class, format);
					clip = (Clip) AudioSystem.getLine(info);
					clip.open(ais);
					
					clip.loop(Clip.LOOP_CONTINUOUSLY);		
					clip.start();
					Thread.sleep(50);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					ballTouchThePaddle = false;
				clip.stop();
			}
				}
				
			}
		});
	}
}
