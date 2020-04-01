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
import java.util.Timer;
import java.util.TimerTask;

public class Starobinec {
    private ArrayList<Zamestnanec> zamestnanci = new ArrayList<>();
    private ArrayList<Dochodca> dochodcovia = new ArrayList<>();
    private ArrayList<Zariadenie> zariadenia = new ArrayList<>();
    private int i = 0;

    public Starobinec() {
        vytvorZariadenia();
    }

    private void vytvorZariadenia() {
        zariadenia.add(new Kamera());
        zariadenia.add(new Senzor());
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
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(i == 0) {
                    vykonajKontrolu();
                    //reset timer
                    i = 10;
                }
                //System.out.println("Cas: " + i);
                StarobinecGUI.vypisCas(i);
                i--;
            }
        }, 0, 1000);
    }

    public void vykonajKontrolu() {
        for (int j = 0; j < zariadenia.size(); ++j) {
            zariadenia.get(j).skontrolujDochodcov();
        }
        StarobinecGUI.skontrolovane();
    }
}
