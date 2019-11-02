package unsw.dungeon;

public class Exit extends Obstacle {
	
	public Exit(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void trigger(boolean state) {
		// End the application when leaving the maze
		if (state) {
			System.out.println("Player exited the dungeon.");
			System.exit(0);
		}
	}
	
}
