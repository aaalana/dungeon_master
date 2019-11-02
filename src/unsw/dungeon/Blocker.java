package unsw.dungeon;

public abstract class Blocker extends Entity {

	public Blocker(int x, int y) {
		super(x, y);
	}
	
	public abstract boolean block(Player player);
}
