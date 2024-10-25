import java.util.ArrayList;

public class Menu {

    private ArrayList<Pizza> pizzaMenu;

    public Menu() {
        this.pizzaMenu = new ArrayList<>();
    }



    public void addPizza(Pizza pizza) {
        pizzaMenu.add(pizza);
    }



    public void updatePizzaPris(String nummer, int nyPris) {
        for (Pizza pizza : pizzaMenu) {
            if (pizza.getNummer().equals(nummer)) {
                pizza.setPris(nyPris);
                System.out.println("Prisen for pizzaen " + pizza.getNavn() + " er opdateret til " + nyPris + " kr.");
            }
        }
    }

    public void visMenu() {
        System.out.println("Menu:");
        for (Pizza pizza : pizzaMenu) {
            System.out.println(pizza);
        }
    }
}