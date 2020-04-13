package zariadenia;

import ludia.Dochodca;

import java.util.ArrayList;

public abstract class Zariadenie {
    abstract public void skontrolujDochodcov(ArrayList<Dochodca> dochodcovia);
    abstract void predstavSa();
    abstract boolean jeVzakazanejZone(Dochodca dochodca);
}
