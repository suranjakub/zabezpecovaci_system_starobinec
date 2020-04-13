package ludia;

import main.Starobinec;

import java.util.ArrayList;

public class Manazer extends Zamestnanec{
    private Starobinec starobinec;

    public Manazer(Starobinec starobinec) {
        super("Peter","manazer");
        this.starobinec = starobinec;
    }

    public void pokarhaj(ArrayList<Dochodca> zlyDochodci) {
        for (Dochodca zlyDochodca :
                zlyDochodci) {
            zlyDochodca.zvysPocUteceni();
            //starobinec.pridajDoDB(zlyDochodca);
            starobinec.vypisDoGUI("MANAZER POKARHAL DOCHODCU "+zlyDochodca.getId());
        }
        //teraz mozeme vycistit zoznam, uz boli pokarhani
        zlyDochodci.clear();
    }

    public void vyhod(ArrayList<Dochodca> utecenci) {
        for (Dochodca utecenec: utecenci) {
            starobinec.zmazDochodcu(utecenec);
            starobinec.vypisDoGUI("MANAZER VYHODIL DOCHODCU "+utecenec.getId());
        }
        //teraz mozeme vycistit zoznam, uz boli vyhodeny
        utecenci.clear();
    }

    public void pokarhaj(Dochodca dochodca) {
        dochodca.zvysPocUteceni();
        starobinec.vypisDoGUI("MANAZER POKARHAL DOCHODCU "+dochodca.getId());
        System.out.println("Pocet uteceni dochodcu "+dochodca.getId()+" je "+dochodca.getPocUteceni());
    }

    public void vyhod(Dochodca dochodca) {
        starobinec.zmazDochodcu(dochodca);
        starobinec.vypisDoGUI("MANAZER VYHODIL DOCHODCU "+dochodca.getId());
    }
}
