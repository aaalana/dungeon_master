package unsw.dungeon;

import java.util.ArrayList;

public class GoalCombination {
	private ArrayList<GoalCombinations> goals;

	public GoalCombination() {
		this.goals = new ArrayList<>();
	}

	private void addGoal(GoalCombinations goal) {
		goals.add(goal);
	}

	private boolean completed() {
		return false;
	}
	
	
}
