import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ordre {

    private Pizza pizza;
    private Kunde kunde;
    private int pris;
    private boolean aktiv;
    private int o = 0;




    public Ordre(Pizza pizza, Kunde kunde) {
        this.pizza = pizza; this.kunde = kunde;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getPris() {
        pris += pizza.getPris();

        float nyPris = (float) (pris * 0.90);

        if(kunde.isGuldKunde()){
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


//Skal genere en tekstil med indholdet: DATO + 000(Ordre på dagen);KUNDE(if online så NAVN - E-Mail - TLF nr);Pizzaer;Pris
    public void writeToFile() {
        if (!aktiv) {
            String forbrugerFil = "OrdreArkiv.txt";
            try (FileWriter writer = new FileWriter(forbrugerFil, true)) {
                //writer.append(dagsDato)
                if(kunde.getEmail() == null && kunde.getTelefonNr() == null){
                    String ordreNummer = String.format("%03d", o);
                    writer.append("Dagsdato" + ordreNummer + ";");
                    writer.append("WalkIn kunde: " + kunde.getNavn() + ";");
                    writer.append(pizza + ";");
                    writer.append(pris + ";");
                    writer.append("\n");
                    o++;
                }else{
                    String ordreNummer = String.format("%03d", o);
                    writer.append("Dagsdato" + ordreNummer + ";");
                    writer.append("Online kunde: " + kunde.getNavn() + kunde.getTelefonNr() + kunde.getEmail() + ";");
                    writer.append(pizza + ";");
                    writer.append(pris + ";");
                    writer.append("\n");
                    o++;
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }

    public static void main(String[] args) {



    }


}
