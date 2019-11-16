package unsw.dungeon;

public class Boulder extends Blocker {
	
	public Boulder(int x, int y, MoveStrategy move) {
        super(x, y, move);
    }
  
	@Override
	public boolean block(Player player) {
		return false;
	}
}