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
import javafx.stage.Stage;

/**
 * The dungeonControllerLoader sets up the creation of the dungeonController.
 * It collects ImageViews from the DungeonLoader for the UI and connects
 * them via listeners to the model. 
 * 
 * @author Robert Clifton-Everest
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;
    
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
    }

    @Override
    public ImageView onLoad(Entity entity, Image image) {
        ImageView view = new ImageView(image);
        addEntity(entity, view);
        return view;
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
    public DungeonController loadController(Stage primaryStage) throws FileNotFoundException {
        return new DungeonController(load(), entities, primaryStage);
    }
}
