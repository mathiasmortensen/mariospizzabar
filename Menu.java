import java.util.ArrayList;

public class Menu {

    public ArrayList<Pizza> menu;

    public Menu() {
        this.menu = new ArrayList<>();
    }

    public void addPizza(Pizza p) {
        menu.add(p);
    }

    public void Pizzaer(){
        Pizza pizzaer = new Pizza();
        pizzaer.nyPizza("1", "Margherita", 70);
        menu.add(pizzaer);
    }

    public void visMenu(){
        System.out.println(menu);
    }
}
