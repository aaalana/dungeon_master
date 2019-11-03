package unsw.dungeon;

import java.util.ArrayList;

public class GoalCombination extends Goal {
	private ArrayList<GoalCombinations> subGoals;

	public GoalCombination(Dungeon dungeon) {
		super(dungeon);
		this.subGoals = new ArrayList<>();
	}

	private void addGoal(GoalCombinations goal) {
		subGoals.add(goal);
	}

	
}
