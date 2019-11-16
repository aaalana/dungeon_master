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

    /**
     * Gets the x property 
     * @return
     */
    public IntegerProperty x() {
        return x;
    }
    
    /**
     * Gets the y property 
     * @return
     */
    public IntegerProperty y() {
        return y;
    }

    /**
     * Gets the y coordinate of the entity's position
     * @return
     */
    public int getY() {
        return y().get();
    }

    /**
     * Gets the x coordinate of the entity's position
     * @return
     */
    public int getX() {
        return x().get();
    }
    
    /**
     * Sets the x and y coordinates of the entity's position
     * @param x
     */
    public void setPosition(int x, int y) {
    	x().set(x);
    	y().set(y);
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

    /**
     * Gets the class name of the entity
     * @return
     */
    public String getClassName() {
    	return getClass().getName();
    }
   
    /**
     * sets the image of the entity
     * @param view
     */
  	public void setImage(ImageView view) {
  		this.image = view;
  		
  	}
  	
  	/**
  	 * replaces the entity's image
  	 * @param old
  	 * @param imageManager
  	 */
  	public void replaceImage(ImageView old, ImageManager imageManager) {
  		imageManager.removeImage(old);
  		imageManager.addImage(this);
  	}
  	
  	/**
  	 * Gets the image of the entity
  	 * @return
  	 */
  	public ImageView getImage() {
  		return image;
  	}
  	
}
