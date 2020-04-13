package zariadenia;

import ludia.Dochodca;
import ludia.Recepcny;
import main.Starobinec;

import java.util.ArrayList;

public class Senzor extends Zariadenie {
    private Recepcny recepcny;
    private Starobinec starobinec;

    public Senzor() {
        predstavSa();
    }

    public Senzor(Starobinec starobinec, Recepcny recepcny) {
        predstavSa();
        this.starobinec = starobinec;
        this.recepcny = recepcny;
    }

    public void skontrolujDochodcov(ArrayList<Dochodca> dochodcovia) {
        ArrayList<Dochodca> zlyDochodcovia = new ArrayList<>();

        int pocUtecenych = 0;
        //ak zisti pohyb, posle x, y suradnice recepcnemu
        //ten potom musi overit ci sa jedna o falosny poplach

        if (dochodcovia == null) {
            Starobinec.getInstance().vypisDoGUI("NIE SU VYTVORENY DOCHODCOVIA!");
            System.out.println("NIE SU VYTVORENY DOCHODCOVIA!");
        }
        else {
            for (Dochodca dochodca : dochodcovia) {
                if (jeVzakazanejZone(dochodca)) {
                    pocUtecenych++;
                    int x = dochodca.getX();
                    int y = dochodca.getY();
                    String s = "\nSenzor zachytil pohyb v zone ["+x+","+y+"]";
                    starobinec.vypisDoGUI(s);
                    System.out.println(s);
                    recepcny.skontroluj(x, y, dochodca);
                }
            }
            //uz som skontroloval vsetkych, mozem ich spracovat
            recepcny.spracujZlychaUtecencov();
        }

        if (pocUtecenych == 0) {
            String s = "Senzory skontrolovane - ziadny poplach";
            System.out.println(s);
            starobinec.vypisDoGUI(s);
        }
    }

    public boolean jeVzakazanejZone(Dochodca dochodca) {
        int x = dochodca.getX();
        int y = dochodca.getY();
        return x > 150 && x < 250 || y > 150 && y < 250;
    }

    void predstavSa() {
        System.out.println("Senzor vytvoreny");
    }
}
