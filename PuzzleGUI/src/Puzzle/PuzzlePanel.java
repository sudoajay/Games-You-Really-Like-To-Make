package Puzzle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {

	public JButton[] button;
	public final int noBox = 16;

	public PuzzlePanel() {
		setPreferredSize(new Dimension(1300, 1100));
		setBackground(Color.BLACK);
		setLayout(null);

		PuzzleJInternalFrame puzzleJInternalFrame = new PuzzleJInternalFrame();
		puzzleJInternalFrame.setLocation(400, 120);
		puzzleJInternalFrame.pack();
		puzzleJInternalFrame.setVisible(true);
		add(puzzleJInternalFrame);
	}

}
