package BallGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

public class Boxes {
	public int x, y, width = 48, height = 20;
	public Ball ball;
	public Power power;
	public Thread boxThread, soundThread;
	public ArrayList<Boxes> arrayForBoxes;
	public BallBreakerJPanel panel;
	public Random random;
	public Paddler paddler;
	public Clip clip;
	public boolean allBoxesGone, ballTouchTheBox;

	public Boxes(Ball ball, BallBreakerJPanel panel, Power power, Paddler paddler) {
		this.ball = ball;
		this.panel = panel;
		this.power = power;
		this.paddler = paddler;
		arrayForBoxes = new ArrayList<>();
		random = new Random();
		boxUpdate();
		SoundEffect();
		boxThread.start();
		soundThread.start();

	}

	public Boxes(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	public void paint(Graphics g) {
		if (arrayForBoxes.size() > 1) {

			if (panel.level == 1) {
				g.setColor(Color.RED);
				for (int i = 1; i < 18; i++) {

					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.YELLOW);
				for (int i = 18; i < 35; i++) {

					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.CYAN);
				for (int i = 35; i < 52; i++) {

					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.BLUE);
				for (int i = 52; i < 69; i++) {

					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.MAGENTA);
				for (int i = 69; i < arrayForBoxes.size(); i++) {

					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
			} else if (panel.level == 3) {
				g.setColor(Color.BLUE.brighter());
				for (int i = 1; i < 19; i++) {

					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.RED.brighter());
				for (int i = 19; i < 35; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.GREEN.brighter());
				for (int i = 35; i < 49; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.MAGENTA.brighter());
				for (int i = 49; i < 61; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.CYAN.brighter());
				for (int i = 61; i < 71; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.decode("#ff9c1c"));
				for (int i = 71; i < 79; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.decode("#c863ff"));
				for (int i = 79; i < 85; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.YELLOW.brighter());
				for (int i = 85; i < 89; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.LIGHT_GRAY.brighter());
				for (int i = 89; i < arrayForBoxes.size(); i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
			} else if (panel.level == 2) {
				g.setColor(Color.BLUE.darker());
				for (int i = 0; i < 13; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.YELLOW.darker());
				for (int i = 13; i < 25; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.decode("#6d20f1").darker());
				for (int i = 25; i < 37; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.CYAN.darker());
				for (int i = 37; i < 49; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.RED.darker());
				for (int i = 49; i < 61; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.GREEN.darker());
				for (int i = 61; i < 73; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.RED.darker());
				for (int i = 73; i < 85; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.CYAN.darker());
				for (int i = 85; i < 97; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.decode("#6d20f1").darker());
				for (int i = 97; i < 109; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.YELLOW.darker());
				for (int i = 109; i < 121; i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
				g.setColor(Color.BLUE.darker());
				for (int i = 121; i < arrayForBoxes.size(); i++) {
					g.fillRect(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y, arrayForBoxes.get(i).width,
							arrayForBoxes.get(i).height);
				}
			}
		}
	}

	public void boxUpdate() {
		boxThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (ball.nextLevel) {
						if (panel.level == 1) {
							for (int i = 1; i < 18; i++) {

								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));

							}
							arrayForBoxes
									.add(new Boxes(arrayForBoxes.get(1).x, arrayForBoxes.get(1).y - 22, width, height));
							for (int i = 19; i < 35; i++) {

								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));

							}
							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(18).x, arrayForBoxes.get(18).y - 22, width, height));
							for (int i = 36; i < 52; i++) {

								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));

							}
							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(35).x, arrayForBoxes.get(35).y - 22, width, height));
							for (int i = 53; i < 69; i++) {

								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));

							}
							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(52).x, arrayForBoxes.get(52).y - 22, width, height));
							for (int i = 70; i < 86; i++) {

								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));

							}
						} else if (panel.level == 3) {
							arrayForBoxes.add(new Boxes(arrayForBoxes.get(0).x + 46, 230, width, height));
							for (int i = 2; i < 10; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 49,
										arrayForBoxes.get(i - 1).y - 22, width, height));

							}
							arrayForBoxes
									.add(new Boxes(arrayForBoxes.get(9).x + 49, arrayForBoxes.get(9).y, width, height));
							for (int i = 11; i < 19; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 46,
										arrayForBoxes.get(i - 1).y + 23, width, height));

							}

							for (int i = 1; i < 9; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 22, width, height));
							}
							for (int i = 11; i < 19; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 22, width, height));
							}
							for (int i = 19; i < 26; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 22, width, height));

							}
							for (int i = 28; i < 35; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 23, width, height));

							}
							for (int i = 35; i < 41; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 22, width, height));

							}
							for (int i = 43; i < 49; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 23, width, height));

							}
							for (int i = 49; i < 54; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 22, width, height));

							}
							for (int i = 56; i < 61; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 23, width, height));

							}
							for (int i = 61; i < 65; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 22, width, height));

							}
							for (int i = 67; i < 71; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 23, width, height));

							}
							for (int i = 71; i < 74; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 22, width, height));

							}
							for (int i = 76; i < 79; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 23, width, height));

							}
							for (int i = 79; i < 81; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 22, width, height));

							}
							for (int i = 83; i < 85; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 23, width, height));

							}
							for (int i = 85; i < 86; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 22, width, height));

							}
							for (int i = 88; i < 89; i++) {
								arrayForBoxes.add(
										new Boxes(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y - 23, width, height));

							}

						} else if (panel.level == 2) {
							arrayForBoxes.add(new Boxes(150, 150, width, height));
							for (int i = 2; i < 13; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}
							arrayForBoxes
									.add(new Boxes(arrayForBoxes.get(1).x, arrayForBoxes.get(1).y + 22, width, height));
							for (int i = 14; i < 25; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}
							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(13).x, arrayForBoxes.get(13).y + 22, width, height));
							for (int i = 26; i < 37; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}
							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(25).x, arrayForBoxes.get(25).y + 22, width, height));
							for (int i = 38; i < 49; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}
							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(37).x, arrayForBoxes.get(37).y + 22, width, height));
							for (int i = 50; i < 61; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}
							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(49).x, arrayForBoxes.get(49).y + 22, width, height));
							for (int i = 62; i < 73; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}
							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(61).x, arrayForBoxes.get(61).y + 22, width, height));
							for (int i = 74; i < 85; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}

							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(73).x, arrayForBoxes.get(73).y + 22, width, height));
							for (int i = 86; i < 97; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}

							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(85).x, arrayForBoxes.get(85).y + 22, width, height));
							for (int i = 98; i < 109; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}

							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(97).x, arrayForBoxes.get(97).y + 22, width, height));
							for (int i = 110; i < 121; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}
							arrayForBoxes.add(
									new Boxes(arrayForBoxes.get(109).x, arrayForBoxes.get(109).y + 22, width, height));
							for (int i = 122; i < 133; i++) {
								arrayForBoxes.add(new Boxes(arrayForBoxes.get(i - 1).x + 50, arrayForBoxes.get(i - 1).y,
										width, height));
							}

						}
						ball.nextLevel = false;
						panel.repaint();

					} else {
						for (int i = 0; i < arrayForBoxes.size(); i++) {

							if (ball.ballRect.intersects(new Rectangle(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y,
									arrayForBoxes.get(i).width, arrayForBoxes.get(i).height))) {
								ballTouchTheBox = true;
								if (!power.touchBomb) {
									ball.ySpeed = -ball.ySpeed;
								}

								panel.score += random.nextInt(50) + 50;
								if (!power.powerOn) {

									if (random.nextInt(3) + 1 == 1) {
										power.power = true;
										power.powerOn = true;
										power.returnRandomNo();
										power.GetLocation(arrayForBoxes.get(i).x, arrayForBoxes.get(i).y);
									}
								}

								arrayForBoxes.get(i).x = -26;
								arrayForBoxes.get(i).y = 200;
								arrayForBoxes.get(i).width = 0;
								arrayForBoxes.get(i).height = 0;

							}

						}

						for (int j = 0; j < arrayForBoxes.size(); j++) {

							if (arrayForBoxes.get(j).x == -26) {
								allBoxesGone = true;
								continue;
							}
							allBoxesGone = false;
							break;
						}

						if (allBoxesGone) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ball.nextLevel = true;
							LocationOfNewLevelBall();
							panel.level++;
							panel.GameLevels();
							panel.timer.stop();
							if (panel.level != 4) {
								JOptionPane.showConfirmDialog(null, "Congrats You Enter Next Level Good Luck  ",
										"Congrats For Next Level !", JOptionPane.WARNING_MESSAGE,
										JOptionPane.YES_OPTION, panel.congratsImage);

							} else {
								JOptionPane.showConfirmDialog(null, "Congrats You Complete All Level  ", "Game Ends !",
										JOptionPane.WARNING_MESSAGE, JOptionPane.YES_OPTION, panel.congratsImage);
								panel.GameRestart();
							}
							panel.timer.start();
						}
					}

				}
			}

		});
	}

	public void LocationOfNewLevelBall() {
		ball.ballRect.x = 200;
		ball.ballRect.y = 500;
		if (ball.ySpeed > 0) {
			ball.ySpeed = +ball.ySpeed;

		} else {
			ball.ySpeed = -ball.ySpeed;
		}

		if (ball.xSpeed > 0) {
			ball.xSpeed = +ball.xSpeed;

		} else {
			ball.xSpeed = -ball.xSpeed;
		}

	}

	public void SoundEffect() {
		soundThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (ballTouchTheBox) {
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

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ballTouchTheBox = false;
						clip.stop();
					}
				}

			}
		});
	}
}
