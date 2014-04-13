import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maze extends JFrame {
	/*Window Variables*/
	public int width = 600, height = 400, margin = 100, adjusty, adjustx;
	public int tileSize = 40;
	public int xTiles = width / tileSize, yTiles = height / tileSize;
	public int numNodes;
	
	public boolean[][] adj;
	public Node[] nodes;
	
	public Maze(){	
		System.out.println(xTiles);
		
		setTitle("Maze Game");
		
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(margin + width + margin, margin + height + margin));
		this.getContentPane().add(jp);
		this.pack();
		adjusty = margin + getInsets().top;
		adjustx = margin + getInsets().left;
		
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		
		numNodes = xTiles * yTiles;
		adj = new boolean[numNodes][numNodes];
		nodes = new Node[numNodes];
		for(int i = 0; i < numNodes; i++){
			nodes[i] = new Node(i % xTiles, i / xTiles, i);
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
		lines = new Line2D[(xTiles + 1) * (yTiles + 1) * 2];
		int pos = 0;
		for(int i = 0; i < numNodes; i++){
			if(nodes[i].x == 0 || !adj[i][i - 1]){
				lines[pos++] = new Line2D.Float(nodes[i].x * tileSize + adjustx,
												nodes[i].y * tileSize + adjusty,
												nodes[i].x * tileSize + adjustx,
												nodes[i].y * tileSize + adjusty + tileSize);
			}
			if(nodes[i].y == 0 || !adj[i][i - xTiles]){
				lines[pos++] = new Line2D.Float(nodes[i].x * tileSize + adjustx,
												nodes[i].y * tileSize + adjusty,
												nodes[i].x * tileSize + adjustx + tileSize,
												nodes[i].y * tileSize + adjusty);

			}
		}
		for(int i = 0; i < xTiles; i++){
			lines[pos++] = new Line2D.Float(i * tileSize + adjustx,
											adjusty + height,
											i * tileSize + adjustx + tileSize,
											adjusty + height);
		}
		for(int i = 0; i < yTiles; i++){
			lines[pos++] = new Line2D.Float(adjustx + width,
											i * tileSize + adjusty,
											adjustx + width,
											i * tileSize + adjusty + tileSize);
		}
		//lines[pos++] = new Line2D.Float(adjustx, adjusty + height, adjustx + width, adjusty + height);
		//lines[pos++] = new Line2D.Float(adjustx + width, adjusty, adjustx + width, adjusty + height);
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
