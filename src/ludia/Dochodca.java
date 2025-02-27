package ludia;

import gui.StarobinecGUI;
import main.Starobinec;

import javax.print.Doc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class Dochodca implements Serializable {
    private int x, y, id;
    private transient static boolean naplanovanyUtek = false;
    private String meno;
    private static int pocDochodcov = 0;
    private static ArrayList<Dochodca> dochodcovia = new ArrayList<>();
    private Starobinec starobinec;
    //private static StarobinecGUI GUIko;
    private static final int cas = 10;
    private transient static PlanovacUteku timer = null;
    private int pocUteceni;

    public Dochodca(Starobinec starobinec) {
        this.meno = "Jozko";
        this.x = getRandomNumberInRange(1, 100);
        this.y = getRandomNumberInRange(1, 100);
        this.id = 1 + dochodcovia.size();
        this.starobinec = starobinec;
        this.pocUteceni = 0;
        /*if(!naplanovanyUtek)
            this.naplanujUtek();*/
    }

    static class PlanovacUteku implements Runnable {
        private Thread planovac;
        private final AtomicBoolean running = new AtomicBoolean(false);
        private int interval;

        public PlanovacUteku(int sleepInterval) {
            interval = sleepInterval;
        }

        public void start() {
            planovac = new Thread(this);
            planovac.start();
        }

        public void stop() {
            running.set(false);
        }

        @Override
        public void run() {
            running.set(true);
            while (running.get()) {
                try {
                    Thread.sleep(interval*1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Vlakno bolo prerusene, nie je mozne dokoncit operaciu");
                }
                //kod planovaca
                if (!naplanovanyUtek && dochodcovia.size() > 0) {
                    naplanovanyUtek = true;
                    //kazdych cas sekund random dochodca utecie
                    int cislo = getRandomNumberInRange(0, dochodcovia.size()-1);
                    //System.out.println(pocDochodcov);
                    System.out.println("Chysta sa utiect index "+cislo);
                    dochodcovia.get(cislo).utec();
                }
            }
        }
    }

    private static int getRandomNumberInRange(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    public String predstavSa() {
        String newLine = System.getProperty("line.separator");
        String s = "Prijaty dochodca " + this.meno + " " + this.id;
        s += ", izbu ma na [" + this.x + "," + this.y + "]" + newLine;
        return s;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int getId() { return this.id; }

    public ArrayList<Dochodca> getDochodcovia() {
        return dochodcovia;
    }

    public void setArraylist(ArrayList<Dochodca> arrayList) {
        dochodcovia = arrayList;
    }

    public void naplanujUtek() {
        if (timer == null) {
            timer = new PlanovacUteku(cas);
            timer.start();
        }
    }

    private void prejdiDoZakazanejZony() {
        this.x = getRandomNumberInRange(150, 500);
        if(x > 250)
            this.y = getRandomNumberInRange(250, 500);
        else if(x < 250 && x > 150)
            this.y = getRandomNumberInRange(150, 250);
        else
            this.y = getRandomNumberInRange(0, 150);

        String sprava;
        sprava = "Dochodca "+this.id+" zmnenil poziciu na ["+this.x+","+this.y+"]";
        System.out.println(sprava);
        starobinec.vypisDoGUI(sprava);
    }

    private void utec() {
        //este nie je iste, ze utecie, nemusi sa dostat do zakazanej zony
        prejdiDoZakazanejZony();
    }

    public void vypniCasovac() {
        if (timer != null)
            timer.stop();
    }

    public int getPocUteceni() {
        return this.pocUteceni;
    }

    public void zvysPocUteceni() {
        this.pocUteceni++;
    }

    public void reagujNaPokarhanie() {
        if(Math.random() < 0.35) {
            // rozmyslel som si to, uz nebudem utekat
            // dochodca prejde do povolenej zony
            this.x = getRandomNumberInRange(0, 150);
            this.y = getRandomNumberInRange(0, 150);
            starobinec.vypisDoGUI("Dochodca "+this.id+" sa vratil do povolenej zony");
            zrusUtek();
        }
        else {
            prejdiDoZakazanejZony();
        }
    }

    public void zrusUtek() {
        naplanovanyUtek = false;
        System.out.println("Utek dochodcu "+this.id+" bol zruseny");
    }
}
