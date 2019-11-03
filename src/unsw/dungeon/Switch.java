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
    	if (state)
    		System.out.println("switch triggered!");
    	else
    		System.out.println("switch untriggered!");
    	
    	this.triggered = state;
    }
   
    public boolean getState() {
    	return triggered;
    }
}