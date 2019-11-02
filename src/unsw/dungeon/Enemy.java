package unsw.dungeon;

public class Enemy extends LivingCreature {

    public Enemy(int x, int y) {
       super(x, y);
    }

	@Override
	public void killOff() {
		// TODO Auto-generated method stub
		System.out.println("enemy killed");
	}

}