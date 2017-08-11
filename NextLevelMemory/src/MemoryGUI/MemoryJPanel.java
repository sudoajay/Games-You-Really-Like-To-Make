package MemoryGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class MemoryJPanel extends JPanel implements MouseListener {

	public Icon hideIcon;
	public Icon bootstrapIcon;
	public Icon cIcon;
	public Icon html5Icon;
	public Icon javaIcon;
	public Icon javaScriptIcon;
	public Icon phpIcon;
	public Icon pythonIcon;
	public Icon swiftIcon;
	public Icon defaultIcon;
	public Icon mouseClcikedIcon;
	public Icon restartIcon;
	public Icon hintIcon;
	public Border deafaultBorder;
	public String firstClciked;
	public String secondClciked;
	public int firstNo;
	public int secondNo,get =2;
	public final int box = 16;
	public JButton[] button;
	public ArrayList<Integer> saveNo = new ArrayList<>();
	public ArrayList<Integer> doneNo = new ArrayList<>();
	public Thread threadCheck;
	public Thread threadForHint;
	public int mouseClciked;
	boolean hintUse = false;
	boolean pass = false, hint = true, mouseClicked = true,onetime;

	public MemoryJPanel() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600, 520));
		addKeyListener(new Listener());
		setFocusable(true);
		button = new JButton[box];
		addMouseListener(this);
		allImage();

		startTheGame();
		RandomNo();
		checkTheGame();
		threadCheck.start();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.drawRect(getWidth() / 2 - 20, getHeight() - 50, 40, 40);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		g.setColor(Color.GRAY);
		g.drawString(mouseClciked + "", getWidth() / 2 - 5, getHeight() - 25);

		g.setColor(Color.BLUE);
		g.drawRect(120, getHeight() - 50, 70, 40);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 20));
		g.setColor(Color.GRAY);
		g.drawString("Clear", 130, getHeight() - 25);

		g.setColor(Color.BLUE);
		g.drawRect(getWidth() - 200, getHeight() - 50, 70, 40);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 20));
		g.setColor(Color.GRAY);
		g.drawString("Hint", getWidth() - 185, getHeight() - 25);
	}

	public void allImage() {
		hideIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/mainImage.jpg");
		bootstrapIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/BootstrapIcon.png");
		cIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/C++Icon.png");
		html5Icon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/Html5Icon.png");
		javaIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/JavaIcon.png");
		javaScriptIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/JavaScriptIcon.png");
		phpIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/PhpIcon.png");
		pythonIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/PythonIcon.png");
		swiftIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/SwiftIcon.png");
		mouseClcikedIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/MouseClick.png");
		restartIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/Restart.png");
		hintIcon = new ImageIcon("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/HintIcon.png");
	}

	public void startTheGame() {

		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton("");
			button[i].setIcon(hideIcon);
			button[i].addActionListener(new Listener());
			button[i].addMouseListener(new Listener());
			defaultIcon = button[i].getIcon();
			button[i].setFocusable(false);
			deafaultBorder = button[i].getBorder();
			add(button[i]);
		}

	}

	class Listener implements KeyListener, ActionListener, MouseListener {

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
			// threadCheck TODO Auto-generated method stub

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String restart = "restart";
			for (int i = 0; i < button.length; i++) {
				if (e.getSource() == button[i]) {
					if (button[i].getIcon() == defaultIcon) {
						returnImage(i);

						if (firstClciked != null && secondClciked != null)
							secondNo = i;

					}

				}
				if (button[i].getIcon() == defaultIcon)
					restart = "GameOn";
			}
			if (mouseClicked) {
				if (restart.equalsIgnoreCase("restart")) {
					JOptionPane.showConfirmDialog(null, "Your Mouse Clicked  - " + mouseClciked, "Score Board",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_OPTION, mouseClcikedIcon);

					int getInput = JOptionPane.showConfirmDialog(null, "Are You Sure To Restart ?", "Restart The Game ",
							JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartIcon);
					if (getInput == 0)
						RestartTheGame();
					else {
						System.exit(0);
					}
				}
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(!onetime ){
				
				HintForWHile();
				threadForHint.start();
				onetime =true;
			}
			if (mouseClicked) {
				mouseClciked += 1;
				repaint();
			
					for (int i = 0; i < button.length; i++) {
						if (e.getSource() == button[i]) {
							button[i].setBackground(UIManager.getColor("control"));
						}
					}
				
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			for (int i = 0; i < button.length; i++) {
				if (e.getSource() == button[i]) {
					if (button[i].getIcon() == defaultIcon)
						button[i].setBackground(Color.BLUE);
				}
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {
			for (int i = 0; i < button.length; i++) {
				if (e.getSource() == button[i]) {
					button[i].setBackground(UIManager.getColor("control"));
				}
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void RandomNo() {

		for (int i = 0; i < button.length; i++) {
			saveNo.add(i + 1);
		}
		Collections.shuffle(saveNo);

	}

	public void returnImage(int i) {

		if (saveNo.get(i) == 1 || saveNo.get(i) == 9) {
			button[i].setIcon(bootstrapIcon);
			if (!pass) {
				if (firstClciked == null) {
					firstClciked = "Bootstrap";
					firstNo = i;
				} else {
					secondClciked = "Bootstrap";
				}
			}
		} else if (saveNo.get(i) == 2 || saveNo.get(i) == 10) {
			button[i].setIcon(cIcon);
			if (!pass) {
				if (firstClciked == null) {
					firstClciked = "C";
					firstNo = i;
				} else {
					secondClciked = "C";
				}
			}
		} else if (saveNo.get(i) == 3 || saveNo.get(i) == 11) {
			button[i].setIcon(html5Icon);
			if (!pass) {
				if (firstClciked == null) {
					firstClciked = "Html5";
					firstNo = i;
				} else {
					secondClciked = "Html5";
				}
			}
		} else if (saveNo.get(i) == 4 || saveNo.get(i) == 12) {
			button[i].setIcon(javaIcon);
			if (!pass) {
				if (firstClciked == null) {
					firstClciked = "Java";
					firstNo = i;
				} else {
					secondClciked = "Java";
				}
			}
		} else if (saveNo.get(i) == 5 || saveNo.get(i) == 13) {
			button[i].setIcon(phpIcon);
			if (!pass) {
				if (firstClciked == null) {
					firstClciked = "Php";
					firstNo = i;
				} else {
					secondClciked = "Php";
				}
			}
		} else if (saveNo.get(i) == 6 || saveNo.get(i) == 14) {
			button[i].setIcon(pythonIcon);
			if (!pass) {
				if (firstClciked == null) {
					firstClciked = "Python";
					firstNo = i;
				} else {
					secondClciked = "Python";
				}
			}
		} else if (saveNo.get(i) == 7 || saveNo.get(i) == 15) {
			button[i].setIcon(javaScriptIcon);
			if (!pass) {
				if (firstClciked == null) {
					firstClciked = "JavaScript";
					firstNo = i;
				} else {
					secondClciked = "JavaScript";
				}
			}
		} else if (saveNo.get(i) == 8 || saveNo.get(i) == 16) {
			button[i].setIcon(swiftIcon);
			if (!pass) {
				if (firstClciked == null) {
					firstClciked = "Swift";
					firstNo = i;
				} else {
					secondClciked = "Swift";
				}
			}
		}

	}

	public void checkTheGame() {
		threadCheck = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (firstClciked != null && secondClciked != null) {

						if (firstClciked != secondClciked) {

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							button[firstNo].setIcon(defaultIcon);
							button[secondNo].setIcon(defaultIcon);
						} else {
							doneNo.add(firstNo);
							doneNo.add(secondNo);
						}
						firstClciked = null;
						secondClciked = null;
					}

				}
			}
		});
	}

	public void RestartTheGame() {
		for (int i = 0; i < button.length; i++) {
			button[i].setIcon(defaultIcon);
		}
		Collections.shuffle(saveNo);
		mouseClciked = 0;
		repaint();
		firstClciked = null;
		secondClciked = null;
		hintUse = false;
		doneNo.clear();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getX() >= 124 && e.getY() >= 472) {

			if (e.getX() <= 188 && e.getY() <= 507) {
				RestartTheGame();

			}
		}
		if (!hintUse) {
			if(!onetime ){
				HintForWHile();
				threadForHint.start();
				onetime =true;
			}
			if (e.getX() >= 402 && e.getY() >= 472) {

				if (e.getX() <= 470 && e.getY() <= 507) {
					get =10;
					 get = JOptionPane.showConfirmDialog(null, "You Have Only one Chance To use Hint Option ",
							"Hint Option !", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, hintIcon);
					if (get == 0) {
						hintUse = true;
						hint = true;
					
						
					}
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

	public void HintForWHile() {
		threadForHint = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (hint && get==0) {
						mouseClicked = false;
						pass = true;
						for (int i = 0; i < button.length; i++) {
							returnImage(i);
						}
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for (int i = 0; i < button.length; i++) {

							button[i].setIcon(defaultIcon);
						}

						for (int j = 0; j < doneNo.size(); j++) {
							returnImage(doneNo.get(j));

						}
						pass = false;
						hint = false;
						mouseClicked = true;
						
						get =2;
					}

				}
			}
		});
	}

}
