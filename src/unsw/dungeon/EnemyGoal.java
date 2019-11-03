package unsw.dungeon;

public class EnemyGoal extends Goal {

	public EnemyGoal(Dungeon dungeon) {
		super(dungeon);
	}
		
	public void updateGoal() {
		this.setComplete();
		Dungeon.update();
	}

}
