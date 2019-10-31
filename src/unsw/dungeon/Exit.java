package unsw.dungeon;

public class Exit extends Entity {
	private Dungeon dungeon;
	private boolean triggered;
	
	public Exit(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.triggered = false;
	}
	
	public void setTriggered() {
		if (dungeon.getOnTopOf(this, "exit") instanceof Exit) {
			System.out.println("Player is exiting the dungeon. Puzzle is complete.");
			// might want to stop them from moving as a temporarily fix
			// need to find a way to restart the game?
			this.triggered = true;
		}
	}
	
	public boolean isTriggered() {
		return this.triggered;
	}
	
}
