package unsw.dungeon;
import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends LivingCreature {
	
    private PlayerState invincibilityState;
    private PlayerState normalState;
    private PlayerState deadState;
    private PlayerState state;
    private ArrayList<Item> inventory;
   
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(int x, int y) {
        super(x, y);
        inventory = new ArrayList<Item>();
        invincibilityState = new InvincibilityState(this);
        normalState = new NormalState(this);
        deadState = new DeadState(this);
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
    public void drinkInvincibilityPotion(Item potion) {
    	state.drinkInvincibilityPotion(potion);
    }
	
    /**
     * Changes the player's state to normalState
     * @param potion
     */
    public void expelInvincibilityPotion(Item potion) {
    	state.expelInvincibilityPotion(potion);
    }
    
    /**
     * Changes the player's state to deadState
     * (the player dies)
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
    	if (item instanceof Sword && !hasCertainItem(item)) {
    		inventory.add(item);
    		printInventory();
    		return true;
    	} else if (item instanceof Key && !hasCertainItem(item))  {
    		inventory.add(item);
    		printInventory();
    		return true;
    	} else if (item instanceof InvincibilityPotion) {
    		inventory.add(item);
    		drinkInvincibilityPotion(item);
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
	 * -gets the class of an item given the item a player has
	 * -treasure cannot be received since it is not a usable item
	 * @param item
	 * @return type of item
	 */
	public Item getItemByName(String name) {
		for (Item item : inventory) {
			if (item.sameName(name) && !name.equals("treasure")) {
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
}