package unsw.dungeon;

public class Door extends Blocker {
	private int id;
	private boolean locked;
	public Door(int x, int y, int id) {
		super(x, y);
		this.id = id;
		this.locked = true;
	}
	@Override
	public void block() {
		// TODO Auto-generated method stub
		
	}
}
