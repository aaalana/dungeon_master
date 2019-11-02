package unsw.dungeon;

public class Wall extends Blocker {
    
	public Wall(int x, int y) {
        super(x, y);
    }
	
	@Override
	public boolean block(Player player) {
		return true;
	}
}
