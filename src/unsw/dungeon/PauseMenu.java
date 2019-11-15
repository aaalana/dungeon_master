package unsw.dungeon;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PauseMenu {

	private Stage stage;
	private Popup popup;
	
	public PauseMenu(Stage stage) {
		this.stage = stage;
		this.popup = new Popup();
		generatePauseMenu();
	}

	/**
	 * Signals the popup to show
	 */
	public void showPopUp() {
		popup.show(stage);
	}
	
	/**
	 * Generates a pause menu
	 */
	public void generatePauseMenu() {
		popUpHandler();
		
        Label label = createLabel();
       	Button resume = resumeHandler();
       	Button restart = restartHandler();
       	Button exit = exitHandler();
       	
       	VBox vbox = createVBox();
       	
		VBox.setMargin(label, new Insets(40, 30, 20, 30));  
		VBox.setMargin(resume, new Insets(10, 30, 20, 30));  
		VBox.setMargin(restart, new Insets(10, 30, 20, 30)); 
		VBox.setMargin(exit, new Insets(10, 30, 30, 30));  
		
		ObservableList<Node> list = vbox.getChildren(); 
		list.addAll(label, resume, restart, exit);
		
		popup.getContent().add(vbox);
    }
	
	/**
	 * Sets the pop up properties
	 */
	public void popUpHandler() {
	    popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);
        popup.setX(stage.getX() + 32 * 4);
        popup.setY(stage.getY() + 32 * 4); 
	}
	
	/**
	 * Creates the Pause Menu title
	 * @return
	 */
	public Label createLabel() {
        Label label = new Label("P A U S E   S C R E E N");
       	label.setStyle("-fx-font-weight: bold;");
       	label.setFont(new Font("Arial", 20));
       	return label;
	}
	
	/**
	 * Creates the VBox layout which acts as the pause Menu's structural base
	 * @return
	 */
	public VBox createVBox() {
	 	VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
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
        return vbox;
	}

	/**
	 * Creates and styles a resume button
	 * @return
	 */
	public Button resumeHandler() {
		Button resume = new Button("RESUME");
		resume.setStyle("-fx-background-color: #b07356; -fx-text-fill: white;");
		resume.setPrefHeight(80);
	    resume.setPrefWidth(250);
	        
		resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.hide();
            }
        });
		
		return resume;
	}
	
	/**
	 * Creates and styles a restart button
	 * @return
	 */
	public Button restartHandler() {
		Button restart = new Button("RESTART");
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
		Button exit = new Button("EXIT");
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
