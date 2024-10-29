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
        for(int i = 0; i < pizzaer.size(); i++) {
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
                writer.append(" Samlet pris: "+getPris() + ";");
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

