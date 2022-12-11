
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Road_STUDENT_Test {
	private Road road;
	private Town town1;
	private Town town2;
	  
	@Before
	public void setUp() throws Exception {
		town1 = new Town("Gaithersburg");
		town2 = new Town("Rockville");
		road = new Road(town1, town2, 5, "355");		 
	}

	@After
	public void tearDown() throws Exception {
		road = null;
		town1 = null;
		town2 = null;
	}

	@Test
	public void testGetName() {
		assertEquals(road.getName(), "355");
	}

	@Test
	public void testGetSource() {
		assertEquals(road.getSource(), town1);
	}

	@Test
	public void testGetWeight() {
		assertEquals(road.getWeight(), 5);
	}

	@Test
	public void testGetDestination() {
		assertEquals(road.getDestination(), town2);
	}
	
	@Test
	public void testEquals() {
		Road road2 = new Road(town1, town2, 5, "355");		 
		assertTrue(road.equals(road2));
	}
	
	@Test
	public void testContains() {
		assertTrue(road.contains(town2));
	}
	
	@Test
	public void testToString() {
		assertEquals(road.toString(), "The road 355 is 5 miles long and starts at Gaithersburg and it ends at Rockville");
	}
	
	@Test
	public void testCompareTo() {
		Road road2 = new Road(town1, town2, 6, "355");		 
		assertTrue(road.compareTo(road2) != 0);
	}



}