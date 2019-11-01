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
    private PlayerState state;
    private ArrayList<Item> inventory;
   
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        inventory = new ArrayList<Item>();
        invincibilityState = new InvincibilityState(this);
        normalState = new NormalState(this);
        deadState = new DeadState(this);
        this.state = normalState;
    }

	public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    
    /**
     * 
     * @param potion
     */
    public void drinkInvincibilityPotion(Item potion) {
    	state.drinkInvincibilityPotion(potion);
    }
	
    /**
     * 
     * @param potion
     */
    public void expelInvincibilityPotion(Item potion) {
    	state.expelInvincibilityPotion(potion);
    }
    
    /**
     * 
     */
    public void killOff() {
    	state.killPlayer();
    }
    
    /**
     * 
     * @return
     */
    public PlayerState getInvincibilityState() {
		return invincibilityState;
	}
    
    /**
     * 
     * @return
     */
	public PlayerState getNormalState() {
		return normalState;
	}

	/**
	 * 
	 * @return
	 */
	public PlayerState getDeadState() {
		return deadState;
	}
	
	/**
	 * 
	 * @param s
	 */
	public void setState(PlayerState s) {
		this.state = s;
	}

	/**
	 * 
	 * @return
	 */
	public PlayerState getState() {
		return state;
	}
	
	/**
	 * Adds an item to the inventory. Swords and Keys can only be picked up one at a time.
	 * @param item
	 * @return
	 */
    public void collectItem(Item item) {
    	if (item instanceof Sword && !hasCertainItem(item)) {
    		inventory.add(item);
    		dungeon.removeItem(item);
    	} else if (item instanceof Key && !hasCertainItem(item))  {
    		inventory.add(item);
    		dungeon.removeItem(item);
    	} else if (item instanceof InvincibilityPotion) {
    		inventory.add(item);
    		drinkInvincibilityPotion(item);
    		dungeon.removeItem(item);
    	} else if (!(item instanceof Sword) && !(item instanceof Key)) {
    		inventory.add(item);
    		dungeon.removeItem(item);
    	}
    	
    	// temp testing: print out the inventory
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
		for (Item i : inventory) {
    		if (i.getClass().equals(obj.getClass())) {
    			return true;
    		} 
    	}
		return false;
	}
	
	/**
	 * Removes an item from the inventory
	 * @param item
	 */
	public void removeItem(Item item) {
		inventory.remove(item);
	}
}