package unsw.dungeon;

/**
 * Polymorphism 
 * @author z5209503
 *
 */
public abstract class Blocker extends Entity {

	public Blocker(int x, int y, MoveStrategy movementType) {
		super(x, y, movementType);
	}
	
	public abstract boolean block(Player player);
}
