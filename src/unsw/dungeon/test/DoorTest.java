package unsw.dungeon.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import unsw.dungeon.CantMove;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

class DoorTest {

	@Test
	void testBlock() {
		Door door = new Door(0, 0, 0, new CantMove());
		Door door2 = new Door(0, 0, 1, new CantMove());
		Dungeon dungeon = new Dungeon(18, 16);
		Player player = new Player(1, 0, dungeon);
		Player player2 = new Player(0, 1, dungeon);
		Key matchingKey = new Key(1, 0, 0);
		Key wrongKey = new Key(1, 0, 1);
		
		// no key at door
		assertTrue(door.block(player));
		
		player.collectItem(wrongKey);
		player2.collectItem(matchingKey);
		
		// wrong key at door
		assertTrue(door.block(player));
		assertTrue(door2.block(player2));
		
		// unlocking the door is tested in the 
		// front end
	}
	
	@Test
	void testGetId() {
		Door door = new Door(0, 0, 0, new CantMove());
		Door door2 = new Door(0, 0, 1, new CantMove());
		Door door3 = new Door(0, 0, 100, new CantMove());
		
		// testing with different ids
		assertEquals(0, door.getId());
		assertEquals(1, door2.getId());
		assertEquals(100, door3.getId());
	}


}
