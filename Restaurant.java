import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class Restaurant { //
    private static boolean aktiv;

    public void startRestaurant() {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        aktiv = true;
        Bestillingsliste listen = new Bestillingsliste();

        while (aktiv) {
            System.out.println("\nPizza Menu System");
            System.out.println("1. Tilføj ny pizza");
            System.out.println("2. Opdater pris på pizza");
            System.out.println("3. Vis menu");
            System.out.println("4. Slet pizza fra menuen");
            System.out.println("5. Opret ny ordre");
            System.out.println("6. Afslut");
            System.out.print("Vælg en mulighed: ");
            String valg = scanner.next();
            scanner.nextLine();

            if (valg.equals("1")) {
                System.out.print("Indtast pizzaens nummer: ");
                String nummer = scanner.nextLine();
                System.out.print("Indtast pizzaens navn: ");
                String navn = scanner.nextLine();
                System.out.print("Indtast pizzaens pris: ");
                int pris = scanner.nextInt();
                scanner.nextLine();
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
                 ArrayList<Pizza> valgtePizzaer = new ArrayList<>();
                while (true) {
                    System.out.print("Indtast pizzaens nummer til ordren (eller 'stop' for at afslutte): ");
                    String pizzaNummer = scanner.nextLine();

                    if (pizzaNummer.equalsIgnoreCase("stop")) {
                        break;
                    }

                    Pizza valgtPizza = menu.findPizza(pizzaNummer);
                    if (valgtPizza != null) {
                        valgtePizzaer.add(valgtPizza);
                    } else {
                        System.out.println("Pizza med dette nummer blev ikke fundet.");
                    }
                }

                System.out.println("Er det en online kunde? (y/n): ");
                String erOnline = scanner.nextLine();
                Kunde kunde;

                if (erOnline.equalsIgnoreCase("y")) {
                    System.out.print("Indtast kundens navn: ");
                    String navn = scanner.nextLine();
                    System.out.print("Indtast kundens e-mail: ");
                    String email = scanner.nextLine();
                    System.out.print("Indtast kundens telefonnummer: ");
                    String telefonNr = scanner.nextLine();
                    kunde = new Kunde(navn, email, telefonNr);
                } else {
                    System.out.print("Indtast kundens navn: ");
                    String navn = scanner.nextLine();
                    kunde = new Kunde(navn);
                }

                Ordre ordre = new Ordre(valgtePizzaer, kunde);
                ordre.saveOrderToFile();
                listen.tilfoejOrdre(ordre);
                System.out.println("Ordre gemt og tilføjet til Bestillingslisten");
            }


            else if (valg.equals("6")) {
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