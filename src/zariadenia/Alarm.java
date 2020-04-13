package zariadenia;

import ludia.Dochodca;

import java.util.ArrayList;

public class Alarm {

    public Alarm() {
        predstavSa();
    }


    public void skontrolujDochodcov(ArrayList<Dochodca> dochodcovia) {
        //pokial je v databaze
        /*if(jeVdb()) {

        }
        //nie je v databaze
        else {
            ukazRecepcnemu();
            ulozitDoDB();
        }*/
        System.out.println("Alarm skontrolovana");
    }

    void predstavSa() {
        System.out.println("Alarm vytvoreny");
    }

}
