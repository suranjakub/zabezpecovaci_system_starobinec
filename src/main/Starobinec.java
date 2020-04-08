package main;

import gui.StarobinecGUI;
import ludia.Dochodca;
import ludia.Manazer;
import ludia.Recepcia;
import ludia.Zamestnanec;
import zariadenia.Kamera;
import zariadenia.Senzor;
import zariadenia.Zariadenie;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Starobinec {
    private static Starobinec starobinec = null;
    private ArrayList<Zamestnanec> zamestnanci = new ArrayList<>();
    private ArrayList<Dochodca> dochodcovia = null;
    private ArrayList<Dochodca> dbUtecencov = null;
    private ArrayList<Zariadenie> zariadenia = new ArrayList<>();
    private int cas = 0;
    private StarobinecGUI GUIko;
    private Timer timer = null;

    private Starobinec() {
        vytvorZariadenia();
    }

    public static Starobinec getInstance() {
        if(starobinec == null)
            starobinec = new Starobinec();
        return starobinec;
    }

    public void setGUI(StarobinecGUI referencia) {
        this.GUIko = referencia;
    }

    public StarobinecGUI getGUI() {
        return this.GUIko;
    }

    private void vytvorZariadenia() {
        zariadenia.add(new Kamera());
        zariadenia.add(new Senzor());
    }

    public void vytvorZamestnancov() {
        zamestnanci.add(new Recepcia());
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
        dochodca1.naplanujUtek();
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
                synchronized (Starobinec.class) {
                    GUIko.vypisCas(starobinec.cas);
                    if(starobinec.cas == 0) {
                        vykonajKontrolu();
                        //System.out.println("Vykonavam kontrolu");
                        //reset timer
                        starobinec.cas = 10;
                    }
                    //System.out.println("Cas: " + i);
                    starobinec.cas--;
                }
            }
        }, 1000, 1000);
    }

    public void vypniSa() {
        if (timer != null)
            timer.cancel();
        if (dochodcovia != null)
            dochodcovia.get(0).vypniCasovac();
    }

    public void vykonajKontrolu() {
        new Kamera().skontrolujDochodcov(dochodcovia);
        /*for (int j = 0; j < zariadenia.size(); ++j)
            zariadenia.get(j).skontrolujDochodcov(dochodcovia);*/
        //String s = "Kamery a senzory skontrolovane";
        //GUIko.vypis(s);
    }

    public ArrayList<Dochodca> getDB() {
        return dbUtecencov;
    }

    public void pridajDoDB(Dochodca dochodca) {
        dbUtecencov.add(dochodca);
        System.out.println("Dochodca "+dochodca.getId()+" pridany do databazy");
    }

    public void vypisDoGUI(String s) {
        GUIko.vypis(s);
    }
}
