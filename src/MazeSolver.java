import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;


public class MazeSolver {
	public static int n, m;
	public static Stack<Integer> stack;
	public boolean[][] visited;
	
	public static int[] dirx = {-1, 0, 1, 0};
	public static int[] diry = {0, -1, 0, 1};
	
	public void nextDFS(Graphics2D g) {
		g.setColor(Color.RED);
		int out = stack.peek();
		boolean add = false;
		for (int i = 0; i < 4; i++) {
			int newx = out / m + dirx[i];
			int newy = out % m + diry[i];
			if (newx < 0 || newx >= n || newy < 0 || newy >= m) {
				continue;
			}
			if (Maze.adj[out][newx * m + newy] && !visited[newx][newy]) {
				stack.add(newx * m + newy);
				g.drawRect(Maze.adjustx + (newx * m + newy) / m * Maze.tileSize, Maze.adjusty + (newx * m + newy) % m * Maze.tileSize, Maze.tileSize, Maze.tileSize);
				add = true;
			}
		}
		if (!add) {
			stack.pop();
			g.setColor(Color.WHITE);
			g.drawRect(Maze.adjustx + out / m * Maze.tileSize, Maze.adjusty + out % m * Maze.tileSize, Maze.tileSize, Maze.tileSize);
		}
	}
	
	public void init(Node start, int n, int m) {
		this.n = n;
		this.m = m;
		visited = new boolean[n][m];
		stack = new Stack<Integer> ();
		stack.add(start.id);
	}
}
