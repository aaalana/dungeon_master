package unsw.dungeon;

public interface PlayerState {
	public void drinkInvincibilityPotion(InvincibilityPotion potion);
	public void expelInvincibilityPotion(InvincibilityPotion potion);
	public void killPlayer();
}
