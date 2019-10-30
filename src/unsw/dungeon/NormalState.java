package unsw.dungeon;

public class NormalState implements PlayerState {
	private Player player;
	
	public NormalState(Player player) {
		this.player = player;
	}
	
	@Override
	public void drinkInvincibilityPotion() {
		System.out.println("The player is now invincible for 30 seconds.");
		player.setState(player.getInvincibilityState());
	}

	@Override
	public void expelledInvincibilityPotion() {
		System.out.println("The player is already not invincible.");
	}
	
}
