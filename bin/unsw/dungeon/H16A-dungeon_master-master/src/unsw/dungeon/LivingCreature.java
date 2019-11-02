package unsw.dungeon;

public abstract class LivingCreature extends Entity {
	
	private Dungeon dungeon;
	public LivingCreature(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

    
    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    

	public abstract void killOff();
}