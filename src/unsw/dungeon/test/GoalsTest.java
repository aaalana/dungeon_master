package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

/**
 * Goals test
 * 
 *
 */

class GoalsTest {
	@Test
	void TestSImpleGoal() {
		Dungeon dungeon = new Dungeon(20, 18);
		
		ExitGoal exitGoal = new ExitGoal(dungeon);
		EnemyGoal enemyGoal = new EnemyGoal(dungeon);
		TreasureGoal treasureGoal = new TreasureGoal(dungeon);
		BoulderGoal boulderGoal = new BoulderGoal(dungeon);
			
		//Testing with a simple, single goal
		dungeon.setGoal(exitGoal);
		exitGoal.setComplete();
		assertTrue(dungeon.updateGoal());
	}
	
	/**
	 * Testing a single AND/OR goal 
	 */
	@Test
	void TestSingleNesting() {
		Dungeon dungeon = new Dungeon(20, 18);
		ExitGoal exitGoal = new ExitGoal(dungeon);
		TreasureGoal treasureGoal = new TreasureGoal(dungeon);
		ANDGoal andGoal = new ANDGoal(dungeon);
		
		
		dungeon.setGoal(andGoal);
		andGoal.addGoal(exitGoal);
		andGoal.addGoal(treasureGoal);
		assertFalse(dungeon.updateGoal());
		exitGoal.setComplete();
		assertFalse(dungeon.updateGoal());
		treasureGoal.setComplete();
		assertTrue(dungeon.updateGoal());
		
	}
	
	/**
	 * Testing a complicated nesting
	 */
	@Test
	void TestComplexNesting() {
		Dungeon dungeon = new Dungeon(20, 18);
		
		ANDGoal andGoal1 = new ANDGoal(dungeon);
		ANDGoal andGoal2 = new ANDGoal(dungeon);
		ORGoal orGoal = new ORGoal(dungeon);
		
		ExitGoal exitGoal = new ExitGoal(dungeon);
		EnemyGoal enemyGoal = new EnemyGoal(dungeon);
		TreasureGoal treasureGoal = new TreasureGoal(dungeon);
		BoulderGoal boulderGoal = new BoulderGoal(dungeon);
		
		dungeon.setGoal(andGoal1);
		
		andGoal1.addGoal(exitGoal);
		andGoal1.addGoal(orGoal);
		
		orGoal.addGoal(enemyGoal);
		orGoal.addGoal(andGoal2);
		
		andGoal2.addGoal(treasureGoal);
		andGoal2.addGoal(boulderGoal);
		
		//Player must get to the exit
		//They must also, EITHER kill or enemies, 
		//or they must get all the treasure AND activate the switches
		
		treasureGoal.setComplete();
		assertFalse(andGoal2.getStatus());
		assertFalse(orGoal.getStatus());
		assertFalse(andGoal1.getStatus());
		assertFalse(dungeon.updateGoal());
		
		enemyGoal.setComplete();
		assertFalse(andGoal2.getStatus());
		assertTrue(orGoal.getStatus());
		assertFalse(andGoal1.getStatus());
		assertFalse(dungeon.updateGoal());
		
		exitGoal.setComplete();
		assertFalse(andGoal2.getStatus());
		assertTrue(orGoal.getStatus());
		assertTrue(andGoal1.getStatus());
		assertTrue(dungeon.updateGoal());
	}
}