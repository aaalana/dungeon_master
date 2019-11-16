package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

/**
 * Portal Test 
 * @author Alana Hua
 *
 */
class PortalTest {

	@Test
	void testGetId() {
		Dungeon dungeon = new Dungeon(18,16);
		
		// testing with different portals for reliability
		Portal portal = new Portal(0, 0, 0, dungeon);
		assertEquals(0, portal.getId());
		
		Portal portal2 = new Portal(0, 0, 8, dungeon);
		assertEquals(8, portal2.getId());
	}
	
	@Test
	void testGetPortalAssist() {
		Dungeon dungeon = new Dungeon(18,16);
		PortalSystem sys = new PortalSystem();
		
		// Can't be equal to sys since there a different  portal system 
		// defined in the dungeon constructor
		Portal portal = new Portal(0, 0, 0, dungeon);
		assertNotSame(sys, portal.getPortalAssist());
		
		Portal portal2 = new Portal(0, 0, 8, dungeon);
		assertNotSame(sys, portal2.getPortalAssist());
	}
}
