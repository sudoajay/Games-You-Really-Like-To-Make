package MoveForwardPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Paddler {
	public MainPanel panel;
	public ArrayList<Rectangle> arrayForPaddler;
	public Random random  = new Random();
	public Paddler(MainPanel panel) {
		this.panel = panel;
		arrayForPaddler = new ArrayList<>();
		StartingOfGame();
	}
	public void StartingOfGame() {
		arrayForPaddler.add(new Rectangle(0, 300, 200, 15));
		for (int i = 0; i < 8; i++) {
			CreatedAnPaddler();	
		}	
	}
	public void CreatedAnPaddler() {
		arrayForPaddler.add(new Rectangle(arrayForPaddler.get(arrayForPaddler.size() - 1).x
				+ arrayForPaddler.get(arrayForPaddler.size() - 1).width + 80,random.nextInt(200)+200 , random.nextInt(130)+50, 15));
	}
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		for (int i = 0; i < arrayForPaddler.size(); i++) {

			g.fillRect(arrayForPaddler.get(i).x, arrayForPaddler.get(i).y, arrayForPaddler.get(i).width,
					arrayForPaddler.get(i).height);
		}

	}
	public void PaddlerUpdate() {
		for (int i = 0; i < arrayForPaddler.size(); i++) {
			arrayForPaddler.get(i).x-=1;
		}
		if(arrayForPaddler.get(0).x == -150) {
		
			arrayForPaddler.remove(0);
			CreatedAnPaddler();
		}
		
	}
	
}
