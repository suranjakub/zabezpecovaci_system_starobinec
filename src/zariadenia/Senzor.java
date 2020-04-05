package zariadenia;

import ludia.Dochodca;

import java.util.ArrayList;

public class Senzor extends Zariadenie {

    public Senzor() {
        predstavSa();
    }

    public void skontrolujDochodcov(ArrayList<Dochodca> dochodcovia) {
        //ak zisti pohyb, posle x, y suradnice recepcnemu
        //ten potom musi overit ci sa jedna o falosny poplach
        System.out.println("Senzor skontrolovany");
    }

    void predstavSa() {
        System.out.println("Senzor vytvoreny");
    }
}
