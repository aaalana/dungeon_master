package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Potion responsible for temporarily giving the player speed 
 * i.e. the player is able to move at a max of 2 squares at a time
 * for three seconds
 * @author Alana Hua
 *
 */
public class SpeedPotion extends Item {
	public Timer timer;
	
    public SpeedPotion(int x, int y) {
        super(x, y, "speed");
		this.timer = new Timer();
    }
   
    @Override
	public void useItem(Player player) {
		System.out.println("3 second countdown for speed potion initiated.");
		
		SpeedPotion potion = this;
		TimerTask task = new TimerTask() {
			public void run() {
				Platform.runLater(() -> {
					player.expelPotion(potion);
	            });
			}
		};
		timer.schedule(task, 3000);
	}
}
