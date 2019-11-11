package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import unsw.dungeon.DungeonControllerLoader.ImageManager;

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
   
  	public void setImage(ImageView view) {
  		this.image = view;
  		
  	}
  	
  	public void replaceImage(ImageView old, ImageManager imageManager) {
  		imageManager.replaceImage(old, this);
  	}
  	
  	public ImageView getImage() {
  		return image;
  	}
  	
}
