package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.DungeonControllerLoader.ImageManager;

/**
 * System for managing the transportation of the player when the player enters
 * a portal
 * @author Alana Hua
 *
 */
public class PortalSystem {
	private List<Portal> portals;
	private ImageManager imageManager;
	
	public PortalSystem() {
		this.portals = new ArrayList<Portal>(); 
		this.imageManager = new DungeonControllerLoader.ImageManager();
	}
	
	/**
	 * Adds a portal to a list of portals
	 * @param portal
	 */
	public void addPortal(Portal portal) {
		portals.add(portal);
	}
	
	/**
	 * Finds a corresponding portal given a portal that has the same id
	 * @param portal
	 * @return
	 */
	public Portal findMatchingPortal(Portal portal) {
		for(Portal match : portals) {
			if (match.getId() == portal.getId() && !isSamePortal(portal, match)) {
				return match;
			}
		}
		return null;
	}
	
	/**
	 * Moves the player from one portal to its corresponding portal
	 * @param portal
	 * @param player
	 */
	public void transportPlayer(Portal portal, Player player) {
		Portal match = findMatchingPortal(portal);
		match.setState(false);
		try { 
			player.setPosition(match.getX(), match.getY());
			imageManager.toFront(player.getImage());
	    } catch (NullPointerException e) { 
	        System.out.println("Could not find a matching portal. Can't teleport player."); 
	    } 
	}
	
	/**
	 * Checks if the two portals are the same instance
	 * @param portal one portal instance
	 * @param match another portal instance
	 * @return true when the two instances are the same
	 */
	public boolean isSamePortal(Portal portal, Portal match) {
		if (portal == null || match == null) 
			return false;
		
		// it is assumed by design that all entities for the current application run belong to the same dungeon
		return portal.getX() == match.getX() && portal.getY() == match.getY() && portal.getId() == match.getId();
	}
}
