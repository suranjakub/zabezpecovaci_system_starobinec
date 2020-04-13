package zariadenia;

import ludia.Dochodca;
import ludia.Recepcny;
import main.Starobinec;

import java.util.ArrayList;

public class Kamera extends Zariadenie {
    private static ArrayList<Dochodca> databaza;
    private static final int hranicaX = 250, hranicaY = 250;
    private Starobinec starobinec;
    private Recepcny recepcny;

    public Kamera() {
        predstavSa();
    }

    public Kamera(Starobinec starobinec, Recepcny recepcny) {
        predstavSa();
        this.starobinec = starobinec;
        this.recepcny = recepcny;
    }

    public void skontrolujDochodcov(ArrayList<Dochodca> dochodcovia) {
        ArrayList<Dochodca> chronickyUtecenci = new ArrayList<>();
        ArrayList<Dochodca> zlyDochodcovia = new ArrayList<>();

        //nie su vytvoreny dochodcovia, nie je koho kontrolovat
        if (dochodcovia == null) {
            Starobinec.getInstance().vypisDoGUI("NIE SU VYTVORENY DOCHODCOVIA!");
            System.out.println("NIE SU VYTVORENY DOCHODCOVIA!");
        }
        //ak dochodcovia existuju
        else {
            //skontroluj ci sa dochodca nachadza v DB
            skontrolujSpojeniesDB();
            for (Dochodca dochodca : dochodcovia) {
                if (jeVzakazanejZone(dochodca))
                    if (skontrolujCiJevDB(dochodca)) {
                        //String s = "Dochodca "+dochodca.getId()+" utiekol a JE aj v DB";
                        String s = "Kamera zaznamenala uteceneho dochodcu a JE aj v DB";
                        //teraz vyhadzujem dochodcu, ale nemozem modifikovat array lebo som v cykle;
                        //recepcny.spracujZleho(dochodca);
                        chronickyUtecenci.add(dochodca);
                        System.out.println(s);
                        starobinec.vypisDoGUI(s);
                    }
                    else {
                        //String s = "Dochodca "+dochodca.getId()+" utiekol ale NIE JE v DB";
                        String s = "Kamera zaznamenala poplach v zone ["+dochodca.getX()+","+dochodca.getY()+"]";
                        System.out.println(s);
                        starobinec.vypisDoGUI(s);
                        if(recepcny.skontroluj(dochodca)) {
                            zlyDochodcovia.add(dochodca);
                            //starobinec.pridajDoDB(dochodca);
                        }

                    }
            }

            recepcny.spracujZlych(zlyDochodcovia);
            recepcny.spracujUtecencov(chronickyUtecenci);

            //poslat alarm hlasenie recepcnemu, ci ho pozna
            //ak to nebol falosny poplach, pridat dochodcu do DB,
            //zmenit mu flag - utecenec

            if (zlyDochodcovia.isEmpty() && chronickyUtecenci.isEmpty()) {
                String s = "\nKamery skontrolovane - ziadny poplach";
                System.out.println(s);
                starobinec.vypisDoGUI(s);
            }
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
                if (jeVzakazanejZone(i) && dochodca == i)
                    return true;
            }
            return false;
        }
    }

    public boolean jeVzakazanejZone(Dochodca dochodca) {
        return dochodca.getX() > hranicaX || dochodca.getY() > hranicaY;
    }

    void predstavSa() {
        System.out.println("Kamera vytvorena");
    }
}
