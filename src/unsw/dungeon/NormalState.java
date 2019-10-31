package unsw.dungeon;

public class NormalState implements PlayerState {
	private Player player;
	
	public NormalState(Player player) {
		this.player = player;
	}
	
	@Override
	public void drinkInvincibilityPotion(Item potion) {
		System.out.println("The player is now invincible for 5 seconds.");
		potion.useItem(this.player);
		player.setState(player.getInvincibilityState());
	}

	@Override
	public void expelInvincibilityPotion(Item potion) {
		System.out.println("The player is already not invincible.");
	}

	@Override
	public void killPlayer() {
		player.setState(player.getDeadState());
	}
	
}
