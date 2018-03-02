package snake;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class EndScreen extends JFrame {

	private static final String SCORE_PREFIX = "Your Score:";
	private JLabel score;
	private JButton restart, quit;
	private JSlider dificulty;
	private int dificultyLevel = 90;

	public EndScreen(int points, SnakeGame snakeGame) {
		setTitle("You died");
		dificulty = new JSlider(80, 99, 90);
		Hashtable labelTable = new Hashtable();
		labelTable.put(new Integer(80), new JLabel("insane"));
		labelTable.put(new Integer(90), new JLabel("normal"));
		labelTable.put(new Integer(99), new JLabel("easy"));
		dificulty.setLabelTable(labelTable);
		dificulty.setPaintLabels(true);
		dificulty.addChangeListener(e -> {
			JSlider source = (JSlider) e.getSource();

			if (!source.getValueIsAdjusting()) {
				dificultyLevel = source.getValue();

			}
		});
		score = new JLabel(SCORE_PREFIX + points, JLabel.CENTER);
		setLocation(300, 300);
		quit = new JButton("quit");
		quit.setPreferredSize(new Dimension(200, 50));
		quit.addActionListener(e -> {
			snakeGame.quit();
			setVisible(false); // you can't see me!
			dispose(); // Destroy the JFrame object
		});
		restart = new JButton("restart");
		score.setPreferredSize(new Dimension(200, 50));
		restart.setPreferredSize(new Dimension(200, 50));
		restart.addActionListener(e -> {
			snakeGame.restart(dificultyLevel);
			setVisible(false); // you can't see me!
			dispose(); // Destroy the JFrame object
		});
		Container contents = getContentPane();

		JPanel buttonPane = new JPanel(new BorderLayout());

		contents.add(buttonPane, BorderLayout.SOUTH);
		contents.add(score, BorderLayout.CENTER);
		buttonPane.add(quit, BorderLayout.EAST);
		buttonPane.add(restart, BorderLayout.WEST);
		buttonPane.add(dificulty, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		// TODO Auto-generated constructor stub
	}

}
