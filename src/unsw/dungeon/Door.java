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
	
	/**
	 * sets the door's state to unlocked
	 */
	public void unlock() {
		this.locked = false;
	}
	
	/**
	 * gets the door's id
	 * @return door's id
	 */
	public int getId() {
		return this.id;
	}
}
