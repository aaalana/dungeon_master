package unsw.dungeon;

/**
 * Polymorphism for goal
 * @author Arthur Wong
 *
 */
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
	 * Gets the type of goal in the form of a string
	 * i.e. the name of the goal
	 * @return
	 */
	public String getClassName() {
		return getClass().getName();
	}
	
	/**
	 * Sets the goal as complete
	 */
	public void setComplete() {
		this.completed = true;
		dungeon.updateGoal();
	}
	
	/**
	 * Updates the overall goal of the dungeon
	 */
	public void update() {
		dungeon.updateGoal();
	}

	/**
	 * Updates the overall goal of the dungeon and sets the goal as complete
	 */
	public void updateGoal() {
		this.setComplete();
		dungeon.updateGoal();
	}

}
