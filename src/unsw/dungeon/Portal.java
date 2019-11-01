package unsw.dungeon;

public class Portal extends Obstacle {
	// to pair match portals
	private int id; 
	private Dungeon dungeon;
	
	public Portal(int x, int y, int id, Dungeon dungeon) {
        super(x, y);
        this.id = id;
        this.dungeon = dungeon;
    }

	@Override
	public void trigger(boolean state) {
		if (state) {
			getPortalAssist().transportPlayer(this, dungeon.getPlayer());
			System.out.println("transported player via portal");
		}
	}

	/**
	 * gets the ID of the portal
	 * @return portal id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Calls on the portal system to help transport the player
	 * @return portalSystem
	 */
	public PortalSystem getPortalAssist() {
		return dungeon.getPortals();
	}
}
