package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class EnemySystemTest {

	@Test
	void testGetEnemies() {
		EnemySystem sys = new EnemySystem();
		Dungeon dungeon = new Dungeon(18,16);
		Enemy e1 = new Enemy(dungeon, 2, 3);
		Enemy e2 = new Enemy(dungeon, 8, 3);
		Enemy e3 = new Enemy(dungeon, 8, 7);
		
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		
		// empty list
		assertEquals(enemies, sys.getEnemies());
		
		// has enemies
		enemies.add(e1);
		enemies.add(e2);
		
		sys.addEnemy(e1);
		sys.addEnemy(e2);
		assertEquals(enemies, sys.getEnemies());
		
		// different lists
		enemies.add(e3);
		assertNotSame(enemies, sys.getEnemies());
	}

}
