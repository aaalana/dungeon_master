package unsw.dungeon;

/**
 * The player's dead state
 * @author z5209503
 */
public class DeadState implements PlayerState {
	Player player;
	
	public DeadState(Player player) {
		this.player = player;
	}
	
	// any attempts for the player to perform can action is rejected
	// since the player is dead
	
	@Override
	public void drinkPotion(Item potion) {
		System.out.println("The player's dead. Can't do anything.");
	}

	@Override
	public void expelPotion(Item potion) {
		System.out.println("The player's dead. Can't do anything.");
	}
	
	@Override
	public void killPlayer() {
		System.out.println("Can't kill a dead player.");
	}
}
