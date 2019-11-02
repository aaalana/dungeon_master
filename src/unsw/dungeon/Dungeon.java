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
 * The dungeon manages itself by signaling its entities perform certain tasks. 
 * However the entity tasks themselves are performed within the entities. 
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private EnemySystem enemies;
    private List<Obstacle> obstacles;
    private List<Item> items;
    private List<Blocker> blockers;

    private BoulderSystem boulders;
    private PortalSystem portals;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.entities = new ArrayList<>();
       
        this.obstacles = new ArrayList<>();
        this.items = new ArrayList<>();
        this.blockers = new ArrayList<>();
     
        this.enemies = new EnemySystem();
        this.boulders = new BoulderSystem(this);
        this.portals = new PortalSystem();
     
        this.player = null;
    }

    /**
     * Gets the dungeon's width
     * @return width
     */
	public int getWidth() {
        return width;
    }

	/**
     * Gets the dungeon's height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the player entity
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player entity of the dungeon
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * Adds an enemy to an enemy system
     * @param enemy
     */
    public void addEnemy(Entity enemy) {
    	enemies.addEnemy(enemy);
    }
    
    /**
     * Adds a boulder to a boulder system
     * @param boulder
     */
    public void addBoulder(Entity boulder) {
    	boulders.addBoulder(boulder);
    }
    
    /**
     * Adds an entity to the dungeon's list of entities
     * @param entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    /**
     * Adds a portal to a portal assistant system 
     * @param portal
     */
    public void addPortals(Portal portal) {
    	portals.addPortal(portal);
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
     * add an blocker to the blockers list
     * @param blocker
     */
    public void addBlocker(Blocker blocker) {
    	blockers.add(blocker);
    }
    
    /**
     * gets the portal system
     * @return portals
     */
    public PortalSystem getPortals() {
    	return portals;
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
    			//System.out.println("Found the entity" + entity.getClass().getName() + "at co-ordinates (" + x + ", " + y + ")");
    			return entity.getClass().getName();
    		}
    	}
    	return "None";
    }
    
    /**
     * checks when the player's movement should be blocked when facing an blocker entity
     * @param x
     * @param y
     * @return true when the player should be blocked and false otherwise
     */
    public boolean checkBlocker(int x, int y) {
    	for (Blocker entity : this.blockers) {
    		if (entity == null) continue;
    		
    		if (entity.getX() == x && entity.getY() == y) {
    			if (entity instanceof Wall) {
    				return true;
    			} else if (entity instanceof Door) {
    				return entity.block(player);
    			}
    		}
    	}
    	return false;
    }
        
    /**
     * Checks if two particular entities are sharing the same square 
     * @param sharedWith an entity sharing a square
     * @return true when two entity are sharing the same square, false otherwise
     */
    public boolean shareSquare(Entity sharedWith) {
     	for (Entity entity : this.entities) {
    		if (entity == null) continue;
    		
    		if ((sharedWith instanceof Switch && entity instanceof Boulder) ||
    		   ((sharedWith instanceof Exit || sharedWith instanceof Portal) && entity instanceof Player) || 
    			(sharedWith instanceof Player && entity instanceof Enemy)) {
    			if (entity.getX() == sharedWith.getX() && entity.getY() == sharedWith.getY()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    /**
     * Signals enemies to move
     */
    public void moveEnemies() {
    	enemies.moveEnemies(getPlayer().getX(), getPlayer().getY());
    }

    /**
     * Kills off living creatures when they come in contact with one another
     * -When the player is invincible and touches an enemy, the enemy is signalled to die
     * -When the player is not invincible and touches an enemy, the player is signalled to die
     */
    public void killCreature() {
    	List<Entity> tempList = new ArrayList<>(enemies.getEnemies());	
    	for (Entity enemy: tempList) {
    		if (enemy == null) 
    			continue;
    		
    		if (player.getX() == enemy.getX() && player.getY() == enemy.getY()) {
    			if (player.getState() instanceof InvincibilityState) {
    				enemies.removeEnemy(enemy);
    				System.out.println("enemy killed");
	    		} else if (player.getState() instanceof NormalState) {
	    			player.killOff();
		    		player = null;
		    		System.out.println("player killed");
		    			
	    			// close application to end game
	    			System.exit(0);
	    		}
    		}
    	}
    }
    
    /**
     * -Signals the player to attempt to collect an item into the player's inventory 
     * when the player walks on top of the item.
     * -When collection is successful, the dungeon removes the item from itself
     */
    public void removeFromGround() {
    	List<Item> tempList = new ArrayList<>(items);
    	for (Item item: tempList) {
    		if (item == null) continue;
    		
    		if (player.getX() == item.getX() && player.getY() == item.getY()) {
    			if (player.collectItem(item))
    				items.remove(item);
    		}
    	}
    }
    
    /**
     * signals an obstacle to update its state under certain conditions:
     * -when a player interacts with a portal/exit 
     * -when a boulder interacts with a switch
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
     * Signals the movement of boulders caused by the player
     * @param x
     * @param y
     * @param direction
     * @return true when the boulder should be moved and false otherwise
     */
    public boolean pushBoulder(int x, int y, String direction) {
    	return boulders.pushBoulder(x, y, direction);
    }
    
    
}

