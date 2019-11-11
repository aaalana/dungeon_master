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

	public List<Treasure> getTreasures() {
		return treasures;
	}

	public void setTreasures(List<Treasure> treasures) {
		this.treasures = treasures;
	}

	public TreasureGoal getTreasureGoal() {
		return treasureGoal;
	}

	public void setTreasureGoal(TreasureGoal treasureGoal) {
		this.treasureGoal = treasureGoal;
	}
	 
	public void addTreasure(Treasure treasure) {
		this.treasures.add(treasure);
	}

	public <T> void removeTreasure(T treasure) {
		this.treasures.remove(treasure);
		this.update();
	}
	
	public void update() {
		if (this.treasures.size() == 0) {
			System.out.println("No more treasures");
			this.treasureGoal.updateGoal();
		}
	}
	
}
