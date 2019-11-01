package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class PortalSystem {
	private List<Portal> portals;
	
	public PortalSystem() {
		this.portals = new ArrayList<Portal>(); 
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
			if (match.getId() == portal.getId()) {
				return match;
			}
		}
		return null;
	}
	
	/**
	 * Calculates the distance the player has to move horizontally to get from
	 * one portal to its corresponding portal
	 * @param portal
	 * @return
	 */
	public int calculateHorizontalDist(Portal portal) {
		if (findMatchingPortal(portal).getX() > portal.getX()) {
			return findMatchingPortal(portal).getX() - portal.getX();
		} else {
			return -1 * (findMatchingPortal(portal).getX() - portal.getX());
		}
	}
	
	/**
	 * Calculates the distance the player has to move vertically to get from
	 * one portal to its corresponding portal
	 * @param portal
	 * @return
	 */
	public int calculateVerticalDist(Portal portal) {
		if (findMatchingPortal(portal).getY() > portal.getY()) {
			return (findMatchingPortal(portal).getY() - portal.getY());
		} else {
			return -1 * (findMatchingPortal(portal).getY() - portal.getY());
		}
	}
	
	/**
	 * Moves the player from one portal to its corresponding portal
	 * @param portal
	 * @param player
	 */
	public void transportPlayer(Portal portal, Player player) {
		int y = calculateVerticalDist(portal); 
		while (y != 0) {
			if (y > 0) {
				player.moveDown();
				y--;
			} else {
				player.moveUp();
				y++;
			}
		}
		
		int x = calculateHorizontalDist(portal);
		while (x != 0) {
			if (x > 0) {
				player.moveLeft();
				x++;
			} else {
				player.moveRight();
				x++;
			}
		}
	}
}
