package unsw.dungeon;

public abstract class Enemy extends LivingCreature {
	
    public Enemy(int x, int y) {
        super(x, y);
    }
    
    public abstract void searchPlayer(int playerX, int playerY, boolean isInvin);
}