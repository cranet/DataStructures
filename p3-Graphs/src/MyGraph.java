import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {

    //Graph representations
	private HashMap<Vertex, Set<Edge>> map;
	private HashMap<String, Vertex> vertMap;
	private HashMap<String, Set<Edge>> edgeMap;

	private Collection<Vertex> vertex;
	private Collection<Edge> edge;

	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges.
	 * 
	 * @param v a collection of the vertices in this graph
	 * @param e a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
	    //Fields
        map = new HashMap<>();
        vertMap = new HashMap<>();
        edgeMap = new HashMap<>();
        vertex = v;
        edge = e;

        //Check edges for exceptions
        checkEdges();

        //Build adjacency list graph
        buildGraph();
	}

    /**
     * Checks that all edges connect to vertices in the graph
     * Checks for negative edge weights
     * Checks for repeated directed edges with different weights
     */
	private void checkEdges() {
        for(Edge e : edge) {
            //Check that all edges connect to existing vertices
            if(!vertex.contains(e.getSource()) || !vertex.contains(e.getDestination())) {
                throw new NoSuchElementException();
            }

            //Check for negative weight
            if (e.getWeight() < 0) {
                throw new IllegalArgumentException();
            }

            //Check for repeated directed edges with different weights
            for(Edge e2 : edge) {
                if(e.getSource().equals(e2.getSource()) && e.getDestination().equals(e2.getDestination())) {
                    if(e.getWeight() != e2.getWeight()) {
                        throw new IllegalArgumentException();
                    }
                }
            }
        }
    }

    /**
     * Builds the graph using a HashMap
     * Keys = Vertices, Values = HashSet of connected edges
     *
     * Builds two extra separate HashMaps (vertMap and edgeMap) for use with Dijkstra
     */
    private void buildGraph() {
        for(Vertex v : vertex) {
            Set<Edge> edges = new HashSet<>();
            for(Edge e : edge) {
                if(e.getSource().equals(v)) {
                    edges.add(e);
                }
            }
            map.put(v, edges);
            vertMap.put(v.getLabel(), v);
            edgeMap.put(v.getLabel(), edges);
        }
        System.out.println(map.toString());
    }

	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Vertex> vertices() {
        return vertex;
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Edge> edges() {
        return edge;
	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException if v does not exist.
	 */
	@Override
	public Collection<Vertex> adjacentVertices(Vertex v) {
        Collection<Vertex> temp = new HashSet<>();

        for(Edge e : edge) {
            if(e.getDestination().equals(v)) {
                temp.add(e.getSource());
            }
        }
        return temp;
	}

	/**
	 * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
	 * graph. Assumes that we do not have negative cost edges in the graph.
	 * 
	 * @param a one vertex
	 * @param b another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException if a or b do not exist.
	 */
	@Override
	public int edgeCost(Vertex a, Vertex b) {
        for(Edge e : edge) {
            if(e.getSource().equals(a) && e.getDestination().equals(b)) {
                return e.getWeight();
            }
        }
        return -1;
	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are non-negative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a the starting vertex
	 * @param b the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException if a or b does not exist.
	 */
	public Path shortestPath(Vertex a, Vertex b) {

        //Set source cost to zero
        a.setCost(0);
        b.setCost(Integer.MAX_VALUE - 1); //Prevent no path loop
        Vertex current = a;

        //Iterate until target (b) is found
        while(!current.equals(b)) {

            //Find vertex with lowest cost, set route, set current cost
            int tempCost = Integer.MAX_VALUE;

            for(Vertex v : vertMap.values()) {
                if(v.getCost() < tempCost && !v.isKnown()) {
                    tempCost = v.getCost();
                    current = v;
                }
            }

            //Set known vertex
            vertMap.get(current.getLabel()).setKnown(true);

            //Find shortest path from current
            for(Edge e : edgeMap.get(current.getLabel())) {

                //If destination cost is greater than current cost, set cost to current cost + edge weight
                Vertex destination = vertMap.get(e.getDestination().getLabel());
                int edgeWeight = e.getWeight();
                int currentCost = current.getCost();

                if(destination.getCost() > edgeWeight + currentCost) {
                    destination.setCost(edgeWeight + currentCost);
                    destination.setPreviousVertex(current); //Set previous vertex
                }
            }
        }

        //Print required prompt
        System.out.println("Shortest path from " + a.getLabel() + " to " + b.getLabel() + ":");
        //If path exists
        if(current.getPreviousVertex() != null || a.equals(b)) {
            Path p = new Path(buildPath(a, current), current.getCost());
            System.out.println(current.getCost());

            //Reset vertices
            resetVertices();
            return p;
        } else {
            //Reset vertices
            resetVertices();
            System.out.println("does not exist");
            return null;
        }
    }

    /**
     * Builds the path to be returned by shortestPath
     *
     * @param start Front vertex
     * @param end Vertex to start building path
     * @return List of vertices
     */
    private List<Vertex> buildPath(Vertex start, Vertex end) {

        //Fields
        Vertex tempRoute = end;
        Stack<Vertex> temp = new Stack<>();
        List<Vertex> pathList= new ArrayList<>();

        //Build path into stack
	    while(!tempRoute.equals(start)) {
	        temp.add(tempRoute);
	        tempRoute = tempRoute.getPreviousVertex();
        }
        temp.add(tempRoute);

	    //Add from stack to list (proper order)
	    while(!temp.isEmpty()) {
	        pathList.add(temp.pop());
        }
        System.out.println(pathList);
        return pathList;
    }

    /**
     * Resets all vertex costs to infinity, known to false, and previous to null
     */
    private void resetVertices() {
	    for(Vertex v : vertMap.values()) {
	        v.setCost(Integer.MAX_VALUE);
	        v.setKnown(false);
	        v.setPreviousVertex(null);
        }
    }
}
