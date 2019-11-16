package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

/**
 * EnemySystem which controls the enemy movement, EnemyGoal and manages the
 * lists of enemies (archers and crabs)
 * @author Alana Hua
 * @author Arthur Wong
 *
 */
public class EnemySystem implements Subject {
	private List<Archer> archers;
	private List<Crab> crabs;
	private EnemyGoal enemyGoal;

	public EnemySystem() {
		super();
		this.archers = new ArrayList<Archer>(); 
		this.crabs = new ArrayList<Crab>();
	}

	/**
	 * Gets a list of all the enemies in the system
	 * @return
	 */
	public List<Enemy> getEnemies() {
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.addAll(crabs);
		enemies.addAll(archers);
		return enemies;
	}

	/**
	 * Gets the enemy goal
	 * @return
	 */
	public Goal getEnemyGoal() {
		return enemyGoal;
	}

	/**
	 * Sets the enemy goal
	 * @param enemyGoal
	 */
	public void setEnemyGoal(EnemyGoal enemyGoal) {
		this.enemyGoal = enemyGoal;
	}
	
	/**
	 * Adds an archer to the archer list
	 * @param enemy
	 */
	public void addArcher(Archer enemy) {
		archers.add(enemy);
	}
	
	/**
	 * Adds a crab to the crabs list
	 * @param enemy
	 */
	public void addCrab(Crab enemy) {
		crabs.add(enemy);
	}
	
	/**
	 * Removes an enemy from the system
	 * @param <T>
	 * @param enemy
	 */
	public <T> void removeEnemy(T enemy) {
		if (enemy instanceof unsw.dungeon.Archer) {
			archers.remove(enemy);
		} else {
			crabs.remove(enemy);
		}
		
		if (archers.size() == 0 && crabs.size() == 0) 
			this.update();
	}

	/**
	 * Moves the enemies of the system
	 * @param playerX
	 * @param playerY
	 * @param isInvincible
	 */
	public void moveEnemies(int playerX, int playerY, boolean isInvincible) {
    	// move the archer enemies
		for (Archer entity : this.archers) {
    		if (entity == null) continue;
    		
    		if (entity instanceof unsw.dungeon.Archer) {
    			entity.searchPlayer(playerX, playerY, isInvincible);
    		}
    	}
    	
		// move the crab enemies
    	for (Crab entity : this.crabs) {
    		if (entity == null) continue;
    		
    		if (entity instanceof unsw.dungeon.Crab) {
    			entity.searchPlayer(playerX, playerY, isInvincible);
    		}
    	}
    }
	
	/**
	 * Updates the enemies goal when completed
	 */
	public void update() {
		System.out.println("All archers are dead, updating goals");
		enemyGoal.updateGoal();
	}
}
