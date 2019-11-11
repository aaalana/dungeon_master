package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> items;
	private Dungeon dungeon;
	
	public Inventory(Dungeon dungeon) {
		this.items = new ArrayList<Item>();
		this.dungeon = dungeon;
	}
	
	/**
	 * Selectively allows some items to be added to the inventory
	 * @param player
	 * @param item
	 * @param dungeon
	 */
	public void addItem(Player player, Item item) {
		
		if (item instanceof Sword && !hasCertainItem(item)) {
			items.add(item);
    		printInventory();
    		occupySlot(item);
    	} else if (item instanceof Key && !hasCertainItem(item))  {
    		items.add(item);
    		printInventory();
    		occupySlot(item);
    	} else if (item instanceof InvincibilityPotion && getItemByName("speed") == null) {
    		activatePotion(player, "invincibility", item);
    		printInventory();
    		occupySlot(item);
    	} else if (item instanceof SpeedPotion && getItemByName("invincibility") == null) { 
    		System.out.println("here");
    		activatePotion(player, "speed", item);
    		printInventory();
    		occupySlot(item);
    	} else if (item instanceof Treasure) {
    		items.add(item);
    		printInventory();
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
		getImageManager().removeImage(item.getImage());
		items.remove(item);
	}

	/**
	 * Gets the image manager from the dungeon
	 * @return
	 */
	public DungeonControllerLoader.ImageManager getImageManager() {
		return dungeon.getImageManager();
	}
	
	/**
	 * Moves the item from the dungeon into the inventory
	 * @param item
	 * @param dungeon
	 */
	public void occupySlot(Item item) {
		int i = 0;
		if (hasCertainItem(item)) {
			for (int x = 9; x < 16; x = x + 2) {
				if (getStoredItemType(x).equals(item.getClassName())) {
					item.x().set(x);
					item.y().set(dungeon.getHeight() + 2);
					return;
				} 
				i++;
			}		
		}
		
		for (int x = 9; x < 16; x = x + 2) {
			if (getStoredItemType(x).equals("None")) {
				item.x().set(x);
				item.y().set(dungeon.getHeight() + 2);
				break;
			} 
			i++;
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
	
	// temp testing: print out the inventory
	// REMOVE THIS FUNCTION LATER
	public void printInventory() {
		System.out.println("Inventory: [");
		for (Item i : items) {
			System.out.println(i + ",");
		}
		System.out.println("]");
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
	 * gets the items from the inventory
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
	
	// not in use - use to show the number of items the player has later?s
//	/**
//	 * Counts the number of items that's in the inventory by its name
//	 * @param name
//	 * @return
//	 */
//	public int countItemByType(String name) {
//		int count = 0;
//		for (Item item : items) {
//			if (item.sameName(name)) {
//				count++;
//			}
//		}
//		return count;
//	}
}