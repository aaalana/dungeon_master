package unsw.dungeon;

public class Key extends Item {
	private int id;
	private boolean usable;
	
	public Key(int x, int y, int id) {
        super(x, y, "key");
        this.id = id;
        this.usable = true;
	}

	@Override
	public void useItem(Player player) {
	    Item door = player.getItemByName("door");
		//if (door.getId() == this.id) {
	    //	door.unlock();
	    	usedUp();
	    //}
	}
	
	public Item getDoorToUnlock(Player player) {
		return player.getItemByName("key");
	}
	
	public void usedUp() {
		this.usable = false;
	}
}
