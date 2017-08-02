package CarRacingGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Layout {
	public MainJPanel panel;
	public ArrayList<Rectangle> arrayForBordersLeft, arrayForBordersRigth, arrayForBordersCenter,
			arrayForLineOnRoadLeft, arrayForLineOnRoadRigth;
	public int counting, level = 1;

	public Layout(MainJPanel panel) {
		this.panel = panel;
		arrayForBordersLeft = new ArrayList<>();
		arrayForBordersRigth = new ArrayList<>();
		arrayForBordersCenter = new ArrayList<>();
		arrayForLineOnRoadLeft = new ArrayList<>();
		arrayForLineOnRoadRigth = new ArrayList<>();
		CreateBorders();
		CreateLinesOutRoad();
	}

	public void CreateBorders() {
		arrayForBordersLeft.add(new Rectangle(0, 0, 70, 700));
		arrayForBordersCenter.add(new Rectangle(300, 0, 70, 700));
		arrayForBordersRigth.add(new Rectangle(580, 0, 70, 700));
		arrayForBordersLeft.add(new Rectangle(0, -700, 70, 700));
		arrayForBordersCenter.add(new Rectangle(300, -700, 70, 700));
		arrayForBordersRigth.add(new Rectangle(580, -700, 70, 700));
		arrayForBordersLeft.add(new Rectangle(0, -1400, 70, 700));
		arrayForBordersCenter.add(new Rectangle(300, -1400, 70, 700));
		arrayForBordersRigth.add(new Rectangle(580, -1400, 70, 700));


	}

	public void CreateLinesOutRoad() {
		// line on road
		for (int i = 1; i <= 4; i++) {
			arrayForLineOnRoadLeft.add(new Rectangle(180, -290 + (i * 300), 10, 80));
		}
		for (int i = 1; i <= 4; i++) {
			arrayForLineOnRoadRigth.add(new Rectangle(470, -290 + (i * 300), 10, 80));
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		for (int i = 0; i < arrayForBordersLeft.size(); i++) {

			g.drawImage(AnImage(i), arrayForBordersLeft.get(i).x, arrayForBordersLeft.get(i).y,
					arrayForBordersLeft.get(i).width, arrayForBordersLeft.get(i).height, null);

			g.drawImage(AnImage(i), arrayForBordersCenter.get(i).x, arrayForBordersCenter.get(i).y,
					arrayForBordersCenter.get(i).width, arrayForBordersCenter.get(i).height, null);

			g.drawImage(AnImage(i), arrayForBordersRigth.get(i).x, arrayForBordersRigth.get(i).y,
					arrayForBordersRigth.get(i).width, arrayForBordersRigth.get(i).height, null);
		}

		for (int i = 0; i < arrayForLineOnRoadLeft.size(); i++) {
			g.fillRect(arrayForLineOnRoadLeft.get(i).x, arrayForLineOnRoadLeft.get(i).y,
					arrayForLineOnRoadLeft.get(i).width, arrayForLineOnRoadLeft.get(i).height);

			g.fillRect(arrayForLineOnRoadRigth.get(i).x, arrayForLineOnRoadRigth.get(i).y,
					arrayForLineOnRoadRigth.get(i).width, arrayForLineOnRoadRigth.get(i).height);
		}

	}

	public void lineOnRoadMovingUpdate() {
		for (int i = 0; i < arrayForLineOnRoadLeft.size(); i++) {

			if (arrayForLineOnRoadLeft.get(i).y >= 880) {
				arrayForLineOnRoadLeft.get(i).y = -290;
				arrayForLineOnRoadRigth.get(i).y = -290;
			}
			arrayForLineOnRoadRigth.get(i).y += 2;

			arrayForLineOnRoadLeft.get(i).y += 2;

		}
		counting++;
		if (counting >= 12000) {
			arrayForBordersLeft.get(0).y += 2;
			arrayForBordersCenter.get(0).y += 2;
			arrayForBordersRigth.get(0).y += 2;

			arrayForBordersLeft.get(1).y += 2;
			arrayForBordersCenter.get(1).y += 2;
			arrayForBordersRigth.get(1).y += 2;

			arrayForBordersLeft.get(2).y += 2;
			arrayForBordersCenter.get(2).y += 2;
			arrayForBordersRigth.get(2).y += 2;
			if (arrayForBordersLeft.get(1).y == 0) {
				if (level == 1)
					panel.timing += 100;
				level++;
				AnImage(3);
				arrayForBordersLeft.remove(0);
				arrayForBordersCenter.remove(0);
				arrayForBordersRigth.remove(0);
				
				arrayForBordersLeft.add(new Rectangle(0, -1400, 70, 700));
				arrayForBordersCenter.add(new Rectangle(300, -1400, 70, 700));
				arrayForBordersRigth.add(new Rectangle(580, -1400, 70, 700));
			}
		}

	}

	public BufferedImage AnImage(int no) {

		if (level == 1) {

			if (no == 0)
				return panel.grassImage;
			else if (no == 3) {
				counting = -9000;
				return null;
			}

			else {
				return panel.sandImage;
			}
		} else if (level == 2) {

			if (no == 0)
				return panel.sandImage;
			else if (no == 3) {
				counting = -200;
				return null;
			} else {
				return panel.grassImage;
			}
		} else if (level == 3) {

			if (no == 0)
				return panel.grassImage;
			else if (no == 3) {
				counting = -90;
				return panel.grassImage;
			}
			else {
				return panel.grassImage;
			}
		}else {
			return null;
		}

	}
}
