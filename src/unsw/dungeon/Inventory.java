package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> items;
	
	public Inventory() {
		this.items = new ArrayList<Item>();
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
}
