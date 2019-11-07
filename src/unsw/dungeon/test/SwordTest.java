package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class SwordTest {

	@Test
	void testGetUses() {
		Sword sword = new Sword(5, 1);
		Dungeon dungeon = new Dungeon(18, 16);
		Player player = new Player(2, 2, dungeon);
		
		// false on default
		assertFalse(sword.getStatus());
		
		// status is true when false previously
		sword.useItem(player);
		assertTrue(sword.getStatus());
		
		// status is false when true previously
		sword.useItem(player);
		assertFalse(sword.getStatus());
	}
	
	@Test
	void testReduceUses() {
		Sword sword = new Sword(5, 1);
		Dungeon dungeon = new Dungeon(18, 16);
		Player player = new Player(2, 2, dungeon);
		
		// default should have 5 uses
		assertEquals(5, sword.getUses());
		
		// reduce use by 1
		sword.reduceUses();
		assertEquals(4, sword.getUses());
		
		// reduce use by 1
		sword.reduceUses();
		assertEquals(3, sword.getUses());
		
		// reduce use by 1
		sword.reduceUses();
		assertEquals(2, sword.getUses());
						
		// reduce use by 1
		sword.reduceUses();
		assertEquals(1, sword.getUses());
		
		// reduce use by 1
		sword.reduceUses();
		assertEquals(0, sword.getUses());	
	}
}
