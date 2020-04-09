package ludia;

import main.Starobinec;

public class Manazer extends Zamestnanec{
    private Starobinec starobinec;

    public Manazer(Starobinec starobinec) {
        super("Peter","manazer");
        this.starobinec = starobinec;
    }

    public void pokarhaj(Dochodca dochodca) {
        starobinec.vypisDoGUI("MANAZER POKARHAL DOCHODCU "+dochodca.getId());
    }

    public void pokarhaj(Dochodca dochodca, int i) {
        if (i == 1) {
            starobinec.zmazDochodcu(dochodca);
            starobinec.vypisDoGUI("MANAZER VYHODIL DOCHODCU");
        }
    }
}
