package unsw.dungeon;

import java.util.ArrayList;

/**
 * ORGoal used for grouping goals where only one is required to be completed
 * @author Alana Hua
 * @author Arthur Wong
 *
 */
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
	
	@Override
	public boolean getStatus() {
	    //Checks the status of the goal when it is an OR goal
		// returns true when at least one goal has been completed and false otherwise
		
		System.out.println("Checking if all the goals were completed");
		for (Goal goal : goals) {
			if (goal.getStatus() == true) {
				System.out.println("The goal: " + goal.getClassName() + " was completed");
				return true;
			}
		}
		
		//Implement for what happens if a GoalCombination has another GoalCombination
		return false;
	}
}
