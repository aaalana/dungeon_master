package unsw.dungeon;

public class Boulder extends Blocker {

	private Dungeon dungeon;
	
	public Boulder(Dungeon dungeon, int x, int y, MoveStrategy move) {
        super(x, y, move);
        this.dungeon = dungeon;
    }
  
	@Override
	public boolean block(Player player) {
		// TODO Auto-generated method stub
		return true;
	}
}