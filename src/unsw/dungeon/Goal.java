package unsw.dungeon;

public class Goal implements Subject {
	private boolean completed;
	private Dungeon dungeon;
	
	public Goal(Dungeon dungeon) {
		this.completed = false;
		this.dungeon = dungeon;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
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
		this.update();
	}
	
	public void update() {
		dungeon.update();
	}


	public void updateGoal() {
		this.setComplete();
		dungeon.update();
	}

}
