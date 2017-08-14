package Puzzle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class PuzzleJInternalFrame extends JInternalFrame implements MouseListener, KeyListener {

	public JButton[] button;
	public final int noBox = 9;
	public Icon greyIcon;
	public Icon mouseClcikedIcon;
	public Icon restartIcon;
	public Random random;
	public Color color;
	public int randomNo;
	public int[] array = { 4, 7, 1 };
	public Integer[] arrayNo = { 1, 2, 3, 4, 5, 6, 7, 8, };
	public List<Integer> arrayNO;
	public TreeMap<Integer, Integer> map = new TreeMap<>();
	public int whereBoxIS;
	public Point size;
	public Thread thread;
	public int onClick;

	public PuzzleJInternalFrame() {
		setPreferredSize(new Dimension(500, 450));
		setBackground(Color.gray);
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 8));
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		addKeyListener(this);
		setFocusable(true);
		random = new Random();
		color = Color.decode("#09A21C");
		button = new JButton[noBox];
		StartTheGame();
		checkForWin();
		thread.start();

	}

	@SuppressWarnings("unchecked")
	public void StartTheGame() {

		greyIcon = new ImageIcon("/home/sudoajay/Documents/workspace/PuzzleGUI/src/Images/grey-background.png");
		mouseClcikedIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/MouseClick.png");
		restartIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/Restart.png");
		randomNo = random.nextInt(array.length);
		whereBoxIS = array[randomNo];
		arrayNO = Arrays.asList(arrayNo);
		Collections.shuffle(arrayNO);
		int j = 0;
		for (int i = 0; i < noBox; i++) {
			if (i != array[randomNo]) {
				button[i] = new JButton("" + arrayNO.get(j));
				map.put(arrayNO.get(j), i);
				j++;
			} else {
				button[i] = new JButton("");
				map.put(9, i);
			}
			button[i].addMouseListener(this);
			button[i].setFont(new Font("FreeSans", Font.BOLD, 55));
			button[i].setPreferredSize(new Dimension(150, 130));
			button[i].setBackground(color);
			button[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
			button[i].setFocusable(false);
			getContentPane().add(button[i]);

		}

		button[array[randomNo]].setBackground(Color.GRAY);
		button[array[randomNo]].setText("");
		button[array[randomNo]].setBorder(BorderFactory.createLineBorder(Color.GRAY, 10));

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if(key == KeyEvent.VK_R){
			RestartTheGame();
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
	public void mouseClicked(MouseEvent e) {

		onClick++;
		for (int i = 0; i < button.length; i++) {
			if (e.getSource() == button[i]) {

				if ((button[whereBoxIS].getX() + 155 == (button[i].getX())
						&& button[whereBoxIS].getY() == (button[i].getY()))
						|| (button[whereBoxIS].getX() - 155 == (button[i].getX())
								&& button[whereBoxIS].getY() == (button[i].getY()))
						|| (button[whereBoxIS].getX() == (button[i].getX())
								&& button[whereBoxIS].getY() + 135 == (button[i].getY()))
						|| (button[whereBoxIS].getX() == (button[i].getX())
								&& button[whereBoxIS].getY() - 135 == (button[i].getY()))) {

					size = button[whereBoxIS].getLocation();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					button[whereBoxIS].setLocation(button[i].getLocation());
					button[i].setLocation(size);

				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void checkForWin() {
		thread = new Thread(new Runnable() {
			boolean pass = false;;

			@Override
			public void run() {
				while (true) {
					pass = false;
					if (button[map.get(1)].getX() == 12 && button[map.get(1)].getY() == 5) {

						if (button[map.get(2)].getX() == 167 && button[map.get(2)].getY() == 5) {

							if (button[map.get(3)].getX() == 322 && button[map.get(3)].getY() == 5) {

								if (button[map.get(4)].getX() == 12 && button[map.get(4)].getY() == 140) {

									if (button[map.get(5)].getX() == 167 && button[map.get(5)].getY() == 140) {

										if (button[map.get(6)].getX() == 322 && button[map.get(6)].getY() == 140) {

											if (button[map.get(7)].getX() == 12 && button[map.get(7)].getY() == 275) {

												if (button[map.get(8)].getX() == 167
														&& button[map.get(8)].getY() == 275) {

													if (button[map.get(9)].getX() == 322
															&& button[map.get(9)].getY() == 275) {
														pass = true;
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if (pass) {
						JOptionPane.showConfirmDialog(null, "Your Mouse Clicked  - " + onClick, "Score Board",
								JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_OPTION, mouseClcikedIcon);

						int getInput = JOptionPane.showConfirmDialog(null, "Are You Sure To Restart ?",
								"Restart The Game ", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION,
								restartIcon);
						if (getInput == 0)
							RestartTheGame();
						else {
							System.exit(0);
						}
					}
					

				}
			}

		});

	}

	public void RestartTheGame() {
		Collections.shuffle(arrayNO);
		whereBoxIS = array[randomNo];
		int j = 0;
		for (int i = 0; i < noBox; i++) {
			if (i != array[randomNo]) {
				button[i].setText("" + arrayNO.get(j));
				j++;
			} else {
				button[i].setText("");
			}

		}
	}
}
