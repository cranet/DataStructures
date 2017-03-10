import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {
	// you will need some private fields to represent the graph
	// you are also likely to want some private helper methods

	// YOUR CODE HERE
    //private Set<Edge> edges;
	private HashMap<Vertex, Set<Edge>> map;
	private HashMap<String, Vertex> vertMap;
	private HashMap<String, Set<Edge>> edgeMap;
	private Collection<Vertex> vertex;
	private Collection<Edge> edge;

	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges.
	 * 
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {

		// YOUR CODE HERE
        //System.out.println(v);
        //System.out.println(e);
        vertex = v;
        edge = e;
        map = new HashMap<>();
        vertMap = new HashMap<>();
        edgeMap = new HashMap<>();

        //edges = new HashSet<>();
        //graph = new HashMap<>();

        //Call helper method to check edges
        checkEdges();

        //Build adjacent map
        buildMap();

        //TEST
        //for(Vertex vtest : vertex) {
            //System.out.println("vtes t   : " + vtest);
            //System.out.println(adjacentVertices(vtest));
        //}

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
     */
    private void buildMap() {
        for(Vertex v : vertex) {
            Set<Edge> edges = new HashSet<>();
            for(Edge e : edge) {
                if(e.getSource().equals(v)) {
                    edges.add(e);
                }
            }
            vertMap.put(v.getLabel(), v);
            edgeMap.put(v.getLabel(), edges);
            map.put(v, edges);

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

		// YOUR CODE HERE

        return vertex;

	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Edge> edges() {

		// YOUR CODE HERE

        return edge;

	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v
	 *            one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException
	 *             if v does not exist.
	 */
	@Override
	public Collection<Vertex> adjacentVertices(Vertex v) {


		// YOUR CODE HERE
        Collection<Vertex> temp = new HashSet<>();
        //for(Map.Entry<Vertex, Edge> entry : map.entrySet())

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
	 * @param a
	 *            one vertex
	 * @param b
	 *            another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException
	 *             if a or b do not exist.
	 */
	@Override
	public int edgeCost(Vertex a, Vertex b) {

		// YOUR CODE HERE
        for(Edge e : edge) {
            if(e.getSource().equals(a) && e.getDestination().equals(b)) {
                return e.getWeight();
            }
        }
        return -1;
	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a
	 *            the starting vertex
	 * @param b
	 *            the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	public Path shortestPath(Vertex a, Vertex b) {

        // YOUR CODE HERE (you might comment this out this method while doing
        // Part 1)

        //If no path return null
        //If start and end vertex are equal, return path with one vertex and cost of 0
        /*
        Otherwise, the path will contain at least two vertices -- the start and end vertices and any
        other vertices along the lowest-cost path. The vertices should be in the order they
        appear on the path.
         */


        /////////////

        //Known vertices
        //Map<String, Set<Edge>> known = new HashMap<>();

        //Unknown vertices
        //Set<Vertex> unknown = new HashSet<>();

//        //Set infinity and false
//        for(Vertex v : map.keySet()) {
//            v.setCost(Integer.MAX_VALUE);
//            v.setKnown(false);
//            unknown.add(v);
//        }

        //Set source cost to zero
        a.setCost(0);

        //Edges
        //System.out.println(map.values().toString());
        Set<Edge> edges = new HashSet<>();
        //List<Edge> edges = new ArrayList<>();
        for(Set<Edge> setE : map.values()) {
            for(Edge e : setE) {
                edges.add(e);
            }
        }


        System.out.println(edges.toString());


        //Go through all vertices
        Vertex current = a;

        while(!unknown.isEmpty()) {

            System.out.println("current: " + current.toString());
            System.out.println("current cost: " + current.getCost());


            //Find vertex with lowest cost
            int tempCost = Integer.MAX_VALUE;


            for(Vertex v : unknown) {
                //System.out.println("d: " + distance + " cost: " + v.getCost());
                if(v.getCost() < tempCost) {
                    tempCost = v.getCost();
                    current = v;
                }
            }

            //Remove from unknown
            known.add(current);
            unknown.remove(current);
            //System.out.println(current.toString());

            //Find shortest path from current
            int temp = Integer.MAX_VALUE;
            for(Edge e : edges) {

//                System.out.println("Label: " + current.getLabel());
//                System.out.println("temp source: " + e.getSource());
//                System.out.println();

                //Check for correct source
                //if(e.getSource().toString().equals(current.getLabel())) {
                if(e.getSource().equals(current)) {

                    System.out.println("TRUE");

                    //Setting cost if less than previous cost
                    if(e.getDestination().getCost() > current.getCost() + e.getWeight()) {

                        e.getDestination().setCost(current.getCost() + e.getWeight());
                    }
                    //Get shortest weight
//                    if(e.getWeight() < temp) {
//                        temp = e.getWeight();
//                        //System.out.println("weight: " + temp);
//                    }
                }
                //Set cost of current vertex
                System.out.println("current cost: " + current.getCost());
                //current.setCost(temp);
            }

            //Add vertex to known
            //known.add(current);

            //Check correct cost
            for(Vertex v : known) {
                System.out.println(v.toString() + " " + v.getCost());
            }

        }



        return null;

    }




        ////////////


        /*
        //Graph = map
        Vertex current = a;
        Vertex tempKey = current;
        Set<Edge> tempValue = map.get(current);
        tempKey.setCost(0);
        map.remove(current);
        map.put(tempKey, tempValue);

        while(!current.equals(b)) {

            int tempCost = Integer.MAX_VALUE;
            for(Map.Entry<Vertex, Set<Edge>> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getKey().getKnown());
                if (entry.getKey().getCost() < tempCost && !entry.getKey().getKnown()) {
                    tempCost = entry.getKey().getCost();
                    current = entry.getKey();
                    //entry.se
                    //entry.getKey().setKnown(true);
                }

                //System.out.println("Entry cost: " + entry.getKey().getCost());
            }
            System.out.println("Current: " + current + ", Cost: " + current.getCost());

//            current.setKnown(true);
            tempKey = current;
            tempValue = map.get(current);
            tempKey.setKnown(true);
            map.remove(current);
            map.put(tempKey, tempValue);
            current = tempKey;

            for(Edge e : map.get(current)) {
                //System.out.println(e.getSource() + " " + e.getDestination());
                if(!e.getDestination().getKnown()) {

                    if(current.getCost() + e.getWeight() < e.getDestination().getCost()) {

                        tempKey = e.getDestination();
                        tempValue = map.get(e.getDestination());
                        tempKey.setCost(current.getCost() + e.getWeight());
                        map.remove(e.getDestination());
                        map.put(tempKey, tempValue);
                        //System.out.print(current + "->");
                    }
                }
            }
        }
        /*

        /*
        while(not all nodes are known) {
            b = find unknown node with smallest cost
            b.known = true
            for each edge (b,a) in G
            if(!a.known)
                if(b.cost + weight((b,a)) < a.cost){
                a.cost = b.cost + weight((b,a))
                a.path = b
            }
        }
        /*


        /*

        //Unvisited vertices
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        //Temp set
        Collection<Vertex> temp = new HashSet<>();

        //Set source cost to 0, add to set
        a.setCost(0);
        temp.add(a);

        for(Map.Entry<Vertex, Set<Edge>> entry : map.entrySet()) {
            while(!entry.getKey().getKnown()) {
                System.out.println("entry value" + entry.getValue());
                Edge nextNode = null;
                int weight = Integer.MAX_VALUE;
                for(Edge e : entry.getValue()) {
                    if(weight > e.getWeight()) {
                        weight = e.getWeight();
                        nextNode = e;
                    }
                }
                System.out.println("lowest weight: " + weight);
                nextNode.getSource().setKnown(true);
            }

        }
        */

        /*

        return null;
	}
	*/

}
