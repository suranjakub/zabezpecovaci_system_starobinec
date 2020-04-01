package zariadenia;

public class Alarm extends Zariadenie {

    public Alarm() {
        predstavSa();
    }

    public void skontrolujDochodcov() {
        /*if(jeVdb()) {

        }
        //nie je v databaze
        else {
            ukazRecepcnemu();
            ulozitDoDB();
        }*/
    }

    private void predstavSa() {
        System.out.println("Alarm vytvoreny");
    }

}
