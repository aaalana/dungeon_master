package unsw.dungeon;

/**
 * Polymorphism for grouping Enemy types and the player
 * @author z5209503
 *
 */
public abstract class LivingCreature extends Entity {

	public LivingCreature(int x, int y, MoveStrategy move) {
		super(x, y, move);
	}
}
