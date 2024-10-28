import java.io.*;
import java.util.ArrayList;

public class Menu {

    private ArrayList<Pizza> pizzaMenu;
    private final String FILE_PATH = "menu.txt";

    public Menu() {
        this.pizzaMenu = new ArrayList<>();
        loadMenuFromFile();  // Læs pizzaer fra filen ved programstart
    }

    public void addPizza(Pizza pizza) {
        pizzaMenu.add(pizza);
        saveMenuToFile();  // Gem menuen til filen efter at have tilføjet pizza
    }

    public void updatePizzaPris(String nummer, int nyPris) {
        for (Pizza pizza : pizzaMenu) {
            if (pizza.getNummer().equals(nummer)) {
                pizza.setPris(nyPris);
                saveMenuToFile();  // Opdater filen efter ændring af pris
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

    // Metode til at gemme pizza-menuen til en tekstfil
    private void saveMenuToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Pizza pizza : pizzaMenu) {
                writer.write(pizza.getNummer() + "," + pizza.getNavn() + "," + pizza.getPris());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }

    // Metode til at indlæse pizza-menuen fra en tekstfil
    private void loadMenuFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] pizzaData = line.split(",");
                if (pizzaData.length == 3) {
                    String nummer = pizzaData[0];
                    String navn = pizzaData[1];
                    int pris = Integer.parseInt(pizzaData[2]);

                    Pizza pizza = new Pizza(nummer, navn, pris);  // Brug konstruktøren her
                    pizzaMenu.add(pizza);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning fra fil: " + e.getMessage());
        }
    }

    public void removePizza(String nummer) {
        boolean found = false;
        for (Pizza pizza : pizzaMenu) {
            if (pizza.getNummer().equals(nummer)) {
                pizzaMenu.remove(pizza);
                saveMenuToFile();  // Opdater filen efter sletning
                System.out.println("Pizza " + pizza.getNavn() + " er blevet fjernet fra menuen.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Ingen pizza fundet med nummer " + nummer);
        }
    }
}