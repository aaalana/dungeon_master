package unsw.dungeon;

public class Portal extends Entity {
	private Dungeon dungeon;
	public Portal(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
}
