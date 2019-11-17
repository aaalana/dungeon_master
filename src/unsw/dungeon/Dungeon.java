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
 * @author Alana Hua
 * @author Arthur Wong
 *
 */
public class Dungeon implements Observer {

    private int width, height;
    
    private List<Entity> entities;
    
    private List<Obstacle> obstacles;
    private List<Item> items;
    private List<Blocker> blockers;
    
    private EnemySystem enemies;
    private BoulderSystem boulders;
    private PortalSystem portals;
    private TreasureSystem treasures;
    private SwitchSystem switches;
    private Player player;
    
    private Goal goal;
    private DungeonControllerLoader.ImageManager imageManager;
    
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.entities = new ArrayList<>();
       
        this.obstacles = new ArrayList<>();
        this.items = new ArrayList<>();
        this.blockers = new ArrayList<>();
     
        this.enemies = new EnemySystem();
        this.boulders = new BoulderSystem(this);
        this.treasures = new TreasureSystem();
        this.switches = new SwitchSystem(this);
        this.portals = new PortalSystem();
     
        this.player = null;
        this.goal = null;
        this.imageManager = new DungeonControllerLoader.ImageManager();
    }

    /**
     * sets the overall goal of the dungeon
     * @param goal
     */
    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    /**
     * Gets the final goal of the dungeon
     * @return
     */
    public Goal getGoal() {
    	return goal;
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
     * Gets the imageManager of the dungeon
     * @return
     */
    public DungeonControllerLoader.ImageManager getImageManager() {
    	return imageManager;
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
    public void addArcher(Archer enemy) {
    	enemies.addArcher(enemy);
    }
    
    /**
     * Adds a crab to the enemy system
     * @param enemy
     */
    public void addCrab(Crab enemy) {
    	enemies.addCrab(enemy);
    }
    
    /**
     * Adds a boulder to a boulder system
     * @param boulder
     */
    public void addBoulder(Entity boulder) {
    	boulders.addBoulder(boulder);
    }
    
    /**
     * Adds a switch to the switch system
     * @param switchItem
     */
    public void addSwitch(Switch switchItem) {
        switches.addSwitch(switchItem);
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
     * Adds a treasure to the treasure system
     * @param treasure
     */
    public void addTreasure(Treasure treasure) {
        treasures.addTreasure(treasure);
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
    public void addItem(Item item) {
    	items.add(item);
    }
    
    /**
     * add an blocker to the blockers list
     * @param blocker
     */
    public void addBlocker(Blocker blocker) {
    	blockers.add(blocker);
    }
    
    /**
     * Removes entities from the dungeon
     * @param <T>
     * @param entity
     */
    public <T extends Entity> void removeEntity(T entity)  {
    	entities.remove(entity);
    	
    	if (entity instanceof Item) {
    		items.remove(entity);
    		if (entity instanceof Treasure) 
				treasures.removeTreasure(entity);   	
    	} else if (entity instanceof Enemy) {
    		imageManager.removeImage(entity.getImage());
    		enemies.removeEnemy(entity);
    	} else if (entity.equals(player)) {
    		player.killOff();
    	}
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
    			return entity.getClassName();
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
    			} else {
    				// change the locked door image to an unlocked door 
    				if (!entity.block(player)) 
    					if (entity instanceof Door) ((Door) entity).replaceDoorImage(player, imageManager);
    				
    				return entity.block(player);
    			}
    		}
    	}
    	return false;
    }
    
    /**
     * Checks if two particular entities are sharing the same square in relation to 
     * particular obstacles
     * @param sharedWith an entity sharing a square
     * @return true when two entity are sharing the same square, false otherwise
     */
    public boolean shareSquare(Entity sharedWith) {
     	for (Entity entity : this.entities) {
    		if (entity == null) continue;
    		
    		if (entity.getX() == sharedWith.getX() && entity.getY() == sharedWith.getY()) {
	    		if (sharedWith instanceof Switch && entity instanceof Boulder) {
	    			return true;
	    		} else if (sharedWith instanceof Exit && entity instanceof Player) {
	    			return true;
	    		} else if (sharedWith instanceof Portal && entity instanceof Player) {
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
    	enemies.moveEnemies(getPlayer().getX(), getPlayer().getY(), getPlayer().isInvincible());
    }

    /**
     * Kills off living creatures when they come in contact with one another
     * -When the player is invincible and touches an enemy, the enemy is signalled to die
     * -When the player is not invincible and touches an enemy, the player is signalled to die
     */
    public void killCreature(Sword sword) {
    	List<Enemy> tempList = new ArrayList<>(enemies.getEnemies());	
    	
    	for (Enemy enemy: tempList) {
    		if (enemy == null) continue;
    		
    		if (player.getX() == enemy.getX() && player.getY() == enemy.getY()) {
    			if (player.getState() instanceof InvincibilityState) {
    				removeEntity(enemy);
	    		} else if (player.getState() instanceof NormalState) {
	    			// kill the player
	    			if (sword == null) 
	    				removeEntity(player);
		    		// kill the enemy
	    			 else if (sword.getStatus()) {
	    				removeEntity(enemy);
	    				sword.reduceUses();
	    			 }
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
    		if (player.getX() == item.getX() && player.getY() == item.getY() &&
    			player.collectItem(item)) 
    			removeEntity(item);
    	}
    }
    
    /**
     * Signals an obstacle to update its state under certain conditions:
     * -when a player interacts with a portal/exit 
     * -when a boulder interacts with a switch
     * @param e
     */
    public void updateObstacle() {
    	for (Obstacle o : this.obstacles) {
    		if (o == null) continue;
    		o.trigger(shareSquare(o));
    	}
    	switches.checkSwitches();
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
    
    /**
     * Sets the goal for killing enemies
     * @param enemyGoal
     */
    public void setEnemyGoal(EnemyGoal enemyGoal) {
        if (this.enemies != null) {
            enemies.setEnemyGoal(enemyGoal);
        }
    }
    
    /**
     * Sets the goal for exiting the dungeon
     * @param exitGoal
     */
    public void setExitGoal(ExitGoal exitGoal) {
    	for (Obstacle o : obstacles) {
    		o.setExitGoal(exitGoal);
    	}
    }

    /**
     * Sets the goal for collecting treasure
     * @param treasureGoal
     */
    public void setTreasureGoal (TreasureGoal treasureGoal) {
        treasures.setTreasureGoal(treasureGoal);
    }

    /**
     * Sets the goal for switches/boulders
     * @param boulderGoal
     */
    public void setSwitchGoal(BoulderGoal boulderGoal) {
    	switches.setBoulderGoal(boulderGoal);
    }

    /**
     * Keeps track of when a goal has been completed
     * - If the overall goal is complete, then true is returned
     * - Otherwise, false is returned
     */
    public boolean updateGoal() {
    	if (goal.getStatus()) 
    		return true;
    	return false;
    }
}

