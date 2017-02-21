/**
 * Tests the Baron Burger
 *
 * @author Todd Crane, Caleb Smith
 * @version 1/27/2017
 */
public class BurgerTest {
    public static void main(String[] args) {
        //Test burger
        Burger b = new Burger(false);
        System.out.println(b.toString());

        //Add patties
        System.out.println("add patties");

        b.addPatty();
        System.out.println(b.toString());
        b.addPatty();
        System.out.println(b.toString());
        b.addPatty();
        System.out.println(b.toString());

        //Remove patties
        System.out.println("remove patties");

        b.removePatty();
        System.out.println(b.toString());
        //b.removePatty();
        System.out.println(b.toString());
        //b.removePatty();
        System.out.println(b.toString());

        //Change patties
        System.out.println("change patties");

        b.changePatties("Chicken");
        System.out.println(b.toString());
        b.changePatties("Veggie");
        System.out.println(b.toString());

        //Add cheese category
        System.out.println("add cheese category");
        Burger c = new Burger(false);
        System.out.println(c.toString());
        c.addCategory("Cheese");
        System.out.println(c.toString());

        //Test cheese with multiple patties
        System.out.println("multiple patty");
        Burger d = new Burger(false);
        d.addPatty();
        d.addPatty();
        System.out.println(d.toString());
        d.addCategory("Cheese");
        System.out.println(d.toString());

        //Add sauce category
        System.out.println("add sauce category");
        Burger e = new Burger(false);
        System.out.println(e.toString());
        e.addCategory("Sauce");
        System.out.println(e.toString());

        //Add veggie category
        System.out.println("add veggie category");
        Burger f = new Burger(false);
        System.out.println(f.toString());
        f.addCategory("Veggies");
        System.out.println(f.toString());
        f.addCategory("Cheese");
        System.out.println(f.toString());
        f.addCategory("Sauce");
        System.out.println(f.toString());
        f.addPatty();
        f.addPatty();
        System.out.println(f.toString());
    }
}
