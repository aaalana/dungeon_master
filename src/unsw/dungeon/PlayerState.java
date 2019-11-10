package unsw.dungeon;

public interface PlayerState {
	public void drinkInvincibilityPotion(Item potion);
	public void expelInvincibilityPotion(Item potion);
	public void killPlayer();
	public void drinkSpeedPotion(Item potion);
	public void expelSpeedPotion(Item potion);
}
