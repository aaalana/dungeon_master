package unsw.dungeon;
import java.util.ArrayList;

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
    private ArrayList<Item> inventory;
   
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(int x, int y, Dungeon dungeon) {
        super(x, y);
        this.dungeon = dungeon;
        inventory = new ArrayList<Item>();
        invincibilityState = new InvincibilityState(this);
        normalState = new NormalState(this);
        deadState = new DeadState(this);
        speedState = new SpeedState(this);
        this.state = normalState;
    }
    
    /**
     * Teleports the player to another location. 
     * This is used when the player enters a portal
     * @param x
     * @param y
     */
    public void teleport(int x, int y) {
    	x().set(x);
    	y().set(y);
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
	
	/**
	 * Adds an item to the inventory. Swords and Keys can only be picked up one at a time.
	 * @param item
	 * @return true when the item has been
	 */
    public boolean collectItem(Item item) {
    	if (item == null)
    		return false;
    	
    	if (item instanceof Sword && !hasCertainItem(item)) {
    		inventory.add(item);
    		printInventory();
    		return true;
    	} else if (item instanceof Key && !hasCertainItem(item))  {
    		inventory.add(item);
    		printInventory();
    		return true;
    	} else if (item instanceof InvincibilityPotion && getItemByName("speed") == null) {
    		if (getItemByName("invincibility") == null) {
    			inventory.add(item);
    			drinkPotion(item);
    		} else {
    			inventory.add(item);
    		}
    		printInventory();
    		return true;
    	} else if (item instanceof SpeedPotion && getItemByName("invincibility") == null) { 
    		if (getItemByName("speed") == null) {
    			inventory.add(item);
    			drinkPotion(item);
    		} else {
    			inventory.add(item);
    		}
    		printInventory();
    		return true;
    	} else if (!(item instanceof Sword) && !(item instanceof Key)) {
    		inventory.add(item);
    		printInventory();
    		return true;
    	} 
    	return false;
    }
    
 // temp testing: print out the inventory
 // REMOVE THIS FUNCTION LATER
    public void printInventory() {
		System.out.println("Inventory: [");
		for (Item i : inventory) {
			System.out.println(i + ",");
		}
		System.out.println("]");
    }
    
    /**
     * Check if the player has a sword in the inventory
     * @return
     */
	public boolean hasCertainItem(Item obj) {
		for (Item item : inventory) {
    		if (item.isSameItem(obj)) {
    			return true;
    		} 
    	}
		return false;
	}
	
	/**
	 * -gets an item from the inventory given its name
	 * -this function is most useful for items that can 
	 *  only be collected once 
	 * @param item
	 * @return type of item
	 */
	public Item getItemByName(String name) {
		for (Item item : inventory) {
			if (item.sameName(name)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Removes an item from the inventory
	 * @param item
	 */
	public void removeItem(Item item) {
		inventory.remove(item);
	}
	
    /**
     * If a player has a sword, the player can attempt to use the sword to kill surrounding enemies
     */
    public void useSword() {
    	if (getItemByName("sword") != null) {
    		Sword sword = (Sword) getItemByName("sword");
    		
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