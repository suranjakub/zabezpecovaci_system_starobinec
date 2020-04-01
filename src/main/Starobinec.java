package main;

import gui.StarobinecGUI;
import ludia.Dochodca;
import ludia.Manazer;
import ludia.Recepcny;
import ludia.Zamestnanec;
import zariadenia.Alarm;
import zariadenia.Kamera;
import zariadenia.Senzor;
import zariadenia.Zariadenie;

import java.util.ArrayList;
import java.util.List;

public class Starobinec {
    private ArrayList<Zamestnanec> zamestnanci = new ArrayList<>();
    private ArrayList<Dochodca> dochodcovia = new ArrayList<>();
    private ArrayList<Zariadenie> zariadenia;

    public Starobinec() {
        vytvorZariadenia();
    }

    private void vytvorZariadenia() {
        zariadenia = new ArrayList<>();
        zariadenia.add(new Kamera());
        zariadenia.add(new Senzor());
        zariadenia.add(new Alarm());
    }

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

    public void spustiZabezpecenie() {
        Casovac.spusti();
    }

    public void vykonajKontrolu() {
        for (int i = 0; i < zariadenia.size(); ++i) {
            zariadenia.get(i).
        }
        StarobinecGUI.skontrolovane();
    }
}
