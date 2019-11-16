package unsw.dungeon;

/**
 * The player's normal state
 * @author Alana Hua
 */
public class NormalState implements PlayerState {
	private Player player;
	
	public NormalState(Player player) {
		this.player = player;
	}
	
	@Override
	public void drinkPotion(Item potion) {
		potion.useItem(this.player);
		if (potion instanceof InvincibilityPotion) {
			System.out.println("The player is now invincible for 5 seconds.");
			player.setState(player.getInvincibilityState());
		} else {
			System.out.println("The player is now speedy for 3 seconds.");
			player.setState(player.getSpeedState());
		}
	}

	@Override
	public void expelPotion(Item potion) {
		System.out.println("The player is already in normal state.");
	}

	@Override
	public void killPlayer() {
		player.setState(player.getDeadState());
		player = null;
		System.out.println("Player died. :(");
		System.out.println("-----GAME OVER-----");
	}
}
