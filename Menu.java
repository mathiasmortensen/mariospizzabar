import java.io.*;
import java.util.ArrayList;

public class Menu {

    private ArrayList<Pizza> pizzaMenu;
    private final String FILE_PATH = "menu.txt"; // Bruges til at låse FILE_PATH så den ikke ved en fejl bliver ændret.

    public Menu() {
        this.pizzaMenu = new ArrayList<>();
        loadMenuFromFile();  // Dette gør så vi aflæser menu.txt filen fra programstart
    }

    public void addPizza(Pizza pizza) {
        pizzaMenu.add(pizza);
        saveMenuToFile();  // Gem menuen til filen efter at have tilføjet pizza
    }

    //Ændrer prisen på den valgte pizza
    public void updatePizzaPris(String nummer, int nyPris) {
        for (Pizza pizza : pizzaMenu) {
            if (pizza.getNummer().equals(nummer)) {
                pizza.setPris(nyPris);
                saveMenuToFile();  // Opdaterer txt filen efter ændring af pris
                System.out.println("Prisen for pizzaen " + pizza.getNavn() + " er opdateret til " + nyPris + " kr.");
            }
        }
    }

    //Printer indholdet af menuen ud
    public void visMenu() {
        System.out.println("Menu:");
        for (Pizza pizza : pizzaMenu) {
            System.out.println(pizza);
        }
    }

    //Metoden til at finde pizza fra menu ved hjælp af nummeret
    public Pizza findPizza(String nummer) {
        for (Pizza pizza : pizzaMenu) {
            if (pizza.getNummer().equals(nummer)) {
                return pizza;
            }
        }
        System.out.println("Ingen pizza med nummeret " + nummer);
        return null;
    }

    // Metoden til at gemme og opdatere pizza menuen til vores menu.txt fil
    private void saveMenuToFile() {

        //sortere pizzanumre i rækkefølge fra 0 og op
        pizzaMenu.sort((p1, p2) -> Integer.compare(Integer.parseInt(p1.getNummer()), Integer.parseInt(p2.getNummer())));

        // Bruges til at gemme/opdatere vores nye pizzaer til txt filen
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Pizza pizza : pizzaMenu) {
                writer.write(pizza.getNummer() + "," + pizza.getNavn() + "," + pizza.getPris());
                writer.newLine();
            }
            System.out.println("Menuen er blevet opdateret og gemt.");
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage()); //Giver os besked om hvad den mulige fejl er
        }
    }

    // Metode til at indlæse pizza menuen fra en vores menu.txt fil
    private void loadMenuFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String Linje;
            while ((Linje = reader.readLine()) != null) {
                String[] pizzaData = Linje.split(","); //Gør kommaet til en opdeler
                if (pizzaData.length == 3) { //Sikrer sig at pizzaData arrayet har 3 elementer(nummer, navn og pris)
                    String nummer = pizzaData[0];
                    String navn = pizzaData[1];
                    int pris = Integer.parseInt(pizzaData[2]);

                    Pizza pizza = new Pizza(nummer, navn, pris);  //Tilføjer den nye pizza til menuen, hvis formatet er overholdt
                    pizzaMenu.add(pizza);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Fejl ved læsning fra fil: " + e.getMessage()); //Giver os besked om hvad den mulige fejl er
        }
    }

    //Metode til at fjerne en pizza fra menuen
    public void removePizza(String nummer) {
        boolean fundet = false;
        for (Pizza pizza : pizzaMenu) {
            if (pizza.getNummer().equals(nummer)) {
                pizzaMenu.remove(pizza); //fjerner valgte pizza ved hjælp af pizzaens nummer
                saveMenuToFile();  // Opdater filen efter sletning
                System.out.println("Pizza " + pizza.getNavn() + " er blevet fjernet fra menuen.");
                fundet = true;
                break;
            }
        }
        if (!fundet) { //Fejl besked hvis det indtastede nummer ikke findes
            System.out.println("Ingen pizza fundet med nummer " + nummer);
        }
    }
}