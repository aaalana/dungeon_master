package unsw.dungeon;

public class Exit extends Entity {
	private Dungeon dungeon;
	
	public Exit(int x, int y, Dungeon dungeon) {
		super(x, y);
	}
	
	public boolean triggered() {
		return dungeon.isOnTopOf(getX(), getY(), "Exit");
	}
	

}
