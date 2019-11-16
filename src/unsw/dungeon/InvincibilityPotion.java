package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Potion entity responsible for temporarily giving the player invincibility
 * @author Alana Hua
 *
 */
public class InvincibilityPotion extends Item {
	public Timer timer;
	
    public InvincibilityPotion(int x, int y) {
        super(x, y, "invincibility");
		this.timer = new Timer();
    }
   
    @Override
	public void useItem(Player player) {
		System.out.println("5 second countdown for invincibility potion initiated.");
		
		InvincibilityPotion potion = this;
		TimerTask task = new TimerTask() {
			public void run() {
				Platform.runLater(() -> {
					player.expelPotion(potion);
	            });
			}
		};
		timer.schedule(task, 5000);
	}
}
