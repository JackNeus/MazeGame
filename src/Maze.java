import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Maze extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/** Window Variables */
	public int width = 600, height = 400, margin = 100, adjusty, adjustx;
	public int tileSize = 5;
	
	public int xTiles = width / tileSize, yTiles = height / tileSize;
	public int numNodes;

	public UnionFind joiner;
	public MazeSolver solver;
	public Robot robot;
	
	public static boolean[][] adj;
	public static boolean[][] visited;
	public Node[] nodes;
	public int entry, exit, entryl, exitl;
	
	public Maze(){
		this(600, 400, 5);
	}
	
	public Maze(int width, int height, int tileSize) {
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
		this.xTiles = width / tileSize;
		this.yTiles = height / tileSize;
		
		robot = new Robot(this);
		setPreferredSize(new Dimension(margin + width + margin, margin + height + margin));
		
		setBackground(Color.LIGHT_GRAY);
		adjusty = margin + getInsets().top;
		adjustx = margin + getInsets().left;
		
		
		numNodes = xTiles * yTiles;
		visited = new boolean[yTiles][xTiles];
		adj = new boolean[numNodes][numNodes];
		nodes = new Node[numNodes];
		for(int i = 0; i < numNodes; i++) {
			nodes[i] = new Node(i / xTiles, i % xTiles, i);
		}
		for(int i = 0; i < numNodes; i++) {
			for(int j = 0; j < numNodes; j++) { 
				adj[i][j] = false;
			}
		}
		joiner = new UnionFind(yTiles, xTiles);
		genMaze();
		genLines();
		setVisible(true);
		solver = new MazeSolver(yTiles, xTiles);
		solver.bfs(entry, exit);
	}
	
	int dirs[];
	public void genMaze() {
		dirs = new int[]{-1, 1, xTiles, -xTiles};
		
		while(joiner.components > 1) {
			int id = (int) Math.floor(Math.random() * numNodes);
			int dir = (int) Math.floor(Math.random() * 4);
			
			for(int i = 0; i < 4; i++) {
				if(dir == 0 && nodes[id].y == 0) continue;
				if(dir == 1 && nodes[id].y == xTiles - 1) continue;
				if(dir == 2 && nodes[id].x == yTiles - 1) continue;
				if(dir == 3 && nodes[id].x == 0) continue;
				
				int nid = id + dirs[dir];
				if(adj[id][nid] || adj[nid][id]) continue;
				if(joiner.component[id][1] == joiner.component[nid][1]) continue;
				
				adj[id][nid] = true;
				adj[nid][id] = true;
				
				joiner.union(nodes[id], nodes[nid]);	
			}
		}
		
		entry = (int) (Math.floor(Math.random() * yTiles)) * xTiles;
		exit = (int) (Math.floor(Math.random() * yTiles) + 1) * xTiles - 1;
	}
	
	public Line2D[] lines;
	public void genLines() {
		lines = new Line2D[(xTiles + 1) * (yTiles + 1) * 2];
		int pos = 0;
		for(int i = 0; i < numNodes; i++) {
			if(nodes[i].y == 0 || !adj[i][i - 1]) {
				if(i == entry) entryl = pos;
				lines[pos++] = new Line2D.Float(nodes[i].y * tileSize + adjustx,
												nodes[i].x * tileSize + adjusty,
												nodes[i].y * tileSize + adjustx,
												nodes[i].x * tileSize + adjusty + tileSize);
			}
			if(nodes[i].x == 0 || !adj[i][i - xTiles]) {
				lines[pos++] = new Line2D.Float(nodes[i].y * tileSize + adjustx,
												nodes[i].x * tileSize + adjusty,
												nodes[i].y * tileSize + adjustx + tileSize,
												nodes[i].x * tileSize + adjusty);
			}
		}
		for(int i = 0; i < xTiles; i++) {
			lines[pos++] = new Line2D.Float(i * tileSize + adjustx,
											adjusty + height,
											i * tileSize + adjustx + tileSize,
											adjusty + height);
		}
		for(int i = 0; i < yTiles; i++) {
			if((i + 1) * xTiles - 1 == exit) exitl = pos;
			lines[pos++] = new Line2D.Float(adjustx + width,
											i * tileSize + adjusty,
											adjustx + width,
											i * tileSize + adjusty + tileSize);
		}
		//lines[pos++] = new Line2D.Float(adjustx, adjusty + height, adjustx + width, adjusty + height);
		//lines[pos++] = new Line2D.Float(adjustx + width, adjusty, adjustx + width, adjusty + height);
	}
	
	public void drawPath(Graphics2D g) {
		int next = exit, last = exit;
		g.setColor(Color.RED);
		//g.fillRect(adjustx + exit % xTiles * tileSize + 2, adjusty + exit / xTiles * tileSize + 3, tileSize - 3, tileSize - 3);
		while (next != entry) {
			next = solver.camefrom[next / xTiles][next % xTiles];
			//g.fillRect(adjustx + next % xTiles * tileSize + 2, adjusty + next / xTiles * tileSize + 2, tileSize - 3, tileSize - 3);
			if (Math.abs(next - last) > 1) {
				g.drawLine(adjustx + next % xTiles * tileSize + tileSize / 2, adjusty + next / xTiles * tileSize + tileSize / 2, 
						adjustx + last % xTiles * tileSize + tileSize / 2, adjusty + last / xTiles * tileSize + tileSize / 2);
			} else {
				g.drawLine(adjustx + next % xTiles * tileSize  + tileSize / 2, adjusty + next / xTiles * tileSize + tileSize / 2, 
						adjustx + last % xTiles * tileSize + tileSize / 2, adjusty + last / xTiles * tileSize + tileSize / 2);
			}
			last = next;
		}
		if (entry % xTiles == 0) {
			g.drawLine(adjustx, adjusty + entry / xTiles * tileSize + tileSize / 2, 
					adjustx + tileSize / 2, adjusty + entry / xTiles * tileSize + tileSize / 2);
		} else if (entry % xTiles == xTiles - 1) {
			g.drawLine(adjustx + entry % xTiles * tileSize + tileSize / 2, adjusty + entry / xTiles * tileSize + tileSize / 2, 
					adjustx + entry % xTiles * tileSize + tileSize, adjusty + entry / xTiles * tileSize + tileSize / 2);
		} else if (entry < xTiles) {
			g.drawLine(adjustx + entry % xTiles * tileSize + tileSize / 2, adjusty, 
					adjustx + entry % xTiles * tileSize + tileSize / 2, adjusty + tileSize / 2);
		} else {
			g.drawLine(adjustx + entry % xTiles * tileSize + tileSize / 2, adjusty + entry / xTiles * tileSize + tileSize / 2, 
					adjustx + entry % xTiles * tileSize + tileSize / 2, adjusty + entry / xTiles * tileSize + tileSize);
		}
		if (exit % xTiles == 0) {
			g.drawLine(adjustx, adjusty + exit / xTiles * tileSize + tileSize / 2, 
					adjustx + tileSize / 2, adjusty + exit / xTiles * tileSize + tileSize / 2);
		} else if (exit % xTiles == xTiles - 1) {
			g.drawLine(adjustx + exit % xTiles * tileSize + tileSize / 2, adjusty + exit / xTiles * tileSize + tileSize / 2, 
					adjustx + exit % xTiles * tileSize + tileSize, adjusty + exit / xTiles * tileSize + tileSize / 2);
		} else if (exit < xTiles) {
			g.drawLine(adjustx + exit % xTiles * tileSize + tileSize / 2, adjusty, 
					adjustx + exit % xTiles * tileSize + tileSize / 2, adjusty + tileSize / 2);
		} else {
			g.drawLine(adjustx + exit % xTiles * tileSize + tileSize / 2, adjusty + exit / xTiles * tileSize + tileSize / 2, 
					adjustx + exit % xTiles * tileSize + tileSize / 2, adjusty + exit / xTiles * tileSize + tileSize);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(adjustx, adjusty, width, height);
		g2.setColor(Color.BLACK);
		for(int i = 0; i < lines.length; i++) {
			if(i == entryl || i == exitl) {
			}
			else if(lines[i] != null) {
				g2.draw(lines[i]);
			}
		}
		if(robot != null) robot.solve(g2);
		drawPath(g2);
	}
}
