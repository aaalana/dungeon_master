package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

public class EnemySystem {
	private List<Enemy> enemies;

	public EnemySystem() {
		super();
		this.enemies = new ArrayList<Enemy>(); 
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void addEnemy(Enemy enemy) {
		this.enemies.add((Enemy) enemy);
	}
	
	public void removeEnemy(Enemy enemy) {
		this.enemies.remove(enemy);
	}
	
	 public void moveEnemies(int playerX, int playerY) {
    	for (Enemy entity : this.enemies) {
    		if (entity == null) continue;
    		entity.searchPlayer(playerX, playerY);
    	}
	 }

}
