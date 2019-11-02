package unsw.dungeon;

public class CantMove implements MoveStrategy {

	@Override
	public void move(String direction, Dungeon dungeon, Entity entity) {
		// TODO Auto-generated method stub
		System.out.println("I can't move.");
	}
}
