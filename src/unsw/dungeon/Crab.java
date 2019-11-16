package unsw.dungeon;

/**
 * Crab Entity
 * @author Arthur Wong
 *
 */
public class Crab extends Enemy {
	
	private Dungeon dungeon;
	private int count;
	private String direction;
	
    public Crab(Dungeon dungeon, int x, int y, MoveStrategy move) {
        super(x, y, move);
        this.dungeon = dungeon;
        count = 0;
        direction = "left";
    }

    /**
     * The enemy will take in the player's co-ordinates and finds the best way to reach
     * those co-ordinates.
     * @param x
     * @param y
     */
    @Override
    public void searchPlayer(int playerX, int playerY, boolean isInvin) {
    	this.tryToMove(direction, dungeon, this);
    	if (direction == "left") {
    		count++;
    		if (count == 3) direction = "right";
    	} else {
    		count--;
    		if (count == 0) direction = "left";
    	}
    }
}