package unsw.dungeon;

public abstract class Obstacle extends Entity {
	
	public Obstacle(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	abstract public void trigger(boolean state);
	abstract public boolean getState();
}
