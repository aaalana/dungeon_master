package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

class DungeonTest {

	@Test
	void testCheckSquare() {
		Dungeon dungeon = new Dungeon(18,16);
		Portal portal = new Portal(5,0,0, dungeon);
		Portal portal2 = new Portal(8,9,0, dungeon);
		Wall wall = new Wall(0,0, new CantMove());
		Wall wall2 = new Wall(1,0, new CantMove());
		Sword sword = new Sword(9,9);
		Key key = new Key(10,9,0);
		
		dungeon.addEntity(portal);
		dungeon.addEntity(portal2);
		dungeon.addEntity(wall);
		dungeon.addEntity(wall2);
		dungeon.addEntity(sword);
		dungeon.addEntity(key);
		
		// random testing of coordinates
		assertEquals("unsw.dungeon.Portal", dungeon.checkSquare(5,0));
		assertEquals("unsw.dungeon.Portal", dungeon.checkSquare(8,9));
		assertEquals("unsw.dungeon.Wall", dungeon.checkSquare(0,0));
		assertEquals("unsw.dungeon.Wall", dungeon.checkSquare(1,0));
		
		// check edge cases next to portal
		assertEquals("unsw.dungeon.Sword", dungeon.checkSquare(9,9));
		assertEquals("unsw.dungeon.Key", dungeon.checkSquare(10,9));
		
		// check null cases
		assertEquals("None", dungeon.checkSquare(18,9));
		assertEquals("None", dungeon.checkSquare(0,1));
		assertEquals("None", dungeon.checkSquare(11,1));
	}
	
	@Test
	void testCheckBlocker() {
		Dungeon dungeon = new Dungeon(18,16);
		Player player = new Player(0, 0, dungeon);
		Wall wall = new Wall(0,0, new CantMove());
		Wall wall2 = new Wall(1,0, new CantMove());
		Door door = new Door(2,1,0, new CantMove());
		
		// check empty list
		assertFalse(dungeon.checkBlocker(0,0));
		assertFalse(dungeon.checkBlocker(9,0));
		
		dungeon.addBlocker(null);
		dungeon.addBlocker(null);
		dungeon.addBlocker(wall);
		dungeon.addBlocker(wall2);
		dungeon.addBlocker(door);
		dungeon.setPlayer(player);
		
		// random testing of coordinates for walls and locked door
		assertTrue(dungeon.checkBlocker(0,0));
		assertTrue(dungeon.checkBlocker(1,0));
		assertTrue(dungeon.checkBlocker(2,1));
		
		// unlocked door and revisiting door
		door.unlock();
		assertFalse(dungeon.checkBlocker(2,1));
		assertFalse(dungeon.checkBlocker(2,1));
	}
	
	@Test
	void testShareSquare() {
		Dungeon dungeon = new Dungeon(18,16);
		Player player = new Player(0, 0, dungeon);
		Portal portal = new Portal(1,0,0,dungeon);
		Portal portal2 = new Portal(8,0,0,dungeon);
		Switch _switch = new Switch(3,2);
		Switch _switch2 = new Switch(1,1);
		Boulder boulder = new Boulder(dungeon, 3,2, new ItMoves());
		Boulder boulder2 = new Boulder(dungeon, 2,2, new ItMoves());
		Exit exit = new Exit(15,6);
		
		// check empty list
		assertFalse(dungeon.shareSquare(null));
		assertFalse(dungeon.shareSquare(_switch));
		
		dungeon.addEntity(exit);
		dungeon.addEntity(_switch);
		dungeon.addEntity(_switch2);
		dungeon.addEntity(boulder);
		dungeon.addEntity(boulder2);
		dungeon.addEntity(portal);
		dungeon.addEntity(portal2);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		// switches - triggered and untriggered
		assertTrue(dungeon.shareSquare(_switch));
		assertFalse(dungeon.shareSquare(_switch2));
		
		// exit - player not at exit and player at exit
		assertFalse(dungeon.shareSquare(exit));
		player.teleport(15,6);
		assertTrue(dungeon.shareSquare(exit));
		
		// portal - player not at portal and player at portal
		assertFalse(dungeon.shareSquare(portal));
		player.teleport(1,0);
		assertTrue(dungeon.shareSquare(portal));
	}
	
}
