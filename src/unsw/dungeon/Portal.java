package unsw.dungeon;

public class Portal extends Obstacle {
	// to pair match portals
	private int id; 
	
	public Portal(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

	@Override
	public void trigger(boolean state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}
}
