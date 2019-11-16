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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * A JavaFX controller for the dungeon. It is responsible for the management of the game's UI
 * and providing automatic surveillance of the dungeon's entities. 
 * @author Robert Clifton-Everest
 * @author Alana Hua
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private Stage stage;
    
    private EndScreen endScreen;
   
    @FXML
    private StackPane stack;
    
    @FXML
    private Button pause;
    
    private PauseMenu pauseMenu;
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, Stage primaryStage) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.stage = primaryStage;
        this.initialEntities = new ArrayList<>(initialEntities);
        this.endScreen = new EndScreen(stage);
        this.stack = new StackPane();
        this.pause = new Button("||");
        this.pauseMenu = new PauseMenu(stage, dungeon.getGoal());
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
       
        // add a pause button and menu
        pauseButtonHandler();
        
        // add in the end screen
        stack.getChildren().add(endScreen.getPopUp());
        
        // add entities
        for (ImageView entity : initialEntities)
            getSquaresChildren().add(entity);
        
        // allow the player to move
    	squares.setFocusTraversable(true);  
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
     * Handles the pause button to open the pause menu when clicked on
     */
    public void pauseButtonHandler() {
    	stack.getChildren().add(pauseMenu.getPopUp());
        squares.add(pause, 2, dungeon.getHeight() + 2);
    	pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	pauseMenu.showPopUp();
            }
        });
    }
    
    @FXML
    public void handleKeyPress(KeyEvent event) {
    	
    	if (!endScreen.getShowing()) {
	    	
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
		
	        // end the game when the user wins/loses
	        endGame();
    	}
    }
   
    /**
     * Shows the endScreen when the game is finished
     */
    public void endGame() {
		if (player.getState() instanceof DeadState) {
			endScreen.generateEndScreen(false);
	     	endScreen.showPopUp();
	     	pause.setDisable(true);
	    } else if (dungeon.updateGoal()) {
	    	endScreen.generateEndScreen(true);
	    	endScreen.showPopUp();
	    	pause.setDisable(true);
	    }
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
	        
	        if (direction.equals("left"))
	        	x--;
	        else if (direction.equals("right"))
	        	x++;
	        else if (direction.equals("up"))
	        	y--;
	        else
	        	y++;
	        
			if (checkMove(x, y, direction) && player.getState() instanceof SpeedState && 
				oldState instanceof SpeedState) {
				player.tryToMove(direction, dungeon, player);
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

