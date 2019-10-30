package unsw.dungeon;

public class Exit extends Entity {
	
	private String name = "exit";
	public Exit(int x, int y) {
		super(x, y);
	    this.name = "exit";
	}
	
	public boolean triggered() {
		return false;
	}
	
}
