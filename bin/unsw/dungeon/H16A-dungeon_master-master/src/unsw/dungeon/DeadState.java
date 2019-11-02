package unsw.dungeon;

public class DeadState implements PlayerState {
	Player player;
	
	public DeadState(Player player) {
		this.player = player;
	}

	@Override
	public void drinkInvincibilityPotion(Item potion) {
		System.out.println("The player's dead. Can't do anything.");
	}

	@Override
	public void expelInvincibilityPotion(Item potion) {
		System.out.println("The player's dead. Can't do anything.");
	}
	
	@Override
	public void killPlayer() {
		System.out.println("Can't kill a dead player.");
	}	
}
