package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        
        JSONObject goals = json.getJSONObject("goal-condition");
        System.out.println(goals.toString(2));
        Goal finalGoal = loadGoals(goals, dungeon);
        dungeon.setGoal(finalGoal);
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(x, y, dungeon);
            dungeon.setPlayer(player);
            ImageView pView = onLoad(player, new Image("/human_new.png"));
            player.updateImage(pView);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y, new CantMove());
            ImageView wView = onLoad(wall, new Image("/brick_brown_0.png"));
            wall.updateImage(wView);
            entity = wall;
            dungeon.addBlocker(wall);
            break;
        // TODO Handle other possible entities
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            entity = treasure;
            ImageView tView = onLoad(treasure, new Image("/gold_pile.png"));
            treasure.updateImage(tView);
            dungeon.addTreasure(treasure);
            dungeon.addItem(treasure);
        	break;
        case "invincibility":
            InvincibilityPotion invincibility = new InvincibilityPotion(x, y);
            ImageView iView = onLoad(invincibility, new Image("/brilliant_blue_new.png"));
            invincibility.updateImage(iView);
            entity = invincibility;
            dungeon.addItem(invincibility);
        	break;
        case "speed":
            SpeedPotion speed = new SpeedPotion(x, y);
            ImageView spView = onLoad(speed, new Image("/bubbly.png"));
            speed.updateImage(spView);
            entity = speed;
            dungeon.addItem(speed);
        	break;
        case "switch":
            Switch _switch = new Switch(x, y);
            ImageView sView = onLoad(_switch, new Image("/pressure_plate.png"));
            _switch.updateImage(sView);
            entity = _switch;
            dungeon.addObstacle(_switch);
            dungeon.addSwitch(_switch);
        	break;
        case "boulder":
            Boulder boulder = new Boulder(x, y, new ItMoves());
            ImageView bView = onLoad(boulder, new Image("/boulder.png"));
            boulder.updateImage(bView);
            entity = boulder;
            dungeon.addBlocker(boulder);
            dungeon.addBoulder(boulder);
        	break;
        case "sword":
            Sword sword = new Sword(x, y);
            ImageView swView = onLoad(sword, new Image("/greatsword_1_new.png"));
            sword.updateImage(swView);
            entity = sword;
            dungeon.addItem(sword);
        	break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            ImageView eView = onLoad(enemy, new Image("/deep_elf_master_archer.png"));
            enemy.updateImage(eView);
            entity = enemy;
            dungeon.addEnemy(enemy);
        	break;
        case "exit":
        	Exit exit = new Exit(x, y);
            ImageView exView = onLoad(exit, new Image("/exit.png"));
            exit.updateImage(exView);
            entity = exit;
            dungeon.addObstacle(exit);
         	break;
        case "key":
        	int keyId = json.getInt("id");
            Key key = new Key(x, y, keyId);
            ImageView kView = onLoad(key, new Image("/key.png"));
            key.updateImage(kView);
            entity = key;
            dungeon.addItem(key);
        	break;
        case "door":
        	int doorId = json.getInt("id");
        	Door door = new Door(x, y, doorId, new CantMove());
        	ImageView dView = onLoad(door, new Image("/closed_door.png"));
            door.updateImage(dView);
        	entity = door;
        	dungeon.addBlocker(door);
        	break;
        case "portal":
        	int portalId = json.getInt("id");
        	Portal portal = new Portal(x, y, portalId, dungeon);
        	ImageView poView = onLoad(portal, new Image("/portal.png"));
            portal.updateImage(poView);
        	entity = portal;
        	dungeon.addObstacle(portal);
        	dungeon.addPortals(portal);
        	break;
        }
        dungeon.addEntity(entity);
    }
    
    private Goal loadGoals(JSONObject goals, Dungeon dungeon){
        String type = goals.getString("goal");
        System.out.println(goals);
        Goal goal = null;
        switch(type) {
        	// if the goals is AND, add all goals
            case "AND":
            	JSONArray subgoals = goals.getJSONArray("subgoals");
                
            	ANDGoal ANDgoals = new ANDGoal(dungeon);
				for (int i = 0; i < subgoals.length(); i++) {
				    Goal subgoal = loadGoals(subgoals.getJSONObject(i), dungeon);
				    ANDgoals.addGoal(subgoal);
				}
                goal = ANDgoals;
				break;
            case "OR":
            	JSONArray subgoals2 = goals.getJSONArray("subgoals");
                
            	ORGoal ORgoals = new ORGoal(dungeon);
                for (int i = 0; i < subgoals2.length(); i++) {
                    Goal subgoal = loadGoals(subgoals2.getJSONObject(i), dungeon);
                    ORgoals.addGoal(subgoal);
                }
                goal = ORgoals;
                break;
            case "exit":
                ExitGoal exitGoal = new ExitGoal(dungeon);
                goal = exitGoal;
                dungeon.setExitGoal(exitGoal);
                break;
            case "enemies":
                EnemyGoal enemyGoal = new EnemyGoal(dungeon);
                goal = enemyGoal;
                dungeon.setEnemyGoal(enemyGoal);
                break;
            case "treasure":
                TreasureGoal treasureGoal = new TreasureGoal(dungeon);
                goal = treasureGoal;
                dungeon.setTreasureGoal(treasureGoal);
                break;
            case "boulders":
                BoulderGoal boulderGoal = new BoulderGoal(dungeon);
                goal = boulderGoal;
                dungeon.setSwitchGoal(boulderGoal);
                break;
            default:
                break;
        }
        return goal;
    }
      
	public abstract ImageView onLoad(Entity entity, Image image);

    // TODO Create additional abstract methods for the other entities
}
