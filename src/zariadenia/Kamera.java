package zariadenia;

public class Kamera extends Zariadenie {
    public void skontrolujDochodcov() {
        if(jeVdb()) {

        }
        //nie je v databaze
        else {
            ukazRecepcnemu();
            ulozitDoDB();
        }
    }
}
