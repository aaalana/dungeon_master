package unsw.dungeon;

public class InvincibilityState implements PlayerState {
	Player player;
	 
	public InvincibilityState(Player player) {
		this.player = player;
	}

	@Override
	public void drinkInvincibilityPotion() {
		System.out.println("The player is now invincible for an extra 30 seconds.");
	}

	@Override
	public void expelledInvincibilityPotion() {
		System.out.println("The player is no longer invincible.");
		player.setState(player.getNormalState());
	}
	
}
