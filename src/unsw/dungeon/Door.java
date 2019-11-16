package unsw.dungeon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.dungeon.DungeonControllerLoader.ImageManager;

/**
 * Door entity is locked at default and can be unlocked if given a key with the 
 * same ID as that of the door
 * @author Alana Hua
 *
 */
public class Door extends Blocker {
	private int id;
	private boolean locked;
	
	public Door(int x, int y, int id, MoveStrategy movementType) {
		super(x, y, movementType);
		this.id = id;
		this.locked = true;
	}
	
	@Override
	public boolean block(Player player) {
		Key key = (Key)player.getItem("key");
		
		if (key != null && (matchingKey(key))) {
			unlock(player, key);
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
	public void unlock(Player player, Key key) {
		key.useItem(player);
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
	
	/**
	 * Changes the door icon from closed to open
	 * @param player
	 * @param imageManager
	 */
	public void replaceDoorImage(Player player, ImageManager imageManager) {
		ImageView old = this.getImage();
		this.setImage(new ImageView(new Image("/open_door.png")));
		this.replaceImage(old, imageManager);
	}
}
