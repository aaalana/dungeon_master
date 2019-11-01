package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

public class BoulderSystem {
	private List<Boulder> boulders;
	private Dungeon dungeon;
	
	public BoulderSystem(Dungeon dungeon) {
		super();
		this.boulders = new ArrayList<Boulder>(); 
		this.dungeon = dungeon;
	}

	public void addBoulder(Entity boulder) {
		this.boulders.add((Boulder) boulder);
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
    		
    		//If there's a boulder at the specified position
    		if (boulder.getX() == x && boulder.getY() == y) {
    			//If there's a boulder or a wall at the position next to the boulder, then return false
    			if (direction == "left" && (dungeon.checkSquare(x - 1, y) == "unsw.dungeon.Boulder" || dungeon.checkSquare(x - 1, y) == "unsw.dungeon.Wall")) return false;
    			if (direction == "right" && (dungeon.checkSquare(x + 1, y) == "unsw.dungeon.Boulder" || dungeon.checkSquare(x + 1, y) == "unsw.dungeon.Wall")) return false;
    			if (direction == "up" && (dungeon.checkSquare(x, y + 1) == "unsw.dungeon.Bouder" || dungeon.checkSquare(x, y + 1) == "unsw.dungeon.Wall")) return false;
    			if (direction == "down" && (dungeon.checkSquare(x, y - 1) == "unsw.dungeon.Boulder" || dungeon.checkSquare(x, y - 1) == "unsw.dungeon.Wall")) return false;
    			    			
    			//If not, then move the boulder and return true
    			if (direction == "left") {
    				boulder.moveLeft();
    			} else if (direction == "right") {
    				boulder.moveRight();
    			} else if (direction == "up") {
    				boulder.moveUp();
    			} else if (direction == "down") {
    				boulder.moveDown();
    			}	
    			
    			return true;
    		}
    	}
    	
    	//If there's no boulder there, return true
    	return true;
    }
}
