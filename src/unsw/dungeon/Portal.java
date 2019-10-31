package unsw.dungeon;

public class Portal extends Entity {
	// to pair match portals
	private String id; 
	
	public Portal(int x, int y, String id) {
        super(x, y);
        this.id = id;
    }
}
