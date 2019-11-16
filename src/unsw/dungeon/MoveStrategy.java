package unsw.dungeon;

/**
 * Interface used to implement as move strategy for strategy pattern
 * @author z5209503
 *
 */
public interface MoveStrategy {
	public void move(String direction, Dungeon dungeon, Entity entity);
}
