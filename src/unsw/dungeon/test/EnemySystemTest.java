package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

/**
 * Archer Test in enemy system
 * @author Alana Hua
 *
 */
class EnemySystemTest {

	@Test
	void testGetEnemies() {
		EnemySystem sys = new EnemySystem();
		Dungeon dungeon = new Dungeon(18,16);
		Archer e1 = new Archer(dungeon, 2, 3, new ItMovesInFourDirec());
		Archer e2 = new Archer(dungeon, 8, 3, new ItMovesInFourDirec());
		Archer e3 = new Archer(dungeon, 8, 7, new ItMovesInFourDirec());
		Crab e4 = new Crab(dungeon, 8, 8, new ItMovesInFourDirec());
		
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		
		// empty list
		assertEquals(enemies, sys.getEnemies());
		
		// has enemies
		enemies.add(e4);
		enemies.add(e1);
		enemies.add(e2);
		
		
		sys.addCrab(e4);
		sys.addArcher(e1);
		sys.addArcher(e2);
		
		assertEquals(enemies, sys.getEnemies());
		
		// different lists
		enemies.add(e3);
		assertNotSame(enemies, sys.getEnemies());
	}

}
