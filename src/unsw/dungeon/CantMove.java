package unsw.dungeon;

/**
* Concrete class:
* Entities that have this move strategy are not allowed to move. 
* This strategy is part of the strategy pattern that differentiates entities
* as those that can move and can't move.
* @author Alana Hua
*/
public class CantMove implements MoveStrategy {

	@Override
	public void move(String direction, Dungeon dungeon, Entity entity) {
		System.out.println("I can't move.");
	}
}
