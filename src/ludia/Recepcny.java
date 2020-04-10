package ludia;

import main.Starobinec;

import java.util.ArrayList;

public class Recepcny extends Zamestnanec {
    private Starobinec starobinec;
    private Manazer manazer;
    private int poslednaKontrolaX, poslednaKontrolaY;

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

    public boolean skontroluj(int x, int y, Dochodca dochodca) {
        String s = "Recepcny dobehol na ["+x+","+y+"]";
        if(Math.random() < 0.5) {
            if (rovnakeMiestoAkoNaposledy(x, y))
                s += ", dochodca sa pravdepodobne schoval";
            else {
                poslednaKontrolaX = x;
                poslednaKontrolaY = y;
                s += ", bol to falosny poplach";
            }
            starobinec.vypisDoGUI(s);
            return false;
        } else {
            //manazer.pokarhaj(dochodca);
            s += ", uteceny dochodca bude zavedeny k manazerovi";
            starobinec.vypisDoGUI(s);
            if(dochodca.getPocUteceni() < 2)
                manazer.pokarhaj(dochodca);
            else
                manazer.vyhod(dochodca);
            return true;
        }
    }

    private boolean rovnakeMiestoAkoNaposledy(int x, int y) {
        return (x == poslednaKontrolaX) && (y == poslednaKontrolaY);
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
