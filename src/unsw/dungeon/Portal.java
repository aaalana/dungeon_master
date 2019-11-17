package unsw.dungeon;

/**
 * Portal entity which is connected to another.
 * -it communicates with the portal system to transport a 
 * player that enters it
 * another
 * @author Alana Hua
 *
 */
public class Portal extends Obstacle {
	
	// id is used to pair match portals
	private int id; 
	
	private Dungeon dungeon;
	
	// when the portal's state is false - the player teleported from the portal
	// when the portal's state is true - the player is entering the portal
	private boolean state;
	
	public Portal(int x, int y, int id, Dungeon dungeon) {
        super(x, y);
        this.id = id;
        this.dungeon = dungeon;
        this.state = true;
    }

	@Override
	public void trigger(boolean state) {
		// if the player is entering the portal and is positioned on the portal,
		// teleport the player to a connected portal
		if (state && this.state) {
			System.out.println("transporting player via portal");
			getPortalAssist().transportPlayer(this, dungeon.getPlayer());
			System.out.println("transported player via portal");
		}
		
		// if the player is not on the portal, set the portal state to true
		if (!state) {
			setState(true);
		}
	}
	
	/**
	 * Sets the portal's state
	 */
	public void setState(boolean _state) {
		this.state = _state;
	}
	
	/**
	 * Gets the ID of the portal
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

	@Override
	public void setExitGoal(ExitGoal exitGoal) {
	     // Unused:
	     // Used in exit class (method in Obstacle to avoid 
	     // typecasting obstacles to exits)
	}
}
