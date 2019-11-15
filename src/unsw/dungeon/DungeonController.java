package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

    private Stage stage;
    
    @FXML
    private Button pause;
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, Stage primaryStage) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.stage = primaryStage;
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
        
        // add an inventory
        loadInventory();
     
        // add a pause menu button
        Button btn = new Button("||");
        PauseMenu pauseMenu = new PauseMenu(stage);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pauseMenu.showPopUp();
            }
        });
        squares.add(btn, 2, dungeon.getHeight() + 2);
        
        // add entities
        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
    }
    
    /**
     * Creates a inventory for the game's frontend
     */
    public void loadInventory() {
        Image tiles = new Image("/tiles.png");
        Image slot = new Image("/darkTiles.png");
        Image wood = new Image("/wood.png");
        
    	for (int x = 0; x < dungeon.getWidth(); x++) {
        	squares.add(new ImageView(wood), x, dungeon.getHeight());
        }
        
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = dungeon.getHeight() + 1; y < dungeon.getHeight() + 4; y++) {
                if (x == 0 || x == 7 || x == dungeon.getWidth() - 1) {
                	squares.add(new ImageView(wood), x, y);
                } else if ((x == 9 || x == 11 || x == 13 || x == 15) && y == dungeon.getHeight() + 2) {
                	squares.add(new ImageView(slot), x, y);
                } else {
                	squares.add(new ImageView(tiles), x, y);
                }
            }
        }
        
        for (int x = 0; x < dungeon.getWidth(); x++) {
        	squares.add(new ImageView(wood), x, dungeon.getHeight() + 4);
        }
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
        	dungeon.moveEnemies();
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
        	dungeon.moveEnemies();
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
        	dungeon.moveEnemies();
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
        	dungeon.moveEnemies();
            break;
        default:
            break; 
        }
        player.useSword();
       
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
    
    public void signalMovement() {
    	
    }
    
}

