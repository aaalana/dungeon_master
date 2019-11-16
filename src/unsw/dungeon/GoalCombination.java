package unsw.dungeon;

public abstract class GoalCombination extends Goal {
	public GoalCombination(Dungeon dungeon) {
		super(dungeon);
	}
	
	public abstract boolean getStatus();
}
