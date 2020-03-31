package main;

import ludia.Manazer;
import ludia.Recepcny;
import ludia.Zamestnanec;

import java.util.ArrayList;
import java.util.List;

public class Starobinec {
    private List<Zamestnanec> zamestnanci = new ArrayList<>();

    public void vytvorZamestnancov() {
        zamestnanci.add(new Recepcny());
        zamestnanci.add(new Manazer());
    }
}
