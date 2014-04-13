import javax.swing.JFrame;
import java.awt.Color;

public class Maze extends JFrame {
	/*Window Variables*/
	public static int width = 800, height = 600, margin = 100;
	public static int tileSize = 20;
	public static int xTiles = width / tileSize, yTiles = height / tileSize;
	public static int numNodes;
	
	public static boolean adj[][];
	public static Node nodes[];
	
	public Maze(){	
		setTitle("Maze Game");
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		
		numNodes = xTiles * yTiles;
		adj = new boolean[numNodes][numNodes];
		nodes = new Node[numNodes];
		for(int i = 0; i < numNodes; i++){
			nodes[i] = new Node(i % xTiles, i / yTiles, i);
		}
		for(int i = 0; i < numNodes; i++){
			for(int j = 0; j < numNodes; j++){
				adj[i][j] = false;
			}
		}
		
		setVisible(true);
	}
	
	public static void drawMaze(){
		
	}
	
	public static void main(String args[]){
		new Maze();
	}
}
