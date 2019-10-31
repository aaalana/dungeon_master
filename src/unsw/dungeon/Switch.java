package unsw.dungeon;

public class Switch extends Obstacle {
	private boolean triggered;

    public Switch(int x, int y) {
        super(x, y);
        this.triggered = false;
    }
 
    @Override
    public void trigger(boolean state){
    	// temporary testing - remove later
    	System.out.println("------");
    	if (state)
    		System.out.println("switch triggered!");
    	else
    		System.out.println("switch untriggered!");
    	System.out.println("------");
    	
    	this.triggered = state;
    	
    }
    
	@Override
    public boolean getState() {
    	return triggered;
    }
}