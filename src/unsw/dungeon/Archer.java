package unsw.dungeon;

public class Archer extends Enemy {
	
	private Dungeon dungeon;
	
    public Archer(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * The enemy will take in the player's co-ordinates and finds the best way to reach
     * those co-ordinates.
     * @param x
     * @param y
     */
    public void searchPlayer(int playerX, int playerY, boolean isInvin) {
    	if (getX() != playerX) {
    		if (!isInvin) {
    			if (getX() > playerX && !dungeon.checkBlocker(getX() - 1, getY())) {
        			this.tryToMove("left", dungeon, this);
        			return;
        		} else if (getX() < playerX && !dungeon.checkBlocker(getX() + 1, getY())) {
        			this.tryToMove("right", dungeon, this);
        			return;
        		}	
    		} else {
    			if (getX() > playerX && !dungeon.checkBlocker(getX() + 1, getY())) {
        			this.tryToMove("right", dungeon, this);
        			return;
        		} else if (getX() < playerX && !dungeon.checkBlocker(getX() - 1, getY())) {
        			this.tryToMove("left", dungeon, this);
        			return;
        		}	
    		}
    		
    	}
    	
    	if (getY() != playerY) {
    		if (!isInvin) {
    			if (getY() > playerY && !dungeon.checkBlocker(getX(), getY() - 1)) {
        			this.tryToMove("up", dungeon, this);
        			return;
        		} else if (getY() < playerY && !dungeon.checkBlocker(getX(), getY() + 1)) {
        			this.tryToMove("down", dungeon, this);
        			return;
        		}
    		} else {
    			if (getY() > playerY && !dungeon.checkBlocker(getX(), getY() + 1)) {
        			this.tryToMove("down", dungeon, this);
        			return;
        		} else if (getY() < playerY && !dungeon.checkBlocker(getX(), getY() - 1)) {
        			this.tryToMove("up", dungeon, this);
        			return;
        		}
    		}
    	}
    }
}