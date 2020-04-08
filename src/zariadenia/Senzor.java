package zariadenia;

import ludia.Dochodca;
import main.Starobinec;

import java.util.ArrayList;

public class Senzor extends Zariadenie {

    public Senzor() {
        predstavSa();
    }

    public void skontrolujDochodcov(ArrayList<Dochodca> dochodcovia) {
        //ak zisti pohyb, posle x, y suradnice recepcnemu
        //ten potom musi overit ci sa jedna o falosny poplach

        if (dochodcovia == null) {
            Starobinec.getInstance().vypisDoGUI("NIE SU VYTVORENY DOCHODCOVIA!");
            System.out.println("NIE SU VYTVORENY DOCHODCOVIA!");
        }
        else {
            for (Dochodca dochodca : dochodcovia) {
                jeVzakazanejZone(dochodca);
                int x = dochodca.getX();
                int y = dochodca.getY();
                recepcia.skontrolujZonu(x, y);
            }
        }

        System.out.println("Senzor skontrolovany");
    }

    private boolean jeVzakazanejZone(Dochodca dochodca) {
        int x = dochodca.getX();
        int y = dochodca.getY();
        return x > 150 && x < 250 || y > 150 && y < 250;
    }

    void predstavSa() {
        System.out.println("Senzor vytvoreny");
    }
}
