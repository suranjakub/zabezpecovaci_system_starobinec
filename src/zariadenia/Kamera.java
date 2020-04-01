package zariadenia;

public class Kamera extends Zariadenie {

    public Kamera() {
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
        System.out.println("Kamera vytvorena");
    }
}
