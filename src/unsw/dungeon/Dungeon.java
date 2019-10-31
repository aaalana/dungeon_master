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
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
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
        entities.add(entity);
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
     * Checks if two entities are sharing the same square and returns the entity shared
     * @param sharedWith
     * @param situation
     * @return the entity sharing the same space as sharedWith
     */
    public Entity shareSquare(Entity sharedWith, String situation) {
     	for (Entity entity : this.entities) {
    		if (entity == null) continue;
    		
    		if ((situation.equals("switch") && entity instanceof Boulder) ||
    		 	(entity instanceof Player && (situation.equals("collect") || situation.equals("exit")))) {
    			if (entity.getX() == sharedWith.getX() && entity.getY() == sharedWith.getY()) {
    				return entity;
    			}
    		}
    	}
    	return null;
    }
}