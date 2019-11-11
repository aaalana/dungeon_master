package unsw.dungeon;

public class Enemy extends LivingCreature {
	
	private Dungeon dungeon;
	
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * The enemy will take in the player's co-ordinates and finds the best way to reach
     * those co-ordinates.
     * @param x
     * @param y
     */
    public void searchPlayer(int playerX, int playerY) {
//    	System.out.println("Player is at (" + playerX  + ", " + playerY + ")");
//    	System.out.println("Enemy is at (" + getX() + ", " + getY() + ")");
    	//Try and move horizontally to find the player
    	if (getX() != playerX) {
    		if (getX() > playerX && !dungeon.checkBlocker(getX() - 1, getY())) {
    			this.tryToMove("left", dungeon, this);
    			return;
    		} else if (getX() < playerX && !dungeon.checkBlocker(getX() + 1, getY())) {
    			this.tryToMove("right", dungeon, this);
    			return;
    		}
    	}
    	
    	if (getY() != playerY) {
    		if (getY() > playerY && !dungeon.checkBlocker(getX(), getY() - 1)) {
    			this.tryToMove("up", dungeon, this);
    			return;
    		} else if (getY() < playerY && !dungeon.checkBlocker(getX(), getY() + 1)) {
    			this.tryToMove("down", dungeon, this);
    			return;
    		}
    	}
    	
    }
}