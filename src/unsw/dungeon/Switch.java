package unsw.dungeon;

/**
 * Switch entity
 * @author z5209503
 *
 */
public class Switch extends Obstacle {
	private boolean triggered;

    public Switch(int x, int y) {
        super(x, y);
        this.triggered = false;
    }
 
    @Override
    public void trigger(boolean state){
    	if (state)
    		System.out.println("switch triggered!");
    	else
    		System.out.println("switch untriggered!");
    	
    	// sets the triggered state of the switch
    	this.triggered = state;
    }
    
    public void setExitGoal(ExitGoal e) {
    	return;
    }
   
    public boolean getState() {
    	return triggered;
    }
}