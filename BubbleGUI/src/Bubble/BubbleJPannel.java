package Bubble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class BubbleJPannel extends JPanel {

	boolean enter;
	public ArrayList<BubbleDraw> bubbleDraw;
	public int size = 50;
	public Timer timer;
	public final int delay = 33;

	/**
	 * Create the panel.
	 */
	public BubbleJPannel() {
		bubbleDraw = new ArrayList<>();

		addMouseListener(new BubbleListner());
		addMouseMotionListener(new BubbleListner());
		addMouseWheelListener(new BubbleListner());
		addKeyListener(new BubbleListner());
		setFocusable(true);

		timer = new Timer(delay, new BubbleListner());
		timer.start();
		setBackground(Color.BLACK);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (BubbleDraw draw : bubbleDraw) {
			g.setColor(draw.color);
			g.fillOval(draw.x - draw.size / 2, draw.y - draw.size / 2, draw.size, draw.size);
		}

		g.setColor(Color.GREEN);
		g.drawString("Dots - " + bubbleDraw.size(), 5, 15);
	}

	class BubbleListner implements MouseListener, KeyListener, MouseMotionListener, MouseWheelListener, ActionListener {

		Random random = new Random();

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			timer.stop();

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			timer.start();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			bubbleDraw.add(new BubbleDraw(e.getX(), e.getY(), size));
			repaint();

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			size += e.getWheelRotation();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			for (BubbleDraw draw : bubbleDraw) {
				draw.Update();
			}
			repaint();
		}

	}

	class BubbleDraw {
		public int x;
		public int y;
		public int size;
		public Color color;
		public int maxSpeed = 5;
		public int xSpeed;
		public int ySpeed;

		public BubbleDraw(int newX, int newY, int newSize) {
			this.x = newX;
			this.y = newY;
			this.size = newSize;
			color = new Color((float) Math.random(),
					(float) Math.random(), (float) Math.random(),(float) Math.random());

			xSpeed = (int) (Math.random() * maxSpeed * 2 - maxSpeed);
			ySpeed = (int) (Math.random() * maxSpeed * 2 - maxSpeed);

			if (xSpeed == 0 && ySpeed == 0) {
				xSpeed = 1;
				ySpeed = 1;
			}
		}

		public void Update() {
			x += xSpeed;
			y += ySpeed;

			if (x < size / 2 || x + size / 2 > getWidth()) {
				xSpeed = -xSpeed;
			}
			if (y < size / 2 || y + size / 2 > getHeight()) {
				ySpeed = -ySpeed;
			}
		}
	}

}
