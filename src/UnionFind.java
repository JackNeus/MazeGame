import java.util.LinkedList;
import java.util.Queue;


public class UnionFind {
	public int n, m, components;
	/**
	 * First parameter is going to be component size, the second is going to be
	 * the rep id
	 */
	public int[][] component;

	public static int[] dirx = { 1, 0, -1, 0 };
	public static int[] diry = { 0, 1, 0, -1 };

	public UnionFind(int n, int m) {
		this.n = n;
		this.m = m;
		components = n * m;
		component = new int[n * m][2];
		for (int i = 0; i < n * m; i++) {
			component[i][1] = i;
			component[i][0] = 1;
		}
	}

	public void union(Node x, Node y) {
		components--;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Maze.visited[i][j] = false;
			}
		}
		
		if (component[x.id][0] > component[y.id][0]) {
			component[x.id][0] += component[y.id][0];
			component[y.id][0] = component[x.id][0];
			floodfill(component[x.id][1], y.x * m + y.y);
		} else {
			component[y.id][0] += component[x.id][0];
			component[x.id][0] = component[y.id][0];
			floodfill(component[y.id][1], x.x * m + x.y);
		}
	}
	
	/*public void floodfill(int key, int val) {
		int old = component[val][1];
		component[val][1] = key;
		Maze.visited[val / m][val % m] = true;
		for (int i = 0; i < 4; i++) {
			int newx = val / m + dirx[i];
			int newy = val % m + diry[i];
			if (newx < 0 || newx >= n ||newy < 0 || newy >= m) {
				continue;
			}
			if (!Maze.visited[newx][newy] && component[newx * m + newy][1] == old) {
				floodfill(key, newx * m + newy);
			}
		}
	} */

	public void floodfill(int key, int val) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Maze.visited[i][j] = false;
			}
		}
		Queue<Integer> q = new LinkedList<Integer> ();
		q.add(val);
		int old = component[val][1];
		while (!q.isEmpty()) {
			int a = (int) q.remove();
			if (Maze.visited[a / m][a % m]) {
				continue;
			}
			Maze.visited[a / m][a % m] = true;
			
			component[a][1] = key;
			for(int i = 0; i < 4; i++) {
				int newx = a / m + dirx[i];
				int newy = a % m + diry[i];
				if (newx < 0 || newx >= n || newy < 0 || newy >= m) {
					continue;
				}
				if (!Maze.visited[newx][newy] && component[newx * m + newy][1] == old) {
					q.add(newx * m + newy);
				}
			}
		}
	}
}
