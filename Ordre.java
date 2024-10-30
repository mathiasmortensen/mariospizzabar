import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;


public class Ordre {

    private ArrayList<Pizza> pizzaer;
    private Kunde kunde;
    private Pizza pizza;
    private int pris;
    private boolean aktiv;
    private static int o = 1;// Gør o statisk, så den opdateres korrekt
    private boolean guldKunde = false;


    public Ordre(ArrayList<Pizza> pizzaer, Kunde kunde) {
        this.pizzaer = pizzaer;
        this.kunde = kunde;
        this.aktiv = true; // Initialisere som aktiv ordre
    }


    public String toString() {
        String kundeInfo = "Kunde: " + kunde.getNavn() + " ";
        if (kunde.getTelefonNr() != null) {
            kundeInfo += " - Telefon: " + kunde.getTelefonNr() + " ";
        }
        if (kunde.getEmail() != null) {
            kundeInfo += " - Email: " + kunde.getEmail() + " ";
        }

        String pizzaInfo = "Pizzaer: ";
        for (Pizza pizza : pizzaer) {
            pizzaInfo += "" + pizza.toString() + " ";
        }

        String prisInfo = "Samlet pris: " + getPris() + "kr";

        return kundeInfo + pizzaInfo + prisInfo;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public boolean erGuldKunde() {
        return guldKunde;
    }


    public int getPris() {
        for (int i = 0; i < pizzaer.size(); i++) {
            pris += pizzaer.get(i).getPris();
        }
        float nyPris = (float) (pris * 0.90);
        if (erGuldKunde()) {
            pris += nyPris;
        }
        return pris;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public void writeToFile() {
        if (!aktiv) {
            String ordrer = "OrdreArkiv.txt";
            try (FileWriter writer = new FileWriter(ordrer, true)) {
                LocalDate dato = LocalDate.now();
                String ordreNummer = String.format("%03d", o);
                writer.append("\n");
                writer.append(dato + "-" + ordreNummer + ";");
                if (kunde.getEmail() == null && kunde.getTelefonNr() == null) {
                    writer.append("WalkIn kunde: " + kunde.getNavn() + "-");
                } else {
                    writer.append("Online kunde: " + kunde.getNavn() + " - " + kunde.getTelefonNr() + " - " + kunde.getEmail() + ";");
                }
                for (Pizza pizza : pizzaer) {
                    writer.append(pizza.toString() + ";");
                }
                writer.append(" Samlet pris: " + getPris() + ";");
                writer.append("\n");
                o++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

/*
    private boolean checkGuldKunde(String telefonNr) {
        int pizzaCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("OrdreArkiv.txt"))) {
            String linje;
            while ((linje = br.readLine()) != null) {
                if (linje.contains(telefonNr)) {
                    String[] data = linje.split(";");
                    for (String entry : data) {
                        if (entry.contains("Pizza")) {
                            pizzaCount++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pizzaCount > 10;
    }

*/


    public void saveOrderToFile() {
        this.setAktiv(false);
        this.writeToFile();
    }

    //
    public static void visMestPopulaerePizzaer() {
        String dataFile = "OrdreArkiv.txt";
        ArrayList<String> pizzaNavne = new ArrayList<>();
        ArrayList<Integer> pizzaTællinger = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] ordreDelt = line.split(";");
                for (String item : ordreDelt) {
                    if (item.contains("kr")) {  // Find kun pizzaer baseret på "kr"
                        String pizzaNavn = item.trim().split(" ")[1]; // Hent pizza-navnet

                        if (pizzaNavn.equalsIgnoreCase("kunde:")) {
                            continue; // Spring over til næste iteration, hvis pizzaNavn er "kunde"
                        }

                        // Tjek om pizzaen allerede findes i listen
                        int index = pizzaNavne.indexOf(pizzaNavn);
                        if (index != -1) {
                            // Pizzaen findes allerede, så øg tællingen
                            pizzaTællinger.set(index, pizzaTællinger.get(index) + 1);
                        } else {
                            // Pizzaen findes ikke, så tilføj den
                            pizzaNavne.add(pizzaNavn);
                            pizzaTællinger.add(1); // Start tællingen på 1
                        }
                    }
                }
            }

            // Sorter pizzaerne efter tællinger fra højeste til laveste
            for (int i = 0; i < pizzaTællinger.size() - 1; i++) {
                for (int j = 0; j < pizzaTællinger.size() - 1 - i; j++) {
                    if (pizzaTællinger.get(j) < pizzaTællinger.get(j + 1)) {
                        // Byt tællinger
                        int tempTælling = pizzaTællinger.get(j);
                        pizzaTællinger.set(j, pizzaTællinger.get(j + 1));
                        pizzaTællinger.set(j + 1, tempTælling);

                        // Byt navne
                        String tempNavn = pizzaNavne.get(j);
                        pizzaNavne.set(j, pizzaNavne.get(j + 1));
                        pizzaNavne.set(j + 1, tempNavn);
                    }
                }
            }

            //Udskriv resultatet
            for (int i = 0; i < pizzaNavne.size(); i++) {
                System.out.println(pizzaNavne.get(i) + " - bestilt " + pizzaTællinger.get(i) + " gange");
            }

        } catch (IOException e) {
            System.out.println("Fejl ved læsning fra fil: " + e.getMessage());
        }
    }

}

