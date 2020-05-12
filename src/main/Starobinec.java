package main;

import gui.StarobinecGUI;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import ludia.Dochodca;
import ludia.Manazer;
import ludia.Recepcny;
import ludia.Zamestnanec;
import zariadenia.Kamera;
import zariadenia.Senzor;
import zariadenia.Zariadenie;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Starobinec implements Serializable {
    private ArrayList<Zamestnanec> zamestnanci = new ArrayList<>();
    private ArrayList<Dochodca> dochodcovia = null;
    private ArrayList<Dochodca> dbUtecencov = new ArrayList<>();
    private ArrayList<Zariadenie> zariadenia = new ArrayList<>();
    private int cas = 0;
    private transient StarobinecGUI GUIko; //neulozi sa to do suboru, ked sa uklada starobinec
    private transient Timer timer = null;
    private Recepcny recepcny;
    private Manazer manazer;

    public Starobinec() {
        vytvorSa();
    }

    public int getPocDochodcovia() {
        if(dochodcovia == null)
            return 0;
        return dochodcovia.size();
    }

    public ArrayList<Dochodca> getDochodcovia () {
        return this.dochodcovia;
    }

    public void setGUI(StarobinecGUI referencia) {
        this.GUIko = referencia;
    }

    private void vytvorSa() {
        manazer = new Manazer(this);
        recepcny = new Recepcny(this, manazer);
        zamestnanci.add(recepcny);
        zamestnanci.add(manazer);

        zariadenia.add(new Kamera(this, recepcny));
        zariadenia.add(new Senzor(this, recepcny));
    }

    public String predstavZamestnancov() {
        String sprava = "";
        for (int i = 0; i < zamestnanci.size(); ++i) {
            sprava += zamestnanci.get(i).predstavSa();
        }
        return sprava;
    }

    public void vytvorDochodcov(int pocet) {
        Dochodca dochodca1 = new Dochodca(this);
        dochodcovia = dochodca1.getDochodcovia();
        dochodcovia.add(dochodca1);
        for (int i = 1; i < pocet; ++i)
            dochodcovia.add(new Dochodca(this));
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
        String s = "\nZabezpecovaci system aktivovany";
        vypisDoGUI(s);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (Starobinec.class) {
                    //System.out.println(GUIko);
                    GUIko.vypisCas(cas);
                    if(cas == 0) {
                        try {
                            vykonajKontrolu();
                        } catch (NonDochodcaException e) {
                            timer.cancel();
                            Platform.runLater(() -> {
                                Alert chyba = new Alert(Alert.AlertType.ERROR);
                                chyba.setContentText(e.getMessage());
                                chyba.showAndWait();
                            });
                        }
                        //System.out.println("Vykonavam kontrolu");
                        //reset timer
                        cas = 15;
                    }
                    //System.out.println("Cas: " + i);
                    cas--;
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

    public void vykonajKontrolu() throws NonDochodcaException {
        //new Kamera(starobinec, recepcny).skontrolujDochodcov(dochodcovia);
        //new Senzor(starobinec, recepcny).skontrolujDochodcov(dochodcovia);
        for (int j = 0; j < zariadenia.size(); ++j)
            zariadenia.get(j).skontrolujDochodcov(dochodcovia);
        //String s = "Kamery a senzory skontrolovane";
        //GUIko.vypis(s);
    }

    public ArrayList<Dochodca> getDB() {
        return dbUtecencov;
    }

    public void pridajDoDB(Dochodca dochodca) {
        if (dbUtecencov == null)
            dbUtecencov = new ArrayList<>();
        dbUtecencov.add(dochodca);
        System.out.println("Dochodca "+dochodca.getId()+" pridany do databazy");
    }

    public void vypisDoGUI(String s) {
        GUIko.vypis(s);
    }

    public void zmazDochodcu(Dochodca dochodca) {
        dochodcovia.remove(dochodca);
    }
}
