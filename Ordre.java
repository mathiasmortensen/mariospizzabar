import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;


public class Ordre {
    
    private     ArrayList<Pizza>        pizzaer;
    private     Kunde                   kunde;
    private     Pizza                   pizza;
    private     int                     pris;
    private     boolean                 aktiv;
    private     static int              o = 0;// Gør o statisk, så den opdateres korrekt


    public Ordre(ArrayList<Pizza> pizzaer, Kunde kunde) {
        this.pizzaer = pizzaer;
        this.kunde = kunde;
        this.aktiv = true; // Initialisere som aktiv ordre
    }

    public Pizza getPizza() {
        return pizza;
    }


    public int getPris() {
        for(int i = 0; i < pizzaer.size(); i++) {
            pris += pizzaer.get(i).getPris();
        }
        float nyPris = (float) (pris * 0.90);
        if (kunde.erGuldKunde()) {
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
            String forbrugerFil = "OrdreArkiv.txt";
            try (FileWriter writer = new FileWriter(forbrugerFil, true)) {
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
        public void saveOrderToFile() {
            this.setAktiv(false);
            this.writeToFile();
        }
    }
