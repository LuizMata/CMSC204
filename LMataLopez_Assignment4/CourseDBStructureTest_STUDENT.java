
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the CourseDBManager which is implemented from the
 * CourseDBManagerInterface
 * 
 */
public class CourseDBStructureTest_STUDENT {
	CourseDBStructure structure;

	@Before
	public void setUp() throws Exception {
		structure = new CourseDBStructure(20);
	}

	@After
	public void tearDown() throws Exception {
		structure = null;
	}

	/**
	 * Test the tableSize for CourseDBStructures constructed with both constructors
	 */
	@Test
	public void testGetTableSize() {
		assertEquals(19, structure.getTableSize());
	}

	@Test
	public void testHashTable() {

		//Create a course 
		CourseDBElement c1 = new CourseDBElement("Cmsc250", 12345, 4, "distance-learning", "ash"); 
		
		structure.add(c1); 
		structure.add(c1);  //should not add twice
	 
		ArrayList<String> list = structure.showAll(); 
		assertTrue(list.size()==1);  
		
		//Create another course
		CourseDBElement c2 = new CourseDBElement("Cmsc207", 54321, 4, "SC223", "Me"); 
	 
 		try {
			assertEquals(12345, structure.get(c1.getCRN()).getCRN());  
			structure.get(c2.getCRN()).getCRN(); // should throw exception
		} catch (IOException e) {

			assertTrue("threw Exception successfuly for the course not found", true);
		}
		
 		structure.add(c2);
 		list = structure.showAll(); 
		assertTrue(list.size()==2);  
		
		try {
			assertEquals(54321, structure.get(c2.getCRN()).getCRN());
		} catch (IOException e) {
			 
			fail("Should not throw exception");
		}  
		CourseDBElement c1Update = new CourseDBElement("math050", 12345, 4, "SC100", "new ash");
		structure.add(c1Update);  //Same CRN updated information
		list = structure.showAll(); 
		assertTrue(list.size()==2);  
		
		try {
			assertEquals(12345, structure.get(c1Update.getCRN()).getCRN());
			assertEquals("math050", structure.get(c1Update.getCRN()).getID());
		} catch (IOException e) {
			 
			fail("Should not throw exception");
		}  
	
	}
}
