package zariadenia;

import ludia.Dochodca;
import main.Starobinec;

import javax.print.Doc;
import java.util.ArrayList;

public class Kamera extends Zariadenie {
    private static ArrayList<Dochodca> databaza = null;
    private static final int hranicaX = 250, hranicaY = 250;

    public Kamera() {
        predstavSa();
    }

    public void skontrolujDochodcov(ArrayList<Dochodca> dochodcovia) {
        //skontroluj ci sa dochodca nachadza v DB
        this.skontrolujSpojeniesDB();
        this.prehladajDB(dochodcovia);
        //poslat alarm hlasenie recepcnemu, ci ho pozna
        //ak to nebol falosny poplach, pridat dochodcu do DB,
        //zmenit mu flag - utecenec
        System.out.println("Kamera skontrolovana");
    }

    private void skontrolujSpojeniesDB() {
        if (databaza == null)
            databaza = Starobinec.getInstance().getDB();
    }

    public int prehladajDB(ArrayList<Dochodca> dochodcovia) {
        for (int i = 0; i < databaza.size(); i++) {
            for (int j = 0; j < dochodcovia.size(); j++) {
                Dochodca dochodca1 = dochodcovia.get(j);
                Dochodca dochodca2 = dochodcovia.get(i);
                //dochodca je v rangi a aj v DB
                if(jeVzornomPoli(dochodca1) && (dochodca1 == dochodca2))
                    return 1;
            }
        }
        return 0;
    }

    private boolean jeVzornomPoli(Dochodca dochodca) {
        if(dochodca.getX() > hranicaX || dochodca.getY() > hranicaY)
            return true;
        return false;
    }

    void predstavSa() {
        System.out.println("Kamera vytvorena");
    }
}
