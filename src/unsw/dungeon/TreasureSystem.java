package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

public class TreasureSystem implements Subject {
	private List<Treasure> treasures;
	private TreasureGoal treasureGoal;
	
	public TreasureSystem() {
		super();
		this.treasures = new ArrayList<Treasure>();
	}

	/**
	 * Sets the treasure goal
	 * @param treasureGoal
	 */
	public void setTreasureGoal(TreasureGoal treasureGoal) {
		this.treasureGoal = treasureGoal;
	}
	 
	/**
	 * Adds the treasure goal
	 * @param treasure
	 */
	public void addTreasure(Treasure treasure) {
		this.treasures.add(treasure);
	}

	/**
	 * Removes the treasure from the treasures list
	 * @param <T>
	 * @param treasure
	 */
	public <T> void removeTreasure(T treasure) {
		treasures.remove(treasure);
		this.update();
	}
	
	/**
	 * updates the treasure goal as complete when all
	 * treasure has been collected
	 */
	public void update() {
		if (treasures.size() == 0) {
			System.out.println("No more treasures");
			treasureGoal.updateGoal();
		}
	}
	
}
