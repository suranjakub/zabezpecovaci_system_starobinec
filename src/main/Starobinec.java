package main;

import ludia.Dochodca;
import ludia.Manazer;
import ludia.Recepcny;
import ludia.Zamestnanec;

import java.util.ArrayList;
import java.util.List;

public class Starobinec {
    private ArrayList<Zamestnanec> zamestnanci = new ArrayList<>();
    private ArrayList<Dochodca> dochodcovia = new ArrayList<>();

    public void vytvorZamestnancov() {
        zamestnanci.add(new Recepcny());
        zamestnanci.add(new Manazer());
    }

    public String predstavZamestnancov() {
        String sprava = "";
        for (int i = 0; i < zamestnanci.size(); ++i) {
            sprava += zamestnanci.get(i).predstavSa();
        }
        return sprava;
    }

    public void vytvorDochodcov(int pocet) {
        for (int i = 0; i < pocet; ++i)
            dochodcovia.add(new Dochodca());
    }

    public String predstavDochodcov() {
        String sprava = "";
        for (int i = 0; i < dochodcovia.size(); ++i) {
            sprava += dochodcovia.get(i).predstavSa();
        }
        return sprava;
    }
}
