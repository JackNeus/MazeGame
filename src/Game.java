import javax.swing.JFrame;

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	public static boolean gameRunning = true;
	public static int fps = 0;
	public static long lastFpsTime = 0;
	public static int width = 600, height = 600;

	public Game() {
		setTitle("MAZE SOLVER");
		setSize(width, height);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		new Game();
		gameloop();
	}

	public static void gameloop() {
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

		while (gameRunning) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_TIME);

			lastFpsTime += updateLength;
			fps++;

			if (lastFpsTime >= 1000000000) {
				System.out.println("(FPS: " + fps + ")");
				lastFpsTime = 0;
				fps = 0;
			}

			update(delta);
			render();

			try {
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void update(double delta) {
	}

	public static void render() {

	}
}
