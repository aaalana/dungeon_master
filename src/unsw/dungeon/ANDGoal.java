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
		// TODO Auto-generated method stub
		goals.add(goal);
	}
	
	public boolean completed() {
		for (Goal goal : goals) {
			if (goal.getStatus() == false) {
				return false;
			}
		}
		
		//Implement for what happens if a GoalCombination has another GoalCombination
		return true;
	}
	
}
