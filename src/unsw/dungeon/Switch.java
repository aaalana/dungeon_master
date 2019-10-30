package unsw.dungeon;

public class Switch extends Entity {
	private switchState triggeredState;
	private switchState untriggeredState;
	private switchState state;
	private Dungeon dungeon;
	
    public Switch(int x, int y, Dungeon dungeon) {
        super(x, y);
        triggeredState = new TriggeredState(this);
        untriggeredState = new UntriggeredState(this);
        this.dungeon = dungeon;
        this.state = untriggeredState;
    }
  
	public Dungeon getDungeon() {
    	return this.dungeon;
    }
    
    public void setState(switchState state) {
        this.state = state;		
    }
    
    public switchState getState(){
        return state;
    }
    
    public switchState getTriggeredState() {
		return triggeredState;
	}

	public switchState getUntriggeredState() {
		return untriggeredState;
	}
}
