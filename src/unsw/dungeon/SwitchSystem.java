package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

public class SwitchSystem implements Subject {
	private List<Switch> switches;
	private BoulderGoal boulderGoal;
	private Dungeon dungeon;

	public SwitchSystem(Dungeon dungeon) {
		super();
		this.switches = new ArrayList<Switch>();
		this.dungeon = dungeon;
	}
	
	public List<Switch> getSwitches() {
		return switches;
	}
	public void setSwitches(List<Switch> switches) {
		this.switches = switches;
	}
	public BoulderGoal getBoulderGoal() {
		return boulderGoal;
	}
	public void setBoulderGoal(BoulderGoal boulderGoal) {
		this.boulderGoal = boulderGoal;
	}

	public void addSwitch(Switch switchItem) {
		this.switches.add(switchItem);
	}

	public void checkSwitches() {
		for (Switch switchItem : this.switches) {
			if (dungeon.shareSquare(switchItem) == false) {
				return;
			}
		}

		update();
	}

	public void update() {
		System.out.println("All switches are active");
		this.boulderGoal.updateGoal();
	}

}
