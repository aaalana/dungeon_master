package unsw.dungeon;

/**
 * Treasure entity
 * @author Alana Hua
 *
 */
public class Treasure extends Item {

    public Treasure(int x, int y) {
        super(x, y, "treasure");
    }

	@Override
	public void useItem(Player player) {
		System.out.println("Treasure can't be used, only collected.");
	}
}