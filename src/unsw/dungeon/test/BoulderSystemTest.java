package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

class BoulderSystemTest {

	@Test
	void testPushBoulder() {
		Dungeon dungeon = new Dungeon(20,18);	
		Boulder boulder = new Boulder(0, 0, new ItMoves());
		Boulder boulder2 = new Boulder(0, 1, new ItMoves());
		Boulder boulder3 = new Boulder(2, 0, new ItMoves());
		Boulder boulder4 = new Boulder(9, 9, new ItMoves());
		Boulder boulder5 = new Boulder(10, 9, new ItMoves());
		Boulder boulder6 = new Boulder(10, 10, new ItMoves());
		Wall wall = new Wall(1, 0, new CantMove());
		BoulderSystem sys = new BoulderSystem(dungeon);
		
		// no boulder in the way + testing edges/corners
		assertTrue(sys.pushBoulder(0, 1, "up")); 
		assertTrue(sys.pushBoulder(0, 18, "down")); 
		assertTrue(sys.pushBoulder(0, 18, "left")); 
		assertTrue(sys.pushBoulder(20, 0, "right")); 
		
		// has boulders but not in the way
		sys.addBoulder(boulder);
		dungeon.addEntity(boulder);
		assertTrue(sys.pushBoulder(0, 1, "up"));
		assertTrue(sys.pushBoulder(8, 9, "right"));
		assertTrue(sys.pushBoulder(9, 8, "down"));
		assertTrue(sys.pushBoulder(1, 1, "left"));
		
		sys.addBoulder(boulder3);
		sys.addBoulder(boulder2);
		sys.addBoulder(boulder4);
		sys.addBoulder(boulder5);
		sys.addBoulder(boulder6);
		
		dungeon.addEntity(wall);
		dungeon.addEntity(boulder2);
		dungeon.addEntity(boulder3);
		dungeon.addEntity(boulder4);
		dungeon.addEntity(boulder5);
		dungeon.addEntity(boulder6);
		
		assertTrue(sys.pushBoulder(9, 10, "up"));
		assertTrue(sys.pushBoulder(8, 9, "right"));
		assertTrue(sys.pushBoulder(9, 8, "down"));
		assertTrue(sys.pushBoulder(0, 1, "left")); 
		
		// has boulders but in the way
		assertFalse(sys.pushBoulder(0, 1, "up"));
		assertFalse(sys.pushBoulder(9, 9, "right"));
		assertFalse(sys.pushBoulder(10, 9, "left"));
		assertFalse(sys.pushBoulder(10, 9, "down")); 
	}

}
