package unsw.dungeon;

public class Exit extends Obstacle {
	private boolean isBeingUsed;
	
	public Exit(int x, int y) {
		super(x, y);
		this.isBeingUsed = false;
	}
	
	@Override
	public void trigger(boolean state) {
		// might want to stop them from moving as a temporarily fix
		// need to find a way to restart the game?
		if (state) {
			System.out.println("Player is exiting the dungeon.");
		}
		
		this.isBeingUsed = state;
	}
	
	@Override
	public boolean getState() {
		return this.isBeingUsed;
	}
}
