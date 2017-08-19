package Simon;

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
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SimonJPanel extends JPanel implements KeyListener, ActionListener, MouseListener {
	public final int boxSize = 4;
	public SimonJButton button1;
	public SimonJButton button2;
	public SimonJButton button3;
	public SimonJButton button4;
	public Border defaultBorder;
	public int lvl = 1;
	public int cpuInput = 0;
	public int userInput = 0;
	public Random random;
	boolean pass = true;
	public Thread threadToStart;
	public boolean gameContinue =true;
	public ArrayList<Integer> arrayForCheck;
	public Icon badgeIcon;
	public Icon restartIcon;
	public int count;

	public SimonJPanel() {
		setPreferredSize(new Dimension(630, 710));
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 8));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		addMouseListener(new Listener());
		arrayForCheck = new ArrayList<>();
		random = new Random();
		GameStart();
		Images();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.drawRect(getWidth() / 2 - 240, getHeight() - 80, 130, 50);
		g.setColor(Color.WHITE);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 20));
		g.drawString("Step - " + userInput + "/" + cpuInput, getWidth() / 2 - 230, getHeight() - 50);

		g.setColor(Color.GRAY);
		g.drawRect(getWidth() / 2 - 40, getHeight() - 80, 80, 50);
		g.setColor(Color.WHITE);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 20));
		g.drawString("Lvl - " + lvl, getWidth() / 2 - 30, getHeight() - 50);

		g.setColor(Color.GRAY);
		g.drawRect(getWidth() - 200, getHeight() - 80, 110, 50);
		g.setColor(Color.WHITE);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 20));
		g.drawString("Restart", getWidth() - 185, getHeight() - 50);
	}

	public void Images() {
		badgeIcon = new ImageIcon("/home/sudoajay/Documents/workspace/SimonGUI/src/Images/Badge.png");
		restartIcon = new ImageIcon("/home/sudoajay/Documents/workspace/SimonGUI/src/Images/Restart.png");
	}

	public void GameStart() {

		button1 = new SimonJButton();
		button1.setPreferredSize(new Dimension(300, 300));
		button1.setBorder(null);
		button1.setBackground(Color.RED.darker());
		button1.setPressedBackgroundColor(Color.RED.brighter());
		button1.addActionListener(this);
		button1.addMouseListener(this);
		button1.setFocusable(false);
		button1.setVisible(true);
		defaultBorder = button1.getBorder();
		add(button1);

		button2 = new SimonJButton();
		button2.setPreferredSize(new Dimension(300, 300));
		button2.setBorder(null);
		button2.setBackground(Color.BLUE.darker());
		button2.setPressedBackgroundColor(Color.BLUE.brighter());
		button2.addActionListener(this);
		button2.addMouseListener(this);
		button2.setFocusable(false);
		button2.setVisible(true);
		add(button2);

		button3 = new SimonJButton();
		button3.setPreferredSize(new Dimension(300, 300));
		button3.setBorder(null);
		button3.setBackground(Color.GREEN.darker());
		button3.setPressedBackgroundColor(Color.GREEN.brighter());
		button3.addActionListener(this);
		button3.addMouseListener(this);
		button3.setFocusable(false);
		button3.setVisible(true);
		add(button3);

		button4 = new SimonJButton();
		button4.setPreferredSize(new Dimension(300, 300));
		button4.setBorder(null);
		button4.setBackground(Color.YELLOW.darker());
		button4.setPressedBackgroundColor(Color.YELLOW.brighter());
		button4.addActionListener(this);
		button4.addMouseListener(this);
		button4.setFocusable(false);
		button4.setVisible(true);

		add(button4);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (key == KeyEvent.VK_SPACE) {
			if (pass) {
				gameContinue = true;
				gameStart();
				threadToStart.start();

			}
			pass = false;
		}
		if(key ==KeyEvent.VK_R){
			GameRestart();
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
	public void actionPerformed(ActionEvent e) {
		if (!gameContinue) {
			if (checkWithButtonIsClicked(e.getSource()) != arrayForCheck.get(count)) {

				JOptionPane.showConfirmDialog(null, "Sorry You Lost Your Game \n   Your Lvl - " + lvl, "Try Again",
						JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_OPTION, badgeIcon);
				int getInput = JOptionPane.showConfirmDialog(null, "Are You Sure To Restart ?", "Restart The Game ",
						JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartIcon);
				if (getInput == 0) {
					GameRestart();
				} else {
					System.exit(0);
				}

			} else {
				count++;

			}
			userInput++;
			repaint();
			if (count == lvl) {
				lvl++;
				cpuInput = 0;
				userInput = 0;
				repaint();
				gameContinue = true;
				count = 0;
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (pass) {
			gameContinue = true;
			gameStart();
			threadToStart.start();

		}
		pass = false;
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		int get = checkWithButtonIsClicked(e.getSource());
		if (get == 1) {
			button1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		} else if (get == 2) {
			button2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		} else if (get == 3) {
			button3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		} else {
			button4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		int get = checkWithButtonIsClicked(e.getSource());
		if (get == 1) {
			button1.setBorder(defaultBorder);
		} else if (get == 2) {
			button2.setBorder(defaultBorder);
		} else if (get == 3) {
			button3.setBorder(defaultBorder);
		} else {
			button4.setBorder(defaultBorder);
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

	public int checkWithButtonIsClicked(Object e) {
		if (e == button1) {
			return 1;
		} else if (e == button2) {
			return 2;
		} else if (e == button3) {
			return 3;
		} else {
			return 4;
		}

	}
	public void GameRestart(){
		if (!gameContinue) {
		lvl = 1;
		count =0;
		cpuInput = 0;
		userInput=0;
		repaint();
		gameContinue = true;
		}
	}
	public void gameStart() {

		threadToStart = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (gameContinue) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						arrayForCheck.clear();
						for (int i = 0; i < lvl; i++) {
							int getNo = random.nextInt(4) + 1;
							arrayForCheck.add(getNo);
							
							if (getNo == 1) {
								button1.setBackground(Color.RED.brighter());
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								button1.setBackground(Color.RED.darker());

							} else if (getNo == 2) {
								button2.setBackground(Color.BLUE.brighter());
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								button2.setBackground(Color.BLUE.darker());

							} else if (getNo == 3) {
								button3.setBackground(Color.GREEN.brighter());
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								button3.setBackground(Color.GREEN.darker());

							} else {
								button4.setBackground(Color.YELLOW.brighter());
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								button4.setBackground(Color.YELLOW.darker());

							}
							cpuInput++;
							repaint();
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						gameContinue = false;
					}

				}
			}
		});
	}
	 class Listener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getX() >= 433 && e.getY() >=632 ){
				if(e.getX() <= 536 && e.getY() <= 676){
						GameRestart();
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
		 
	 }
	
}
