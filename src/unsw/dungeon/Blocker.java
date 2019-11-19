package unsw.dungeon;

/**
 * Polymorphism to group entities that can block the movement of living creatures
 * @author Alana Hua
 *
 */
public abstract class Blocker extends Entity {

	public Blocker(int x, int y, MoveStrategy movementType) {
		super(x, y, movementType);
	}
	
	public abstract boolean block(Player player, Entity creature);
}