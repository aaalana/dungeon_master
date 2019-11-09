package unsw.dungeon;

import javafx.scene.image.ImageView;

public class Exit extends Obstacle {
	private ExitGoal exitGoal;
	
	public Exit(int x, int y) {
		super(x, y);
	}
	
	
	public ExitGoal getExitGoal() {
		return exitGoal;
	}


	public void setExitGoal(ExitGoal exitGoal) {
		this.exitGoal = exitGoal;
	}
	
	@Override
	public void trigger(boolean state) {
		// End the application when leaving the maze
		if (state) {
			System.out.println("Player exited the dungeon.");
			exitGoal.setComplete();
			
		}
	}
}
