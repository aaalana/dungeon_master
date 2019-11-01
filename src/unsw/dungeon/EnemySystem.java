package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

public class EnemySystem {
	private List<Entity> enemies;

	public EnemySystem() {
		super();
		this.enemies = new ArrayList<Entity>(); 
	}

	public List<Entity> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Entity> enemies) {
		this.enemies = enemies;
	}
	
	public void addEnemy(Entity enemy) {
		this.enemies.add((Enemy) enemy);
	}
	
	public void removeEnemy(Entity enemy) {
		this.enemies.remove(enemy);
	}
	
	 public void moveEnemies(int playerX, int playerY) {
	    	for (Entity entity : this.enemies) {
	    		if (entity == null) continue;
	    		
	    		if (entity instanceof unsw.dungeon.Enemy) {
	    			Enemy enemy = (Enemy) entity;
	    			enemy.searchPlayer(playerX, playerY);
	    		}
	    	}
	    }

}
