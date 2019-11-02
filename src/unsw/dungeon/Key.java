package unsw.dungeon;

public class Key extends Item {
	private int id;
	private boolean usable;
	
	public Key(int x, int y, int id) {
        super(x, y, "key");
        this.id = id;
        this.usable = true;
	}

	public void useItem(Player player, Door door) {
	    if (door.getId() == this.id) {
	    	door.unlock();
	    	usedUp();
	    }
	}

	@Override
	public void usedUp() {
		this.usable = false;
	}
}
