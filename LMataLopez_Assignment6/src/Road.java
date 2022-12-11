/**
 * Road data element, used to represent links/edges between 2 towns	
 * @author Luiz
 *
 */
public class Road implements Comparable<Road>{

	private String name;
	private int weight;
	private Town source;
	private Town destination;
	
	/**
	 * Default constructor with fields for source node, destination node, edge weight, and road name
	 * @param source Town of origin
	 * @param destination Town of destination
	 * @param weight Edge weight/distance in miles
	 * @param name Name of the Road
	 */
	public Road(Town source, Town destination, int weight, String name) {
		this.source = source;
		this.destination = destination;
		this.name = name;
		this.weight = weight; 
	}

	/**
	 * Default constructor with fields for source node, destination node, and road name, weight set to 1
	 * @param source Town of origin
	 * @param destination Town of destination
	 * @param name Name of the Road
	 */
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.name = name;
		this.weight = 1;
	}

	/**
	 * Getter for Road name field
	 * @return Name of the road
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for the source node
	 * @return Node/Town of origin
	 */
	public Town getSource() {
		return source;
	}
	
	/**
	 * Getter for the edge weight
	 * @return Distance/length of the road
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Getter for the destination node/town
	 * @return destination node/town
	 */
	public Town getDestination() {
		return destination;
	}
	
	/**
	 * contains method to see if a town is present at either end of the road
	 * @param town Town that is being searched
	 * @return is the town present (T/F)
	 */
	public boolean contains(Town town){	
		if(town == null) {
			return false;
		}
		return town.equals(source) || town.equals(destination);
	}
	
	/**
	 * Equals method for roads 
	 * @return True if they are equal, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		boolean flag = false;
		Road r = (Road)o;
		if((this.destination.equals(r.getDestination()) || this.source.equals(r.getDestination()))
			 && (this.destination.equals(r.getSource()) || this.source.equals(r.getSource()))) {
			flag = true;
		}

		return flag;
	}
	
	/**
	 * toString method for roads
	 * @return A formatted string that contains all info about a road
	 */
	@Override
	public String toString() {
		return "The road " + this.name + " is " + this.weight + " miles long and starts at " + this.source + " and it ends at " + this.destination;
	}	
	
	/**
	 * compareTo method to compare 2 different towns based on distance
	 * @return 0 if they are the same, and non-zero integer otherwise
	 */
	@Override
	public int compareTo(Road o) {
		return this.weight - o.getWeight();
	}

}
