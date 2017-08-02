package CarRacingGUI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.cert.CRLReason;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tree {
	public MainJPanel panel;
	public ArrayList<Rectangle> rectforTree;
	public ArrayList<BufferedImage> saveBufferImage;
	public BufferedImage tree1, tree2, tree3, tree4, tree5, tree6;
	public Random random;
	public Layout layout;

	public Tree(MainJPanel panel, Layout layout) {
		this.panel = panel;
		this.layout = layout;
		rectforTree = new ArrayList<>();
		saveBufferImage = new ArrayList<>();
		random = new Random();
		LoadImage();
		CreatedTree();
	}

	public void LoadImage() {
		try {
			tree1 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Tree1.png"));
			tree2 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Tree2.png"));
			tree3 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Tree3.png"));
			tree4 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Cactus.png"));
			tree5 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Cactus1.png"));
			tree6 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Cactus2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CreatedTree() {
		rectforTree.add(new Rectangle(300, -(random.nextInt(400)), 60, 80));
		rectforTree.add(new Rectangle(10, -(random.nextInt(400)), 60, 80));
		rectforTree.add(new Rectangle(580, -(random.nextInt(400)), 60, 80));
		for (int i = 0; i < 3; i++) {
			randomImage(random.nextInt(3) + 1);
		}
	}

	public void paint(Graphics g) {
		for (int i = 0; i < rectforTree.size(); i++) {
			g.drawImage(saveBufferImage.get(i), rectforTree.get(i).x, rectforTree.get(i).y, rectforTree.get(i).width,
					rectforTree.get(i).height, null);
		}
	}

	public void TreeUpdate() {
		int saveTheRandomNo;
		for (int i = rectforTree.size() - 1; i >= 0; i--) {
			rectforTree.get(i).y += 2;
			if (rectforTree.get(i).y >= 700) {
				rectforTree.remove(i);
				saveBufferImage.remove(i);
				if (i == rectforTree.size()) {
					saveTheRandomNo = random.nextInt(3) + 2;
					for (int j = 0; j < saveTheRandomNo; j++) {
						randomPlace(random.nextInt(3) + 1);
						randomImage(random.nextInt(3) + 1);
					}
				}

			}

		}
	}

	public void randomPlace(int no) {
		if (no == 1) {
			rectforTree.add(new Rectangle(300, -(random.nextInt(400) + 100), 60, 80));

		} else if (no == 2) {
			rectforTree.add(new Rectangle(10, -(random.nextInt(400) + 100), 60, 80));

		} else {
			rectforTree.add(new Rectangle(580, -(random.nextInt(400) + 100), 60, 80));

		}
	}

	public void randomImage(int no) {
		if (layout.level == 1) {
			if (no == 1) {
				saveBufferImage.add(tree1);
			} else if (no == 2) {
				saveBufferImage.add(tree2);
			} else {
				saveBufferImage.add(tree3);
			}
		} else {
			if (no == 1) {
				saveBufferImage.add(tree4);
			} else if (no == 2) {
				saveBufferImage.add(tree5);
			} else {
				saveBufferImage.add(tree6);
			}
		}
	}
}