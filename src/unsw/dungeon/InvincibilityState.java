package unsw.dungeon;

public class InvincibilityState implements PlayerState {
	private Player player;
	
	public InvincibilityState(Player player) {
		this.player = player;
	}

	@Override
	public void drinkPotion(Item potion) {
		System.out.println("The player is now invincible for +5 seconds.");
		potion.useItem(this.player);
	}

	@Override
	public void expelPotion(Item potion) {
		System.out.println("One invincibility potion was all used up.");
		player.removeItem(potion);
		
		if (player.getItem("invincibility") == null) {
			System.out.println("The player is no longer invincible.");
			player.setState(player.getNormalState());
		} else {
			drinkPotion(player.getItem("invincibility"));
		}
	}
	
	@Override
	public void killPlayer() {
		player.setState(player.getDeadState());
	}
}
