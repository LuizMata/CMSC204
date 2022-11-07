import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Manager for the course database, makes use of the courseDBStructure and allows for file input.
 * @author Luiz
 *
 */
public class CourseDBManager implements CourseDBManagerInterface{
	String id;
	int crn;
	int credits;
	String roomNum;
	String instructor;
	
	CourseDBStructure storage;

	/**
	 * Default no argument constructor.
	 */
	public CourseDBManager() {
		storage = new CourseDBStructure(20);
	}


	/**
	 * Add method which allows the user to add courses to the structure.
	 * @param id Course ID ie. CMSC204
	 * @param crn CRN or Course Registration Number.
	 * @param credits Number of credits for a course.
	 * @param roomNum Course room number.
	 * @param instructor Instructor for the course.
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement c1 = new CourseDBElement(id, crn, credits, roomNum, instructor);
		storage.add(c1);
	}

	/**
	 * A class search which returns the CourseDBElement of your choice
	 * @param crn The CRN of the course which is to be found
	 * @throws IOException When the course that is being queried does not exist
	 * @return CourseDBElement object associated with provided CRN
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return storage.get(crn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Reads provided files which contain classes, information is separated by spaces.
	 * @param input Input files which contains courses/information
	 * @throws FileNotFoundException When the provided file cannot be found.
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner sc = new Scanner(input);	
		String[] arr;
		while(sc.hasNextLine()) {
			String s = sc.nextLine();
			
			//extracting information from lines of text.
			arr = s.split(" ",5);
			id = arr[0];
			crn = Integer.valueOf(arr[1]);
			credits = Integer.valueOf(arr[2]);
			roomNum = arr[3];
			instructor = arr[4];
			CourseDBElement c = new CourseDBElement(id, crn, credits, roomNum, instructor);
			storage.add(c);
		}
		sc.close();
		
	}

	/**
	 * A method to display all courses contained in the structure.
	 * @return An arraylist containing all of the courses
	 */
	@Override
	public ArrayList<String> showAll() {
		return storage.showAll();
	}

}
