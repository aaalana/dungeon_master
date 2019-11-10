package unsw.dungeon;

public interface PlayerState {
	public void drinkPotion(Item potion);
	public void expelPotion(Item potion);
	public void killPlayer();
}
