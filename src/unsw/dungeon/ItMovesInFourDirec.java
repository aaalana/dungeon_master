package unsw.dungeon;

/**
 * Concrete class:
 * Entities that have this move strategy are able to move in four directions given a direction.
 * This strategy is part of the strategy pattern that differentiates entities
 * as those that can move and can't move.
 * @author z5209503
 *
 */
public class ItMovesInFourDirec implements MoveStrategy {

	@Override
	public void move(String direction, Dungeon dungeon, Entity entity) {
		if (direction.equals("up")) {
			moveUp(dungeon, entity);
		} else if (direction.equals("down")) {
			moveDown(dungeon, entity);
		} else if (direction.equals("left")) {
			moveLeft(dungeon, entity);
		} else if (direction.equals("right")) {
			moveRight(dungeon, entity);
		}
	}
	
	/**
	 * Moves the entity up one square
	 * @param dungeon
	 * @param entity
	 */
	public void moveUp(Dungeon dungeon, Entity entity) {
        if (entity.getY() > 0)
        	entity.setPosition(entity.getX(), entity.getY() - 1);
    }

	/**
	 * Moves the entity down one square
	 * @param dungeon
	 * @param entity
	 */
    public void moveDown(Dungeon dungeon, Entity entity) {
        if (entity.getY() < dungeon.getHeight() - 1)
        	entity.setPosition(entity.getX(), entity.getY() + 1);
    }

    /**
	 * Moves the entity left one square
	 * @param dungeon
	 * @param entity
	 */
    public void moveLeft(Dungeon dungeon, Entity entity) {
        if (entity.getX() > 0)
        	entity.setPosition(entity.getX() - 1, entity.getY());
    }

    /**
	 * Moves the entity right one square
	 * @param dungeon
	 * @param entity
	 */
    public void moveRight(Dungeon dungeon, Entity entity) {
        if (entity.getX() < dungeon.getWidth() - 1)
        	entity.setPosition(entity.getX() + 1, entity.getY());
    }
}
