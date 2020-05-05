package kontroly;

import ludia.Dochodca;
import ludia.Recepcny;
import main.Starobinec;

public class KontrolaDochodcu implements TypKontroly {
    private Recepcny recepcny = null;

    public KontrolaDochodcu(Recepcny recepcny) {
        this.recepcny = recepcny;
    }

    public boolean skontroluj(Dochodca dochodca) {
        String s = "Recepcny overil zaznam";
        // 30% sanca, ze ani recepcny nespozna
        // dochodcu podla poslaneho zaznamu z kamery
        if(Math.random() < 0.3 && !rovnakaOsobaAkoNaposledy(dochodca)) {
            s += ", nebol to dochodca";
            recepcny.setPoslednaOsoba(dochodca);
            recepcny.getStarobinec().vypisDoGUI(s);
            return false;
        } else {
            s += ", potvrdil, ze to je dochodca";
            recepcny.getStarobinec().vypisDoGUI(s);
            return true;
        }
    }

    private boolean rovnakaOsobaAkoNaposledy(Dochodca dochodca) {
        return dochodca == recepcny.getPoslednaOsoba();
    }
}
