import java.util.LinkedList;
import java.util.Queue;



public class MazeSolver {
	public int n, m;
	public int[][] camefrom;
	
	public int[] dirx = {-1, 0, 1, 0};
	public int[] diry = {0, -1, 0, 1};
	
	public MazeSolver(int n, int m) {
		this.n = n;
		this.m = m;
		camefrom = new int[n][m];
	}
	
	public void bfs(int start, int end) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Maze.visited[i][j] = false;
			}
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		Maze.visited[start / m][start % m] = true;
		while (!q.isEmpty()) {
			int next = q.remove();
			Maze.visited[next / m][next % m] = true;
			if (next == end) {
				return;
			}
			for (int i = 0; i < 4; i++) {
				int newx = next / m + dirx[i];
				int newy = next % m + diry[i];
				if (newx < 0 || newx >= n || newy < 0 || newy >= m) continue;
				if (Maze.adj[next][newx * m + newy] && !Maze.visited[newx][newy]) {
					q.add(newx * m + newy);
					camefrom[newx][newy] = next;
				}
			}
		}
	}
}
