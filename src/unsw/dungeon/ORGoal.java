package unsw.dungeon;

import java.util.ArrayList;

public class ORGoal extends GoalCombination implements GoalComponent {
	
	private ArrayList<Goal> goals;
	
	public ORGoal(Dungeon dungeon) {
		super(dungeon);
		this.goals = new ArrayList<>();
	}

	@Override
	public void addGoal(Goal goal) {
		goals.add(goal);
	}
	
	/**
	 * Checks the status of the goal when it is an OR goal
	 * @return true when at least one goal has been completed and false otherwise
	 */
	public boolean getStatus() {
		System.out.println("Checking if all the goals were completed");
		for (Goal goal : goals) {
			if (goal.getStatus() == true) {
				System.out.println("The goal: " + goal.getClass().getName() + " was completed");
				return true;
			}
		}
		
		//Implement for what happens if a GoalCombination has another GoalCombination
		return false;
	}
}
