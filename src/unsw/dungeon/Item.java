package unsw.dungeon;

/**
 * Polymorphism 
 * @author z5209503
 *
 */
public abstract class Item extends Entity {
	
	String name;
	
	public Item(int x, int y, String name) {
		super(x, y, new CantMove());
		this.name = name;
	}
	
	/**
	 * Gets the name of item
	 * @return item name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Checks if one item is the same type as an item passed in 
	 * @return item name
	 */
	public boolean isSameItem(Item comparedTo) {
		return checkItemType(this).equals(checkItemType(comparedTo));
	}
	
	/**
	 * Checks if one item has the same name as the another item
	 * @return item name
	 */
	public boolean sameName(String comparedTo) {
		return getName().equals(comparedTo);
	}
	
	/**
	 * gets the class of an item 
	 * (used to not break Delimiter's law for the isSameItem function)
	 * @param item
	 * @return type of item
	 */
	public Class<?> checkItemType(Item item) {
		return item.getClass();
	}
	
	public abstract void useItem(Player player);

}
