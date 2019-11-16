package unsw.dungeon.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import unsw.dungeon.*;

class PlayerTest {

	@Test
	void testsetPosition() {
		Dungeon dungeon = new Dungeon(18,16);
		Player player = new Player(0,0, dungeon);
		
		// try the same location as the player
		player.setPosition(0, 0);
		assertEquals(0, player.getX());
		assertEquals(0, player.getY());
		
		// try a different location from the player 
		player.setPosition(3, 4);
		assertEquals(3, player.getX());
		assertEquals(4, player.getY());
		
		// trying out with another player for reliability
		Player player2 = new Player(6,6, dungeon);
		assertNotSame(new InvincibilityState(player2), player2.getInvincibilityState());
		player2.setPosition(0, 0);
		assertEquals(0, player2.getX());
		assertEquals(0, player2.getY());
	}
	
	@Test
	void testPotion() {
		Dungeon dungeon = new Dungeon(18,16);
		Player player = new Player(0,0, dungeon);
		Player player2 = new Player(0,0, dungeon);
		InvincibilityPotion potion = new InvincibilityPotion(2, 3);
		InvincibilityPotion potion2 = new InvincibilityPotion(7, 3);
		SpeedPotion potion3 = new SpeedPotion(2, 3);
		SpeedPotion potion4 = new SpeedPotion(7, 3);
		
		// test the player's invincibility time period
		player.drinkPotion(potion);
		assertSame(player.getInvincibilityState(), player.getState());
		
		// when the player drinks another potion while invincibility is on
		player.drinkPotion(potion2);
		assertSame(player.getInvincibilityState(), player.getState());
		
		// when the player is killed off
		player.killOff();
		assertSame(player.getDeadState(), player.getState());
		
		// note: the timer for invincibility and speed states is tested in the 
		// frontend
		
		// test the player's invincibility time period
		player2.drinkPotion(potion3);
		assertSame(player2.getSpeedState(), player2.getState());
		
		// when the player drinks another potion while invincibility is on
		player2.drinkPotion(potion4);
		assertSame(player2.getSpeedState(), player2.getState());
		
		// note: the timer for invincibility and speed states is tested in the 
		// frontend
	}
	
	@Test
	void testGetInvincibilityState() {
		// trying with out 2 different players for reliability
		Dungeon dungeon = new Dungeon(18,16);
		
		Player player = new Player(0, 0, dungeon);
		assertNotSame(new InvincibilityState(player), player.getInvincibilityState());
		
		Player player2 = new Player(6, 6, dungeon);
		assertNotSame(new InvincibilityState(player2), player2.getInvincibilityState());
	}
	
	@Test
	void testGetNormalState() {
		// trying with out 2 different players for reliability
		Dungeon dungeon = new Dungeon(18,16);
		
		Player player = new Player(0,0, dungeon);
		assertNotSame(new NormalState(player), player.getNormalState());
		
		Player player2 = new Player(9,0, dungeon);
		assertNotSame(new NormalState(player2), player2.getNormalState());
	}

	@Test
	void testGetDeadState() {
		// trying with out 2 different players for reliability
		Dungeon dungeon = new Dungeon(18,16);
		Player player = new Player(0, 0, dungeon);
		assertNotSame(new DeadState(player), player.getDeadState());
		
		Player player2 = new Player(9, 0, dungeon);
		assertNotSame(new DeadState(player2), player2.getDeadState());
	}
	
	@Test
	void testgetState() {
		Dungeon dungeon = new Dungeon(18,16);
		Player player = new Player(0, 0, dungeon);
		
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
		Dungeon dungeon = new Dungeon(18,16);
		Player player = new Player(0, 0, dungeon);
		
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
	void testgetItem() {
		Dungeon dungeon = new Dungeon(18, 16);
		Player player = new Player(0, 9, dungeon);
		Sword sword = new Sword(5, 6);
		Treasure treasure = new Treasure(1,2);
		Key key = new Key(7,7,0);
		InvincibilityPotion potion = new InvincibilityPotion(2,4);
		
		// empty inventory
		assertNull(player.getItem("sword"));
		assertNull(player.getItem("rubbish"));
		assertNull(player.getItem("rjiojsh"));
		
		// has items in inventory
		player.collectItem(sword);
		assertEquals(sword, player.getItem("sword"));
		
		// does not have item in non-empty inventory
		assertNull(player.getItem("treasure"));
		
		// has items in inventory: other entities
		player.collectItem(treasure);
		assertEquals(treasure, player.getItem("treasure"));
		
		player.collectItem(key);
		assertEquals(key, player.getItem("key"));
		
		// collection of invincibility potion tested in the front end
		// collection of speed potion tested in the front end
		// (since these rely on timers are more easily tested when observed
	}
}
