import java.util.Collection;
import java.util.LinkedList;

/**
 * Tests for edge cases in MyGraph class.
 *
 * @author Todd Crane (cranet@uw.edu)
 * @author Caleb Smith (caleb447@uw.edu)
 * @version 3/10/2017.
 */
public class TestGraph {

    private static Collection<Vertex> v = new LinkedList<>();
    private static Collection<Edge> e = new LinkedList<>();

    public static void main (String[] args) {

        //Check for unconnected (floating) vertex
        System.out.println("Test 1 \n");
        //floatVertex();
        MyGraph g1 = new MyGraph(v, e);
        Vertex a = new Vertex("A");
        Vertex c = new Vertex("C");
        g1.shortestPath(a, c);
        v.clear();
        e.clear();

        //Check that all edges connect to existing vertices
        System.out.println("Test 2 \n");
        //existVertex();
        MyGraph g2 = new MyGraph(v, e);
        v.clear();
        e.clear();

        //Check for negative weights
        System.out.println("Test 3 \n");
        //negativeWeight();
        MyGraph g3 = new MyGraph(v, e);
        v.clear();
        e.clear();

        //Check for repeated directed edges with different weights
        System.out.println("Test 4 \n");
        //diffWeight();
        MyGraph g4 = new MyGraph(v, e);
        v.clear();
        e.clear();

        //Check for null vertex (adjacentVertices method and edgeCost method)
        System.out.println("Test 5 \n");
        //nullVertex();
        MyGraph g5 = new MyGraph(v, e);
        for(Vertex temp : v) {
            g5.adjacentVertices(temp);
        }
        v.clear();
        e.clear();
    }

    //Check for unconnected (floating) vertex
    private static void floatVertex() {
        System.out.println("Floating Vertex\n");

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");

        v.add(A);
        v.add(B);
        v.add(C);

        Edge eA = new Edge(A, B, 10);
        e.add(eA);
    }

    //Check that all edges connect to existing vertices
    private static void existVertex() {
        System.out.println("Existing Vertex\n");

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");

        v.add(A);
        v.add(B);
        v.add(C);
        //DON'T ADD D

        Edge eA = new Edge(A, D, 10);
        e.add(eA);
    }

    //Check for negative weight
    private static void negativeWeight() {
        System.out.println("Negative Weight\n");

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");

        v.add(A);
        v.add(B);
        v.add(C);

        Edge eA = new Edge(A, B, -10);
        e.add(eA);
    }

    //Check for repeated directed edges with different weights
    private static void diffWeight() {
        System.out.println("Repeated Directed Edges w/ Different Weights\n");

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");

        v.add(A);
        v.add(B);
        v.add(C);

        Edge eA = new Edge(A, B, 10);
        Edge eB = new Edge(A, B, 15);
        e.add(eA);
        e.add(eB);
    }

    //Check for null vertex (adjacentVertices method)
    private static void nullVertex() {
        System.out.println("Null Vertex\n");

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex(null);

        v.add(A);
        v.add(B);
        v.add(C);

        Edge eA = new Edge(A, B, 10);
        e.add(eA);
    }
}


