import java.awt.Color;
import java.awt.Graphics2D;

public class Robot {
	int[] dirs = new int[]{1, 1, -1, -1};
	Maze maze;
	
	int pos;
	int dir = 0;
	
	public Robot(Maze maze) {
		this.maze = maze;
		pos = maze.entry;
		dir = 0;
		dirs[1] *= maze.xTiles;
		dirs[3] *= maze.xTiles;
	}
	
	public void solve(Graphics2D g2) {
		pos = maze.entry;
		while(true) {
			if(pos == maze.exit) break;
			g2.setColor(new Color(255, 255, 0, 64));
			g2.fillRect(pos % maze.xTiles * maze.tileSize + maze.adjustx, pos / maze.xTiles * maze.tileSize + maze.adjusty, maze.tileSize, maze.tileSize);
			int r = (dir + 1) % 4, l = dir - 1;
			if(l == -1) l = 3;
			if(pos + dirs[r] >= 0 && pos + dirs[r] < maze.numNodes && Maze.adj[pos][pos + dirs[r]]) {
				pos = pos + dirs[r];
				dir = r;
			}
			else if(pos + dirs[dir] >= 0 && pos + dirs[dir] < maze.numNodes && Maze.adj[pos][pos + dirs[dir]]) {
				pos = pos + dirs[dir];
			}
			else if(pos + dirs[l] > 0 && pos + dirs[l] < maze.numNodes && Maze.adj[pos][pos + dirs[l]]) {
				pos = pos + dirs[l];
				dir = l;
			}
			else {
				dir = (dir + 1) % 4;
			}
		}
	}
}

