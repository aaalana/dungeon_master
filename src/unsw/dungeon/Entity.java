package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    public MoveStrategy moveable;
    private ImageView image;
  
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, MoveStrategy movingType) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.moveable = movingType;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    /**
     * Allows the entity to move if it has an ItMoves strategy
     * Otherwise, the entity can't move
     * @param direction
     * @param dungeon
     * @param entity
     */
    public void tryToMove(String direction, Dungeon dungeon, Entity entity) {
    	moveable.move(direction, dungeon, entity);
    }

    public String getClassName() {
    	return getClass().getName();
    }
   
  	public void updateImage(ImageView view) {
  		this.image = view;
  	}
  	
  	public ImageView getImage() {
  		return image;
  	}
  	
}
