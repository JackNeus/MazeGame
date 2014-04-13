public class UnionFind {
	public int n, m, components = n * m;
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
		visited = new boolean[n][m];
		component = new int[n * m][2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				component[i * n + j][1] = i * n + j;
				component[i * n + j][0] = 1;
			}
		}
	}

	public void union(Node x, Node y) {
		components--;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = false;
			}
		}
		if (component[x.x * n + x.y][0] > component[y.x * n + y.y][0]) {
			floodfill(component[x.x * n + x.y][1], y.x, y.y);
			component[x.x * n + x.y][0] += component[y.x * n + y.y][0];
			component[y.x * n + y.y][0] = component[x.x * n + x.y][0];
		} else {
			floodfill(component[y.x * n + y.y][1], x.x, x.y);
		}
	}

	public void floodfill(int key, int x, int y) {
		visited[x][y] = true;
		component[x * n + y][1] = key;
		for (int i = 0; i < 4; i++) {
			int newx = x + dirx[i];
			int newy = y + diry[i];
			if (newx >= 0 && newx < n && newy >= 0 && newy < m
					&& !visited[newx][newy]
					&& component[newx * n + newy][1] == component[x][y]) {
				floodfill(key, newx, newy);
			}
		}
	}
}
