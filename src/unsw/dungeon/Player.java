package unsw.dungeon;
//import Java.util.ArrayList;

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
    
    //private ArrayList<Item> inventory;
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        invincibilityState = new InvincibilityState(this);
        normalState = new NormalState(this);
        this.state = normalState;
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
    
    /*
    public Item getItem() {
    	for (Item i: inventory) {
    		
    	}
    }*/
}
