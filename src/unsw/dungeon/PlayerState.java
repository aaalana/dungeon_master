package unsw.dungeon;

/**
 * -Interface for implementation of state pattern
 * -The player has 4 states: invincibilityState, speedState, normalState and
 * deadState
 * (all of which are mutually exclusive i.e. the player can only be in one 
 *  of these states at a time)
 * @author Alana Hua
 *
 */
public interface PlayerState {
	public void drinkPotion(Item potion);
	public void expelPotion(Item potion);
	public void killPlayer();
}
