package unsw.dungeon;

public class SpeedState implements PlayerState {
	Player player;
	
	public SpeedState(Player player) {
		this.player = player;
	}

	@Override
	public void drinkInvincibilityPotion(Item potion) {
		System.out.println("The player is now invincible for +5 seconds.");
		potion.useItem(this.player);
	}

	@Override
	public void expelInvincibilityPotion(Item potion) {
		System.out.println("One invincibility potion was all used up.");
		player.removeItem(potion);
		
		if (!player.hasCertainItem(potion)) {
			System.out.println("The player is no longer invincible.");
			player.setState(player.getNormalState());
		}
	}
	
	@Override
	public void drinkSpeedPotion(Item potion) {
		System.out.println("The player is now invincible for +5 seconds.");
		potion.useItem(this.player);
	}

	@Override
	public void expelSpeedPotion(Item potion) {
		System.out.println("One invincibility potion was all used up.");
		player.removeItem(potion);
		
		if (!player.hasCertainItem(potion)) {
			System.out.println("The player is no longer invincible.");
			player.setState(player.getNormalState());
		}
	}
	
	@Override
	public void killPlayer() {
		player.setState(player.getDeadState());
	}

}
