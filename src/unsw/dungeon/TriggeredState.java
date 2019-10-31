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
		if (_switch.getDungeon().getOnTopOf(_switch, "switch") == null) {
			System.out.println("The user untriggered the switch.");
			_switch.setState(_switch.getUntriggeredState()); 
		}
	}
}
