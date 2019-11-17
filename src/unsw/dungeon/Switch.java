package unsw.dungeon;

/**
 * Switch entity which can become triggered and untriggered by 
 * boulders
 * @author Alana Hua
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
    
    @Override
    public void setExitGoal(ExitGoal e) {
    	// Unused:
	    // Used in exit class (method in Obstacle to avoid 
	    // typecasting obstacles to exits)
    }
   
    /**
     * Gets the state of the switch
     * @return
     */
    public boolean getState() {
    	return triggered;
    }
}