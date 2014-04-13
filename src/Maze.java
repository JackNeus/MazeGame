import javax.swing.JFrame;

public class Maze extends JFrame {
	/*Window Variables*/
	public static int width = 800, height = 600, margin = 100;
	public static int tileSize = 20;
	public static int xTiles = width / tileSize, yTiles = height / tileSize;
	
	public static boolean adj[][];
	
	
	public Maze(){	
		setTitle("Maze Game");
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		adj = new boolean[xTiles * yTiles][xTiles * yTiles];
		
		setVisible(true);
	}
	
	public static void main(){
		new Maze();
	}
}
