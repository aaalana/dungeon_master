package unsw.dungeon;

public class Switch extends Entity {
	private SwitchState triggeredState;
	private SwitchState untriggeredState;
	private SwitchState state;
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
    
    public void setState(SwitchState state) {
        this.state = state;		
    }
    
    public SwitchState getState(){
        return state;
    }
    
    public SwitchState getTriggeredState() {
		return triggeredState;
	}

	public SwitchState getUntriggeredState() {
		return untriggeredState;
	}
}
