import java.util.NoSuchElementException;

/**
 * Linked list implementation of a stack
 *
 * Reference: Mark Allen Weiss, Robert Lafore
 *
 * @author Todd Crane, Caleb Smith
 * @version 1/27/2017
 */
public class MyStack {

    //Fields
    private Node top; //Top of stack
    private int size; //Number of items in stack

    /**
     * Constructs an empty stack
     */
    public MyStack() {
        top = null;
    }

    /**
     * Checks if the stack is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (top == null);
    }

    /**
     * Adds an item to the top of the stack
     * @param data to insert
     */
    public void push(String data) {
        top = new Node(data, top);
        size++;
    }

    /**
     * Removes the item at the top of the stack
     * @return data at top
     * @throws NoSuchElementException
     */
    public String pop() {
        if(isEmpty()) throw new NoSuchElementException();
        String data = top.data;
        top = top.next;
        size--;
        return data;
    }

    /**
     * Returns the item on the top of the stack, does not alter stack
     * @return data at top
     * @throws NoSuchElementException
     */
    public String peek() {
        if(isEmpty()) throw new NoSuchElementException();
        return top.data;
    }

    /**
     * Returns the number of items in the stack
     * @return items in stack
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String result = "[";
        Node current = top;
        while (current != null) {
            result += current.data + ", ";
            current = current.next;
        }
        result += "]";
        return result;
    }

    //Node class
    private static class Node {

        //Fields
        private String data; //Data
        private Node next; //Next node in list

        /**
         * Constructs an empty node
         */
        public Node(String data) {
            this.data = data;
            this.next = null;
        }

        /**
         * Constructs a node
         * @param data
         * @param next
         */
        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
