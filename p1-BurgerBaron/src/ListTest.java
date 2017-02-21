/**
 * Created by todc on 1/22/2017.
 */
public class ListTest {
    public static void main(String[] args) {
        //Test stack
        MyStack stack = new MyStack();

        //Should print null
        System.out.println(stack.toString());
        //Check empty
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());

        //Add some items
        stack.push("a");
        stack.push("b");
        stack.push("c");
        //Print
        System.out.println(stack.toString());
        //Peek (should be c)
        System.out.println("peek" + stack.peek());
        //Pop (c)
        System.out.println(stack.pop());
        //Pop the rest off
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        //Check for error
        //System.out.println("peek" + stack.pop());
        //System.out.println("pop" + stack.pop());

    }
}
