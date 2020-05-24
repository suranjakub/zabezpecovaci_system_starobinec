package kontroly;

import ludia.Dochodca;
import ludia.Recepcny;
import main.Starobinec;

import java.util.ArrayList;

/**
 * Trieda pouzivana recepcnym na skontrolovanie
 * miesta, po zisteni utecenia nejakeho dochodcu
 */
public class KontrolaMiesta implements TypKontroly {
    private Recepcny recepcny = null;

    public KontrolaMiesta(Recepcny recepcny) {
        this.recepcny = recepcny;
    }

    /**
     * Metoda simuluje kontrolovanie miesta v realnom zivote
     * pomocou nahodneho generovania stavov.
     * Vyuziva generovanie sance na urovni 30%, ze sa
     * dany dochodca, pri kontrole miesta schova. Logicky
     * je 70% sanca, ze bude dochodca na mieste a recepcny ho zbada.
     * @param dochodca kontrolovany dochodca
     * @return true ak bol dochodca prichyteny pri uteku, false ak bol falosny poplach
     */
    @Override
    public boolean skontroluj(Dochodca dochodca) {
        int x = dochodca.getX();
        int y = dochodca.getY();
        String s = "Recepcny dobehol na ["+x+","+y+"]";
        //sanca 30 ku 70, ze sa dochodca schova
        if(Math.random() < 0.3) {
            if (rovnakeMiestoAkoNaposledy(x, y))
                s += ", dochodca sa pravdepodobne schoval";
            else {
                recepcny.setPoslednaKontrolaX(x);
                recepcny.setPoslednaKontrolaY(y);
                s += ", nebol tu dochodca";
            }
            recepcny.getStarobinec().vypisDoGUI(s);
            return false;
        }
        //dochodca prichyteny pri uteku
        else {
            recepcny.setPoslednaKontrolaX(x);
            recepcny.setPoslednaKontrolaY(y);
            //manazer.pokarhaj(dochodca);
            s += ", uteceny dochodca bude zavedeny k manazerovi";
            recepcny.getStarobinec().vypisDoGUI(s);
            if(dochodca.getPocUteceni() < 1)
                recepcny.getNaPokarhanie().add(dochodca);
                //manazer.pokarhaj(dochodca);
            else
                recepcny.getNaVyhodenie().add(dochodca);
            //manazer.vyhod(dochodca);
            return true;
        }
    }

    private boolean rovnakeMiestoAkoNaposledy(int x, int y) {
        return (x == recepcny.getPoslednaKontrolaX()) && (y == recepcny.getPoslednaKontrolaY());
    }
}
