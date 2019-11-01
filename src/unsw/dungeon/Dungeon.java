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
    private List<Obstacle> obstacles;
    private List<Item> items;
    private List<Blocker> blockers;
    private List<LivingCreature> livingCreatures;
    
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
        this.livingCreatures = new ArrayList<>();
        
        this.boulders = new BoulderSystem(this);
        this.portals = new PortalSystem();
        
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
     * Adds to a list of portals 
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
     * add an LivingCreature to the LivingCreatures list
     * @param c
     */
    public void addLivingCreature(LivingCreature c) {
    	livingCreatures.add(c);
    }
    
    /**
     * remove an living creature from the livingCreatures list
     * @param c
     */
    public void removeLivingCreature(LivingCreature c) {
    	livingCreatures.remove(c);
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
     * Kills off living creatures when they come in contact with one another
     * -When the player is invincible and touches an enemy, the enemy is signalled to die
     * -When the player is not invincible and touches an enemy, the player is signalled to die
     */
    public void killCreature() {
    	List<LivingCreature> tempList = new ArrayList<>(livingCreatures);	
    	for (LivingCreature e: tempList) {
    		if (e == null || !(e instanceof Enemy)) 
    			continue;
    		
    		if (player.getX() == e.getX() && player.getY() == e.getY()) {
	    		if (player.getState() instanceof InvincibilityState) {
		    			e.killOff();
		    			removeLivingCreature(e);
	    		} else if (player.getState() instanceof NormalState) {
		    			player.killOff();
		    			removeLivingCreature(player);
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