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
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            loadImage(wall);
            entity = wall;
            break;
        // TODO Handle other possible entities
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            loadImage(treasure);
            entity = treasure;
        	break;
        case "invincibility":
            Invincibility invincibility = new Invincibility(x, y);
            loadImage(invincibility);
            entity = invincibility;
        	break;
        case "switch":
            Switch _switch = new Switch(x, y);
            loadImage(_switch);
            entity = _switch;
        	break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            loadImage(boulder);
            entity = boulder;
        	break;
        case "sword":
            Sword sword = new Sword(x, y);
            loadImage(sword);
            entity = sword;
        	break;
        case "enemy":
            Enemy enemy = new Enemy(x, y);
            loadImage(enemy);
            entity = enemy;
        	break;
        case "exit":
        	Exit exit = new Exit(x, y);
            loadImage(exit);
            entity = exit;
         	break;
        // not in json files
        case "key":
        	int keyId = 0;
            Key key = new Key(x, y, keyId);
            loadImage(key);
            entity = key;
        	break;
        case "door":
        	int doorId = 0;
        	Door door = new Door(x, y, doorId);
        	loadImage(door);
        	entity = door;
        	break;
        }
        dungeon.addEntity(entity);
    }

	public abstract void onLoad(Entity entity, Image image);

    // TODO Create additional abstract methods for the other entities
    protected abstract void loadImage(Entity entity);
}
