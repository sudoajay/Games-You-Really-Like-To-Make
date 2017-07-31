package DodgeIT;

import java.awt.Font;

import javax.swing.JFrame;

public class DodgeITFrame extends JFrame {

	public static void main(String[] args) {
		JFrame dodge = new JFrame("Dodge It !");
		dodge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dodge.setBounds(300, 100, 700, 600);
		dodge.setFont(new Font("Chandas", Font.BOLD, 20));

		dodge.getContentPane().add(new DodgeItPanel());
		dodge.setVisible(true);
	}

}
