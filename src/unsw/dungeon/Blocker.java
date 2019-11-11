package unsw.dungeon;

public abstract class Blocker extends Entity {

	public Blocker(int x, int y, MoveStrategy movementType) {
		super(x, y, movementType);
	}
	
	public abstract boolean block(Player player);
}
