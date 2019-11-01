package unsw.dungeon;

public class Exit extends Obstacle {
	private boolean isBeingUsed;
	
	public Exit(int x, int y) {
		super(x, y);
		this.isBeingUsed = false;
	}
	
	@Override
	public void trigger(boolean state) {
		// End the application when leaving the maze
		if (state) {
			System.out.println("Player exited the dungeon.");
			System.exit(0);
		}
		this.isBeingUsed = state;
	}
	
	@Override
	public boolean getState() {
		return this.isBeingUsed;
	}
}
