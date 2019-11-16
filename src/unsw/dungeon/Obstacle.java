package unsw.dungeon;

/**
 * Polymorphism used for grouping together entities that the player
 * interacts with (i.e. portals, switches, exits)
 * @author Alana Hua
 *
 */
public abstract class Obstacle extends Entity {
	
	public Obstacle(int x, int y) {
		super(x, y, new CantMove());
	}
	
	public abstract void trigger(boolean state);
	public abstract void setExitGoal(ExitGoal exitGoal);
}
