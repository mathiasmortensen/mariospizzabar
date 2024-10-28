import java.util.Scanner;

public class Restaurant {
    private static boolean aktiv;

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        aktiv = true;

        while (aktiv) {
            System.out.println("\nPizza Menu System");
            System.out.println("1. Tilføj ny pizza");
            System.out.println("2. Opdater pris på pizza");
            System.out.println("3. Vis menu");
            System.out.println("4. Slet pizza fra menuen");
            System.out.println("5. Afslut");
            System.out.print("Vælg en mulighed: ");
            String valg = scanner.next();
            scanner.nextLine();  // Ryd scannerens buffer

            if (valg.equals("1")) {
                System.out.print("Indtast pizzaens nummer: ");
                String nummer = scanner.nextLine();
                System.out.print("Indtast pizzaens navn: ");
                String navn = scanner.nextLine();
                System.out.print("Indtast pizzaens pris: ");
                int pris = scanner.nextInt();
                scanner.nextLine();  // Ryd scannerens buffer
                Pizza nyPizza = new Pizza(nummer, navn, pris);
                menu.addPizza(nyPizza);
                System.out.println("Pizza tilføjet til menuen.");

            }
            else if (valg.equals("2")) {
                System.out.print("Indtast nummeret på pizzaen, hvis pris skal opdateres: ");
                String opdaterNummer = scanner.nextLine();
                System.out.print("Indtast den nye pris: ");
                int nyPris = scanner.nextInt();
                scanner.nextLine();  // Ryd scannerens buffer
                menu.updatePizzaPris(opdaterNummer, nyPris);

            }
            else if (valg.equals("3")) {
                menu.visMenu();

            }
            else if (valg.equals("4")) {
                System.out.print("Indtast nummeret på pizzaen, der skal slettes: ");
                String slettetNummer = scanner.nextLine();
                menu.removePizza(slettetNummer);

            }
            else if (valg.equals("5")) {
                aktiv = false;
                System.out.println("Afslutter programmet.");

            }
            else {
                System.out.println("Ugyldigt valg, prøv igen.");
            }
        }

        scanner.close();
    }
}