package zariadenia;

public class Alarm extends Zariadenie {

    public Alarm() {
        predstavSa();
    }

    public void skontrolujDochodcov() {
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
