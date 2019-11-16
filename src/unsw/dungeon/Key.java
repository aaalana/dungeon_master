package unsw.dungeon;

/**
 * Key entity which has a unique ID that matches a door in the dungeon
 * @author Alana Hua
 *
 */
public class Key extends Item {
	private int id;
	
	public Key(int x, int y, int id) {
        super(x, y, "key");
        this.id = id;
	}

	@Override
	public void useItem(Player player) {
		player.removeItem(this);
	}
		
	/**
	 * gets the id of the key
	 * @return key id
	 */
	public int getId() {
		return this.id;
	}
}
