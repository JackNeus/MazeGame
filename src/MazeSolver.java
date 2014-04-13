import java.util.Stack;


public class MazeSolver {
	public static int n, m;
	public static Stack<Integer> stack;
	public boolean[][] visited;
	
	public void nextDFS() {
		int out = stack.peek();
		
	}
	
	public void init(Node start, int n, int m) {
		this.n = n;
		this.m = m;
		visited = new boolean[n][m];
		stack = new Stack<Integer> ();
		stack.add(start.id);
	}
}
