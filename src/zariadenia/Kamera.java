package zariadenia;

public class Kamera extends Zariadenie {

    public Kamera() {
        predstavSa();
    }

    public void skontrolujDochodcov() {
        //skontroluj ci sa dochodca nachadza v DB
        //poslat alarm hlasenie recepcnemu, ci ho pozna
        //ak to nebol falosny poplach, pridat dochodcu do DB,
        //zmenit mu flag - utecenec
        System.out.println("Kamera skontrolovana");
    }

    void predstavSa() {
        System.out.println("Kamera vytvorena");
    }
}
