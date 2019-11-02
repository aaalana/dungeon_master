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
	public boolean block(Player player) {
		Key key = (Key)player.getItemByName("key");
		
		if (key != null && (matchingKey(key))) {
			key.useItem(player);
			unlock();
			System.out.println("Player unlocked door.");
			return false;
		} else if (this.locked) {
			System.out.println("Player does not have a key or has the wrong key to unlock the door.");
			return true;
		} else {
			System.out.println("Player can pass. Door was previously unlocked.");
			return false;
		}
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
	
	/**
	 * Checks if a unique key matching to a door via checking for equal IDs
	 * i.e. is able to unlock the door
	 * @param key
	 * @return true when the key matches the door and false otherwise
	 */
	public boolean matchingKey(Key key) {
		return this.id == key.getId();
	}
}
