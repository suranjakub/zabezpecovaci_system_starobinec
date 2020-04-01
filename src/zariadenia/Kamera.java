package zariadenia;

public class Kamera extends Zariadenie {

    public Kamera() {
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
        System.out.println("Kamera skontrolovana");
    }

    void predstavSa() {
        System.out.println("Kamera vytvorena");
    }
}
