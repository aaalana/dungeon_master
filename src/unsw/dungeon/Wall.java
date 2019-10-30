package unsw.dungeon;

public class Wall extends Entity {
	private String name;
	
    public Wall(int x, int y) {
        super(x, y);
        this.name = "Wall";
    }
    /*
    public getName() {
    	return this.name;
    }*/

}
