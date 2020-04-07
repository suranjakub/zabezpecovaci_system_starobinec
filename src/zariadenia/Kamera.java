package zariadenia;

import ludia.Dochodca;
import main.Starobinec;

import java.util.ArrayList;

public class Kamera extends Zariadenie {
    private static ArrayList<Dochodca> databaza;
    private static final int hranicaX = 250, hranicaY = 250;
    private Starobinec starobinec;

    public Kamera() {
        predstavSa();
    }

    public void skontrolujDochodcov(ArrayList<Dochodca> dochodcovia) {
        if (dochodcovia == null) {
            Starobinec.getInstance().vypisDoGUI("NIE SU VYTVORENY DOCHODCOVIA!");
            System.out.println("NIE SU VYTVORENY DOCHODCOVIA!");
        }
        else {
            //skontroluj ci sa dochodca nachadza v DB
            skontrolujSpojeniesDB();
            for (Dochodca dochodca : dochodcovia) {
                if (jeVzornomPoli(dochodca))
                    if (skontrolujCiJevDB(dochodca))
                        System.out.println("Dochodca "+dochodca.getId()+" utiekol a JE aj v DB");
                    else
                        System.out.println("Dochodca "+dochodca.getId()+" utiekol ale NIE JE v DB");
            }

            //poslat alarm hlasenie recepcnemu, ci ho pozna
            //ak to nebol falosny poplach, pridat dochodcu do DB,
            //zmenit mu flag - utecenec

            System.out.println("Kamera skontrolovana");
        }
    }

    private void skontrolujSpojeniesDB() {
        if (databaza == null)
            databaza = Starobinec.getInstance().getDB();
    }

    public boolean skontrolujCiJevDB(Dochodca dochodca) {
        if (databaza == null) {
            return false;
        }
        else {
            for (Dochodca i : databaza) {
                //pokial je dochodca je v databaze
                if (jeVzornomPoli(i) && dochodca == i)
                    return true;
            }
            return false;
        }
    }

    private boolean jeVzornomPoli(Dochodca dochodca) {
        return dochodca.getX() > hranicaX || dochodca.getY() > hranicaY;
    }

    void predstavSa() {
        System.out.println("Kamera vytvorena");
    }
}
