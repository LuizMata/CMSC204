/**
 * Town data element used to represent a node on a graph
 * @author Luiz
 *
 */
public class Town implements Comparable<Town>{

	private String name;
	
	/**
	 * Constructor for town with name field
	 * @param name Name of the town
	 */
	public Town(String name) {
		this.name = name;
	}

	/**
	 * Copy constructor for the town
	 * @param copy Town to be copied
	 */
	public Town(Town copy) {
		this.name = copy.getName();
	}
	
	/**
	 * Getter for town name field
	 * @return The town's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * hashCode function for the town based on the name
	 * @return hashCode integer for a given town
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * toString method for printing town information
	 * @return Town description
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * equals method for towns, based on the name of the town
	 * @param o Town object
	 * @return If the 2 compared towns are the same (T/F)
	 */
	@Override
	public boolean equals(Object o) {
		Town t = (Town)o;
		return this.name.equals(t.getName());
	}
	
	/**
	 * compareTo method for towns, based on the name of the town
	 * @param T The town to be compared
	 * @return 0 if the two are same, 1 otherwise
	 */
	public int compareTo(Town T) {
		int status = 1;
		
		if(this.name.equals(T.getName())) {
			status = 0;
		}
	
		return status;
	}

}
