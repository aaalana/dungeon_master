package unsw.dungeon;

import java.util.ArrayList;

/**
 * 
 * @author Arthur Wong
 *
 */
public interface GoalComponent {
	public void addGoal(Goal goal);
	public ArrayList<Goal> getGoal();
}
