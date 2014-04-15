import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public Window(int widthval, int heightval, int tileval) {
		Maze maze = new Maze(widthval, heightval, tileval);
		JScrollPane scrollPane = new JScrollPane(maze);
		setTitle("Maze Game");
		getContentPane().setPreferredSize(new Dimension(600, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(scrollPane);
		pack();
		setVisible(true);
		paintComponents(getGraphics());
	}
}
