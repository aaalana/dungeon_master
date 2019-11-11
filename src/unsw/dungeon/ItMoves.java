package unsw.dungeon;

import javafx.beans.property.IntegerProperty;

public class ItMoves implements MoveStrategy {

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
        	getYProperty(entity).set(entity.getY() - 1);
    }

	/**
	 * Moves the entity down one square
	 * @param dungeon
	 * @param entity
	 */
    public void moveDown(Dungeon dungeon, Entity entity) {
        if (entity.getY() < dungeon.getHeight() - 1)
        	getYProperty(entity).set(entity.getY() + 1);
    }

    /**
	 * Moves the entity left one square
	 * @param dungeon
	 * @param entity
	 */
    public void moveLeft(Dungeon dungeon, Entity entity) {
        if (entity.getX() > 0)
        	getXProperty(entity).set(entity.getX() - 1);
    }

    /**
	 * Moves the entity right one square
	 * @param dungeon
	 * @param entity
	 */
    public void moveRight(Dungeon dungeon, Entity entity) {
        if (entity.getX() < dungeon.getWidth() - 1)
        	getXProperty(entity).set(entity.getX() + 1);
    }
    
    /**
     * Gets the horizontal Integer property of an entity's coordinates
     * @param entity
     * @return x Integer property
     */
    public IntegerProperty getXProperty(Entity entity) {
    	return entity.x();
    }
    
    /**
     * Gets the vertical Integer property of an entity's coordinates
     * @param entity
     * @return y Integer property
     */
    public IntegerProperty getYProperty(Entity entity) {
    	return entity.y();
    }
}
