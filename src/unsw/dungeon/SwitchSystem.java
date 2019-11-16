package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

/**
 * Switch system which monitors whether the boulderGoal has been
 * completed
 * @author Arthur Wong
 *
 */
public class SwitchSystem implements Subject {
	private List<Switch> switches;
	private BoulderGoal boulderGoal;
	private Dungeon dungeon;

	public SwitchSystem(Dungeon dungeon) {
		super();
		this.switches = new ArrayList<Switch>();
		this.dungeon = dungeon;
	}
	
	/**
	 * Sets the boulder goal
	 * @param boulderGoal
	 */
	public void setBoulderGoal(BoulderGoal boulderGoal) {
		this.boulderGoal = boulderGoal;
	}

	/**
	 * Adds switches to the switch list
	 * @param switchItem
	 */
	public void addSwitch(Switch switchItem) {
		switches.add(switchItem);
	}

	/**
	 * Checks if all switches has been triggered. If so, the bouldergoal is updated
	 * as complete
	 */
	public void checkSwitches() {
		for (Switch switchItem : this.switches) {
			if (!dungeon.shareSquare(switchItem)) {
				return;
			}
		}
		update();
	}

	/**
	 * Updates the boulder goal as complete
	 */
	public void update() {
		if (boulderGoal != null) {
			System.out.println("All switches are active");
			boulderGoal.updateGoal();
		}
	}

}
