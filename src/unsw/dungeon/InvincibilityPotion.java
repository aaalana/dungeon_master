package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityPotion extends Item {
	public Timer timer;
	public int timeLeft;
	public boolean usedUp;
	
    public InvincibilityPotion(int x, int y) {
        super(x, y, "potion");
    	this.timeLeft = 5;
		this.timer = new Timer();
		this.usedUp = false;
    }
   
    @Override
	public void useItem(Player player) {
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
