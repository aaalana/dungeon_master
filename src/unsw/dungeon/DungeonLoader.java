package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.scene.image.Image;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            loadImage(player);
            entity = player;
//            dungeon.addLivingCreature(player);
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            loadImage(wall);
            entity = wall;
            dungeon.addBlocker(wall);
            break;
        // TODO Handle other possible entities
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            loadImage(treasure);
            entity = treasure;
            dungeon.addItem(treasure);
        	break;
        case "invincibility":
            InvincibilityPotion invincibility = new InvincibilityPotion(x, y);
            loadImage(invincibility);
            entity = invincibility;
            dungeon.addItem(invincibility);
        	break;
        case "switch":
            Switch _switch = new Switch(x, y);
            loadImage(_switch);
            entity = _switch;
            dungeon.addObstacle(_switch);
        	break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            loadImage(boulder);
            entity = boulder;
            dungeon.addBlocker(boulder);
        	break;
        case "sword":
            Sword sword = new Sword(x, y);
            loadImage(sword);
            entity = sword;
            dungeon.addItem(sword);
        	break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            loadImage(enemy);
            entity = enemy;
            dungeon.addEnemy(enemy);
        	break;
        case "exit":
        	Exit exit = new Exit(x, y);
            loadImage(exit);
            entity = exit;
            dungeon.addObstacle(exit);
         	break;
        // not in json files
        case "key":
        	// temporary
        	int keyId = 0;
            Key key = new Key(x, y, keyId);
            loadImage(key);
            entity = key;
            dungeon.addItem(key);
        	break;
        case "door":
        	// temporary
        	int doorId = 0;
        	Door door = new Door(x, y, doorId);
        	loadImage(door);
        	entity = door;
        	dungeon.addBlocker(door);
        	break;
        case "portal":
        	// temporary
        	int portalId = 0;
        	Portal portal = new Portal(x, y, portalId);
        	loadImage(portal);
        	entity = portal;
        	break;
        }
        dungeon.addEntity(entity);
    }

    public void extractGoal(Dungeon dungeon, JSONObject json) {
        String goal = json.getString("goal-condition");
    	
        // maybe ??? dungeon.addGoal(goal);	
    }
    
	public abstract void onLoad(Entity entity, Image image);

    // TODO Create additional abstract methods for the other entities
    protected abstract void loadImage(Entity entity);
}
