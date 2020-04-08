package ludia;

import main.Starobinec;

public class Recepcny extends Zamestnanec {
    private Starobinec starobinec;

    public Recepcny() {
        super("Dundee","recepcny");
    }

    public Recepcny(Starobinec starobinec) {
        super("Dundee","recepcny");
        this.starobinec = starobinec;
    }

    @Override
    public void skontroluj(int x, int y) {

    }

    public void skontroluj(Dochodca dochodca) {
        String s = "Recepcny skontroloval dochocu";
        System.out.println(s);
        this.starobinec.vypisDoGUI(s);
    }
}
