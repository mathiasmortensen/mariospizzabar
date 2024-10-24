import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Kunde {

    private String navn;
    private String email;
    private String telefonNr;
    boolean guldKunde = false;

    //KUN ONLINE
    public Kunde(String navn, String email, String telefonNr) {
        this.navn = navn;
        this.email = email;
        this.telefonNr = telefonNr;
    }

    //KUN WALK-IN
    public Kunde(String navn){
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefonNr() {
        return telefonNr;
    }

    //Hvordan walk-in guldkunde?
    public boolean isGuldKunde(){
        return guldKunde;
    }

    public static void readFile()
    {
        String semikolon = ";";
        String line = "";
        String dataFile = "OrdreArkiv.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String[] data = line.split(semikolon);
            String kundeNavn = data[0]; //første kollone er kundens navn

            while ((line = br.readLine()) != null) {
                String[] data = line.split(semikolon);

            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }








}
