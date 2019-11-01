package unsw.dungeon;

public class Exit extends Obstacle {
	private boolean triggered;
	
	public Exit(int x, int y) {
		super(x, y);
		this.triggered = false;
	}
	
	@Override
	public void trigger(boolean state) {
		// might want to stop them from moving as a temporarily fix
		// need to find a way to restart the game?
		this.triggered = state;
	}
	
	public boolean getState() {
		return this.triggered;
	}
}
