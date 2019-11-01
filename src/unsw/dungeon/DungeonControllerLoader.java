package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image boulderImage;
    private Image switchImage;
    private Image enemyImage;
    private Image swordImage;
    private Image treasureImage;
    private Image invincibilityImage;
    private Image doorImage;
    private Image keyImage;
    private Image portalImage;
    
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        
        exitImage = new Image("/exit.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        swordImage = new Image("/greatsword_1_new.png");
        treasureImage = new Image("/gold_pile.png");
        invincibilityImage = new Image("/brilliant_blue_new.png");
        doorImage = new Image("/closed_door.png");
        keyImage = new Image("/key.png");
        portalImage = new Image("/portal.png");   
    }

    @Override
    public void onLoad(Entity entity, Image image) {
        ImageView view = new ImageView(image);
        addEntity(entity, view);
    }
    
    @Override
    public void loadImage(Entity entity) {
    	if (entity instanceof Player) {
    		onLoad(entity, playerImage);    		
    	} else if (entity instanceof Wall) {
    		onLoad(entity, wallImage);
    	} else if (entity instanceof Exit) {
    		onLoad(entity, exitImage);
    	} else if (entity instanceof Boulder) {
    		onLoad(entity, boulderImage);
    	} else if (entity instanceof Switch) {
    		onLoad(entity, switchImage);
    	} else if (entity instanceof Enemy) {
    		onLoad(entity, enemyImage);
    	} else if (entity instanceof Sword) {
    		onLoad(entity, swordImage);
    	} else if (entity instanceof Treasure) {
    		onLoad(entity, treasureImage);
    	} else if (entity instanceof InvincibilityPotion) {
    		onLoad(entity, invincibilityImage);
    	} else if (entity instanceof Door) {
    		onLoad(entity, doorImage);
    	} else if (entity instanceof Key) {
    		onLoad(entity, keyImage);
    	} else if (entity instanceof Portal) {
    		onLoad(entity, portalImage);
    	}
    }
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
