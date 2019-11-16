package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

/**
 * Boulder System responsible for pushing boulders
 * @author Arthur Wong
 *
 */
public class BoulderSystem {
	private List<Boulder> boulders;
	private Dungeon dungeon;
	
	public BoulderSystem(Dungeon dungeon) {
		super();
		this.boulders = new ArrayList<Boulder>(); 
		this.dungeon = dungeon;
	}

	/**
	 * Adds a boulder to the boulder system
	 * @param boulder
	 */
	public void addBoulder(Entity boulder) {
		this.boulders.add((Boulder) boulder);
	}
	
	/**
	 * Checks if a boulder lies in the position where
	 * x and y parameters are defined
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkBoulder(int x, int y) {
		for (Boulder boulder : this.boulders) {
			if (boulder.getX() == x && boulder.getY() == y) {
				return true;
			}
		}
		return false;
	}
	
	 /**
     * This function returns true if there are not more than two boulders aligned in the specified direction
     * @param x
     * @param y
     * @param direction
     * @return
     */
    public boolean pushBoulder(int x, int y, String direction) {
    	for (Boulder boulder : this.boulders) {
    		if (boulder == null) continue;
    		
    		// If there's a boulder at the specified position
    		if (boulder.getX() == x && boulder.getY() == y) {
    			// If there's a boulder or a wall at the position next to the boulder, then return false
    			if (direction == "left" && (this.checkBoulder(x - 1, y) == true || dungeon.checkSquare(x - 1, y) == "unsw.dungeon.Wall")) return false;
    			if (direction == "right" && (this.checkBoulder(x + 1, y) == true || dungeon.checkSquare(x + 1, y) == "unsw.dungeon.Wall")) return false;
    			if (direction == "up" && (this.checkBoulder(x, y - 1) == true || dungeon.checkSquare(x, y - 1) == "unsw.dungeon.Wall")) return false;
    			if (direction == "down" && (this.checkBoulder(x, y + 1) == true || dungeon.checkSquare(x, y + 1) == "unsw.dungeon.Wall")) return false;
    			
    			// If not, then move the boulder and return true
    			boulder.tryToMove(direction, dungeon, boulder);
    			return true;
    		}
    	}
    	//If there's no boulder there, return true
    	return true;
    }
}
