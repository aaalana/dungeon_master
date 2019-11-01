package unsw.dungeon;


public class Enemy extends LivingCreature {
	private Dungeon dungeon;
	
    public Enemy(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.dungeon = dungeon;
    }

    /**
     * The enemy will take in a set of co-ordinates and find the best way to reach
     * those co-ordinates.
     * @param x
     * @param y
     */
    public void searchPlayer(int playerX, int playerY) {
//    	System.out.println("Player is at (" + playerX  + ", " + playerY + ")");
//    	System.out.println("Enemy is at (" + getX() + ", " + getY() + ")");
    	//Try and move horizontally to find the player
    	if (getX() != playerX) {
    		if (getX() > playerX && !dungeon.checkWall(getX() - 1, getY())) {
    			moveLeft();
    			return;
    		} else if (getX() < playerX && !dungeon.checkWall(getX() + 1, getY())) {
    			moveRight();
    			return;
    		}
    	}
    	
    	if (getY() != playerY) {
    		if (getY() > playerY && !dungeon.checkWall(getX(), getY() - 1)) {
    			moveUp();
    			return;
    		} else if (getY() < playerY && !dungeon.checkWall(getX(), getY() + 1)) {
    			moveDown();
    			return;
    		}
    	}
    	
    }
    
    @Override
    public void killOff() {
    	
    }
}