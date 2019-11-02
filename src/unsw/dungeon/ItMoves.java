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
	
	public void moveUp(Dungeon dungeon, Entity entity) {
        if (entity.getY() > 0)
        	getYProperty(entity).set(entity.getY() - 1);
    }

    public void moveDown(Dungeon dungeon, Entity entity) {
        if (entity.getY() < dungeon.getHeight() - 1)
        	getYProperty(entity).set(entity.getY() + 1);
    }

    public void moveLeft(Dungeon dungeon, Entity entity) {
        if (entity.getX() > 0)
        	getXProperty(entity).set(entity.getX() - 1);
    }

    public void moveRight(Dungeon dungeon, Entity entity) {
        if (entity.getX() < dungeon.getWidth() - 1)
        	getXProperty(entity).set(entity.getX() + 1);
    }
    
    public IntegerProperty getXProperty(Entity entity) {
    	return entity.x();
    }
   
    public IntegerProperty getYProperty(Entity entity) {
    	return entity.y();
    }
}
