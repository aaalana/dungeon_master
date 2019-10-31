package unsw.dungeon;

public class UntriggeredState implements SwitchState {
	Switch _switch;
	 
	public UntriggeredState(Switch _switch) {
		this._switch = _switch;
	}
	
	@Override
	public void triggerSwitch() {
		if (_switch.getDungeon().isOnTopOf(_switch, "switch")) {
			_switch.setState(_switch.getTriggeredState());
			System.out.println("The user triggered the switch.");
		}
	}

	@Override
	public void untriggerSwitch() {
		System.out.println("The user can't untrigger a triggered switch.");
	}
}
