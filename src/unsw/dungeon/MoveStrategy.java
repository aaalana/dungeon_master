package unsw.dungeon;

public interface MoveStrategy {
	public void move(String direction, Dungeon dungeon, Entity entity);
}
