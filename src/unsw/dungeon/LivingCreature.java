package unsw.dungeon;

public abstract class LivingCreature extends Entity {

	public LivingCreature(int x, int y) {
		super(x, y, new ItMoves());
	}
	
	public abstract void killOff();
}
