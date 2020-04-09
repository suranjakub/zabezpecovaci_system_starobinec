package ludia;

import main.Starobinec;

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

    public void spracujZleho(Dochodca dochodca) {
        manazer.pokarhaj(dochodca, 1);
    }

    public void skontroluj(int x, int y, Dochodca dochodca) {
        String s = "Recepcny dobehol na ["+x+","+y+"]";
        if(Math.random() < 0.5)
            s += ", bol to falosny poplach";
        else {
            manazer.pokarhaj(dochodca);
            s += ", dochodca bol pokarhany";
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
