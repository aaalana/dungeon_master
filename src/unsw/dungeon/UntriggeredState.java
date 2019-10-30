package unsw.dungeon;

public class UntriggeredState implements SwitchState {
	Switch _switch;
	 
	public UntriggeredState(Switch _switch) {
		this._switch = _switch;
	}
	
	@Override
	public void triggerSwitch() {
		System.out.println("The user triggered the switch.");
		if (_switch.getDungeon().isOnTopOf(_switch, "switch")) {
			_switch.setState(_switch.getTriggeredState()); 
		}
	}

	@Override
	public void untriggerSwitch() {
		System.out.println("The user can't untrigger a triggered switch.");
	}
}
