package unsw.dungeon;

public class Portal extends Entity {
	// to pair match portals
	private int id; 
	
	public Portal(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }
}
