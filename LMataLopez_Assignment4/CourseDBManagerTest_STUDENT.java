

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the STUDENT test file for the CourseDBManager
 * which is implemented from the CourseDBManagerInterface
 * 
 */
public class CourseDBManagerTest_STUDENT {
	private CourseDBManagerInterface Manager;

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Manager = new CourseDBManager();
	}

	/**
	 * Set Manager reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		Manager = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			Manager.add("BIOL150",12345,4,"SC321","Luiz Mata");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		Manager.add("Cmsc140", 12345, 4, "Sc132", "luiz");
		Manager.add("Cmsc140", 34574, 4, "Sc223", "david");
		Manager.add("Cmsc140", 11343, 4, "Sc423", "chris");
		Manager.add("Cmsc140", 34373, 4, "Sc323", "marcos");
		ArrayList<String> list = Manager.showAll();
		assertEquals(list.get(0),"\nCourse:Cmsc140 CRN:11343 Credits:4 Instructor:chris Room:Sc423");
	 	assertEquals(list.get(1),"\nCourse:Cmsc140 CRN:34373 Credits:4 Instructor:marcos Room:Sc323");
		assertEquals(list.get(2),"\nCourse:Cmsc140 CRN:34574 Credits:4 Instructor:david Room:Sc223");
		assertEquals(list.get(3),"\nCourse:Cmsc140 CRN:12345 Credits:4 Instructor:luiz Room:Sc132");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("example.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CHEM131 54129 4 SC333 Mr Chem teacher");
			inFile.print("CHEM132 91238 4 SC332 Ms Chem teacher");
			inFile.close();
			
			Manager.readFile(inputFile);
			assertEquals("CHEM131",Manager.get(54129).getID());
			assertEquals("Mr Chem teacher",Manager.get(54129).getInstructorName());
			assertEquals("SC333",Manager.get(54129).getRoomNum());
			assertEquals("CHEM132",Manager.get(91238).getID());
			assertEquals("Ms Chem teacher",Manager.get(91238).getInstructorName());
			assertEquals("SC332",Manager.get(91238).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
