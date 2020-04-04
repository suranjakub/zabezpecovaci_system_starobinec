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
    private ArrayList<Dochodca> dochodcovia;
    private ArrayList<Zariadenie> zariadenia = new ArrayList<>();
    private int cas = 0;
    private StarobinecGUI GUIko;
    private Timer timer;
    private Starobinec hlavnyStarobinec;

    public Starobinec() {
        hlavnyStarobinec = this;
        vytvorZariadenia();
    }

    public void ziskajGUI(StarobinecGUI referencia) {
        this.GUIko = referencia;
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
        Dochodca dochodca1 = new Dochodca();
        dochodcovia = dochodca1.getDochodcovia();
        dochodcovia.add(dochodca1);
        for (int i = 1; i < pocet; ++i)
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
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                GUIko.vypisCas(hlavnyStarobinec.cas);
                if(hlavnyStarobinec.cas == 0) {
                    vykonajKontrolu();
                    //reset timer
                    hlavnyStarobinec.cas = 10;
                }
                //System.out.println("Cas: " + i);
                hlavnyStarobinec.cas--;
            }
        }, 1000, 1000);
    }

    public void vypniSa() {
        timer.purge();
        timer.cancel();
    }

    public void vykonajKontrolu() {
        for (int j = 0; j < zariadenia.size(); ++j) {
            zariadenia.get(j).skontrolujDochodcov();
        }
        String s = "Kamery a senzory skontrolovane" + System.lineSeparator();
        GUIko.vypis(s);
    }
}
