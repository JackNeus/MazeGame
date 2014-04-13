public class UnionFind {
	public int n, m, components;
	public boolean[][] visited;
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
		visited = new boolean[n][m];
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
				visited[i][j] = false;
			}
		}
		if (component[x.id][0] > component[y.id][0]) {
			floodfill(component[x.id][1], y.x, y.y);
			component[x.id][0] += component[y.id][0];
			component[y.id][0] = component[x.id][0];
		} else {
			floodfill(component[y.id][1], x.x, x.y);
		}
	}

	public void floodfill(int key, int x, int y) {
		visited[x][y] = true;
		component[x * m + y][1] = key;
		for (int i = 0; i < 4; i++) {
			int newx = x + dirx[i];
			int newy = y + diry[i];
			if (newx >= 0 && newx < m && newy >= 0 && newy < n
					&& !visited[newx][newy]
					&& component[newx * m + newy][1] == component[x][y]) {
				floodfill(key, newx, newy);
			}
		}
	}
}
