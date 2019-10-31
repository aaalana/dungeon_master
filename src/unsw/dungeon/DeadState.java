package unsw.dungeon;

public class DeadState implements PlayerState {
	Player player;
	Dungeon dungeon;
	
	public DeadState(Player player, Dungeon dungeon) {
		this.player = player;
		this.dungeon = dungeon;
	}

	@Override
	public void drinkInvincibilityPotion(InvincibilityPotion potion) {
		System.out.println("The player's dead. Can't do anything.");
	}

	@Override
	public void expelInvincibilityPotion(InvincibilityPotion potion) {
		System.out.println("The player's dead. Can't do anything.");
	}
	
	@Override
	public void killPlayer() {
		System.out.println("Can't kill a dead player.");
		System.out.println("-----GAME OVER-----");
	}	
}
