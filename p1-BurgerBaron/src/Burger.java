/**
 * Builds the Baron Burger
 *
 * @author Todd Crane, Caleb Smith
 * @version 1/27/2017
 */
public class Burger {

    //Fields
    private String [] baronRecipe; //List of all possible ingredients available
    private MyStack currentOrder; //Current order
    private MyStack theBurger; //Final burger to be returned
    private MyStack temp; //Temp storage
    private int numPatty; //Number of patties
    private String pattyType; //Patty type (beef, chicken, veggie)

    //Categories of toppings
    private String[] Cheese = new String[] {"Cheddar", "Mozzarella", "Pepperjack"};
    private String[] Sauce = new String[] {"Ketchup", "Mustard", "Baron-Sauce", "Mayonnaise"};
    private String [] Veggie = new String[] {"Mushrooms", "Onions", "Tomato", "Lettuce", "Pickle"};

    /**
     * Constructs theBurger
     * Default theBurger has: bun, patty, bun
     * @param theWorks boolean for all ingredients
     */
    public Burger(boolean theWorks) {
        //Fields
        baronRecipe = new String[]{"Ketchup", "Mustard", "Mushrooms", "Patty", "Cheddar", "Mozzarella", "Pepperjack",
                "Onions", "Tomato", "Lettuce", "Baron-Sauce", "Mayonnaise", "Bun", "Pickle"};

        currentOrder = new MyStack();
        theBurger = new MyStack();
        temp = new MyStack();
        numPatty = 1;
        pattyType = "Beef";

        //Set up default burger
        addIngredient("Bun");
        addIngredient("Patty");
        addIngredient("Bun");

        //Check for Baron Burger (The Works)
        if(theWorks) {
            addCategory("Cheese");
            addCategory("Sauce");
            addCategory("Veggie");
        }
        buildBurger();
    }

    /**
     * Changes all patties in theBurger to the pattyType
     * @param newPatty new type of patty
     */
    public void changePatties(String newPatty) {

        pattyType = newPatty;
        buildBurger();
    }

    /**
     * Adds one patty to theBurger (max of 3)
     */
    public void addPatty() {

        if(numPatty < 3) {
            numPatty++;
        }
        buildBurger();
    }

    /**
     * Removes one patty from theBurger (min of 1)
     */
    public void removePatty() {

        if(numPatty > 1) {
            numPatty--;
        }
        buildBurger();
    }

    /**
     * Adds all items of the category type to theBurger in proper locations
     * @param categoryType category of ingredients
     */
    public void addCategory(String categoryType) {

        if (categoryType.equals("Cheese")) {
            for (int i = 0; i < Cheese.length; i++) {
                addIngredient(Cheese[i]);
            }
        } else if (categoryType.equals("Sauce")) {
            for (int i = 0; i < Sauce.length; i++) {
                addIngredient(Sauce[i]);
            }
        } else {
            for (int i = 0; i < Veggie.length; i++) {
                addIngredient(Veggie[i]);
            }
        }
    }

    /**
     * Removes all items of the category type from theBurger
     * @param categoryType category of ingredients
     */
    public void removeCategory(String categoryType) {

        if(categoryType.equals("Cheese")) {
            for(int i = 0; i < Cheese.length; i++ ) {
                removeIngredient(Cheese[i]);
            }
        } else if(categoryType.equals("Sauce")) {
            for(int i = 0; i < Sauce.length; i++) {
                removeIngredient(Sauce[i]);
            }
        } else {
            for(int i = 0; i < Veggie.length; i++) {
                removeIngredient(Veggie[i]);
            }
        }
    }

    /**
     * Adds the ingredient type to theBurger in the proper location
     * @param ingredient ingredient to be added
     */
    public void addIngredient(String ingredient) {

        currentOrder.push(ingredient);
        buildBurger();
    }

    /**
     * Removes the ingredient type from theBurger
     * @param ingredient ingredient to be removed
     */
    public void removeIngredient(String ingredient) {
        //Fields
        boolean flag = false;

        //Find ingredient and remove
        while(!currentOrder.isEmpty() && !flag) {
            if(currentOrder.peek().equals(ingredient)) {
                currentOrder.pop();
                flag = true;
            } else {
                temp.push(currentOrder.pop());
            }
        }
        restore();
        buildBurger();
    }

    /**
     * Restores theBurger with new elements
     */
    private void restore() {
        while(!temp.isEmpty()) {
            currentOrder.push(temp.pop());
        }
    }

    /**
     * Put together the final theBurger
     */
    private void buildBurger() {

        //Empty burger
        while(!theBurger.isEmpty()) {
            theBurger.pop();
        }

        //Fencepost the bun
        theBurger.push("Bun");
        //Add ordered ingredients to theBurger
        for(int i = 0; i < baronRecipe.length; i++) {
            //Add correct number of patties
            if(baronRecipe[i].equals("Onions")) {
                for(int j = 1; j < numPatty; j++) {
                    theBurger.push(pattyType);
                }
            }

            if(contains(baronRecipe[i])) {
                theBurger.push(baronRecipe[i]);
            }
        }
    }


    /**
     * Checks if currentOrder contains the ingredient
     * @param ingredient to be checked for
     * @return true if currentOrder contains the ingredient
     */
    private boolean contains(String ingredient) {
        //Fields
        boolean flag = false;

        //Look for ingredient
        while(!currentOrder.isEmpty() && !flag) {
            if(currentOrder.peek().equals(ingredient)) {
                temp.push(currentOrder.pop());
                flag = true;
            } else {
                temp.push(currentOrder.pop());
            }
        }
        restore();
        return flag;
    }

    @Override
    public String toString() {
        buildBurger();
        String stringTemp = "[" + theBurger.pop();
        while(!theBurger.isEmpty()) {
            if(theBurger.peek().equals("Patty")) {
                stringTemp += ", ";
                stringTemp += pattyType;
                theBurger.pop();
            } else {
                stringTemp += ", ";
                stringTemp += theBurger.pop();
            }
        }
        stringTemp += "]";
        return stringTemp;
    }
}
