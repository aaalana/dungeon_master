package unsw.dungeon;

import java.util.ArrayList;

/**
 * AND Goal which collects all subgoals
 * @author Alana Hua
 * @author Arthur Wong
 *
 */
public class ANDGoal extends GoalCombination implements GoalComponent {
	
	private ArrayList<Goal> goals;
	
	public ANDGoal(Dungeon dungeon) {
		super(dungeon);
		this.goals = new ArrayList<>();
	}

	@Override
	public void addGoal(Goal goal) {
		goals.add(goal);
	}
	
	@Override
	public ArrayList<Goal> getGoal() {
		return goals;
	}
	
	@Override
	public boolean getStatus() {
		// Checks the status of the goal when it is an AND goal
		// returns true when all goals are completed and false otherwise
		
		System.out.println("Checking if all the goals were completed");
		for (Goal goal : goals) {
			if (!goal.getStatus()) {
				System.out.println("The goal: " + goal.getClassName() + " was not completed");
				return false;
			}
		}
		
		//Implement for what happens if a GoalCombination has another GoalCombination
		return true;
	}
	
}
