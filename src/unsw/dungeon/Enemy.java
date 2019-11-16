package unsw.dungeon;

public abstract class Enemy extends LivingCreature {
	
    public Enemy(int x, int y, MoveStrategy move) {
        super(x, y, move);
    }
    
    public abstract void searchPlayer(int playerX, int playerY, boolean isInvin);
}