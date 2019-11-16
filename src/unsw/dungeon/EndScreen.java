package unsw.dungeon;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * End screen which shows when the user has won/lost the game
 * @author Alana Hua
 *
 */
public class EndScreen {
	private Stage stage;
	private VBox vbox;
	private Label label;
	private Button restart;
	private Button exit;
	boolean showing;
	
	public EndScreen(Stage stage) {
		this.stage = stage;
		this.label = null;
	    this.restart = restartHandler();
	    this.exit = exitHandler();
		this.vbox = createVBox();
		this.showing = false;
	}

	/**
	 * Signals the popup to display itself
	 */
	public void showPopUp() {
		vbox.setVisible(true);
		this.showing = true;
	}
	
	/**
	 * Gets the pause Menu
	 */
	public VBox getPopUp() {
		return vbox;
	}
	
	/**
	 * Checks if the end screen is visible
	 * @return
	 */
	public boolean getShowing() {
		return showing;
	}
	
	/**
	 * Generates a pause menu
	 */
	public void generateEndScreen(Boolean status) {
	    // set the label by the winning status of the game 
	    label = createLabel(status);
	    
	    // set the margins for the vBox children
	  	VBox.setMargin(label, new Insets(40, 30, 20, 30)); 
		VBox.setMargin(restart, new Insets(10, 30, 20, 30)); 
		VBox.setMargin(exit, new Insets(10, 30, 30, 30)); 
		
	    // add buttons and label into a vBox structure
	    ObservableList<Node> list = vbox.getChildren(); 
		list.addAll(label, restart, exit);
	}
	
	/**
	 * Creates the Pause Menu title
	 * @return
	 */
	public Label createLabel(boolean status) {
		if (status)
			label = new Label("Y O U  W I N !");
		else 
			label = new Label("G A M E  O V E R !");
	   	
		label.setStyle("-fx-font-weight: bold;");
	   	label.setFont(new Font("Arial", 20));
	   	return label;
	}
	
	/**
	 * Creates the VBox layout which acts as the pause Menu's structural base
	 * @return
	 */
	public VBox createVBox() {
	 	vbox = new VBox();
	    
	 	vbox.setSpacing(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setVisible(false);
        
	    BackgroundImage myBI= new BackgroundImage(new Image("/full_background.png"), 
												  null, 
										          BackgroundRepeat.NO_REPEAT, 
											      null, 
											      null);
		vbox.setBackground(new Background(myBI));
		
		String cssLayout = "-fx-border-color: #42160f;\n" +
		"-fx-border-insets: 7;\n" +
		"-fx-border-width: 3;\n" +
		"-fx-border-style: dashed;\n";  
		vbox.setStyle(cssLayout);
	     		
		vbox.setMaxWidth(Region.USE_PREF_SIZE);
		vbox.setMaxHeight(Region.USE_PREF_SIZE);
		
		return vbox;
	}
	
	/**
	 * Creates and styles a restart button
	 * @return
	 */
	public Button restartHandler() {
		restart = new Button("START AGAIN?");
	    restart.setStyle("-fx-background-color:#b07356; -fx-text-fill: white;");
	    restart.setPrefHeight(80);
	    restart.setPrefWidth(250);
	  
		restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
				DungeonApplication app = new DungeonApplication();
				try {
					app.start(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	    });
		
		return restart;
	}
	
	/**
	 * Creates and styles a exit button
	 * @return
	 */
	public Button exitHandler() {
		exit = new Button("EXIT GAME");
	    exit.setStyle("-fx-background-color: #b07356; -fx-text-fill: white;");
	    exit.setPrefHeight(80);
        exit.setPrefWidth(250);
        
	    exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
	    return exit;
	}    
}
