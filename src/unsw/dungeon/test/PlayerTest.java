package unsw.dungeon.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class PlayerTest {

	@Test
	void testGetInvincibilityState() {
		// trying with out 2 different players for reliability
		Player player = new Player(0,0);
		assertNotSame(new InvincibilityState(player), player.getInvincibilityState());
		
		Player player2 = new Player(6,6);
		assertNotSame(new InvincibilityState(player2), player2.getInvincibilityState());
	}
	
	@Test
	void testGetNormalState() {
		// trying with out 2 different players for reliability
		Player player = new Player(0,0);
		assertNotSame(new NormalState(player), player.getNormalState());
		
		Player player2 = new Player(9,0);
		assertNotSame(new NormalState(player2), player2.getNormalState());
	}

	@Test
	void testGetDeadState() {
		// trying with out 2 different players for reliability
		Player player = new Player(0,0);
		assertNotSame(new DeadState(player), player.getDeadState());
		
		Player player2 = new Player(9,0);
		assertNotSame(new DeadState(player2), player2.getDeadState());
	}
	
	@Test
	void testgetState() {
		Player player = new Player(0,0);
		
		// default
		assertNotSame(new NormalState(player), player.getState());
			
		// testing other kinds of states
		PlayerState state = new NormalState(player);
		player.setState(state);
		assertEquals(state, player.getState());
		
		state = new DeadState(player);
		player.setState(state);
		assertEquals(state, player.getState());
		
		state = new InvincibilityState(player);
		player.setState(state);
		assertEquals(state, player.getState());
	}
	
	@Test
	void testCollectItem() {
		Sword sword = new Sword(5,6);
		Sword sword2 = new Sword(5,8);
		Key key = new Key(7,7,0);
		Key key2 = new Key(7,7,9);
		Treasure treasure = new Treasure(7,8);
		Treasure treasure2 = new Treasure(7,9);
		InvincibilityPotion potion = new InvincibilityPotion(2,4);
		InvincibilityPotion potion2 = new InvincibilityPotion(0,4);
		Player player = new Player(0,0);
		
		// no item
		assertFalse(player.collectItem(null));
		
		// sword - test pick once rule
		assertTrue(player.collectItem(sword));
		assertFalse(player.collectItem(sword2));
		
		// key - test pick once rule
		assertTrue(player.collectItem(key));
		assertFalse(player.collectItem(key2));
		
		// treasure - can pick more than one
		assertTrue(player.collectItem(treasure));
		assertTrue(player.collectItem(treasure2));
		
		// potion - can pick more than one
		assertTrue(player.collectItem(potion));
		assertTrue(player.collectItem(potion2));
	}
	
	@Test
	void testHasCertainItem() {
		Player player = new Player(0,0);
		Treasure treasure = new Treasure(1,2);
		Sword sword = new Sword(5,6);
		Key key = new Key(7,7,0);
		InvincibilityPotion potion = new InvincibilityPotion(2,4);
		
		// empty inventory
		assertFalse(player.hasCertainItem(null));
		assertFalse(player.hasCertainItem(treasure));
		assertFalse(player.hasCertainItem(sword));
		assertFalse(player.hasCertainItem(key));
		assertFalse(player.hasCertainItem(potion));
		
		// has items in inventory
		// checks that it works for different instances and classes
		player.collectItem(sword);
		assertTrue(player.hasCertainItem(sword));
		
		Sword sword2 = new Sword(9,6);
		assertTrue(player.hasCertainItem(sword2));
		
		assertFalse(player.hasCertainItem(key));
		
		Key key2 = new Key(7,7,9);
		player.collectItem(key);
		assertTrue(player.hasCertainItem(key));
		assertTrue(player.hasCertainItem(key2));
		
		player.collectItem(potion);
		assertTrue(player.hasCertainItem(potion));
		
		player.collectItem(treasure);
		assertTrue(player.hasCertainItem(treasure));
	}
	
	@Test
	void testGetItemByName() {
		Player player = new Player(0,9);
		Sword sword = new Sword(5,6);
		Treasure treasure = new Treasure(1,2);
		Key key = new Key(7,7,0);
		InvincibilityPotion potion = new InvincibilityPotion(2,4);
		
		// empty inventory
		assertNull(player.getItemByName("sword"));
		assertNull(player.getItemByName("rubbish"));
		assertNull(player.getItemByName("rjiojsh"));
		
		// has items in inventory
		player.collectItem(sword);
		assertEquals(sword, player.getItemByName("sword"));
		
		// does not have item in non-empty inventory
		assertNull(player.getItemByName("treasure"));
		
		// has items in inventory: other entities
		player.collectItem(treasure);
		assertEquals(treasure, player.getItemByName("treasure"));
		
		player.collectItem(key);
		assertEquals(key, player.getItemByName("key"));
		
		player.collectItem(potion);
		assertEquals(potion, player.getItemByName("potion"));
	}
}
