public class Restaurant {

    private static boolean aktiv;



    public static void main(String[] args) {
Kunde kunde = new Kunde("John osti", "John@sexdominator.com", "12345678");


        aktiv = true;
        Menu menu = new Menu();
            // Opret og tilføj pizzaer til menuen
            String[] pizzaNavne = {"Vesuvio -", "Amerikaner -", "Cacciatore -", "Carbona -", "Dennis -", "Bertil -", "Silvia -", "Victoria -", "Toronfo -", "Capricciosa -", "Hawai -", "Le Blissola -", "Venezia -", "Mafia -"};
            int[] pizzaPriser = {57, 53, 57, 63, 65, 57, 61, 61, 61, 61, 61, 61, 61, 61};

            for (int i = 0; i < pizzaNavne.length; i++) {
                Pizza pizza = new Pizza();
                pizza.nyPizza(Integer.toString(i + 1), pizzaNavne[i], pizzaPriser[i]);
                menu.addPizza(pizza);
            }

            // Vis menuen
            menu.visMenu();

        while(aktiv) {
            menu.updatePizzaPris("1", 60);
            menu.visMenu();
            aktiv = false;
        }



}
