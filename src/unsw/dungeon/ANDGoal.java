package unsw.dungeon;

import java.util.ArrayList;

public class ANDGoal extends GoalCombinations implements GoalComponent {
	
	private ArrayList<Goal> goals;
	
	public ANDGoal() {
		this.goals = new ArrayList<>();
	}

	@Override
	public void addGoal(Goal goal) {
		// TODO Auto-generated method stub
		goals.add(goal);
	}
	
	
}
