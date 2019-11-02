package unsw.dungeon;

public class Wall extends Blocker {
    
	public Wall(int x, int y, MoveStrategy movementType) {
        super(x, y, movementType);
    }
	
	@Override
	public boolean block(Player player) {
		return true;
	}
}
