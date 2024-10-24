public class Pizza {

    private int pris;
    private String navn;
    private String nummer;


    public void nyPizza(String nummer, String navn, int pris) {
        this.nummer = nummer;
        this.pris = pris;
        this.navn = navn;
    }

    public int getPris() {
        return pris;
    }

    public String getNavn() {
        return navn;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public void setPizzaNavn(String navn) {
        this.navn = navn;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }




}
