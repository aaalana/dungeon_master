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
 * A JavaFX controller for the dungeon. It is responsible for the management of the game's UI
 * and providing automatic surveillance of the dungeon's entities. 
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
            for (int y = 0; y < dungeon.getHeight(); y++) 
                squares.add(new ImageView(ground), x, y);
        }
        
        // add an inventory
        loadInventory();
       
        // add a pause button
        createPauseButton();
        
        // add entities
        for (ImageView entity : initialEntities)
            getSquaresChildren().add(entity);
    }
    
    /**
     * Helper function which creates a inventory for the game's front end 
     * for the initialize function
     */
    public void loadInventory() {
        Image tiles = new Image("/tiles.png");
        Image slot = new Image("/darkTiles.png");
        Image wood = new Image("/wood.png");
        
    	for (int x = 0; x < dungeon.getWidth(); x++) 
        	squares.add(new ImageView(wood), x, dungeon.getHeight());
        
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
       
        for (int x = 0; x < dungeon.getWidth(); x++) 
        	squares.add(new ImageView(wood), x, dungeon.getHeight() + 4);
    }
    
    /**
     * Helper function which creates a pause button for the game's front end 
     * for the initialize function
     */
    public void createPauseButton() {
        Button btn = new Button("||");
        PauseMenu pauseMenu = new PauseMenu(stage);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pauseMenu.showPopUp();
            }
        });
        squares.add(btn, 2, dungeon.getHeight() + 2);
    }
    
    @FXML
    public void handleKeyPress(KeyEvent event) {
    	PlayerState oldState = player.getState();
    	switch (event.getCode()) {
        case UP:
        	signalMovement(player.getX(), player.getY() - 1, "up", oldState); 
            break;
        case DOWN:
        	signalMovement(player.getX(), player.getY() + 1, "down", oldState); 
            break;
        case LEFT:
        	signalMovement(player.getX() - 1, player.getY(), "left", oldState); 
            break;
        case RIGHT:
        	signalMovement(player.getX() + 1, player.getY(), "right", oldState); 
            break;
        default:
            break; 
        }
    
    	// signal when the player should be using its sword
        player.useSword();
       
        // detects when to kill creatures after all creatures have moved
        dungeon.killCreature(null);
    }
   
    /**
     * Checks if the player's movement should be blocked by an entity
     * @param x
     * @param y
     * @param direction
     * @return
     */
    public boolean checkMove(int x, int y, String direction) {
    	return (!dungeon.checkBlocker(x, y)) && dungeon.pushBoulder(x, y, direction);
    }
    
    /**
     * Signals the movement of living creatures and updates the dungeon as
     * consequence of the player's movement
     * @param x
     * @param y
     * @param direction
     * @param oldState
     */
    public void signalMovement(int x, int y, String direction, PlayerState oldState) {
		if (checkMove(x, y, direction)) {
			player.tryToMove(direction, dungeon, player); 
			dungeon.updateObstacle();
	        dungeon.removeFromGround(); 
	        
			if (checkMove(x, y, direction) && player.getState() instanceof SpeedState && 
				oldState instanceof SpeedState) {
				player.tryToMove("right", dungeon, player);
				dungeon.updateObstacle();
		        dungeon.removeFromGround(); 
			}
		}
		dungeon.moveEnemies();
    }
    
  	/**
	 * Gets the square's nodes
	 * @return
	 */
	public ObservableList<Node> getSquaresChildren() {
    	return squares.getChildren();
    }
	
	/**
	 * Removes an imageView from the gridPane
	 * @return
	 */
	public void removeChild(ImageView view) {
    	getSquaresChildren().remove(view);
    }
	
	/**
	 * Adds an imageView from the gridPane
	 * @return
	 */
	public void addChild(ImageView view, int columnIndex, int rowIndex) {
    	squares.add(view, columnIndex, rowIndex);
    }
}

