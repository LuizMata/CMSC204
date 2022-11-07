public class CourseDBElement implements Comparable<CourseDBElement>{
	private String courseID;
	private int CRN;
	private int numCredits;
	private String roomNumber;
	private String instructorName;
	private CourseDBElement next;
	private CourseDBElement prev;
	
	/**
	 * Default constructor used to create a CourseDBElement.
	 * @param courseID Course ID ie. CMSC204
	 * @param CRN CRN or Course Registration Number
	 * @param credits Number of credits for a course.
	 * @param roomNum Course room number.
	 * @param instructor Instructor for the course.
	 */
	public CourseDBElement(String courseID, int CRN, int numCredits, String roomNumber, String instructorName) {
		this.courseID = courseID;
		this.CRN = CRN;
		this.numCredits = numCredits;
		this.roomNumber = roomNumber;
		this.instructorName = instructorName;
	}
	
	public CourseDBElement() {
		
	}
	
	/**
	 * Getter for next pointer of a CourseDBElement.
	 * @return The CourseDBElement associated with next the pointer.
	 */
	public CourseDBElement getNext(){
		return next;
	}

	/**
	 * Getter for previous pointer of a CourseDBElement.
	 * @return The CourseDBElement associated with the prev pointer.
	 */
    public CourseDBElement getPrev(){
    	return prev;
    }
    
    /**
     * Getter for the Course ID
     * @return Course ID
     */
	public String getID() {
		return courseID;
	}

	/**
	 * Setter for the Course ID
	 * @param courseID The course ID that is to be set
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	/**
	 * Setter for the CRN
	 * @param cRN The CRN that is to be set
	 */
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}

	/**
	 * Setter for the number of credits
	 * @param numCredits The credits that is to be set
	 */
	public void setNumCredits(int numCredits) {
		this.numCredits = numCredits;
	}

	/**
	 * Setter for the room number
	 * @param roomNumber The room number that is to be set
	 */
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	/**
	 * Setter for the Instructor
	 * @param instructorName The Instructor that is to be set
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	/**
	 * Setter for the next pointer of a CourseDBElement
	 * @param next The next pointer that is to be set
	 */
	public void setNext(CourseDBElement next) {
		this.next = next;
	}

	/**
	 * Setter for the previous pointer of a CourseDBElement
	 * @param prev The previous pointer that is to be set
	 */
	public void setPrev(CourseDBElement prev) {
		this.prev = prev;
	}

	/**
     * Getter for the CRN
     * @return the CRN of a course
     */
	public int getCRN() {
		return CRN;
	}

	/**
	 * Getter for the number of credits
	 * @return the number of credits
	 */
	public int getNumCredits() {
		return numCredits;
	}

	/**
	 * Getter for the room number
	 * @return the location of the room
	 */
	public String getRoomNum() {
		return roomNumber;
	}

	/**
	 * Getter for the instructors name
	 * @return the name of instructor
	 */
	public String getInstructorName() {
		return instructorName;
	}
	
	/**
	 * Basic toString method to display all the information about a given course.
	 * @return String with all CourseDBElement information
	 */
	@Override
	public String toString() {
		return "\nCourse:"+ courseID + " CRN:"+ CRN + " Credits:" + numCredits + " Instructor:" + instructorName + " Room:" + roomNumber;
	}
	
	/**
	 * Hash code generator for each CourseDBElement
	 * @return hash code of a CourseDBElement
	 */
	public int hashCode() {
		return (Integer.toString(CRN)).hashCode();
	}
	

	/**
	 * Comparison of CourseDBElement objects
	 * @param o The courseDBElement which is being compared
	 * @return 1 if the two objects are equivalent with respect to CRN
	 * @return 0 if the two objects are not equivalent with respect to CRN
	 */
	public int compareTo(CourseDBElement o) {
		int oCRN=o.getCRN();
		if(this.CRN==oCRN) {
			return 1;
		}
		return 0;
	}

}