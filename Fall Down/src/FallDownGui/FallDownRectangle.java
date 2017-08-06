package FallDownGui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class FallDownRectangle {
	public MainPanel panel;
	public ArrayList<Rectangle> fallItRect;
	public Random random;

	public FallDownRectangle(MainPanel panel) {
		this.panel = panel;
		random = new Random();
		fallItRect = new ArrayList<>();
		GameStart();
		
	}
	public void GameStart() {
		for (int i = 5; i < 11; i++) {
			CreatedAnFallDownRectangle(random.nextInt(13) + 1, 100 * i);

		}
	}
	public void CreatedAnFallDownRectangle(int no, int width) {
		if (no == 1) {
			fallItRect.add(new Rectangle(100, width, 500, 15));
		} else if (no == 2) {
			fallItRect.add(new Rectangle(0, width, 600, 15));
		} else if (no == 3) {
			fallItRect.add(new Rectangle(100, width, 600, 15));
		} else if (no == 4) {
			fallItRect.add(new Rectangle(250, width, 200, 15));
			fallItRect.add(new Rectangle(0, width, 100, 15));
			fallItRect.add(new Rectangle(500, width, 100, 15));
		} else if (no == 5) {
			fallItRect.add(new Rectangle(250, width, 200, 15));
			fallItRect.add(new Rectangle(0, width, 100, 15));
			fallItRect.add(new Rectangle(600, width, 100, 15));
		} else if (no == 6) {
			fallItRect.add(new Rectangle(250, width, 200, 15));
			fallItRect.add(new Rectangle(100, width, 100, 15));
			fallItRect.add(new Rectangle(600, width, 100, 15));
		} else if (no == 7) {

			fallItRect.add(new Rectangle(0, width, 200, 15));
			fallItRect.add(new Rectangle(500, width, 200, 15));
		} else if (no == 8) {
			fallItRect.add(new Rectangle(300, width, 50, 15));
			fallItRect.add(new Rectangle(0, width, 100, 15));
			fallItRect.add(new Rectangle(600, width, 100, 15));
		} else if (no == 9) {

			fallItRect.add(new Rectangle(0, width, 50, 15));
			fallItRect.add(new Rectangle(100, width, 500, 15));

		} else if (no == 10) {
			fallItRect.add(new Rectangle(0, width, 50, 15));
			fallItRect.add(new Rectangle(200, width, 500, 15));
		} else if (no == 11) {
			fallItRect.add(new Rectangle(0, width, 500, 15));
			fallItRect.add(new Rectangle(650, width, 50, 15));
		} else if (no == 12) {
			fallItRect.add(new Rectangle(0, width, 50, 15));
			fallItRect.add(new Rectangle(650, width, 50, 15));
		} else {
			fallItRect.add(new Rectangle(0, width, 250, 15));
			fallItRect.add(new Rectangle(400, width, 300, 15));
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		for (int i = 0; i < fallItRect.size(); i++) {
			g.fillRect(fallItRect.get(i).x, fallItRect.get(i).y, fallItRect.get(i).width, fallItRect.get(i).height);
		}
	}

	public void fallItUpdate() {
		for (int i = fallItRect.size() - 1; i >= 0; i--) {
			fallItRect.get(i).y--;
			
		}
		if(fallItRect.get(0).y == -15) {
			for (int j = fallItRect.size() - 1; j >= 0; j--) {
				if (fallItRect.get(j).y <= -15) {
					fallItRect.remove(j);
				}
			}

			CreatedAnFallDownRectangle(random.nextInt(13) + 1, 585);
			}
	}
}
