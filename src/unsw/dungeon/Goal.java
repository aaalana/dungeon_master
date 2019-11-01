package unsw.dungeon;

public class Goal {
	private boolean completed;
	
	public Goal() {
		this.completed = false;
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
}
