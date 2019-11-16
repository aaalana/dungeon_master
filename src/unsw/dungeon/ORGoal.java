package unsw.dungeon;

import java.util.ArrayList;

public class ORGoal extends GoalCombination implements GoalComponent {
	
	private ArrayList<Goal> goals;
	private ArrayList<GoalCombination> nestedGoals;
	
	public ORGoal(Dungeon dungeon) {
		super(dungeon);
		this.goals = new ArrayList<>();
		this.nestedGoals = new ArrayList<>();
	}

	@Override
	public void addGoal(Goal goal) {
		goals.add(goal);
	}
	
	@Override
	public void addGoal(GoalCombination goal) {
		nestedGoals.add(goal);
	}
	
	
	@Override
	public boolean getStatus() {
	    //Checks the status of the goal when it is an OR goal
		// returns true when any one goal has been completed and false otherwise
		
		System.out.println("Checking if all the goals were completed");
		for (Goal goal : goals) {
			if (goal.getStatus() == true) {
				System.out.println("The goal: " + goal.getClassName() + " was completed");
				return true;
			}
		}
		
		for (GoalCombination goal : nestedGoals) {
			if (goal.getStatus() == true) {
				System.out.println("The goal: " + goal.getClassName() + " was completed");
				return true;
			}
		}
		
		return false;
	}
}
