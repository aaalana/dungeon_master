package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        
        // Add inventory

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }
    
    public ObservableList<Node> getSquaresChildren() {
    	return squares.getChildren();
    }
    
    public void replaceSquares(ImageView image, int x, int y) {
    	squares.add(image, x, y);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        // testing: check if the player's state
    	// System.out.println(player.getState());
    	
    	PlayerState oldState = player.getState();
    	switch (event.getCode()) {
        case UP:
        	if (checkMove(player.getX(), player.getY() - 1, "up")) {
        		player.tryToMove("up", dungeon, player);
        		selfManageDungeon();
        		
        		if (checkMove(player.getX(), player.getY() - 1, "up") &&
        			player.getState() instanceof SpeedState && 
        			oldState instanceof SpeedState) {
        			player.tryToMove("up", dungeon, player);
        			selfManageDungeon();
        		}
        	}
            break;
        case DOWN:
        	if (checkMove(player.getX(), player.getY() + 1, "down")) {
        		player.tryToMove("down", dungeon, player);
        		selfManageDungeon();
        		
        		if (checkMove(player.getX(), player.getY() + 1, "down") && 
        			player.getState() instanceof SpeedState && 
        			oldState instanceof SpeedState) {
        			player.tryToMove("down", dungeon, player);
        			selfManageDungeon();
        		}
        	}
            break;
        case LEFT:
        	if (checkMove(player.getX() - 1, player.getY(), "left")) {
        		player.tryToMove("left", dungeon, player); 
        		selfManageDungeon();
        		
        		if (checkMove(player.getX() - 1, player.getY(), "left")  && 
        			player.getState() instanceof SpeedState && 
        			oldState instanceof SpeedState) {
        			player.tryToMove("left", dungeon, player);
        			selfManageDungeon();
        		}
        	}
            break;
        case RIGHT:
        	if (checkMove(player.getX() + 1, player.getY(), "right")) {
        		player.tryToMove("right", dungeon, player); 
        		selfManageDungeon();
        		
        		if (checkMove(player.getX() + 1, player.getY(), "right") &&
        			player.getState() instanceof SpeedState && 
        			oldState instanceof SpeedState) {
        			player.tryToMove("right", dungeon, player);
        			selfManageDungeon();
        		}
        	}
            break;
        default:
            break; 
        }
    	
        dungeon.moveEnemies();
        
        if (player.getItemByName("sword") != null) {
    		player.useSword();
    	}
        
        // detects when to kill creatures after all creatures have moved
        dungeon.killCreature(null);
    }
 
    /**
     * runs functions that allow the dungeon to manipulate the obstacles and items within it
     */
    public void selfManageDungeon() {
    	dungeon.updateObstacle();
        dungeon.removeFromGround(); 
    }
    
    public boolean checkMove(int x, int y, String direction) {
    	return (!dungeon.checkBlocker(x, y)) && dungeon.pushBoulder(x, y, direction);
    }
    
}

