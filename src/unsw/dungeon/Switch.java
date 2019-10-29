package unsw.dungeon;

public class Switch extends Entity {
	private boolean triggered;
	
    public Switch(int x, int y) {
        super(x, y);
        this.triggered = false;
    }

}
