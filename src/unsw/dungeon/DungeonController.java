package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
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

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
        	//System.out.println(player.toString());
        	if (checkMove(player.getX(), player.getY() - 1, "up")) {
        		player.moveUp();
        	}
            break;
        case DOWN:
        	if (checkMove(player.getX(), player.getY() + 1, "down")) {
        		player.moveDown();	
        	}
            break;
        case LEFT:
        	if (checkMove(player.getX() - 1, player.getY(), "left")) {
        		player.moveLeft();	 
        	}
            break;
        case RIGHT:
        	if (checkMove(player.getX() + 1, player.getY(), "right")) {
        		player.moveRight();	  
        	}
            break;
        default:
            break; 
        }
        dungeon.addToInventory();  
        dungeon.updateObstacle();
    }
    
    public boolean checkMove(int x, int y, String direction) {
    	return (!dungeon.checkWall(x, y)) && dungeon.pushBoulder(x, y, direction);
    }
   
}

