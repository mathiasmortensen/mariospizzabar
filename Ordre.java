import java.io.FileWriter;
import java.io.IOException;

public class Ordre {

    private Pizza pizza;
    private Kunde kunde;
    private int pris;
    private boolean aktiv;



    public Ordre(Pizza pizza) {
        this.pizza = pizza;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getPris() {
        pris += pizza.getPris();

        float nyPris = (float) (pris * 0.10);

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



    public void FileWriter() {
        if (!aktiv) {
            String forbrugerFil = "Ordre Arkiv.txt";

            try (FileWriter writer = new FileWriter(forbrugerFil, true)) {
                //writer.append(dagsDato)
                writer.append(kunde + ";");
                writer.append(pizza + ";");
                writer.append(pris + ";");
                writer.append("\n");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }

}
