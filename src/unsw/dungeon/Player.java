package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends LivingCreature {
	
	private Dungeon dungeon;
    private PlayerState invincibilityState;
    private PlayerState normalState;
    private PlayerState deadState;
    private PlayerState speedState;
    private PlayerState state;
    private Inventory inventory;
   
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(int x, int y, Dungeon dungeon) {
        super(x, y);
        this.dungeon = dungeon;
        inventory = new Inventory(dungeon);
        invincibilityState = new InvincibilityState(this);
        normalState = new NormalState(this);
        deadState = new DeadState(this);
        speedState = new SpeedState(this);
        this.state = normalState;
    }
    	
    /**
     * Changes the player's state to invincibilityState
     * @param potion
     */
    public void drinkPotion(Item potion) {
    	state.drinkPotion(potion);
    }
	
    /**
     * Changes the player's state to normalState
     * @param potion
     */
    public void expelPotion(Item potion) {
    	state.expelPotion(potion);
    }
    
    /**
     * Kills off the player by setting the player to deadState
     */
    public void killOff() {
    	state.killPlayer();
    }
    
    /**
     * Gets the invincibilityState
     * @return invincibilityState
     */
    public PlayerState getInvincibilityState() {
		return invincibilityState;
	}
    
    /**
	 * Gets the speedState
	 * @return deadState
	 */
	public PlayerState getSpeedState() {
		return speedState;
	}
	
    /**
     * Gets the normalState
     * @return normalState
     */
	public PlayerState getNormalState() {
		return normalState;
	}

	/**
	 * Gets the deadState
	 * @return deadState
	 */
	public PlayerState getDeadState() {
		return deadState;
	}
	
	/**
	 * Sets the player's state
	 * @param state
	 */
	public void setState(PlayerState state) {
		this.state = state;
	}

	/**
	 * Gets the player's state
	 * @return player's state
	 */
	public PlayerState getState() {
		return state;
	}
	
	public boolean isInvincible() {
		return state instanceof InvincibilityState;
	}
	
	/**
	 * Adds an item to the inventory. Swords and Keys can only be picked up one at a time.
	 * @param item
	 * @return true when the item has been
	 */
    public boolean collectItem(Item item) {
    	if (item == null) return false;
    	return inventory.addItem(this, item);
    }
   
	/**
	 * Allows the player to removes an item from the inventory
	 * @param item
	 */
	public void removeItem(Item item) {
		inventory.removeItem(item);
	}
	
	/**
	 * Allows the player to get an item from the inventory
	 * @param name
	 * @return
	 */
	public Item getItem(String name) {
		return inventory.getItemByName(name);
	}
	
    /**
     * If a player has a sword, the player can attempt to use the sword to kill surrounding enemies
     */
    public void useSword() {
    	if (inventory.getItemByName("sword") != null) {
    		Sword sword = (Sword) inventory.getItemByName("sword");
    		
    		// sets the sword status to being used
    		sword.useItem(this);
    		
    		// kills the enemy is in range of 2 squares from the player
    		dungeon.killCreature(sword);
    		
    		// reset the sword back to not being in use
    		sword.useItem(this);
    		
    		// remove the item from the inventory when the sword is all used up
    		if (sword.getUses() == 0) {
    			System.out.println("Sword was used up.");
    			removeItem(sword);
    		}
    	}
    }
}