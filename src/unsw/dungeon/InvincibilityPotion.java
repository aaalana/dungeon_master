package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Entity {
	public Timer timer;
	public int timeLeft;
	
    public InvincibilityPotion(int x, int y) {
        super(x, y);
    	this.timeLeft = 5;
		this.timer = new Timer();
    }
    
	/**
	 * Count down timer which reduces number of milliseconds the current potion instance will be activated for
	 * @param player
	 */
	public void reduceActivationTime(Player player) {
		System.out.println("5 second countdown for invincibility potion initiated.");
		InvincibilityPotion potion = this;
		TimerTask task = new TimerTask() {
			public void run() {
				player.expelInvincibilityPotion(potion);
			}
		};
		timer.schedule(task, 5000);
	}
}