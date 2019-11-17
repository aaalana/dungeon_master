package unsw.dungeon;

/**
 * Archer entity which moves in four directions
 * @author Arthur Wong
 *
 */
public class Archer extends Enemy {
	
	private Dungeon dungeon;
	
    public Archer(Dungeon dungeon, int x, int y, MoveStrategy move) {
        super(x, y, move);
        this.dungeon = dungeon;
    }

    @Override
    public void searchPlayer(int playerX, int playerY, boolean isInvin) {
    	// the archer should only approach the player when they are not in 
		// invincible state. Otherwise, the archer should run away from the player
    	
    	// moving the archer horizontally
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
    	
    	// moving the archer vertically
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