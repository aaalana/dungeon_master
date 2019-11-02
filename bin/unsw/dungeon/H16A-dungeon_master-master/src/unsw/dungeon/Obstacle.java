package unsw.dungeon;

public abstract class Obstacle extends Entity {
	
	public Obstacle(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void trigger(boolean state);
	public abstract boolean getState();
}
