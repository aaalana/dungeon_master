package unsw.dungeon;

/**
 * Exit entity used as a puzzle to complete the exit goal
 * @author Alana Hua
 *
 */
public class Exit extends Obstacle {
	private ExitGoal exitGoal;
	
	public Exit(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Gets the exit goal
	 */
	public ExitGoal getExitGoal() {
		return exitGoal;
	}

	/**
	 * Sets the exit goal 
	 */
	public void setExitGoal(ExitGoal exitGoal) {
		this.exitGoal = exitGoal;
	}
	
	@Override
	public void trigger(boolean state) {
		// End the application when leaving the maze
		if (state) {
			System.out.println("Player exited the dungeon.");
			exitGoal.setComplete();
		}
	}
}
