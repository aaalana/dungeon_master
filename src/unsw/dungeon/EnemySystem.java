package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;

public class EnemySystem implements Subject {
	private List<Archer> archers;
	private List<Crab> crabs;
	private EnemyGoal enemyGoal;

	public EnemySystem() {
		super();
		this.archers = new ArrayList<Archer>(); 
		this.crabs = new ArrayList<Crab>();
	}

	public List<Enemy> getEnemies() {
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.addAll(crabs);
		enemies.addAll(archers);
		return enemies;
	}

	public void setArchers(List<Archer> archers) {
		this.archers = archers;
	}
	
	public Goal getEnemyGoal() {
		return enemyGoal;
	}

	public void setEnemyGoal(EnemyGoal enemyGoal) {
		this.enemyGoal = enemyGoal;
	}
	
	public void addArcher(Archer enemy) {
		archers.add(enemy);
	}
	
	public void addCrab(Entity enemy) {
		crabs.add((Crab) enemy);
	}
	
	public <T> void removeEnemy(T enemy) {
		if (enemy instanceof unsw.dungeon.Archer) {
			archers.remove(enemy);
		} else {
			crabs.remove(enemy);
		}
		
		if (archers.size() == 0 && crabs.size() == 0) 
			this.update();
	}

	public void moveEnemies(int playerX, int playerY, boolean isInvincible) {
    	for (Entity entity : this.archers) {
    		if (entity == null) continue;
    		
    		if (entity instanceof unsw.dungeon.Archer) {
    			Archer enemy = (Archer) entity;
    			enemy.searchPlayer(playerX, playerY, isInvincible);
    		}
    	}
    	
    	for (Entity entity : this.crabs) {
    		if (entity == null) continue;
    		
    		if (entity instanceof unsw.dungeon.Crab) {
    			Crab enemy = (Crab) entity;
    			enemy.searchPlayer(playerX, playerY, isInvincible);
    		}
    	}
    }
	
	public void update() {
		System.out.println("All archers are dead, updating goals");
		enemyGoal.updateGoal();
	}
}
