package unsw.dungeon;

public class SpeedState implements PlayerState {
	Player player;
	
	public SpeedState(Player player) {
		this.player = player;
	}

	@Override
	public void drinkPotion(Item potion) {
		System.out.println("The player is now speedy for +3 seconds.");
		potion.useItem(this.player);
	}

	@Override
	public void expelPotion(Item potion) {
		System.out.println("One speed potion was all used up.");
		player.removeItem(potion);
		
		if (!player.hasCertainItem(potion)) {
			System.out.println("The player returned to normal state.");
			player.setState(player.getNormalState());
		}
	}
	
	@Override
	public void killPlayer() {
		player.setState(player.getDeadState());
	}

}
