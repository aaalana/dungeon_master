package unsw.dungeon;

/**
 * Interface used to implement as move strategy for strategy pattern
 * @author Alana Hua
 *
 */
public interface MoveStrategy {
	public void move(String direction, Dungeon dungeon, Entity entity);
}
