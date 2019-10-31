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
    //private List<Obstacle> obstacles;
    //private List<Item> items;
    //private List<Enemy> enemies;
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
     * Gets an entity which shares the same square as an entity passed in as the Entity below
     * @param below
     * @param situation
     * @return null when no entity is sharing the same square as below; Otherwise, the entity
     * sharing the same square is returned.
     */
    public Entity getOnTopOf(Entity below, String situation) {
    	for (Entity entity : this.entities) {
    		if (entity == null) continue;
    		
    		if ((situation.equals("switch") && entity instanceof Boulder) ||
    		 	(situation.equals("exit") && entity instanceof Player) ||
    		 	(situation.equals("sword") && entity instanceof Player) || 
    		 	(situation.equals("treasure") && entity instanceof Player) ||
    		 	(situation.equals("invincibility") && entity instanceof Player)) {
    			if (entity.getX() == below.getX() && entity.getY() == below.getY()) {
    				return entity;
    			}
    		}
    	}
    	return null;
    }
     
}
