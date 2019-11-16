package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

public class EnemySystem implements Subject {
	private List<Enemy> enemies;
	private List<Enemy2> enemies2;
	private EnemyGoal enemyGoal;

	public EnemySystem() {
		super();
		this.enemies = new ArrayList<Enemy>(); 
		this.enemies2 = new ArrayList<Enemy2>();
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
	
	public void addEnemy2(Entity enemy) {
		this.enemies2.add((Enemy2) enemy);
	}
	
	public <T> void removeEnemy(T enemy) {
		enemies.remove(enemy);
		if (this.enemies.size() == 0) {
			this.update();
		}
	}
	
<<<<<<< HEAD
	 public void moveEnemies(int playerX, int playerY, boolean isInvincible) {
	    	for (Entity entity : this.enemies) {
	    		if (entity == null) continue;
	    		
	    		if (entity instanceof unsw.dungeon.Enemy) {
	    			Enemy enemy = (Enemy) entity;
	    			enemy.searchPlayer(playerX, playerY, isInvincible);
	    		}
	    	}
	    	
	    	for (Entity entity : this.enemies2) {
	    		if (entity == null) continue;
	    		
	    		if (entity instanceof unsw.dungeon.Enemy2) {
	    			Enemy2 enemy = (Enemy2) entity;
	    			enemy.searchPlayer(playerX, playerY, isInvincible);
	    		}
	    	}
	    }
	 
=======
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
>>>>>>> f82f22e8cafecf4969437c742fdc86a2e6944eca
	public void update() {
		System.out.println("All enemies are dead, updating goals");
		enemyGoal.updateGoal();
	}

}
