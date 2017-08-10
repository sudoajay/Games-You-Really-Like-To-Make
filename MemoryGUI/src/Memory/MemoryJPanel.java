package Memory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MemoryJPanel extends JPanel {

	public Random random;
	public ArrayList<Integer> getRandomNo;
	public int[] saveRandomNo;
	public String firstClick;
	public String secondClick;
	public int firstClickNo;
	public int secondClickNo;
	public Border defaultBorder;
	public Icon ajaxIcon;
	public Icon angularJSIcon;
	public Icon apacheIcon;
	public Icon bootstrapIcon;
	public Icon cIcon;
	public Icon c1Icon;
	public Icon c2Icon;
	public Icon css3Icon;
	public Icon html5Icon;
	public Icon javaIcon;
	public Icon javaScriptIcon;
	public Icon jqueryIcon;
	public Icon mysqlIcon;
	public Icon netIcon;
	public Icon phpIcon;
	public Icon pythonIcon;
	public Icon rubyIcon;
	public Icon swiftIcon;
	public Icon defaultIcon;
	public Icon restartIcon;
	public Icon mouseClick;
	public JButton btnNewButton;
	public JButton btnNewButton_1;
	public JButton btnNewButton_2;
	public JButton btnNewButton_3;
	public JButton btnNewButton_4;
	public JButton btnNewButton_5;
	public JButton btnNewButton_6;
	public JButton btnNewButton_7;
	public JButton btnNewButton_8;
	public JButton btnNewButton_9;
	public JButton btnNewButton_10;
	public JButton btnNewButton_11;
	public JButton btnNewButton_12;
	public JButton btnNewButton_13;
	public JButton btnNewButton_14;
	public JButton btnNewButton_15;
	public JButton btnNewButton_16;
	public JButton btnNewButton_17;
	public JButton btnNewButton_18;
	public JButton btnNewButton_19;
	public JButton btnNewButton_20;
	public JButton btnNewButton_21;
	public JButton btnNewButton_22;
	public JButton btnNewButton_23;
	public JButton btnNewButton_24;
	public JButton btnNewButton_25;
	public JButton btnNewButton_26;
	public JButton btnNewButton_27;
	public JButton btnNewButton_28;
	public JButton btnNewButton_29;
	public JButton btnNewButton_30;
	public JButton btnNewButton_31;
	public JButton btnNewButton_32;
	public JButton btnNewButton_33;
	public JButton btnNewButton_34;
	public JButton btnNewButton_35;
	public Thread checkThread;
	public int mouseClciked;

	public MemoryJPanel() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(635, 466));
		setLayout(null);
		addKeyListener(new Listener());
		setFocusable(true);
		random = new Random();
		getRandomNo = new ArrayList<>();
		saveRandomNo = new int[36];
		allImages();
		getRandomNo();
		GameOn();

	}

	public void allImages() {
		try {
			ajaxIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/AjaxIcon.png");
			angularJSIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/AngularJSIcon.png");
			apacheIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/ApacheIcon.png");
			bootstrapIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/BootstrapIcon.png");
			cIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/CIcon.png");
			c1Icon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/C++Icon.png");
			c2Icon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/C#Icon.png");
			css3Icon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/Css3Icon.png");
			html5Icon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/Html5Icon.png");
			javaIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/JavaIcon.png");
			javaScriptIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/JavaScriptIcon.png");
			jqueryIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/JqueryIcon.png");
			mysqlIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/MysqlIcon.png");
			netIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/NetIcon.png");
			phpIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/PhpIcon.png");
			pythonIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/PythonIcon.png");
			rubyIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/RubyIcon.png");
			swiftIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/SwiftIcon.png");
			restartIcon = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/Restart.png");
			mouseClick = new ImageIcon("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/MouseClick.png");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void GameOn() {

		btnNewButton = new JButton("");
		btnNewButton.setBounds(12, 20, 100, 70);
		defaultIcon = btnNewButton.getIcon();
		defaultBorder = btnNewButton.getBorder();
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton.getIcon() == defaultIcon) {

					getIcon(getIconName(0), 0);
					if (firstClick == null) {
						firstClick = getIconName(0);
						firstClickNo = 0;
					} else {
						secondClick = getIconName(0);
						secondClickNo = 0;
					}
					CheckForMatching();
				}

			}

		});
		btnNewButton.addMouseListener(new Listener());
		add(btnNewButton);

		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(114, 20, 100, 70);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_1.getIcon() == defaultIcon) {
					getIcon(getIconName(1), 1);
					if (firstClick == null) {
						firstClick = getIconName(1);
						firstClickNo = 1;
					} else {
						secondClick = getIconName(1);
						secondClickNo = 1;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_1.addMouseListener(new Listener());
		add(btnNewButton_1);

		btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(216, 20, 100, 70);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_2.getIcon() == defaultIcon) {
					getIcon(getIconName(2), 2);
					if (firstClick == null) {
						firstClick = getIconName(2);
						firstClickNo = 2;
					} else {
						secondClick = getIconName(2);
						secondClickNo = 2;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_2.addMouseListener(new Listener());
		add(btnNewButton_2);

		btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(318, 20, 100, 70);
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_3.getIcon() == defaultIcon) {
					getIcon(getIconName(3), 3);
					if (firstClick == null) {
						firstClick = getIconName(3);
						firstClickNo = 3;
					} else {
						secondClick = getIconName(3);
						secondClickNo = 3;

					}
					CheckForMatching();

				}

			}
		});
		btnNewButton_3.addMouseListener(new Listener());
		add(btnNewButton_3);

		btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(420, 20, 100, 70);
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_4.getIcon() == defaultIcon) {
					getIcon(getIconName(4), 4);
					if (firstClick == null) {
						firstClick = getIconName(4);
						firstClickNo = 4;
					} else {
						secondClick = getIconName(4);
						secondClickNo = 4;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_4.addMouseListener(new Listener());
		add(btnNewButton_4);

		btnNewButton_5 = new JButton("");
		btnNewButton_5.setBounds(522, 20, 100, 70);
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_5.getIcon() == defaultIcon) {
					getIcon(getIconName(5), 5);
					if (firstClick == null) {
						firstClick = getIconName(5);
						firstClickNo = 5;
					} else {
						secondClick = getIconName(5);
						secondClickNo = 5;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_5.addMouseListener(new Listener());
		add(btnNewButton_5);

		btnNewButton_6 = new JButton("");
		btnNewButton_6.setBounds(12, 92, 100, 70);
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_6.getIcon() == defaultIcon) {
					getIcon(getIconName(6), 6);
					if (firstClick == null) {
						firstClick = getIconName(6);
						firstClickNo = 6;
					} else {
						secondClick = getIconName(6);
						secondClickNo = 6;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_6.addMouseListener(new Listener());
		add(btnNewButton_6);

		btnNewButton_7 = new JButton("");
		btnNewButton_7.setBounds(12, 164, 100, 70);
		btnNewButton_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_7.getIcon() == defaultIcon) {
					getIcon(getIconName(7), 7);
					if (firstClick == null) {
						firstClick = getIconName(7);
						firstClickNo = 7;
					} else {
						secondClick = getIconName(7);
						secondClickNo = 7;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_7.addMouseListener(new Listener());
		add(btnNewButton_7);

		btnNewButton_8 = new JButton("");
		btnNewButton_8.setBounds(12, 236, 100, 70);
		btnNewButton_8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_8.getIcon() == defaultIcon) {
					getIcon(getIconName(8), 8);
					if (firstClick == null) {
						firstClick = getIconName(8);
						firstClickNo = 8;
					} else {
						secondClick = getIconName(8);
						secondClickNo = 8;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_8.addMouseListener(new Listener());
		add(btnNewButton_8);

		btnNewButton_9 = new JButton("");
		btnNewButton_9.setBounds(12, 380, 100, 70);
		btnNewButton_9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_9.getIcon() == defaultIcon) {
					getIcon(getIconName(9), 9);
					if (firstClick == null) {
						firstClick = getIconName(9);
						firstClickNo = 9;
					} else {
						secondClick = getIconName(9);
						secondClickNo = 9;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_9.addMouseListener(new Listener());
		add(btnNewButton_9);

		btnNewButton_10 = new JButton("");
		btnNewButton_10.setBounds(12, 308, 100, 70);
		btnNewButton_10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_10.getIcon() == defaultIcon) {
					getIcon(getIconName(10), 10);
					if (firstClick == null) {
						firstClick = getIconName(10);
						firstClickNo = 10;
					} else {
						secondClick = getIconName(10);
						secondClickNo = 10;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_10.addMouseListener(new Listener());
		add(btnNewButton_10);

		btnNewButton_11 = new JButton("");
		btnNewButton_11.setBounds(114, 92, 100, 70);
		btnNewButton_11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_11.getIcon() == defaultIcon) {
					getIcon(getIconName(11), 11);
					if (firstClick == null) {
						firstClick = getIconName(11);
						firstClickNo = 11;
					} else {
						secondClick = getIconName(11);
						secondClickNo = 11;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_11.addMouseListener(new Listener());
		add(btnNewButton_11);

		btnNewButton_12 = new JButton("");
		btnNewButton_12.setBounds(216, 92, 100, 70);
		btnNewButton_12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_12.getIcon() == defaultIcon) {
					getIcon(getIconName(12), 12);
					if (firstClick == null) {
						firstClick = getIconName(12);
						firstClickNo = 12;
					} else {
						secondClick = getIconName(12);
						secondClickNo = 12;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_12.addMouseListener(new Listener());
		add(btnNewButton_12);

		btnNewButton_13 = new JButton("");
		btnNewButton_13.setBounds(318, 92, 100, 70);
		btnNewButton_13.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_13.getIcon() == defaultIcon) {
					getIcon(getIconName(13), 13);
					if (firstClick == null) {
						firstClick = getIconName(13);
						firstClickNo = 13;
					} else {
						secondClick = getIconName(13);
						secondClickNo = 13;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_13.addMouseListener(new Listener());
		add(btnNewButton_13);

		btnNewButton_14 = new JButton("");
		btnNewButton_14.setBounds(420, 92, 100, 70);
		btnNewButton_14.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_14.getIcon() == defaultIcon) {
					getIcon(getIconName(14), 14);
					if (firstClick == null) {
						firstClick = getIconName(14);
						firstClickNo = 14;
					} else {
						secondClick = getIconName(14);
						secondClickNo = 14;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_14.addMouseListener(new Listener());
		add(btnNewButton_14);

		btnNewButton_15 = new JButton("");
		btnNewButton_15.setBounds(522, 92, 100, 70);
		btnNewButton_15.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_15.getIcon() == defaultIcon) {
					getIcon(getIconName(15), 15);
					if (firstClick == null) {
						firstClick = getIconName(15);
						firstClickNo = 15;
					} else {
						secondClick = getIconName(15);
						secondClickNo = 15;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_15.addMouseListener(new Listener());
		add(btnNewButton_15);

		btnNewButton_16 = new JButton("");
		btnNewButton_16.setBounds(114, 164, 100, 70);
		btnNewButton_16.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_16.getIcon() == defaultIcon) {
					getIcon(getIconName(16), 16);
					if (firstClick == null) {
						firstClick = getIconName(16);
						firstClickNo = 16;
					} else {
						secondClick = getIconName(16);
						secondClickNo = 16;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_16.addMouseListener(new Listener());
		add(btnNewButton_16);

		btnNewButton_17 = new JButton("");
		btnNewButton_17.setBounds(114, 236, 100, 70);
		btnNewButton_17.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_17.getIcon() == defaultIcon) {
					getIcon(getIconName(17), 17);
					if (firstClick == null) {
						firstClick = getIconName(17);
						firstClickNo = 17;
					} else {
						secondClick = getIconName(17);
						secondClickNo = 17;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_17.addMouseListener(new Listener());
		add(btnNewButton_17);

		btnNewButton_18 = new JButton("");
		btnNewButton_18.setBounds(114, 308, 100, 70);
		btnNewButton_18.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_18.getIcon() == defaultIcon) {
					getIcon(getIconName(18), 18);
					if (firstClick == null) {
						firstClick = getIconName(18);
						firstClickNo = 18;
					} else {
						secondClick = getIconName(18);
						secondClickNo = 18;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_18.addMouseListener(new Listener());
		add(btnNewButton_18);

		btnNewButton_19 = new JButton("");
		btnNewButton_19.setBounds(114, 380, 100, 70);
		btnNewButton_19.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_19.getIcon() == defaultIcon) {
					getIcon(getIconName(19), 19);
					if (firstClick == null) {
						firstClick = getIconName(19);
						firstClickNo = 19;
					} else {
						secondClick = getIconName(19);
						secondClickNo = 19;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_19.addMouseListener(new Listener());
		add(btnNewButton_19);

		btnNewButton_20 = new JButton("");
		btnNewButton_20.setBounds(216, 164, 100, 70);
		btnNewButton_20.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_20.getIcon() == defaultIcon) {
					getIcon(getIconName(20), 20);
					if (firstClick == null) {
						firstClick = getIconName(20);
						firstClickNo = 20;
					} else {
						secondClick = getIconName(20);
						secondClickNo = 20;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_20.addMouseListener(new Listener());
		add(btnNewButton_20);

		btnNewButton_21 = new JButton("");
		btnNewButton_21.setBounds(318, 164, 100, 70);
		btnNewButton_21.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_21.getIcon() == defaultIcon) {
					getIcon(getIconName(21), 21);
					if (firstClick == null) {
						firstClick = getIconName(21);
						firstClickNo = 21;
					} else {
						secondClick = getIconName(21);
						secondClickNo = 21;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_21.addMouseListener(new Listener());
		add(btnNewButton_21);

		btnNewButton_22 = new JButton("");
		btnNewButton_22.setBounds(420, 164, 100, 70);
		btnNewButton_22.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_22.getIcon() == defaultIcon) {
					getIcon(getIconName(22), 22);
					if (firstClick == null) {
						firstClick = getIconName(22);
						firstClickNo = 22;
					} else {
						secondClick = getIconName(22);
						secondClickNo = 22;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_22.addMouseListener(new Listener());
		add(btnNewButton_22);

		btnNewButton_23 = new JButton("");
		btnNewButton_23.setBounds(522, 164, 100, 70);
		btnNewButton_23.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_23.getIcon() == defaultIcon) {
					getIcon(getIconName(23), 23);
					if (firstClick == null) {
						firstClick = getIconName(23);
						firstClickNo = 23;
					} else {
						secondClick = getIconName(23);
						secondClickNo = 23;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_23.addMouseListener(new Listener());
		add(btnNewButton_23);

		btnNewButton_24 = new JButton("");
		btnNewButton_24.setBounds(216, 236, 100, 70);
		btnNewButton_24.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_24.getIcon() == defaultIcon) {
					getIcon(getIconName(24), 24);
					if (firstClick == null) {
						firstClick = getIconName(24);
						firstClickNo = 24;
					} else {
						secondClick = getIconName(24);
						secondClickNo = 24;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_24.addMouseListener(new Listener());
		add(btnNewButton_24);

		btnNewButton_25 = new JButton("");
		btnNewButton_25.setBounds(216, 308, 100, 70);
		btnNewButton_25.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_25.getIcon() == defaultIcon) {
					getIcon(getIconName(25), 25);
					if (firstClick == null) {
						firstClick = getIconName(25);
						firstClickNo = 25;
					} else {
						secondClick = getIconName(25);
						secondClickNo = 25;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_25.addMouseListener(new Listener());
		add(btnNewButton_25);

		btnNewButton_26 = new JButton("");
		btnNewButton_26.setBounds(216, 380, 100, 70);
		btnNewButton_26.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_26.getIcon() == defaultIcon) {
					getIcon(getIconName(26), 26);
					if (firstClick == null) {
						firstClick = getIconName(26);
						firstClickNo = 26;
					} else {
						secondClick = getIconName(26);
						secondClickNo = 26;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_26.addMouseListener(new Listener());
		add(btnNewButton_26);

		btnNewButton_27 = new JButton("");
		btnNewButton_27.setBounds(318, 236, 100, 70);
		btnNewButton_27.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_27.getIcon() == defaultIcon) {
					getIcon(getIconName(27), 27);
					if (firstClick == null) {
						firstClick = getIconName(27);
						firstClickNo = 27;
					} else {
						secondClick = getIconName(27);
						secondClickNo = 27;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_27.addMouseListener(new Listener());
		add(btnNewButton_27);

		btnNewButton_28 = new JButton("");
		btnNewButton_28.setBounds(420, 236, 100, 70);
		btnNewButton_28.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_28.getIcon() == defaultIcon) {
					getIcon(getIconName(28), 28);
					if (firstClick == null) {
						firstClick = getIconName(28);
						firstClickNo = 28;
					} else {
						secondClick = getIconName(28);
						secondClickNo = 28;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_28.addMouseListener(new Listener());
		add(btnNewButton_28);

		btnNewButton_29 = new JButton("");
		btnNewButton_29.setBounds(522, 236, 100, 70);
		btnNewButton_29.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_29.getIcon() == defaultIcon) {
					getIcon(getIconName(29), 29);
					if (firstClick == null) {
						firstClick = getIconName(29);
						firstClickNo = 29;
					} else {
						secondClick = getIconName(29);
						secondClickNo = 29;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_29.addMouseListener(new Listener());
		add(btnNewButton_29);

		btnNewButton_30 = new JButton("");
		btnNewButton_30.setBounds(318, 308, 100, 70);
		btnNewButton_30.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_30.getIcon() == defaultIcon) {
					getIcon(getIconName(30), 30);
					if (firstClick == null) {
						firstClick = getIconName(30);
						firstClickNo = 30;
					} else {
						secondClick = getIconName(30);
						secondClickNo = 30;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_30.addMouseListener(new Listener());
		add(btnNewButton_30);

		btnNewButton_31 = new JButton("");
		btnNewButton_31.setBounds(318, 380, 100, 70);
		btnNewButton_31.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_31.getIcon() == defaultIcon) {
					getIcon(getIconName(31), 31);
					if (firstClick == null) {
						firstClick = getIconName(31);
						firstClickNo = 31;
					} else {
						secondClick = getIconName(31);
						secondClickNo = 31;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_31.addMouseListener(new Listener());
		add(btnNewButton_31);

		btnNewButton_32 = new JButton("");
		btnNewButton_32.setBounds(420, 308, 100, 70);
		btnNewButton_32.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_32.getIcon() == defaultIcon) {
					getIcon(getIconName(32), 32);
					if (firstClick == null) {
						firstClick = getIconName(32);
						firstClickNo = 32;
					} else {
						secondClick = getIconName(32);
						secondClickNo = 32;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_32.addMouseListener(new Listener());
		add(btnNewButton_32);

		btnNewButton_33 = new JButton("");
		btnNewButton_33.setBounds(522, 308, 100, 70);
		btnNewButton_33.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_33.getIcon() == defaultIcon) {
					getIcon(getIconName(33), 33);
					if (firstClick == null) {
						firstClick = getIconName(33);
						firstClickNo = 33;
					} else {
						secondClick = getIconName(33);
						secondClickNo = 33;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_33.addMouseListener(new Listener());
		add(btnNewButton_33);

		btnNewButton_34 = new JButton("");
		btnNewButton_34.setBounds(420, 380, 100, 70);
		btnNewButton_34.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_34.getIcon() == defaultIcon) {
					getIcon(getIconName(34), 34);
					if (firstClick == null) {
						firstClick = getIconName(34);
						firstClickNo = 34;
					} else {
						secondClick = getIconName(34);
						secondClickNo = 34;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_34.addMouseListener(new Listener());
		add(btnNewButton_34);

		btnNewButton_35 = new JButton("");
		btnNewButton_35.setBounds(522, 380, 100, 70);
		btnNewButton_35.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_35.getIcon() == defaultIcon) {
					getIcon(getIconName(35), 35);
					if (firstClick == null) {
						firstClick = getIconName(35);
						firstClickNo = 35;
					} else {
						secondClick = getIconName(35);
						secondClickNo = 35;

					}
					CheckForMatching();

				}
			}
		});
		btnNewButton_35.addMouseListener(new Listener());
		add(btnNewButton_35);
	}

	public void getIcon(String iconName, int no) {

		if (iconName.equalsIgnoreCase("ajaxIcon")) {
			WhichIcon(ajaxIcon, no);

		} else if (iconName.equalsIgnoreCase("angularJSIcon"))
			WhichIcon(angularJSIcon, no);

		else if (iconName.equalsIgnoreCase("apacheIcon"))
			WhichIcon(apacheIcon, no);

		else if (iconName.equalsIgnoreCase("bootstrapIcon"))
			WhichIcon(bootstrapIcon, no);

		else if (iconName.equalsIgnoreCase("cIcon"))
			WhichIcon(cIcon, no);

		else if (iconName.equalsIgnoreCase("c1Icon"))
			WhichIcon(c1Icon, no);

		else if (iconName.equalsIgnoreCase("css3Icon"))
			WhichIcon(css3Icon, no);

		else if (iconName.equalsIgnoreCase("html5Icon"))
			WhichIcon(html5Icon, no);

		else if (iconName.equalsIgnoreCase("javaIcon"))
			WhichIcon(javaIcon, no);

		else if (iconName.equalsIgnoreCase("javaScriptIcon"))
			WhichIcon(javaScriptIcon, no);

		else if (iconName.equalsIgnoreCase("jqueryIcon"))
			WhichIcon(jqueryIcon, no);

		else if (iconName.equalsIgnoreCase("mysqlIcon"))
			WhichIcon(mysqlIcon, no);

		else if (iconName.equalsIgnoreCase("netIcon"))
			WhichIcon(netIcon, no);

		else if (iconName.equalsIgnoreCase("phpIcon"))
			WhichIcon(phpIcon, no);

		else if (iconName.equalsIgnoreCase("pythonIcon"))
			WhichIcon(pythonIcon, no);

		else if (iconName.equalsIgnoreCase("rubyIcon"))
			WhichIcon(rubyIcon, no);

		else if (iconName.equalsIgnoreCase("c2Icon"))
			WhichIcon(c2Icon, no);

		else if (iconName.equalsIgnoreCase("swiftIcon"))
			WhichIcon(swiftIcon, no);

	}

	public void WhichIcon(Icon iconName, int no) {
		if (no == 0) {
			btnNewButton.setIcon(iconName);
		} else if (no == 1) {
			btnNewButton_1.setIcon(iconName);
		} else if (no == 2) {
			btnNewButton_2.setIcon(iconName);
		} else if (no == 3) {
			btnNewButton_3.setIcon(iconName);
		} else if (no == 4) {
			btnNewButton_4.setIcon(iconName);
		} else if (no == 5) {
			btnNewButton_5.setIcon(iconName);
		} else if (no == 6) {
			btnNewButton_6.setIcon(iconName);
		} else if (no == 7) {
			btnNewButton_7.setIcon(iconName);
		} else if (no == 8) {
			btnNewButton_8.setIcon(iconName);
		} else if (no == 9) {
			btnNewButton_9.setIcon(iconName);
		} else if (no == 10) {
			btnNewButton_10.setIcon(iconName);
		} else if (no == 11) {
			btnNewButton_11.setIcon(iconName);
		} else if (no == 12) {
			btnNewButton_12.setIcon(iconName);
		} else if (no == 13) {
			btnNewButton_13.setIcon(iconName);
		} else if (no == 14) {
			btnNewButton_14.setIcon(iconName);
		} else if (no == 15) {
			btnNewButton_15.setIcon(iconName);
		} else if (no == 16) {
			btnNewButton_16.setIcon(iconName);
		} else if (no == 17) {
			btnNewButton_17.setIcon(iconName);
		} else if (no == 18) {
			btnNewButton_18.setIcon(iconName);
		} else if (no == 19) {
			btnNewButton_19.setIcon(iconName);
		} else if (no == 20) {
			btnNewButton_20.setIcon(iconName);
		} else if (no == 21) {
			btnNewButton_21.setIcon(iconName);
		} else if (no == 22) {
			btnNewButton_22.setIcon(iconName);
		} else if (no == 23) {
			btnNewButton_23.setIcon(iconName);
		} else if (no == 24) {
			btnNewButton_24.setIcon(iconName);
		} else if (no == 25) {
			btnNewButton_25.setIcon(iconName);
		} else if (no == 26) {
			btnNewButton_26.setIcon(iconName);
		} else if (no == 27) {
			btnNewButton_27.setIcon(iconName);
		} else if (no == 28) {
			btnNewButton_28.setIcon(iconName);
		} else if (no == 29) {
			btnNewButton_29.setIcon(iconName);
		} else if (no == 30) {
			btnNewButton_30.setIcon(iconName);
		} else if (no == 31) {
			btnNewButton_31.setIcon(iconName);
		} else if (no == 32) {
			btnNewButton_32.setIcon(iconName);
		} else if (no == 33) {
			btnNewButton_33.setIcon(iconName);
		} else if (no == 34) {
			btnNewButton_34.setIcon(iconName);
		} else if (no == 35) {
			btnNewButton_35.setIcon(iconName);
		}

	}

	public void getRandomNo() {

		for (int i = 1; i < 37; i++) {
			getRandomNo.add(i);
		}
		Collections.shuffle(getRandomNo);

		for (int i = 0; i < saveRandomNo.length; i++) {
			saveRandomNo[i] = getRandomNo.get(i);
		}

	}

	public String getIconName(int no) {
		String retunBack = null;

		if (saveRandomNo[no] == 1 || saveRandomNo[no] == 19)
			retunBack = "AjaxIcon";

		else if (saveRandomNo[no] == 2 || saveRandomNo[no] == 20)
			retunBack = "AngularJSIcon";
		else if (saveRandomNo[no] == 3 || saveRandomNo[no] == 21)
			retunBack = "ApacheIcon";
		else if (saveRandomNo[no] == 4 || saveRandomNo[no] == 22)
			retunBack = "BootstrapIcon";
		else if (saveRandomNo[no] == 5 || saveRandomNo[no] == 23)
			retunBack = "CIcon";
		else if (saveRandomNo[no] == 6 || saveRandomNo[no] == 24)
			retunBack = "C1Icon";
		else if (saveRandomNo[no] == 7 || saveRandomNo[no] == 25)
			retunBack = "Css3Icon";
		else if (saveRandomNo[no] == 8 || saveRandomNo[no] == 26)
			retunBack = "Html5Icon";
		else if (saveRandomNo[no] == 9 || saveRandomNo[no] == 27)
			retunBack = "JavaIcon";
		else if (saveRandomNo[no] == 10 || saveRandomNo[no] == 28)
			retunBack = "JavaScriptIcon";
		else if (saveRandomNo[no] == 11 || saveRandomNo[no] == 29)
			retunBack = "JqueryIcon";
		else if (saveRandomNo[no] == 12 || saveRandomNo[no] == 30)
			retunBack = "MysqlIcon";
		else if (saveRandomNo[no] == 13 || saveRandomNo[no] == 31)
			retunBack = "NetIcon";
		else if (saveRandomNo[no] == 14 || saveRandomNo[no] == 32)
			retunBack = "PhpIcon";
		else if (saveRandomNo[no] == 15 || saveRandomNo[no] == 33)
			retunBack = "PythonIcon";
		else if (saveRandomNo[no] == 16 || saveRandomNo[no] == 34)
			retunBack = "RubyIcon";
		else if (saveRandomNo[no] == 17 || saveRandomNo[no] == 35)
			retunBack = "C2Icon";
		else if (saveRandomNo[no] == 18 || saveRandomNo[no] == 36)
			retunBack = "SwiftIcon";

		return retunBack;
	}

	public void CheckForMatching() {
		boolean loop = true;
		checkThread = new Thread(new Runnable() {

			@Override
			public synchronized void run() {

				while (loop) {
					if (firstClick != null && secondClick != null) {

						if (firstClick != secondClick) {
							try {
								Thread.sleep(150);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (firstClickNo == 0 || secondClickNo == 0)
								btnNewButton.setIcon(defaultIcon);
							if (firstClickNo == 1 || secondClickNo == 1)
								btnNewButton_1.setIcon(defaultIcon);
							if (firstClickNo == 2 || secondClickNo == 2)
								btnNewButton_2.setIcon(defaultIcon);
							if (firstClickNo == 3 || secondClickNo == 3)
								btnNewButton_3.setIcon(defaultIcon);
							if (firstClickNo == 4 || secondClickNo == 4)
								btnNewButton_4.setIcon(defaultIcon);
							if (firstClickNo == 5 || secondClickNo == 5)
								btnNewButton_5.setIcon(defaultIcon);
							if (firstClickNo == 6 || secondClickNo == 6)
								btnNewButton_6.setIcon(defaultIcon);
							if (firstClickNo == 7 || secondClickNo == 7)
								btnNewButton_7.setIcon(defaultIcon);
							if (firstClickNo == 8 || secondClickNo == 8)
								btnNewButton_8.setIcon(defaultIcon);
							if (firstClickNo == 9 || secondClickNo == 9)
								btnNewButton_9.setIcon(defaultIcon);
							if (firstClickNo == 10 || secondClickNo == 10)
								btnNewButton_10.setIcon(defaultIcon);
							if (firstClickNo == 11 || secondClickNo == 11)
								btnNewButton_11.setIcon(defaultIcon);
							if (firstClickNo == 12 || secondClickNo == 12)
								btnNewButton_12.setIcon(defaultIcon);
							if (firstClickNo == 13 || secondClickNo == 13)
								btnNewButton_13.setIcon(defaultIcon);
							if (firstClickNo == 14 || secondClickNo == 14)
								btnNewButton_14.setIcon(defaultIcon);
							if (firstClickNo == 15 || secondClickNo == 15)
								btnNewButton_15.setIcon(defaultIcon);
							if (firstClickNo == 16 || secondClickNo == 16)
								btnNewButton_16.setIcon(defaultIcon);
							if (firstClickNo == 17 || secondClickNo == 17)
								btnNewButton_17.setIcon(defaultIcon);
							if (firstClickNo == 18 || secondClickNo == 18)
								btnNewButton_18.setIcon(defaultIcon);
							if (firstClickNo == 19 || secondClickNo == 19)
								btnNewButton_19.setIcon(defaultIcon);
							if (firstClickNo == 20 || secondClickNo == 20)
								btnNewButton_20.setIcon(defaultIcon);
							if (firstClickNo == 21 || secondClickNo == 21)
								btnNewButton_21.setIcon(defaultIcon);
							if (firstClickNo == 22 || secondClickNo == 22)
								btnNewButton_22.setIcon(defaultIcon);
							if (firstClickNo == 23 || secondClickNo == 23)
								btnNewButton_23.setIcon(defaultIcon);
							if (firstClickNo == 24 || secondClickNo == 24)
								btnNewButton_24.setIcon(defaultIcon);
							if (firstClickNo == 25 || secondClickNo == 25)
								btnNewButton_25.setIcon(defaultIcon);
							if (firstClickNo == 26 || secondClickNo == 26)
								btnNewButton_26.setIcon(defaultIcon);
							if (firstClickNo == 27 || secondClickNo == 27)
								btnNewButton_27.setIcon(defaultIcon);
							if (firstClickNo == 28 || secondClickNo == 28)
								btnNewButton_28.setIcon(defaultIcon);
							if (firstClickNo == 29 || secondClickNo == 29)
								btnNewButton_29.setIcon(defaultIcon);
							if (firstClickNo == 30 || secondClickNo == 30)
								btnNewButton_30.setIcon(defaultIcon);
							if (firstClickNo == 31 || secondClickNo == 31)
								btnNewButton_31.setIcon(defaultIcon);
							if (firstClickNo == 32 || secondClickNo == 32)
								btnNewButton_32.setIcon(defaultIcon);
							if (firstClickNo == 33 || secondClickNo == 33)
								btnNewButton_33.setIcon(defaultIcon);
							if (firstClickNo == 34 || secondClickNo == 34)
								btnNewButton_34.setIcon(defaultIcon);
							if (firstClickNo == 35 || secondClickNo == 35)
								btnNewButton_35.setIcon(defaultIcon);

							firstClick = null;
							secondClick = null;

						} else {
							firstClickNo = 50;
							secondClickNo = 50;
							try {
								Thread.sleep(150);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
	}

	public void RestartTheGame() {

		btnNewButton.setIcon(defaultIcon);
		btnNewButton_1.setIcon(defaultIcon);
		btnNewButton_2.setIcon(defaultIcon);
		btnNewButton_3.setIcon(defaultIcon);
		btnNewButton_4.setIcon(defaultIcon);
		btnNewButton_5.setIcon(defaultIcon);
		btnNewButton_6.setIcon(defaultIcon);
		btnNewButton_7.setIcon(defaultIcon);
		btnNewButton_8.setIcon(defaultIcon);
		btnNewButton_9.setIcon(defaultIcon);
		btnNewButton_10.setIcon(defaultIcon);
		btnNewButton_11.setIcon(defaultIcon);
		btnNewButton_12.setIcon(defaultIcon);
		btnNewButton_13.setIcon(defaultIcon);
		btnNewButton_14.setIcon(defaultIcon);
		btnNewButton_15.setIcon(defaultIcon);
		btnNewButton_16.setIcon(defaultIcon);
		btnNewButton_17.setIcon(defaultIcon);
		btnNewButton_18.setIcon(defaultIcon);
		btnNewButton_19.setIcon(defaultIcon);
		btnNewButton_20.setIcon(defaultIcon);
		btnNewButton_21.setIcon(defaultIcon);
		btnNewButton_22.setIcon(defaultIcon);
		btnNewButton_23.setIcon(defaultIcon);
		btnNewButton_24.setIcon(defaultIcon);
		btnNewButton_25.setIcon(defaultIcon);
		btnNewButton_26.setIcon(defaultIcon);
		btnNewButton_27.setIcon(defaultIcon);
		btnNewButton_28.setIcon(defaultIcon);
		btnNewButton_29.setIcon(defaultIcon);
		btnNewButton_30.setIcon(defaultIcon);
		btnNewButton_31.setIcon(defaultIcon);
		btnNewButton_32.setIcon(defaultIcon);
		btnNewButton_33.setIcon(defaultIcon);
		btnNewButton_34.setIcon(defaultIcon);
		btnNewButton_35.setIcon(defaultIcon);
	};

	class Listener implements KeyListener, MouseListener {
		boolean dontRepeat = true;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
			if (key == KeyEvent.VK_R) {
				int get = JOptionPane.showConfirmDialog(null, "Are You Sure To Restart ?", "Restart The Game ",
						JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartIcon);
				if (get == 0)
					RestartTheGame();

			}

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			mouseClciked += 1;
			if (dontRepeat) {
				checkThread.start();
			}
			dontRepeat = false;
			if (defaultIcon != btnNewButton.getIcon() && defaultIcon != btnNewButton_1.getIcon()) {

				if (defaultIcon != btnNewButton_2.getIcon() && defaultIcon != btnNewButton_3.getIcon()) {

					if (defaultIcon != btnNewButton_4.getIcon() && defaultIcon != btnNewButton_5.getIcon()) {

						if (defaultIcon != btnNewButton_6.getIcon() && defaultIcon != btnNewButton_7.getIcon()) {

							if (defaultIcon != btnNewButton_8.getIcon() && defaultIcon != btnNewButton_9.getIcon()) {

								if (defaultIcon != btnNewButton_10.getIcon() && defaultIcon != btnNewButton_11.getIcon()) {

									if (defaultIcon != btnNewButton_12.getIcon() && defaultIcon != btnNewButton_13.getIcon()) {

										if (defaultIcon != btnNewButton_14.getIcon() && defaultIcon != btnNewButton_15.getIcon()) {

											if (defaultIcon != btnNewButton_16.getIcon() && defaultIcon != btnNewButton_17.getIcon()) {

												if (defaultIcon != btnNewButton_18.getIcon()
														&& defaultIcon != btnNewButton_19.getIcon()) {

													if (defaultIcon != btnNewButton_20.getIcon()
															&& defaultIcon != btnNewButton_21.getIcon()) {

														if (defaultIcon != btnNewButton_22.getIcon()
																&& defaultIcon != btnNewButton_23.getIcon()) {

															if (defaultIcon != btnNewButton_24.getIcon()
																	&& defaultIcon != btnNewButton_25.getIcon()) {

																if (defaultIcon != btnNewButton_26.getIcon()
																		&& defaultIcon != btnNewButton_27.getIcon()) {

																	if (defaultIcon != btnNewButton_28.getIcon()
																			&& defaultIcon != btnNewButton_29.getIcon()) {

																		if (defaultIcon != btnNewButton_30.getIcon()
																				&& defaultIcon != btnNewButton_31.getIcon()) {

																			if (defaultIcon != btnNewButton_32.getIcon() && e
																					.getSource() != btnNewButton_33.getIcon()) {

																				if (defaultIcon != btnNewButton_34.getIcon()
																						&& defaultIcon != btnNewButton_35.getIcon()) {
																					JOptionPane.showConfirmDialog(null,
																							"Your Mouse Clicked  - "
																									+ mouseClciked,
																							"Score Board",
																							JOptionPane.WARNING_MESSAGE,
																							JOptionPane.YES_OPTION,
																							mouseClick);
																					
																					int getInput = JOptionPane
																							.showConfirmDialog(null,
																									"Are You Sure To Restart ?",
																									"Restart The Game ",
																									JOptionPane.WARNING_MESSAGE,
																									JOptionPane.YES_NO_OPTION,
																									restartIcon);
																					if (getInput == 0)
																						RestartTheGame();
																					else{
																						System.exit(0);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == btnNewButton)
				btnNewButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_1)
				btnNewButton_1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_2)
				btnNewButton_2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_3)
				btnNewButton_3.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_4)
				btnNewButton_4.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_5)
				btnNewButton_5.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_6)
				btnNewButton_6.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_7)
				btnNewButton_7.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_8)
				btnNewButton_8.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_9)
				btnNewButton_9.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_10)
				btnNewButton_10.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_11)
				btnNewButton_11.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_12)
				btnNewButton_12.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_13)
				btnNewButton_13.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_14)
				btnNewButton_14.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_15)
				btnNewButton_15.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_16)
				btnNewButton_16.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_17)
				btnNewButton_17.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_18)
				btnNewButton_18.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_19)
				btnNewButton_19.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_20)
				btnNewButton_20.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_21)
				btnNewButton_21.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_22)
				btnNewButton_22.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_23)
				btnNewButton_23.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_24)
				btnNewButton_24.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_25)
				btnNewButton_25.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_26)
				btnNewButton_26.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_27)
				btnNewButton_27.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_28)
				btnNewButton_28.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_29)
				btnNewButton_29.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_30)
				btnNewButton_30.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_31)
				btnNewButton_31.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_32)
				btnNewButton_32.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_33)
				btnNewButton_33.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_34)
				btnNewButton_34.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			else if (e.getSource() == btnNewButton_35)
				btnNewButton_35.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == btnNewButton)
				btnNewButton.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_1)
				btnNewButton_1.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_2)
				btnNewButton_2.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_3)
				btnNewButton_3.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_4)
				btnNewButton_4.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_5)
				btnNewButton_5.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_6)
				btnNewButton_6.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_7)
				btnNewButton_7.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_8)
				btnNewButton_8.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_9)
				btnNewButton_9.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_10)
				btnNewButton_10.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_11)
				btnNewButton_11.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_12)
				btnNewButton_12.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_13)
				btnNewButton_13.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_14)
				btnNewButton_14.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_15)
				btnNewButton_15.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_16)
				btnNewButton_16.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_17)
				btnNewButton_17.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_18)
				btnNewButton_18.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_19)
				btnNewButton_19.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_20)
				btnNewButton_20.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_21)
				btnNewButton_21.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_22)
				btnNewButton_22.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_23)
				btnNewButton_23.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_24)
				btnNewButton_24.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_25)
				btnNewButton_25.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_26)
				btnNewButton_26.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_27)
				btnNewButton_27.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_28)
				btnNewButton_28.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_29)
				btnNewButton_29.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_30)
				btnNewButton_30.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_31)
				btnNewButton_31.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_32)
				btnNewButton_32.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_33)
				btnNewButton_33.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_34)
				btnNewButton_34.setBorder(defaultBorder);
			else if (e.getSource() == btnNewButton_35)
				btnNewButton_35.setBorder(defaultBorder);

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
