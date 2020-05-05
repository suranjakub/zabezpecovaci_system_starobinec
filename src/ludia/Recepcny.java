package ludia;

import kontroly.TypKontroly;
import main.Starobinec;

import javax.print.Doc;
import java.util.ArrayList;

public class Recepcny extends Zamestnanec {
    private Starobinec starobinec;
    private Manazer manazer;
    private int poslednaKontrolaX;
    private int poslednaKontrolaY;
    private ArrayList<Dochodca> naVyhodenie = new ArrayList<>();
    private ArrayList<Dochodca> naPokarhanie = new ArrayList<>();
    private Dochodca poslednaOsoba;

    public Dochodca getPoslednaOsoba() {
        return poslednaOsoba;
    }

    public Starobinec getStarobinec() {
        return starobinec;
    }

    public ArrayList<Dochodca> getNaVyhodenie() {
        return naVyhodenie;
    }

    public ArrayList<Dochodca> getNaPokarhanie() {
        return  naPokarhanie;
    }

    public void setPoslednaOsoba(Dochodca poslednaOsoba) {
        this.poslednaOsoba = poslednaOsoba;
    }

    public int getPoslednaKontrolaX() {
        return poslednaKontrolaX;
    }

    public int getPoslednaKontrolaY() {
        return poslednaKontrolaY;
    }

    public void setPoslednaKontrolaX(int poslednaKontrolaX) {
        this.poslednaKontrolaX = poslednaKontrolaX;
    }

    public void setPoslednaKontrolaY(int poslednaKontrolaY) {
        this.poslednaKontrolaY = poslednaKontrolaY;
    }

    public Recepcny() {
        super("Dundee","recepcny");
    }

    public Recepcny(Starobinec starobinec, Manazer manazer) {
        super("Dundee","recepcny");
        this.starobinec = starobinec;
        this.manazer = manazer;
    }

    public void spracujZlychaUtecencov() {
        if( !(naVyhodenie.isEmpty()) )
            manazer.vyhod(naVyhodenie);
        if( !(naPokarhanie.isEmpty()) )
            manazer.pokarhaj(naPokarhanie);
    }

    public void spracujZlych(ArrayList<Dochodca> zlyDochodcovia) {
        manazer.pokarhaj(zlyDochodcovia);
    }

    public void spracujUtecencov(ArrayList<Dochodca> utecenci) {
        manazer.vyhod(utecenci);
    }

    public boolean skontroluj(TypKontroly typKontroly, Dochodca dochodca) {
        return typKontroly.skontroluj(dochodca);
    }

    /*public boolean skontroluj(int x, int y, Dochodca dochodca) {
        String s = "Recepcny dobehol na ["+x+","+y+"]";
        //sanca 30 ku 70, ze sa dochodca schova
        if(Math.random() < 0.3) {
            if (rovnakeMiestoAkoNaposledy(x, y))
                s += ", dochodca sa pravdepodobne schoval";
            else {
                poslednaKontrolaX = x;
                poslednaKontrolaY = y;
                s += ", nebol tu dochodca";
            }
            starobinec.vypisDoGUI(s);
            return false;
        }
        //dochodca prichyteny pri uteku
        else {
            poslednaKontrolaX = x;
            poslednaKontrolaY = y;
            //manazer.pokarhaj(dochodca);
            s += ", uteceny dochodca bude zavedeny k manazerovi";
            starobinec.vypisDoGUI(s);
            if(dochodca.getPocUteceni() < 1)
                naPokarhanie.add(dochodca);
                //manazer.pokarhaj(dochodca);
            else
                naVyhodenie.add(dochodca);
                //manazer.vyhod(dochodca);
            return true;
        }
    }*/

    /*private boolean rovnakeMiestoAkoNaposledy(int x, int y) {
        return (x == poslednaKontrolaX) && (y == poslednaKontrolaY);
    }*/

    /*private boolean rovnakaOsobaAkoNaposledy(Dochodca dochodca) {
        return dochodca == poslednaOsoba;
    }*/

    /*public boolean skontroluj(Dochodca dochodca) {
        String s = "Recepcny overil zaznam";
        // 30% sanca, ze ani recepcny nespozna
        // dochodcu podla poslaneho zaznamu z kamery
        if(Math.random() < 0.3 && !rovnakaOsobaAkoNaposledy(dochodca)) {
            s += ", nebol to dochodca";
            poslednaOsoba = dochodca;
            starobinec.vypisDoGUI(s);
            return false;
        } else {
            s += ", potvrdil, ze to je dochodca";
            starobinec.vypisDoGUI(s);
            return true;
        }
    }*/
}
