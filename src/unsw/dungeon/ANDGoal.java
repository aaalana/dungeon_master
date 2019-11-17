package unsw.dungeon;

import java.util.ArrayList;

/**
 * AND Goal which collects all subgoals that all must be completed together
 * @author Alana Hua
 * @author Arthur Wong
 *
 */
public class ANDGoal extends GoalCombination implements GoalComponent {
	
	private ArrayList<Goal> goals;
	private ArrayList<GoalCombination> nestedGoals;
	
	public ANDGoal(Dungeon dungeon) {
		super(dungeon);
		this.goals = new ArrayList<>();
		this.nestedGoals = new ArrayList<>();
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
	public void addGoal(GoalCombination goal) {
		nestedGoals.add(goal);
	}
	
	@Override
	public boolean getStatus() {
		// Checks the status of the goal when it is an AND goal
		// returns true only if all goals are completed and false otherwise
		
//		System.out.println("Checking if all the goals were completed");
		for (Goal goal : goals) {
			if (!goal.getStatus()) {
//				System.out.println("The goal: " + goal.getClassName() + " was not completed");
				return false;
			}
		}
		
		for (Goal goal: goals) {
			if (!goal.getStatus()) {
//				System.out.println("The goal: " + goal.getClassName() + " was not completed");
				return false;
			}
		}
		
		return true;
	}
	
}
