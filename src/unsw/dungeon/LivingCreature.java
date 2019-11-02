package unsw.dungeon;

public abstract class LivingCreature extends Entity {

	private Dungeon dungeon;
	public LivingCreature(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	public abstract void killOff();
}
