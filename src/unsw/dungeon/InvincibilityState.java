package unsw.dungeon;

public class InvincibilityState implements PlayerState {
	Player player;
	Dungeon dungeon;
	
	public InvincibilityState(Player player, Dungeon dungeon) {
		this.player = player;
		this.dungeon = dungeon;
	}

	@Override
	public void drinkInvincibilityPotion(InvincibilityPotion potion) {
		System.out.println("The player is now invincible for +5 seconds.");
		potion.reduceActivationTime(this.player);
	}

	@Override
	public void expelInvincibilityPotion(InvincibilityPotion potion) {
		System.out.println("One invincibility potion was all used up.");
		player.removeItem(potion);
		
		if (!player.hasInvincibilityPotion()) {
			System.out.println("The player is no longer invincible.");
			player.setState(player.getNormalState());
		}
	}
	
	@Override
	public void killPlayer() {
		player.setState(player.getDeadState());
	}

}
