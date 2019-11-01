package unsw.dungeon;

public abstract class Blocker extends Entity {

	public Blocker(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void block();
	// change this if you want ^
}
