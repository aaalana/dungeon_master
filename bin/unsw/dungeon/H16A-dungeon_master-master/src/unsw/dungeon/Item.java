package unsw.dungeon;

public abstract class Item extends Entity {

	public Item(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void useItem(Player player);

}
