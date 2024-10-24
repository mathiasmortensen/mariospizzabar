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

    //Hvordan walk-in guldkunde?
    public boolean isGuldKunde(){
        return guldKunde;
    }

    /*if(ordrer < 10){
    guldKunde = true;
    }

     */






}
