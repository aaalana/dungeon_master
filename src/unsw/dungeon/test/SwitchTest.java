package unsw.dungeon.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Switch;

class SwitchTest {

	@Test
	void testGetState() {
		Switch _switch = new Switch(0,0);
		
		// Switch should be false on default unless triggered
		// default
		assertFalse(_switch.getState());
		
		// trigger
		_switch.trigger(true);
		assertTrue(_switch.getState());
		
		// untrigger
		_switch.trigger(false);
		assertFalse(_switch.getState());
	}
}
