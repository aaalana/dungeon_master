package unsw.dungeon.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

class PortalSystemTest {

	@Test
	void testFindMatchingPortal() {
		Dungeon dungeon = new Dungeon(18,16);
		Portal portal = new Portal(0, 0, 0, dungeon);
		Portal portal2 = new Portal(9, 9, 0, dungeon);
		PortalSystem sys = new PortalSystem();
		
		// no portals
		assertNull(null, sys.findMatchingPortal(null));
		
		// check that the same portal is not returned as the matching portal
		sys.addPortal(portal);
		sys.addPortal(portal2);
		assert(portal2.equals(sys.findMatchingPortal(portal)));
		assert(!portal2.equals(sys.findMatchingPortal(portal2)));
		
		// testing with a bigger list
		Portal portal3 = new Portal(0, 7, 1, dungeon);
		Portal portal4 = new Portal(9, 7, 1, dungeon);
		Portal portal5 = new Portal(9, 9, 2, dungeon);
		Portal portal6 = new Portal(5, 7, 2, dungeon);
		sys.addPortal(portal3);
		sys.addPortal(portal4);
		sys.addPortal(portal5);
		sys.addPortal(portal6);
		
		// checking results are symmetric
		assert(portal3.equals(sys.findMatchingPortal(portal4)));
		assert(portal4.equals(sys.findMatchingPortal(portal3)));
		assert(portal5.equals(sys.findMatchingPortal(portal6)));
		assert(portal6.equals(sys.findMatchingPortal(portal5)));
		
	}
	
	@Test
	void testTransportPlayer() {
		Dungeon dungeon = new Dungeon(18,16);
		Portal portal = new Portal(0, 0, 0, dungeon);
		Portal portal2 = new Portal(9, 9, 0, dungeon);
		PortalSystem sys = new PortalSystem();
		Player player = new Player(0, 0, dungeon);
		sys.addPortal(portal);
		sys.addPortal(portal2);
		
		// transporting a null portal
		int originalX = player.getX();
		int originalY = player.getY();
		
		try {
			sys.transportPlayer(null, player);
			fail("Expected an Exception to be thrown");
		} catch (NullPointerException e) {
			System.out.println("null portal!");
		}
		
		// check that the player wasn't transported somewhere
		assertEquals(originalX, player.getX());
		assertEquals(originalY, player.getY());
		
		// Check that the portal system can transport the player to a corresponding
		// portal and back
		
		// Side note: location of the player on the portal is not checked by the portal system.
		// This is managed by the dungeon which calls upon the portal system when this situation
		// occurs
		sys.transportPlayer(portal, player);
		assertEquals(portal2.getX(), player.getX());
		assertEquals(portal2.getY(), player.getY());
		
		sys.transportPlayer(portal2, player);
		assertEquals(portal.getX(), player.getX());
		assertEquals(portal.getY(), player.getY());
		
		// testing more portals
		Portal portal3 = new Portal(0, 7, 1, dungeon);
		Portal portal4 = new Portal(9, 7, 1, dungeon);
		Portal portal5 = new Portal(9, 9, 2, dungeon);
		Portal portal6 = new Portal(5, 7, 2, dungeon);
		sys.addPortal(portal3);
		sys.addPortal(portal4);
		sys.addPortal(portal5);
		sys.addPortal(portal6);
		
		// test 2
		sys.transportPlayer(portal3, player);
		assertEquals(portal4.getX(), player.getX());
		assertEquals(portal4.getY(), player.getY());
		
		sys.transportPlayer(portal4, player);
		assertEquals(portal3.getX(), player.getX());
		assertEquals(portal3.getY(), player.getY());
		
		// test 3
		sys.transportPlayer(portal5, player);
		assertEquals(portal6.getX(), player.getX());
		assertEquals(portal6.getY(), player.getY());
		
		sys.transportPlayer(portal6, player);
		assertEquals(portal5.getX(), player.getX());
		assertEquals(portal5.getY(), player.getY());
	}
	
	@Test
	void testIsSamePortal() {
		Dungeon dungeon = new Dungeon(18,16);
		Portal portal = new Portal(0, 0, 0, dungeon);
		Portal portal2 = new Portal(9, 9, 0, dungeon);
		Portal portal3 = new Portal(15, 9, 0, dungeon);
		
		PortalSystem sys = new PortalSystem();
		
		// try null portal
		assertFalse(sys.isSamePortal(null, null));
		assertFalse(sys.isSamePortal(null, portal));
		assertFalse(sys.isSamePortal(portal, null));
		
		// try same portal
		assertTrue(sys.isSamePortal(portal, portal));
		assertTrue(sys.isSamePortal(portal2, portal2));
		assertTrue(sys.isSamePortal(portal3, portal3));
		
		// try different portals
		assertFalse(sys.isSamePortal(portal, portal2));
		assertFalse(sys.isSamePortal(portal2, portal));
		assertFalse(sys.isSamePortal(portal2, portal3));
		assertFalse(sys.isSamePortal(portal3, portal));
		assertFalse(sys.isSamePortal(portal3, portal2));
		assertFalse(sys.isSamePortal(portal, portal3));
	}
}
