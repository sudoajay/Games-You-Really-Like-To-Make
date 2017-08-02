package CarRacingGUI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class PlayerCar {
	public MainJPanel panel;
	public Rectangle PlayerCarRect;
	public Layout layout;
	public Tree tree;
	public OtherCar otherCar;

	public PlayerCar(MainJPanel panel, Layout layout, Tree tree, OtherCar otherCar) {
		this.panel = panel;
		this.otherCar = otherCar;
		this.layout = layout;
		this.tree = tree;
		PlayerCarRect = new Rectangle(350, 250, 100, 100);
	}

	public void CreatedPlayerCar() {
		PlayerCarRect.setBounds(350, 250, 100, 100);
	}

	public void paint(Graphics g) {
		g.drawImage(panel.playerCar, PlayerCarRect.x, PlayerCarRect.y, PlayerCarRect.width, PlayerCarRect.height, null);
	}

	public void KeyPressed(int key) {
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			if (PlayerCarRect.x + 20 >= 0)
				PlayerCarRect.x -= 10;

		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			if (PlayerCarRect.x + PlayerCarRect.width + 30 <= 700)
				PlayerCarRect.x += 10;
		}

	}

	public void playerCarUpdate() {
		panel.timer.setDelay(4);
		for (int i = 0; i < layout.arrayForBordersLeft.size(); i++) {
			if (PlayerCarRect
					.intersects(new Rectangle(layout.arrayForBordersLeft.get(i).x, layout.arrayForBordersLeft.get(i).y,
							layout.arrayForBordersLeft.get(i).width - 30, layout.arrayForBordersLeft.get(i).height))
					|| PlayerCarRect.intersects(new Rectangle(layout.arrayForBordersCenter.get(i).x + 30,
							layout.arrayForBordersCenter.get(i).y, layout.arrayForBordersCenter.get(i).width - 60,
							layout.arrayForBordersCenter.get(i).height))
					|| PlayerCarRect.intersects(new Rectangle(layout.arrayForBordersRigth.get(i).x + 30,
							layout.arrayForBordersRigth.get(i).y, layout.arrayForBordersRigth.get(i).width - 30,
							layout.arrayForBordersRigth.get(i).height))) {
				panel.timer.setDelay(10);
			}
		}

		for (int i = 0; i < tree.rectforTree.size(); i++) {
			if (PlayerCarRect.contains(tree.rectforTree.get(i))) {
				 panel.GameRestart(2);

			}
		}
		for (int j = 0; j < otherCar.leftHandSide.size(); j++) {
			if (otherCar.saveLeftHandNo.get(j) == 3 || otherCar.saveLeftHandNo.get(j) == 5) {
				if (PlayerCarRect.intersects(
						new Rectangle(otherCar.leftHandSide.get(j).x + 20, otherCar.leftHandSide.get(j).y + 10,
								otherCar.leftHandSide.get(j).width - 49, otherCar.leftHandSide.get(j).height - 20))) {
					panel.GameRestart(3);

				}
			} else if (otherCar.saveLeftHandNo.get(j) == 1) {
				if (PlayerCarRect.intersects(
						new Rectangle(otherCar.leftHandSide.get(j).x + 38, otherCar.leftHandSide.get(j).y + 10,
								otherCar.leftHandSide.get(j).width - 79, otherCar.leftHandSide.get(j).height - 20))) {
					panel.GameRestart(3);
				}
			} else if (otherCar.saveLeftHandNo.get(j) == 2) {
				if (PlayerCarRect.intersects(
						new Rectangle(otherCar.leftHandSide.get(j).x + 38, otherCar.leftHandSide.get(j).y + 10,
								otherCar.leftHandSide.get(j).width - 59, otherCar.leftHandSide.get(j).height - 20))) {
					panel.GameRestart(3);
				}
			} else {
				if (PlayerCarRect.intersects(new Rectangle(otherCar.leftHandSide.get(j).x + 57,
						otherCar.leftHandSide.get(j).y + 10, otherCar.leftHandSide.get(j).width - 109,
						otherCar.leftHandSide.get(j).height - 20))) {
					panel.GameRestart(3);
				}
			}

		}
		for (int j = 0; j < otherCar.rightHandSide.size(); j++) {
			if (otherCar.saveRightHandNo.get(j) == 3 || otherCar.saveRightHandNo.get(j) == 5) {
				if (PlayerCarRect.intersects(
						new Rectangle(otherCar.rightHandSide.get(j).x + 20, otherCar.rightHandSide.get(j).y + 10,
								otherCar.rightHandSide.get(j).width - 49, otherCar.rightHandSide.get(j).height - 20))) {
					panel.GameRestart(3);
				}
			} else if (otherCar.saveRightHandNo.get(j) == 1) {
				if (PlayerCarRect.intersects(
						new Rectangle(otherCar.rightHandSide.get(j).x + 38, otherCar.rightHandSide.get(j).y + 10,
								otherCar.rightHandSide.get(j).width - 79, otherCar.rightHandSide.get(j).height - 20))) {
					panel.GameRestart(3);
				}
			} else if (otherCar.saveRightHandNo.get(j) == 2) {
				if (PlayerCarRect.intersects(
						new Rectangle(otherCar.rightHandSide.get(j).x + 38, otherCar.rightHandSide.get(j).y + 10,
								otherCar.rightHandSide.get(j).width - 59, otherCar.rightHandSide.get(j).height - 20))) {
					panel.GameRestart(3);
				}
			} else {
				if (PlayerCarRect.intersects(new Rectangle(otherCar.rightHandSide.get(j).x + 57,
						otherCar.rightHandSide.get(j).y + 10, otherCar.rightHandSide.get(j).width - 109,
						otherCar.rightHandSide.get(j).height - 20))) {
					panel.GameRestart(3);
				}
			}
		}

	}
}
