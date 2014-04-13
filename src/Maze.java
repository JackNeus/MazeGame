import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maze extends JFrame {
	/*Window Variables*/
	public int width = 600, height = 400, margin = 100;
	public int tileSize = 40;
	public int xTiles = width / tileSize, yTiles = height / tileSize;
	public int numNodes;
	
	public boolean[][] adj;
	public Node[] nodes;
	
	public Maze(){	
		System.out.println(xTiles);
		System.out.println(yTiles);
		
		setTitle("Maze Game");
		
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(margin + width + margin, margin + height + margin));
		this.getContentPane().add(jp);
		this.pack();
		//setSize(margin + width + margin, margin + height + margin);
		
		
		setResizable(true);
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
		genLines();
		
		setVisible(true);
	}
	
	public Line2D[] lines;
	public void genLines(){
		lines = new Line2D[xTiles + 1 * yTiles + 1];
		int pos = 0;
		for(int i = 0; i < numNodes; i++){
			if(nodes[i].x == 0){
				lines[pos++] = new Line2D.Float(nodes[i].x * tileSize + margin,
												nodes[i].y * tileSize + margin,
												nodes[i].x * tileSize + margin,
												nodes[i].y + 1 * tileSize + margin + tileSize);
				System.out.println(nodes[i].y * tileSize + margin);
				System.out.println(nodes[i].y * tileSize + margin + tileSize);
			}
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < lines.length; i++){
			if(lines[i] != null){
				g2.draw(lines[i]);
			}
		}
		
	}
}
