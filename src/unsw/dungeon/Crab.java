package unsw.dungeon;

/**
 * Crab Entity which can only move sideways.
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

    @Override
    public void searchPlayer(int playerX, int playerY, boolean isInvin) {
    	if ((direction == "left" && !dungeon.checkBlocker(getX() - 1, getY(), this)) ||
    		(direction == "right" && !dungeon.checkBlocker(getX() + 1, getY(), this))) 
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