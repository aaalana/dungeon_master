package unsw.dungeon;

public class TriggeredState implements SwitchState {
	Switch _switch;
	 
	public TriggeredState(Switch _switch) {
		this._switch = _switch;
	}

	@Override
	public void triggerSwitch() {
		System.out.println("The user cannot trigger a triggered switch.");
	}

	@Override
	public void untriggerSwitch() {
		System.out.println("The user untriggered the switch.");
		if (!_switch.getDungeon().isOnTopOf(_switch)) {
			_switch.setState(_switch.getUntriggeredState()); 
		}
	}
}
