package unsw.dungeon;

/**
 * Polymorphism 
 * @author z5209503
 *
 */
public abstract class LivingCreature extends Entity {

	public LivingCreature(int x, int y) {
		super(x, y, new ItMoves());
	}
}
