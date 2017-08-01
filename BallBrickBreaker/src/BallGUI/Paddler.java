package BallGUI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Paddler {
	public BallBreakerJPanel panel;
	public int x, y, width, height, mX;
	public BufferedImage paddleImage;
	public Rectangle paddleRect;

	public Paddler(BallBreakerJPanel panel) {
		this.panel = panel;
		x = 200;
		y = 670;
		width = 100;
		height = 20;
		paddleRect = new Rectangle(x, y, width, height);
		PaddlerImage();

	}

	public void PaddlerImage() {
		try {
			paddleImage = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/BallBrickBreaker/src/Images/Paddle.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		g.drawImage(paddleImage, paddleRect.x, paddleRect.y, paddleRect.width, paddleRect.height, null);

	}

	public void PadlerUpdate() {
		if (mX == 8) {
			if (paddleRect.x + paddleRect.width + 20 <= 900) 
				paddleRect.x += mX;
			
		} else if (mX == -8) {
			if (paddleRect.x >= 0 + 20) 
				paddleRect.x += mX;
			
		} else {
			paddleRect.x += mX;
		}
	}

	public void MouseMovement(MouseEvent e) {

		paddleRect.x = e.getX();
		if (paddleRect.x <= 0) {
			paddleRect.x = 0;
		} else if (paddleRect.x + paddleRect.width + 2 >= 900) {
			paddleRect.x = 800;

		}

		panel.repaint();
	}

	public void KeyPressed(int key) {
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)

			mX = 8;
		else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)

			mX = -8;

		panel.repaint();
	}

	public void keyReleased(int key) {
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) 

			mX = 0;
		 else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) 

			mX = 0;
		
		panel.repaint();
	}
}
