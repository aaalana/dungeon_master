package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> items;
	private DungeonControllerLoader.ImageManager imageManager;
	private boolean[] occupiedSlots;
	
	public Inventory() {
		this.items = new ArrayList<Item>();
		this.imageManager = new DungeonControllerLoader.ImageManager();
		this.occupiedSlots = new boolean[4];
	}
	
	public void addItem(Item item, Dungeon dungeon) {
		items.add(item);
		occupySlot(item, dungeon);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
		imageManager.removeImage(item.getImage());
	}
	
	public void occupySlot(Item item, Dungeon dungeon) {
		int i = 0;
		for (int x = 9; x < 16; x = x + 2) {
			if (!occupiedSlots[i]) {
				item.x().set(x);
				item.y().set(dungeon.getHeight() + 2);
				occupiedSlots[i] = true;
				break;
			}
		}	
	}
}
