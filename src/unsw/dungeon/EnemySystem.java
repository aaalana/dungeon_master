package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

public class EnemySystem implements Subject {
	private List<Enemy> enemies;
	private EnemyGoal enemyGoal;

	public EnemySystem() {
		super();
		this.enemies = new ArrayList<Enemy>(); 
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public Goal getEnemyGoal() {
		return enemyGoal;
	}

	public void setEnemyGoal(EnemyGoal enemyGoal) {
		this.enemyGoal = enemyGoal;
	}
	
	public void addEnemy(Entity enemy) {
		this.enemies.add((Enemy) enemy);
	}
	
	public <T> void removeEnemy(T enemy) {
		enemies.remove(enemy);
		if (this.enemies.size() == 0) {
			this.update();
		}
	}
	
	/**
	 * Moves the enemies in the dungeon
	 * @param playerX
	 * @param playerY
	 */
	public void moveEnemies(int playerX, int playerY) {
    	for (Entity entity : this.enemies) {
    		if (entity == null) continue;
    		
    		if (entity instanceof unsw.dungeon.Enemy) {
    			Enemy enemy = (Enemy) entity;
    			enemy.searchPlayer(playerX, playerY);
    		}
    	}
    }
	
	/**
	 * Updates the enemy goal
	 */
	public void update() {
		System.out.println("All enemies are dead, updating goals");
		enemyGoal.updateGoal();
	}

}
