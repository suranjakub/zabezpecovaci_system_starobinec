package ludia;

import main.Starobinec;

import java.util.ArrayList;

public class Recepcny extends Zamestnanec {
    private Starobinec starobinec;
    private Manazer manazer;

    public Recepcny() {
        super("Dundee","recepcny");
    }

    public Recepcny(Starobinec starobinec, Manazer manazer) {
        super("Dundee","recepcny");
        this.starobinec = starobinec;
        this.manazer = manazer;
    }

    public void spracujZlych(ArrayList<Dochodca> zlyDochodcovia) {
        manazer.pokarhaj(zlyDochodcovia);
    }

    public void spracujUtecencov(ArrayList<Dochodca> utecenci) {
        manazer.vyhod(utecenci);
    }

    public void skontroluj(int x, int y, Dochodca dochodca) {
        String s = "Recepcny dobehol na ["+x+","+y+"]";
        if(Math.random() < 0.5)
            s += ", bol to falosny poplach";
        else {
            //manazer.pokarhaj(dochodca);
            s += ", dochodca bude pokarhany";
        }
        starobinec.vypisDoGUI(s);
    }

    public boolean skontroluj(Dochodca dochodca) {
        String s = "Recepcny overil poplach";
        if(Math.random() < 0.5) {
            s += ", nebol to dochodca";
            starobinec.vypisDoGUI(s);
            return false;
        } else {
            s += ", potvrdil, ze to je dochodca";
            starobinec.vypisDoGUI(s);
            return true;
        }
    }
}
