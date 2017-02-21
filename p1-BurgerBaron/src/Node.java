/**
 * Created by todc on 1/22/2017.
 */
public class Node<E> {

    //Fields
    private E data; //Data
    private Node<E> next; //Next node in list

    //Constructor
    public Node() {
        this.data = null;
        this.next = null;
    }

    //Constructor
    public Node(E data, Node<E> next) {
        this.data = data;

    }
}
