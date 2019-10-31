package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

// MIGHT DELETE LATER - CURRENTLY NOT IN USE
public class InvincibilityTimer {
	public Timer timer;
	public int timeLeft;
	
	public InvincibilityTimer() {
		this.timeLeft = 5;
		this.timer = new Timer();
	}
	
	public void reduceActivationTime(Player player) {
		TimerTask task = new TimerTask() {
			public void run() {
				timeLeft--;
				System.out.println(timeLeft + "seconds left of invincibility");
				if (timeLeft == 0) {
				//	player.expelInvincibilityPotion();
				}
			}
		};
		timer.schedule(task, 5000);
	}
	/*
	public void increaseActivationTime() {
		this.timeLeft = this.timeLeft + 5;
	}*/
}
