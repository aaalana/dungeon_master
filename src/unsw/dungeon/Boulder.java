package unsw.dungeon;

/**
 * Boulder Entity 
 * @author Alana Hua
 *
 */
public class Boulder extends Blocker {
	
	public Boulder(int x, int y, MoveStrategy move) {
        super(x, y, move);
    }
  
	@Override
	public boolean block(Player player) {
		return false;
	}
}