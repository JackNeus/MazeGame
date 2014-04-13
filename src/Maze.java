import javax.swing.JFrame;

public class Maze extends JFrame {
	public static int width = 800, height = 600;
	
	public Maze(){
		setTitle("Maze Game");
		setSize(width, height);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(){
		new Maze();
	}
}
