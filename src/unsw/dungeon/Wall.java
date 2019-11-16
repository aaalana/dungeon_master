package unsw.dungeon;

/**
 * Wall entity
 * @author Alana Hua
 *
 */
public class Wall extends Blocker {
    
	public Wall(int x, int y, MoveStrategy movementType) {
        super(x, y, movementType);
    }
	
	@Override
	public boolean block(Player player) {
		// all walls should block
		return true;
	}
}
