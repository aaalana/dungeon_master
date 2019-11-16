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
import java.util.ArrayList;

/**
 * Pause menu used when the user wants to pause the game
 * @author Alana Hua
 *
 */
public class PauseMenu {
	private Stage stage;
	private VBox vbox;
	private Label label;
	private Label goal;
	private Button resume;
	private Button restart;
	private Button exit;
	
	public PauseMenu(Stage stage, Goal dGoal) {
		this.stage = stage;
		this.label = createTitleLabel();
		this.goal = createGoalLabel(dGoal);
	    this.resume = resumeHandler();
	    this.restart = restartHandler();
	    this.exit = exitHandler();
		this.vbox = createVBox();
		generatePauseMenu();
	}

	/**
	 * Signals the popup to display itself
	 */
	public void showPopUp() {
		vbox.setVisible(true);
	}
	
	/**
	 * Gets the pause Menu
	 */
	public VBox getPopUp() {
		return vbox;
	}
	
	/**
	 * Generates a pause menu
	 */
	public void generatePauseMenu() {
        // add buttons and labels into a vBox structure
        ObservableList<Node> list = vbox.getChildren(); 
		list.addAll(label, goal, resume, restart, exit);
		
		VBox.setMargin(label, new Insets(30, 30, 10, 30));  
		VBox.setMargin(goal, new Insets(10, 30, 10, 30));
		VBox.setMargin(resume, new Insets(10, 30, 20, 30));  
		VBox.setMargin(restart, new Insets(10, 30, 20, 30)); 
		VBox.setMargin(exit, new Insets(10, 30, 30, 30)); 
    }
	
	/**
	 * Creates the Pause Menu title
	 * @return
	 */
	public Label createTitleLabel() {
        label = new Label("P A U S E   S C R E E N");
       	label.setStyle("-fx-font-weight: bold;");
       	label.setFont(new Font("Arial", 15));
       	return label;
	}
	
	/**
	 * Creates a label that shows the goals of the dungeon
	 * @return
	 */
	public Label createGoalLabel(Goal dGoal) {
		String goalText="Goal: ";
		if (dGoal.isSameType("unsw.dungeon.ANDGoal"))
			goalText = goalText + "(AND) ";
		else if (dGoal.isSameType("unsw.dungeon.ORGoal"))
			goalText = goalText + "(OR) ";
		
		goalText = extractGoal(dGoal, goalText);
		goal = new Label(goalText);
       	goal.setFont(new Font("Arial", 14));
       	return goal;
	}
	
	/**
	 * Transforms the dungeon goals as text 
	 * @param dGoal
	 * @param goalText
	 * @return
	 */
	public String extractGoal(Goal dGoal, String goalText) {
		if (dGoal.isSameType("unsw.dungeon.TreasureGoal")) {
	    	goalText = goalText + "treasure";
	    } else if (dGoal.isSameType("unsw.dungeon.BoulderGoal")) {
	    	goalText = goalText + "switches";
	    } else if (dGoal.isSameType("unsw.dungeon.ExitGoal")) {
	    	goalText = goalText + "exit";
	    } else if (dGoal.isSameType("unsw.dungeon.EnemyGoal")) {
	    	goalText = goalText + "enemy";
	    } else if (dGoal.isSameType("unsw.dungeon.ANDGoal")) {
	    	ArrayList<Goal> goalList = ((ANDGoal) dGoal).getGoal();
	    	for (Goal g : goalList) {
	    		goalText = goalText + extractGoal(g, "") + "  ";
	    	}
	    } else if (dGoal.isSameType("unsw.dungeon.ORGoal")) {
	    	ArrayList<Goal> goalList = ((ORGoal) dGoal).getGoal();
	    	for (Goal g : goalList) {
	    		goalText = goalText + extractGoal(g, "") + "  ";
	    	}
		}
		return goalText;
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
	 * Creates and styles a resume button
	 * @return
	 */
	public Button resumeHandler() {
		resume = new Button("RESUME");
		resume.setStyle("-fx-background-color: #b07356; -fx-text-fill: white;");
		resume.setPrefHeight(60);
	    resume.setPrefWidth(150);
	        
		resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox.setVisible(false);
            }
        });
		
		return resume;
	}
	
	/**
	 * Creates and styles a restart button
	 * @return
	 */
	public Button restartHandler() {
		restart = new Button("RESTART");
	    restart.setStyle("-fx-background-color:#b07356; -fx-text-fill: white;");
	    restart.setPrefHeight(60);
        restart.setPrefWidth(150);
      
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
		exit = new Button("EXIT");
	    exit.setStyle("-fx-background-color: #b07356; -fx-text-fill: white;");
	    exit.setPrefHeight(60);
        exit.setPrefWidth(150);
        
	    exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
	    
	    return exit;
	}    
}
