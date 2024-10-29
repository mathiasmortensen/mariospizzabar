public class Pizza {
    private int pris;
    private String navn;
    private String nummer;
    private int count; // Ny variabel til at tælle bestillinger

    public Pizza(String nummer, String navn, int pris) {
        this.nummer = nummer;
        this.pris = pris;
        this.navn = navn;
        this.count = 0; // Initialiser count til 0
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

    public int getCount() {
        return count; // Hent antallet af bestillinger
    }

    public void incrementCount() {
        this.count++; // Øg antallet med 1
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public void setPizzaNavn(String navn) {
        this.navn = navn;
    }

    public void setPris(int nyPris) {
        this.pris = nyPris;
    }


    public String toString() {
        return " " + nummer + ". " + navn + " " + pris + "kr "; // Tilføjet count til toString
    }
}
