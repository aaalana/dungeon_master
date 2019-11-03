package unsw.dungeon;

public class Goal implements Subject {
	private boolean completed;
	private Dungeon dungeon;
	
	public Goal(Dungeon dungeon) {
		this.completed = false;
		this.dungeon = dungeon;
	}
	
	/**
	 * returns a status which indicates whether the goal has been completed or not
	 * @return
	 */
	public boolean getStatus() {
		return completed;
	}
	
	/**
	 * 
	 */
	public void setComplete() {
		this.completed = true;
	}
	
	public void update() {
		dungeon.update();
	}
}
