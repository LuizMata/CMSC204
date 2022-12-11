import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * Town map/graph manager which puts all functionality into one program with appropriate function names
 * @author Luiz
 *
 */
public class TownGraphManager implements TownGraphManagerInterface{

	private Graph g;
	
	/**
	 * Default no-arg constructor to initialize a graph to represent the town map
	 */
	public TownGraphManager() {
		g = new Graph();
	}
	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town town1o = getTown(town1);
		Town town2o = getTown(town2);
		g.addEdge(town1o, town2o, weight, roadName);
		
		return true;
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		Town town1o = getTown(town1);
		Town town2o = getTown(town2);
		Road r = g.getEdge(town1o, town2o);
		
		for(Road ro : g.edgesOf(town1o)) {
			if(ro.getDestination().equals(town2o) || ro.getSource().equals(town2o)) {
				return r.getName();
			}
		}
		
		return null;
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		Town town1o = new Town(v);
		return g.addVertex(town1o);
	}

	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) {
		Town town1o = new Town(name);
		for(Town to : g.vertexSet()) {
			if(town1o.equals(to)) {
				return to;
			}
		}
		
		return null;
	}

	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		Town town1o = new Town(v);
		return g.containsVertex(town1o);
	}

	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town town1o = getTown(town1);
		Town town2o = getTown(town2);

		return g.containsEdge(town1o, town2o);
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roads = new ArrayList<>();
		for(Road ro : g.edgeSet()) {
			roads.add(ro.getName());
		}
		
		Collections.sort(roads);
		return roads;
	}

	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town town1o = getTown(town1);
		Town town2o = getTown(town2);
		
		if(g.containsEdge(town1o, town2o)) {
			Road temp = g.getEdge(town1o, town2o);
			Road test = g.removeEdge(town1o, town2o, temp.getWeight() , road);
		
			if(test.equals(temp)) 
				return true;
		}
		
		return false;
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		Town town1o = new Town(v);
		return g.removeVertex(town1o);
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> towns = new ArrayList<>();
		
		for(Town to : g.vertexSet()) {
			towns.add(to.getName());
		}
		
		Collections.sort(towns);
		return towns;
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town town1o = getTown(town1);
		Town town2o = getTown(town2);
		
		ArrayList<String> thePath = g.shortestPath(town1o, town2o);
		return thePath;
	}
	
	/**
	 * Method to populate the town graph
	 * @param input File that contains the towns
	 * @throws FileNotFoundException If input file is not found
	 */
	public void populateTownGraph(File input) throws FileNotFoundException{
		ArrayList<String> list = new ArrayList<>();
		
		if (!input.exists())
			throw new FileNotFoundException();
		
		Scanner sc = new Scanner(input);
		
		while (sc.hasNextLine()) {
			list.add(sc.nextLine());
		}
		
		for (String line : list) {
			String[] split = line.split(";");
			int delim = split[0].indexOf(",");
			String roadName = split[0].substring(0,delim);
			String weight = split[0].substring(delim+1,split[0].length());
			String source = split[1];
			String destination = split[2];
			
			addTown(source);
			addTown(destination);
			
			addRoad(source, destination, Integer.parseInt(weight), roadName);
		}
		
		sc.close();
	}


}
