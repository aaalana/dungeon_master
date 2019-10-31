package unsw.dungeon;
import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private PlayerState invincibilityState;
    private PlayerState normalState;
    private PlayerState state;
    // WE MIGHT NEED AN ITEMS INTERFACE
    private ArrayList<Entity> inventory;
   
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        invincibilityState = new InvincibilityState(this, dungeon);
        normalState = new NormalState(this, dungeon);
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
    
    public void drinkInvincibilityPotion(InvincibilityPotion potion) {
    	state.drinkInvincibilityPotion(potion);
    }
	
    public void expelInvincibilityPotion(InvincibilityPotion potion) {
    	state.expelInvincibilityPotion(potion);
    }
    
    public PlayerState getInvincibilityState() {
		return invincibilityState;
	}

	public PlayerState getNormalState() {
		return normalState;
	}

	public void setState(PlayerState s) {
		this.state = s;
	}

	/**
	 * Add an item to the inventory
	 * @param item
	 * @return
	 */
    public void addItem(Entity item) {
    	if (item instanceof Sword && !hasSword()) {
    		inventory.add(item);
    	} else if (item instanceof Key && !hasKey()) {
    		inventory.add(item);
    	} else if (item instanceof InvincibilityPotion) {
    		inventory.add(item);
    		drinkInvincibilityPotion((InvincibilityPotion) item);
    	}
    }
	
    /**
     * Check if the player has a sword in the inventory
     * @return
     */
	public boolean hasSword() {
	   	for (Entity holding : inventory) {
    		if (holding instanceof Sword) {
    			return true;
    		}
    	}
		return false;
	}
	
	/**
	 * Check if the player has a key in the inventory
	 * @return
	 */
	public boolean hasKey() {
		for (Entity holding : inventory) {
    		if (holding instanceof Key) {
    			return true;
    		}
    	}
		return false;
	}
	
	/** 
	 * Check if the player has invincibility potion/s in the inventory
	 * @return
	 */
	public boolean hasInvincibilityPotion() {
		for (Entity holding : inventory) {
    		if (holding instanceof InvincibilityPotion) {
    			return true;
    		}
    	}
		return false;
	}
	
	/**
	 * Removes an item from the inventory
	 * @param item
	 */
	public void removeItem(Entity item) {
		inventory.remove(item);
	}
}
