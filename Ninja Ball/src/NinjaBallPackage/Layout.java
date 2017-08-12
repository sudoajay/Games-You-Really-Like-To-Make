package NinjaBallPackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Layout {
	public MainPanel panel;
	public ArrayList<Rectangle> rectBricks, standBricks, extraBricks, fallDownBricks, movingDownBricks;
	public Random random = new Random();
	public int saveRandomNo, save ;

	public Layout(MainPanel panel) {
		this.panel = panel;
		standBricks = new ArrayList<>();
		extraBricks = new ArrayList<>();
		rectBricks = new ArrayList<>();
		movingDownBricks = new ArrayList<>();
		fallDownBricks = new ArrayList<>();
		CreatedAnLayout();
	}

	public void MakeFall(int no ) {
		for (int i = no; i < no+10; i++) {
			saveRandomNo = random.nextInt(300) + 200;
			save = random.nextInt(20);
			fallDownBricks.add(new Rectangle(0 + (i * 212) + (save * 5), saveRandomNo, 50, 50));
		}
	}

	public void CreatedAnLayout() {

		for (int i = 0; i < 20; i++) {
			saveRandomNo = random.nextInt(4) + 5;
			save = random.nextInt(20);
			for (int j = 10; j >= saveRandomNo; j--) {

				if (j != saveRandomNo) {
					extraBricks.add(new Rectangle(1325 + (i * 212) + (save * 10), (j * 53) + 20, 50, 50));

				} else {
					standBricks.add(new Rectangle(1325 + (i * 212) + (save * 10), (j * 53) + 20, 50, 50));
				}
			}
		}
		MakeFall(30);

		for (int i = 31; i < 61; i++) {
			save = random.nextInt(20);
			movingDownBricks.add(new Rectangle(1200 + (i * 252) + (save * 10), random.nextInt(50) + 200, 50, 50));
			movingDownBricks.add(new Rectangle(1200 + (i * 252) + (save * 10),
					movingDownBricks.get(movingDownBricks.size() - 1).y + random.nextInt(100) + 200, 50, 50));
			movingDownBricks.add(new Rectangle(1200 + (i * 252) + (save * 10),
					movingDownBricks.get(movingDownBricks.size() - 1).y + random.nextInt(100) + 200, 50, 50));
			movingDownBricks.add(new Rectangle(1200 + (i * 252) + (save * 10),
					movingDownBricks.get(movingDownBricks.size() - 1).y + random.nextInt(100) + 200, 50, 50));

		}

		for (int i = 0; i < 330; i++) {

			rectBricks.add(new Rectangle(0 + (i * 53), 550, 50, 50));
			rectBricks.add(new Rectangle(0 + (i * 53), 497, 50, 50));
			if (i == 23)
				
				i = 106;
			else if (i == 119)
				
				i = 159;

			else if (i == 169)
				i = 312;

		}

	}

	public void paint(Graphics g) {

		for (int i = 0; i < rectBricks.size(); i++) {
			getGraphics(i, rectBricks, g);

		}
		for (int i = 0; i < standBricks.size(); i++) {
			getGraphics(i, standBricks, g);
		}
		for (int i = 0; i < extraBricks.size(); i++) {

			getGraphics(i, extraBricks, g);
		}
		for (int i = 0; i < fallDownBricks.size(); i++) {
			getGraphics(i, fallDownBricks, g);
		}
		for (int i = 0; i < movingDownBricks.size(); i++) {
			getGraphics(i, movingDownBricks, g);
		}
	}

	public void getGraphics(int no, ArrayList<Rectangle> rect, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		GradientPaint gradientColor = null;
		g.setColor(Color.decode("#603803"));
		g.drawLine(rect.get(no).x + 1, rect.get(no).y - 2, rect.get(no).x + 51, rect.get(no).y - 2);
		g.drawLine(rect.get(no).x + 51, rect.get(no).y, rect.get(no).x + 51, rect.get(no).y + 48);
		g.drawLine(rect.get(no).x - 2, rect.get(no).y - 2, rect.get(no).x - 2, rect.get(no).y + 51);
		g.drawLine(rect.get(no).x, rect.get(no).y + 51, rect.get(no).x + 51, rect.get(no).y + 51);
		gradientColor = new GradientPaint(rect.get(no).x, rect.get(no).y, Color.decode("#7c000c"), rect.get(no).x,
				rect.get(no).y + 30, Color.decode("#f75463"));
		g2.setPaint(gradientColor);
		g2.fill(new RoundRectangle2D.Double(rect.get(no).x, rect.get(no).y, rect.get(no).width, rect.get(no).height, 3,
				1));
	}
	public void AllClear() {
		rectBricks.clear();
		fallDownBricks.clear();
		movingDownBricks.clear();
		standBricks.clear();
		extraBricks.clear();
	}

}
