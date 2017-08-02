package CarRacingGUI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import javax.imageio.ImageIO;

public class OtherCar {
	public MainJPanel panel;
	public BufferedImage yellow, police, red, white, cyan, yellow1, police1, red1, white1, cyan1;
	public ArrayList<Rectangle> rightHandSide, leftHandSide;
	public ArrayList<BufferedImage> saveRightHandImages, saveLeftHandImages;
	public ArrayList<Integer> saveRightHandNo, saveLeftHandNo;
	public Boolean pass = false;
	public int getLuck, getNo, getX, tillThere, saveAsPerLevel = 3;
	public Random random = new Random();
	public Layout layout;

	public OtherCar(MainJPanel panel, Layout layout) {
		this.panel = panel;
		this.layout = layout;
		rightHandSide = new ArrayList<>();
		leftHandSide = new ArrayList<>();
		saveRightHandImages = new ArrayList<>();
		saveLeftHandImages = new ArrayList<>();
		saveRightHandNo = new ArrayList<>();
		saveLeftHandNo = new ArrayList<>();
		ImagesOfOtherCar();
		OtherCarCreated();
	}

	public void ImagesOfOtherCar() {
		try {
			yellow = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Yellow.png"));
			police = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Police.png"));
			red = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Red.png"));
			white = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/White.png"));
			cyan = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Cyan.png"));

			// roatate image
			yellow1 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Yellow1.png"));
			police1 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Police1.png"));
			red1 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Red1.png"));
			white1 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/White1.png"));
			cyan1 = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Cyan1.png"));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void OtherCarCreated() {
		rightHandSide.add(new Rectangle(370, -300, 80, 100));
		getRandomImage(random.nextInt(5) + 1, 1);
		rightHandSide.add(new Rectangle(480, 100, 80, 100));
		getRandomImage(random.nextInt(5) + 1, 1);
		leftHandSide.add(new Rectangle(100, -250, 80, 100));
		getRandomImage(random.nextInt(5) + 1, 2);
		leftHandSide.add(new Rectangle(200, 100, 80, 100));
		getRandomImage(random.nextInt(5) + 1, 2);
	}

	public void getNextOtherCarCreated(int no, int whichSide) {
		if (whichSide == 1) {
			if(layout.level ==1 ) {
			if (no == 1)
				rightHandSide.add(new Rectangle(370, -400, 80, 100));
			else {
				rightHandSide.add(new Rectangle(480, -400, 80, 100));
			}
			}else {
				saveAsPerLevel = 2;
				if (no == 1)
					rightHandSide.add(new Rectangle(370, -300, 80, 100));
				else {
					rightHandSide.add(new Rectangle(480, -300, 80, 100));
				}
			}

			getRandomImage(random.nextInt(5) + 1, 1);
		} else

		{
			if (no == 1)
				leftHandSide.add(new Rectangle(100, -300, 80, 100));
			else {
				leftHandSide.add(new Rectangle(200, -300, 80, 100));
			}
			getRandomImage(random.nextInt(5) + 1, 2);

		}
	}

	public void paint(Graphics g) {
		for (int i = 0; i < rightHandSide.size(); i++) {

			g.drawImage(saveRightHandImages.get(i), rightHandSide.get(i).x, rightHandSide.get(i).y,
					rightHandSide.get(i).width, rightHandSide.get(i).height, null);
		}
		for (int i = 0; i < leftHandSide.size(); i++) {
			g.drawImage(saveLeftHandImages.get(i), leftHandSide.get(i).x, leftHandSide.get(i).y,
					leftHandSide.get(i).width, leftHandSide.get(i).height, null);
		}
	}

	public void OtherCarUpdate() {

		for (int i = 0; i < leftHandSide.size(); i++) {
			leftHandSide.get(i).y += 3;

			if (leftHandSide.get(i).y >= 1500) {
				getNextOtherCarCreated(random.nextInt(2) + 1, 2);
				leftHandSide.remove(i);
				saveLeftHandImages.remove(i);
				saveLeftHandNo.remove(i);

			}
		}
		for (int i = 0; i < rightHandSide.size(); i++) {
			rightHandSide.get(i).y++;
			if (rightHandSide.get(i).y == 500) {
				getNextOtherCarCreated(random.nextInt(2) + 1, 1);

			}
			if (rightHandSide.get(i).y >= 700) {
				rightHandSide.remove(i);
				saveRightHandImages.remove(i);
				saveRightHandNo.remove(i);

			}

			if (!pass) {
				if (rightHandSide.get(i).y == 50) {
					getLuck = random.nextInt(saveAsPerLevel) + 1;
					if (getLuck == 1) {
						getNo = i;
						pass = true;
						getX = rightHandSide.get(i).x;
						if (getX == 480) {
							tillThere = random.nextInt(60) + 370;
						} else {
							tillThere = random.nextInt(80) + 400;
						}
					}
				}

			} else {
				MovetheOtherCar();
			}

		}

	}

	public void MovetheOtherCar() {

		if (getX == 370) {
			rightHandSide.get(getNo).x++;
			if (rightHandSide.get(getNo).x == tillThere)
				pass = false;
		} else {
			rightHandSide.get(getNo).x--;
			if (rightHandSide.get(getNo).x == tillThere)
				pass = false;
		}

	}

	public void getRandomImage(int no, int whichSide) {

		if (no == 1) {
			if (whichSide == 1) {
				saveRightHandImages.add(yellow);
				saveRightHandNo.add(1);
			} else {
				saveLeftHandImages.add(yellow1);
				saveLeftHandNo.add(1);
			}
		} else if (no == 2) {
			if (random.nextInt(5) + 1 == 1) {
				if (whichSide == 1) {
					rightHandSide.get(rightHandSide.size() - 1).width = 60;
					saveRightHandImages.add(police);
					saveRightHandNo.add(2);
				} else {
					saveLeftHandNo.add(2);
					leftHandSide.get(leftHandSide.size() - 1).width = 60;
					saveLeftHandImages.add(police1);

				}
			} else {
				if (random.nextInt(2) + 1 == 1) {
					if (whichSide == 1) {
						rightHandSide.get(rightHandSide.size() - 1).width = 50;
						saveRightHandImages.add(red);
						saveRightHandNo.add(3);
					} else {
						saveLeftHandNo.add(3);
						leftHandSide.get(leftHandSide.size() - 1).width = 50;
						saveLeftHandImages.add(red1);
					}
				} else {
					if (whichSide == 1) {
						rightHandSide.get(rightHandSide.size() - 1).width = 110;
						saveRightHandImages.add(white);
						saveRightHandNo.add(4);
					} else {
						saveLeftHandNo.add(4);
						leftHandSide.get(leftHandSide.size() - 1).width = 110;
						saveLeftHandImages.add(white1);
					}
				}
			}
		} else if (no == 3) {
			if (whichSide == 1) {
				rightHandSide.get(rightHandSide.size() - 1).width = 50;
				saveRightHandImages.add(red);
				saveRightHandNo.add(3);
			} else {
				saveLeftHandNo.add(3);
				leftHandSide.get(leftHandSide.size() - 1).width = 50;
				saveLeftHandImages.add(red1);
			}
		} else if (no == 4) {

			if (whichSide == 1) {
				rightHandSide.get(rightHandSide.size() - 1).width = 110;
				saveRightHandImages.add(white);
				saveRightHandNo.add(4);
			} else {
				saveLeftHandNo.add(4);
				leftHandSide.get(leftHandSide.size() - 1).width = 110;
				saveLeftHandImages.add(white1);
			}
		} else {
			if (whichSide == 1) {
				rightHandSide.get(rightHandSide.size() - 1).width = 50;
				saveRightHandImages.add(cyan);
				saveRightHandNo.add(5);
			} else {
				saveLeftHandNo.add(5);
				saveLeftHandImages.add(cyan1);
				leftHandSide.get(leftHandSide.size() - 1).width = 50;

			}
		}

	}

}
