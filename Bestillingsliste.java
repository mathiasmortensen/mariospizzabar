import java.util.ArrayList;



public class Bestillingsliste {
    private ArrayList<Ordre> bestillingsliste = new ArrayList<>();

    //Tilfojer ordre til listen
    public void tilfoejOrdre(Ordre ordre){
        bestillingsliste.add(ordre);
    }

    //Printer listen ud
    public void seListen() {
        for (int i = 0; i < bestillingsliste.size(); i++) {
            System.out.println((i + 1) + ". " + bestillingsliste.get(i).toString());
        }
    }

    //sletter ordre fra listen
    public void sletOrdre(int nummer) {
        if (nummer > 0 && nummer <= bestillingsliste.size()) {
            bestillingsliste.remove(nummer - 1);
        } else {
            System.out.println("Ugyldigt nummer. Vælg et nummer mellem 1 og " + bestillingsliste.size());
        }
    }

    //to String metode
    public String toString(){
        return bestillingsliste.toString();
    }


}
