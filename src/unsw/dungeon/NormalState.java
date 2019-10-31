package unsw.dungeon;

public class NormalState implements PlayerState {
	private Player player;
	private Dungeon dungeon;
	
	public NormalState(Player player, Dungeon dungeon) {
		this.player = player;
		this.dungeon = dungeon;
	}
	
	@Override
	public void drinkInvincibilityPotion(InvincibilityPotion potion) {
		System.out.println("The player is now invincible for 5 seconds.");
		potion.reduceActivationTime(this.player);
		player.setState(player.getInvincibilityState());
	}

	@Override
	public void expelInvincibilityPotion(InvincibilityPotion potion) {
		System.out.println("The player is already not invincible.");
	}

	@Override
	public void killPlayer() {
		player.setState(player.getDeadState());
	}
	
}
