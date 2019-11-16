package unsw.dungeon;

import java.util.ArrayList;

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
	
	/**
	 * Checks the status of the goal when it is an AND goal
	 * @return true when all goals are completed and false otherwise
	 */
	public boolean getStatus() {
		System.out.println("Checking if all the goals were completed");
		for (Goal goal : goals) {
			if (goal.getStatus() == false) {
				System.out.println("The goal: " + goal.getClassName() + " was not completed");
				return false;
			}
		}
		
		//Implement for what happens if a GoalCombination has another GoalCombination
		return true;
	}
	
}
