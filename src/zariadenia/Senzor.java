package zariadenia;

public class Senzor extends Zariadenie {

    public Senzor() {
        predstavSa();
    }

    public void skontrolujDochodcov() {
        //ak zisti pohyb, posle x, y suradnice recepcnemu
        //ten potom musi overit ci sa jedna o falosny poplach
        System.out.println("Senzor skontrolovany");
    }

    void predstavSa() {
        System.out.println("Senzor vytvoreny");
    }
}
