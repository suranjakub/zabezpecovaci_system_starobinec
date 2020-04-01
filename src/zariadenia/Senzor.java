package zariadenia;

public class Senzor extends Zariadenie {

    public Senzor() {
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
        System.out.println("Senzor skontrolovany");
    }

    private void predstavSa() {
        System.out.println("Senzor vytvoreny");
    }
}
