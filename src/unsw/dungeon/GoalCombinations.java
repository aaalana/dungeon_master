package unsw.dungeon;

import java.util.ArrayList;

public class GoalCombinations extends GoalCombination {
	
	private ArrayList<ANDGoal> ANDGoals;
	private ArrayList<ORGoal> ORGoals;
	
	public GoalCombinations() {
		this.ANDGoals = new ArrayList<>();
		this.ORGoals = new ArrayList<>();
	}

	private void addANDGoal(ANDGoal goal) {
		ANDGoals.add(goal);
	}
	
	private void addORGoal(ORGoal goal) {
		ORGoals.add(goal);
	}
}
