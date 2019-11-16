package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

/**
 * Key Test 
 * @author Alana Hua
 *
 */
class KeyTest {

	@Test
	void testGetId() {
		Key key = new Key(9, 2, 0);
		assertEquals(0, key.getId());
		Key key2 = new Key(9, 2, 1);
		assertEquals(1, key2.getId());
		Key key3 = new Key(9, 2, 3);
		assertEquals(3, key3.getId());
	}
}
