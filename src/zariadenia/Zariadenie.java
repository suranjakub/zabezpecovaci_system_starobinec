package zariadenia;

import ludia.Dochodca;
import main.NonDochodcaException;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Zariadenie implements Serializable {
    abstract public void skontrolujDochodcov(ArrayList<Dochodca> dochodcovia) throws NonDochodcaException;
    abstract void predstavSa();
    abstract boolean jeVzakazanejZone(Dochodca dochodca);
}
