public class Kunde {

    private String navn;
    private String email;
    private int telefonNr;
    boolean guldKunde = false;

    public Kunde(String navn, String email, int telefonNr) {
        this.navn = navn;
        this.email = email;
        this.telefonNr = telefonNr;
    }

    public Kunde(String navn){
        this.navn = navn;
    }

    public boolean isGuldKunde(){
        return guldKunde;
    }

    /*if(ordrer < 10){
    guldKunde = true;
    }

     */






}
