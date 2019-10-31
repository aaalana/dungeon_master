/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private List<Obstacle> obstacles;
    private List<Item> items;
    private BoulderSystem boulders;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        this.items = new ArrayList<>();
        this.boulders = new BoulderSystem(this);
        this.player = null;
    }

	public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
    	if (entity instanceof unsw.dungeon.Boulder) {
    		boulders.addBoulder(entity);
    	} 
        entities.add(entity);
    }
    
    /**
     * add an obstacle to the obstacle list
     * @param o
     */
    public void addObstacle(Obstacle o) {
    	obstacles.add(o);
    }
    
    /**
     * add an item to the items list
     * @param i
     */
    public void addItem(Item i) {
    	items.add(i);
    }
    
    /**
     * remove an item from the items list
     * @param item
     */
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    /**
     * This function takes in co-ordinates and returns what entity is on that square
     * @param x
     * @param y
     * @return
     */
    public String checkSquare(int x, int y) {
    	for (Entity entity : this.entities) {
    		if (entity == null) continue; 
    		if (entity.getX() == x && entity.getY() == y) {
    			System.out.println("Found the entity" + entity.getClass().getName() + "at co-ordinates (" + x + ", " + y + ")");
    			return entity.getClass().getName();
    		}
    	}
    	return "None";
    }
    
    /**
     * This function checks if a particular square has a wall in it
     * @param x
     * @param y
     * @return
     */
    public boolean checkWall(int x, int y) {
    	for (Entity entity : this.entities) {
    		if (entity == null) continue;
    		
    		if (entity.getX() == x && entity.getY() == y && entity instanceof unsw.dungeon.Wall) {
    			//System.out.println(entity.toString());
    			return true;
    		}
    	}
    	return false;
    }
        
    /**
     * Checks if two particular entities are sharing the same square 
     * @param sharedWith
     * @param situation
     * @return true when two entity are sharing the same square, false otherwise
     */
    public boolean shareSquare(Entity sharedWith) {
     	for (Entity entity : this.entities) {
    		if (entity == null) continue;
    		
    		if ((sharedWith instanceof Switch && entity instanceof Boulder) ||
    			(sharedWith instanceof Exit && entity instanceof Player)) {
    			if (entity.getX() == sharedWith.getX() && entity.getY() == sharedWith.getY()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    /**
     * attempts to collect the item into the player's inventory when the player walks on top of the item
     */
    public void addToInventory() {
    	List<Item> tempList = new ArrayList<>(items);
    	for (Item i: tempList) {
    		if (i == null) continue;
    		
    		if (player.getX() == i.getX() && player.getY() == i.getY()) {
    			player.collectItem(i);
    		}
    	}
    }
    
    /**
     * signals an obstacle to update its state
     * @param e
     */
    public void updateObstacle() {
    	for (Obstacle o : this.obstacles) {
    		if (o == null) 
    			continue;
    		o.trigger(shareSquare(o));
    	}
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param direction
     * @return
     */
    public boolean pushBoulder(int x, int y, String direction) {
    	return boulders.pushBoulder(x, y, direction);
    }
    
    
}