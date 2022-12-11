import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * Graph data structure of towns and roads to represent a map
 * @author Luiz
 *
 */
public class Graph implements GraphInterface<Town, Road>{
	
	//Matrix was just for experimentation not used in actual graph
	private int adjMatrix[][];
	private Map<Town, Town> prev;
	private Map<Town, Integer> distance;
	private Set<Town> visited;
	private Set<Town> unvisited;
	private HashMap<Town,Integer> vertexList = new HashMap<>();
	private Set<Town> vertexSet = new HashSet<>();
	private Set<Road> edgeSet = new HashSet<>();
	private int vertexCount;
	
	/**
	 * Default no-arg constructor which initializes all ADT's
	 */
	public Graph() {
		this.vertexCount = 0;
		this.adjMatrix = new int[15][15];
		this.distance = new HashMap<>();
		this.prev = new HashMap<>();
		this.visited = new HashSet<>();
		this.unvisited = new HashSet<>();
	}
	
	 /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
			for(Road r:edgeSet) {
				if(r != null) {
					if((r.contains(sourceVertex) && r.contains(destinationVertex))) {
						return r;
					}
				}
			}
			
		
		return null;
	}

	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     * @return The newly created edge if added to the graph, otherwise null.
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		edgeSet.add(newRoad);
	
		if(containsVertex(sourceVertex) && containsVertex(destinationVertex)) {
			int from = vertexList.get(sourceVertex);
			int to = vertexList.get(destinationVertex);
			
			if(adjMatrix[to][from] == 0 && adjMatrix[from][to] == 0) {
				adjMatrix[to][from] = weight;
				adjMatrix[from][to] = weight;
				return newRoad;
			}
			else {
				return getEdge(sourceVertex, destinationVertex);
			}
		}
		else {
			throw new IllegalArgumentException();
		}	
	}

    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     * @return true if this graph did not already contain the specified
     * vertex.
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) {
		if(!containsVertex(v)) {
			vertexSet.add(v);
			vertexList.put(v,vertexCount);
			vertexCount++;
		}
		return true;
	}

	 /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		for(Road r: edgeSet) {
			if(r.contains(sourceVertex) && r.contains(destinationVertex)) {
				return true;
			}
		}
		
		return false;
		
	}

	 /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		if(v == null)
			return false;
		if(vertexSet.contains(v))
			return true;
		for(Town t:vertexSet) {
			if(t.equals(v)) {
				return true;
			}
		}
		
		return false;
	}

	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		return edgeSet;
	}
	
	 /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		return vertexSet;
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> temp = new HashSet<>();
		for(Road r:edgeSet) {
			if(r.contains(vertex)){
				temp.add(r);
			}
		}
		
		return temp;
	}
	
	 /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		for(Road r:edgeSet) {
			if(r.contains(sourceVertex) && r.contains(destinationVertex)) {
				if(r.getWeight() == weight && r.getName().equals(description)) {
					int from = vertexList.get(sourceVertex);
					int to = vertexList.get(destinationVertex);
					Road copy = r;
					edgeSet.remove(r);
					adjMatrix[from][to] = 0;
					adjMatrix[to][from] = 0;
					
					return copy;
				}
			}
		}
		return null;
	}

	 /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {
		if(containsVertex(v)) {
			Set<Road> temp = edgesOf(v);
			for(Road r: temp) {
				removeEdge(r.getSource(), r.getDestination(), r.getWeight(), r.getName());
			}
			vertexSet.remove(v);
			vertexList.remove(v);
			return true;
		}
		
		return false;
	}

	/**
	 * Testing method to print out my matrix
	 */
	public void printMatrix() {
		
		for(int i = 0; i < adjMatrix.length; i++) {
			for(int j = 0; j < adjMatrix[i].length; j++) {
				System.out.print(adjMatrix[i][j]+ "|");
			}
			System.out.println();
		}
		
	}

	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */  
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> shortestPath = new ArrayList<>();
		dijkstraShortestPath(sourceVertex);
		
		Town previous = destinationVertex;
		
		while(previous != null) {
			Town current = previous;
			previous = prev.get(previous);
			Road path = getEdge(current, previous);
			
			if(previous != null){
				shortestPath.add(previous.getName() + " via " + path.getName() + " to " + current.getName() + " " + path.getWeight() + " mi");
			}
		}
		
		Collections.reverse(shortestPath);
		
		return shortestPath;
	}

	 /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		for (Town t : vertexSet) {
			distance.put(t, Integer.MAX_VALUE);
			prev.put(t, null);
			unvisited.add(t);
		}
		
		distance.put(sourceVertex, 0);
		
		
		while (!unvisited.isEmpty()) {
			Town nearest = getNearestUnvisited();
			unvisited.remove(nearest);
			Set<Town> neighbors = getUnvisitedNeighbors(nearest);
			
			for (Town t : neighbors) {
				if(t != null) {
					int netWeight = distance.get(nearest) + getEdge(nearest, t).getWeight();
					
					if(netWeight < distance.get(t)) {
						distance.put(t, netWeight);
						prev.put(t, nearest);
					}
				}	
			}
		}
	}
	
	/**
	 * Method to return the set of unvisited neighbors based on the edgesOf method
	 * @param v vertex for which to get neighbors
	 * @return Set of unvisited neighbors
	 */
	public Set<Town> getUnvisitedNeighbors(Town v) {
		Set<Town> unvisitedNeighbors = new HashSet<>();
		
		for (Road r : edgesOf(v)) {
			Town neighbor = r.getSource();
			
			if(r.getSource() == v) {
				neighbor = r.getDestination();
			}
			if (unvisited.contains(neighbor) && !visited.contains(neighbor))
				unvisitedNeighbors.add(neighbor);
		}
		return unvisitedNeighbors;
	}
	
	/**
	 * Method to find the nearest unvisited town
	 * @return nearest unvisited town
	 */
	public Town getNearestUnvisited() {
		int minWeight = Integer.MAX_VALUE;
		Town nearest = null;
		
		for (Town town : unvisited) {
			if (distance.get(town) <= minWeight) {
				minWeight = distance.get(town);
				nearest = town;
			}
		}
		
		return nearest;
	}
	

}