package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

//INCOMPLETE
class BoulderSystemTest {

	@Test
	void testPushBoulder() {
		Dungeon dungeon = new Dungeon(20,18);	
		Boulder boulder = new Boulder(dungeon, 0, 0, new ItMoves());
		Boulder boulder2 = new Boulder(dungeon, 0, 1, new ItMoves());
		Boulder boulder3 = new Boulder(dungeon, 0, 5, new ItMoves());
		BoulderSystem sys = new BoulderSystem(dungeon);
		
		// no boulder in the way + testing edges/corners
		assertTrue(sys.pushBoulder(0, 1, "up")); 
		assertTrue(sys.pushBoulder(0, 18, "down")); 
		assertTrue(sys.pushBoulder(0, 18, "left")); 
		assertTrue(sys.pushBoulder(20, 0, "right")); 
		
		// has boulders but not in the way
		sys.addBoulder(boulder);
		assertTrue(sys.pushBoulder(0, 1, "up")); 
		assertTrue(sys.pushBoulder(0, 1, "left")); 
		
		// has boulders but in the way
		sys.addBoulder(boulder2);
		//assertFalse(sys.pushBoulder(0, 2, "up")); 
		
	}

}
