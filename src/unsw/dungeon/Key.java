package unsw.dungeon;

public class Key extends Item {
	private int id;
	
	public Key(int x, int y, int id) {
        super(x, y);
        this.id = id;
	}

	@Override
	public void useItem(Player player) {
		// TODO Auto-generated method stub
		
	}
}
