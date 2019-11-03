package unsw.dungeon;

import java.util.ArrayList;

public class ORGoal extends GoalCombinations implements GoalComponent {
	
	private ArrayList<Goal> goals;
	
	public ORGoal(Dungeon dungeon) {
		// TODO Auto-generated constructor stub
		super(dungeon);
		this.goals = new ArrayList<>();
	}

	@Override
	public void addGoal(Goal goal) {
		// TODO Auto-generated method stub
		goals.add(goal);
	}

}
