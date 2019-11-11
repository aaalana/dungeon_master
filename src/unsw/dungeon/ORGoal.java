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
		// TODO Auto-generated method stub
		goals.add(goal);
	}
	
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
