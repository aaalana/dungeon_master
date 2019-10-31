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
    private BoulderSystem boulders;
    //private List<Obstacle> obstacles;
    //private List<Item> items;
    //private List<Enemy> enemies;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
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
    
    public boolean pushBoulder(int x, int y, String direction) {
    	return boulders.pushBoulder(x, y, direction);
    }
   
}
