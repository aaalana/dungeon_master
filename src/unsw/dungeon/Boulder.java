package unsw.dungeon;

/**
 * Boulder Entity which can be pushed by the player. 
 * This is managed by the boulder system.
 * @author Alana Hua
 *
 */
public class Boulder extends Blocker {
	
	public Boulder(int x, int y, MoveStrategy move) {
        super(x, y, move);
    }
  
	@Override
	public boolean block(Player player, Entity creature) {
		return false;
	}
}