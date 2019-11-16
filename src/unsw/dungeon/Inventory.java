package unsw.dungeon;

import java.util.ArrayList;

import unsw.dungeon.DungeonController.ImageManager;

public class Inventory {
	private ArrayList<Item> items;
	private Dungeon dungeon;
	private ImageManager imageManager;
	
	public Inventory(Dungeon dungeon) {
		this.items = new ArrayList<Item>();
		this.dungeon = dungeon;
		this.imageManager = new DungeonController.ImageManager();
	}
	
	/**
	 * Selectively allows items to be added to the inventory
	 * @param player
	 * @param item
	 * @param dungeon
	 */
	public void addItem(Player player, Item item) {
		if (item instanceof Sword && !hasCertainItem(item)) {
			items.add(item);
    		occupySlot(item);
    	} else if (item instanceof Key && !hasCertainItem(item))  {
    		items.add(item);
    		occupySlot(item);
    	} else if (item instanceof InvincibilityPotion && getItemByName("speed") == null) {
    		activatePotion(player, "invincibility", item);
    		occupySlot(item);
    	} else if (item instanceof SpeedPotion && getItemByName("invincibility") == null) { 
    		activatePotion(player, "speed", item);
    		occupySlot(item);
    	} else if (item instanceof Treasure) {
    		items.add(item);
    		occupySlot(item);
    	}
	}
	
	 /**
     * Automatically signals the activation any potion added to the inventory
     * @param name
     * @param item
     */
    public void activatePotion(Player player, String name, Item item) {
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
	 * check if the inventory contains a certain item
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
	 * Gets the items from the inventory
	 * @return
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * -gets an item from the inventory given its name
	 * -this function is most useful for items that can 
	 *  only be collected once 
	 * @param item
	 * @return type of item
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