package unsw.dungeon;

import java.util.ArrayList;

import unsw.dungeon.DungeonControllerLoader.ImageManager;

/**
 * The inventory contains all the items that the player collects from the 
 * dungeon and acts as a manager of the items. This includes responsibilities of 
 * storing, removing and searching of items as well as signaling the automatic 
 * activation of potions.
 * 
 * @author Alana Hua
 *
 */
public class Inventory {
	private ArrayList<Item> items;
	private Dungeon dungeon;
	private ImageManager imageManager;
	
	public Inventory(Dungeon dungeon) {
		this.items = new ArrayList<Item>();
		this.dungeon = dungeon;
		this.imageManager = new DungeonControllerLoader.ImageManager();
	}
	
	/**
	 * Selectively allows items to be added to the inventory
	 * @param player
	 * @param item
	 * @param dungeon
	 * @return true when an item has been successively collected and false 
	 * otherwise
	 */
	public boolean addItem(Player player, Item item) {
		if (item instanceof Sword && !hasCertainItem(item)) {
			items.add(item);
    		occupySlot(item);
    		return true;
    	} else if (item instanceof Key && !hasCertainItem(item))  {
    		items.add(item);
    		occupySlot(item);
    		return true;
    	} else if (item instanceof InvincibilityPotion && getItemByName("speed") == null) {
    		activatePotion(player, "invincibility", item);
    		occupySlot(item);
    		return true;
    	} else if (item instanceof SpeedPotion && getItemByName("invincibility") == null) { 
    		activatePotion(player, "speed", item);
    		occupySlot(item);
    		return true;
    	} else if (item instanceof Treasure) {
    		items.add(item);
    		occupySlot(item);
    		return true;
    	}
		return false;
	}
	
	 /**
     * Automatically signals the activation of a potion added to the inventory.
     * This function is also used to avoid code duplication and increase readability of code
     * in the addItem function.
     * Note: The direct activation of the potion is done within the player class since
     * an inventory cannot drink a potion. 
     * @param name
     * @param item
     */
    public void activatePotion(Player player, String name, Item item) {
    	// if the a potion has not been added yet, signal the activation of that potion
    	if (getItemByName(name) == null) {
			items.add(item);
			player.drinkPotion(item);
		} else {
			items.add(item);
		}	
    }
	
    /**
     * Removes an item from the inventory
     * @param item
     */
	public void removeItem(Item item) {
		imageManager.removeImage(item.getImage());
		items.remove(item);
	}

	/**
	 * Moves the item from the dungeon into the inventory (front end wise)
	 * @param item
	 * @param dungeon
	 */
	public void occupySlot(Item item) {
		// If the item type is already in the inventory, then move the item to 
		// the same location
		if (hasCertainItem(item)) {
			for (int x = 9; x < 16; x = x + 2) {
				if (getStoredItemType(x).equals(item.getClassName())) {
					item.setPosition(x, dungeon.getHeight() + 2);
					return;
				} 
			}		
		}
		
		// Otherwise, move the item to an empty slot in the inventory
		for (int x = 9; x < 16; x = x + 2) {
			if (getStoredItemType(x).equals("None")) {
				item.setPosition(x, dungeon.getHeight() + 2);
				break;
			} 
		}	
	}
	
	/**
	 * Gets the item type from the inventory given an x coordinate
	 * This is used to check which slot in the inventory is carrying
	 * which item front end wise
	 * @param dungeon
	 * @param x
	 * @return 
	 */
	public String getStoredItemType(int x) {
	    for (Item item : this.items) {
    		if (item == null) continue; 
    		if (item.getX() == x) {
    			return item.getClassName();
    		}
    	}
    	return "None";
	}
	
	/**
	 * check if the inventory contains as the same item type 
	 * as the item passed in.
	 * This is useful when knowledge of the item type is 
	 * unknown.
	 * @return
	 */
	public boolean hasCertainItem(Item obj) {
		for (Item item : items) {
			if (item.isSameItem(obj)) 
				return true;
		}
		return false;
	}
	
	/**
	 * Gets the items list from the inventory
	 * @return
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * -gets an item from the inventory given its name
	 * -this function is most useful for checking for the existence of  
	 *  items that can only be carried one at a time
	 * @param name the item's name
	 * @return item
	 */
	public Item getItemByName(String name) {
		for (Item item : items) {
			if (item.sameName(name)) {
				return item;
			}
		}
		return null;
	}
}