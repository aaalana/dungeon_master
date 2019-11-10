package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

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
				player.expelPotion(potion);
			}
		};
		timer.schedule(task, 5000);
	}
}
