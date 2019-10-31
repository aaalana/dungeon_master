package unsw.dungeon;

public class Switch extends Entity {
	private boolean triggered;

    public Switch(int x, int y) {
        super(x, y);
        this.triggered = false;
    }
 
    /**
     * triggers the switch represented by setting its state as true
     */
    public void trigger(Dungeon dungeon){
    	if (dungeon.shareSquare(this, "switch") instanceof Boulder) {
    		this.triggered = true;
    	}
    }
    
    /**
     * Returns the state of the switch
     * @return
     */
    public boolean getState() {
    	return triggered;
    }
}
