package unsw.dungeon;

public class Sword extends Item {
	
	private boolean inUse;
	private int usesLeft = 5;
	
    public Sword(int x, int y) {
        super(x, y, "sword");
        this.inUse = false;
        this.usesLeft = 5;
    }

	@Override
	public void useItem(Player player) {
		if (this.inUse) {
			this.inUse = false;
		} else {
			this.inUse = true;
		}
	}
	
	/**
	 * Reduces the number of uses the sword has 
	 */
	public void reduceUses() {
		if (usesLeft != 0) {
			usesLeft--;
		}
		System.out.println("The sword has " + usesLeft + " use/s left.");
	}
	
	/**
	 * Gets the status of whether the sword is being in use or not
	 * @return
	 */
	public boolean getStatus() {
		return inUse;
	}
	
	/**
	 * Gets the number of uses the sword has 
	 * @return
	 */
	public int getUses() {
		return usesLeft;
	}
}