import java.io.IOException;
import java.util.ArrayList;
/**
 * A course database structure that holds class information
 * @author Luiz
 *
 */
public class CourseDBStructure implements CourseDBStructureInterface{
	
	private CourseDBElement[] storage;
	
	/**
	 * Main constructor that takes in an estimated number of courses
	 * that a student will take.
	 * @param numCourses Approximate number courses taken
	 */
	public CourseDBStructure(int numCourses) {
		int size = primeFinder(numCourses, 1.5);
		storage = new CourseDBElement[size];
	}
	
	/**
	 * Testing Constructor
	 * @param test For testing purposes
	 * @param numCourses Number of courses
	 */
	public CourseDBStructure(String test, int numCourses) {
		storage = new CourseDBElement[numCourses];
	}
	
	/**
	 * Add method which allows the user to add courses to the structure.
	 * @param element An element of type CourseDBElement which is to be added
	 */
	@Override
	public void add(CourseDBElement element) {
		boolean present = false;
		
		int pos = element.getCRN()%storage.length;
		CourseDBElement temp = storage[pos];
		
		while(temp != null) {
			if(storage[pos] == element) {
				present = true;
				break;
			}
			//Check if object with same CRN is being updated
			if(!present && (temp.compareTo(element)==1)) {
			
				//remove old CDE
				if(temp.getNext() == null && temp.getPrev() == null) {
					storage[pos] = null;
					break;
				}
				if(temp.getNext() == null) {
					temp.getPrev().setNext(null);
					break;
				}
				if(temp.getPrev() == null) {
					storage[pos] = temp.getNext();
					break;
				}
				else {
					temp.getPrev().setNext(temp.getNext());
					temp.getNext().setPrev(temp.getPrev());
				}
				
			
			
			}
			temp = temp.getNext();
		}
	
		if(storage[pos] == null) {
			storage[pos]=element;
		}
		else if(present == false) {
			element.setNext(storage[pos]);
			storage[pos].setPrev(element);
			storage[pos] = element;
			
		}
			
	}
		
	/**
	 * A class search which returns the CourseDBElement of your choice
	 * @param crn The CRN of the course which is to be found
	 * @throws IOException When the course that is being queried does not exist
	 * @return CourseDBElement object associated with provided CRN
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		int pos = crn%storage.length;
		CourseDBElement temp = storage[pos];
		
		if(temp == null) {
			throw new IOException("Course not found");
		}
		if(temp.getCRN() == crn) {
			return storage[pos];
		}
		else {
			while((temp != null)) {
				if(temp.getCRN() == crn) {
					return temp;
				}
				temp = temp.getNext();
			}
		}
		throw new IOException("Course not found");
	}

	/**
	 * A method to display all courses contained in the structure.
	 * @return An arraylist containing all of the courses
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> display = new ArrayList<>();
		//look through each element of hash table
		for(int i = 0; i < storage.length; i++) {
			CourseDBElement temp = storage[i];
			//look through the linked list if it exists of each hash table element
			while(temp != null) {
				display.add(temp.toString());
				temp = temp.getNext();
			}
		}

		return display;
	}

	/**
	 * Size method to return the total size of the hash table
	 * @return Size of hash table
	 */
	@Override
	public int getTableSize() {
		return storage.length;
	}
	
	/**
	 * Supplementary method that finds the closest 4k+3 prime to the provided integer
	 * @param n The desired size of hash table/ estimated courseload
	 * @param loadfactor load factor of the bucket hash table
	 * @return A 4k+3 prime
	 */
	public static int primeFinder(int n, double loadfactor) {
		boolean is4kp3 = false;
		boolean isPrime = false;
		int prime, highDivisor, d;
		
		prime = (int)(n/loadfactor);
		if(prime%2 == 0) {prime++;}
		
		while(is4kp3 == false) {
			while(isPrime == false) {
				highDivisor = (int)(Math.sqrt(prime) + 0.5);
				for(d=highDivisor; d>1; d--) {
					if(prime % d == 0) {
						break;
					}
				}
				if(d != 1) {
					prime = prime + 2;
				}
				else {
					isPrime = true;
				}
				
			}
			if((prime - 3) % 4 == 0) {
				is4kp3 = true;
			}
			else {
				prime = prime + 2;
				isPrime = false;
			}
		}
		return prime;
	}

}

 