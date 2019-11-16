package unsw.dungeon;

/**
 * Polymorphism 
 * @author z5209503
 *
 */
public abstract class Obstacle extends Entity {
	
	public Obstacle(int x, int y) {
		super(x, y, new CantMove());
	}
	
	public abstract void trigger(boolean state);
	public abstract void setExitGoal(ExitGoal exitGoal);
}
