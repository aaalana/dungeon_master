package unsw.dungeon;

/**
 * Goal combination responsible for managing and marking the 
 * completion of nested goals
 * @author Alana Hua
 *
 */
public abstract class GoalCombination extends Goal {
	public GoalCombination(Dungeon dungeon) {
		super(dungeon);
	}
	
	public abstract boolean getStatus();
}
