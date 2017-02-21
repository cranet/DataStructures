import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * CodingTree class executes Huffman coding algorithm
 * Generates a compressed encoding of passed in string
 *
 * @author Todd Crane (cranet@uw.edu)
 * @author Caleb Smith (caleb447@uw.edu)
 * @version 2/24/2017.
 */

public class CodingTree {

    //Private fields
    private String message; //Original string to be encoded
    private Map<Character, Integer> count; //Stores character counts
    private PriorityQueue<Node> tree; //Huffman tree

    /**
     * Public map of characters in the message to binary codes (Strings of 1 and 0)
     * created by the tree
     */
    public Map<Character, String> codes;

    /**
     * Public message encoded using Huffman codes
     * public String bits or public List<Byte> bits
     */
    public String bits;

    /**
     * Constructor that takes a string to be compressed
     * Calls private methods that execute the Huffman coding algorithm
     * @param theMessage String to be compressed
     */
    public CodingTree(String theMessage) {

        //Initialize
        message = theMessage;
        count = new TreeMap<>();
        codes = new TreeMap<>();
        tree = new PriorityQueue<>();

        //Execute algorithm
        charCount(message); //Counts character occurrences
        buildTree(); //Build priority queue tree
        merge(); //Merge into Huffman tree, create codes
        encode(); //Create compressed encoding
    }

    /**
     * Counts the characters in a string
     * @param message String to be counted
     */
    private void charCount(String message) {

        //Fields
        char c;

        //Get each character, add to map
        for(int i = 1; i < message.length(); i++) {
            c = message.charAt(i);
            //Add to map
            if(count.containsKey(c)) {
                count.put(c, count.get(c) + 1);
            } else {
                count.put(c, 1);
            }
        }
    }

    /**
     * Builds a priority queue tree using the count map
     */
    private void buildTree() {
        for(Map.Entry<Character, Integer> entry : count.entrySet()) {
            Node n = new Node(entry.getKey(), entry.getValue());
            tree.add(n);
        }
    }

    /**
     * Merges the nodes in the priority queue into a Huffman tree
     * Calls binary to generate binary codes for each letter
     */
    private void merge() {
        /*
        Remove two highest priority nodes
        Put them as children of a new parent node
        Parent has weight equal to sum of children weight
        Add back to priority queue
        Repeat until a single Huffman tree remains
         */
        while(tree.size() > 1) {
            Node left = tree.poll();
            Node right = tree.poll();
            int weight = left.getWeight() + right.getWeight();
            Node parent = new Node(weight, left, right);
            tree.add(parent);
        }

        //Add root to tree
        Node n = tree.poll();
        String b = "";
        //Call binary
        binary(n, b);
    }

    /**
     * Generates binary codes for each letter
     * @param n Root node
     * @param b Binary code
     */
    private void binary(Node n, String b) {
        if(n.left != null) {
            binary(n.left, b + '0');
        }
        if(n.right != null) {
            binary(n.right, b + '1');
        }
        if(n.left == null && n.right == null) {
            codes.put(n.getData(), b);
        }
    }

    /**
     *  Builds compressed encoding of the message
     */
    private void encode() {
        for(int i = 1; i < message.length(); i++) {
            char c = message.charAt(i);
            System.out.println("charr: " + c);
            bits += (codes.get(c));
            bits += ". ";
        }
        System.out.println(bits);
    }

    /**
     * Node class
     * Holds character data and weight (number of occurrences)
     */
    private class Node implements Comparable {
        //Fields
        private char data;
        private int weight;
        //Child nodes
        private Node left;
        private Node right;

        /**
         * Constructor that creates a root node
         * @param d Character data
         * @param w Weight (number of occurrences)
         */
        public Node(Character d, Integer w) {
            data = d;
            weight = w;

            left = null;
            right = null;
        }

        /**
         * Constructor that creates anonymous parent node
         * Weight should be equal to sum of children weight
         * @param w Weight (number of occurrences)
         * @param l Left child node
         * @param r Right child node
         */
        public Node(Integer w, Node l, Node r) {
            weight = w;
            left = l;
            right = r;
        }

        /**
         * Gets character data
         * @return Node character
         */
        public char getData() {
            return data;
        }

        /**
         * Gets weight (number of occurrences)
         * @return Node weight
         */
        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Object o) {
            Node n = (Node) o;
            if(weight > n.getWeight()) {
                //System.out.println(weight + " < " +n.getWeight());
                return 1;
            } else if(weight < n.getWeight()) {
                //System.out.println(weight + " > " +n.getWeight());
                return -1;
            } else {
                //System.out.println(weight + " = " +n.getWeight());
                return 0;
            }
        }
    }
}