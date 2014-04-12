public class Game {
	public static boolean gameRunning = true;
	public static int fps = 0;
	public static long lastFpsTime = 0;

	public static void main(String args[]) {
		System.out.println("Abi sucks");
		System.out.println("Abi is ok");
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
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 100000);
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
