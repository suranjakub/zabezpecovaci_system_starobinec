package ludia;

import gui.StarobinecGUI;
import main.Starobinec;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Dochodca {
    private int x, y, id;
    private static boolean naplanovanyUtek = false;
    private String meno;
    private static int pocDochodcov = 0;
    private static ArrayList<Dochodca> dochodcovia = new ArrayList<>();
    private static StarobinecGUI GUIko;
    private static final int cas = 15;
    private static Timer timer = null;

    public Dochodca() {
        this.meno = "Jozko";
        this.x = getRandomNumberInRange(1, 100);
        this.y = getRandomNumberInRange(1, 100);
        this.id = pocDochodcov++;
        this.ziskajGUI();
        if(!naplanovanyUtek)
            this.naplanujUtek();
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

    public ArrayList<Dochodca> getDochodcovia() {
        return dochodcovia;
    }

    public void naplanujUtek() {
        if(!naplanovanyUtek)
            naplanovanyUtek = true;
        //randomne sa vyberie, ktory dochodca utecie
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //kazdych cas sekund random dochodca utecie
                int cislo = getRandomNumberInRange(0, pocDochodcov-1);
                dochodcovia.get(cislo).utec();
            }
        }, cas*1000, cas*1000);
    }

    public void ziskajGUI() {
        Starobinec starobinec = Starobinec.getInstance();
        GUIko = starobinec.getGUI();
    }

    private void utec() {
        this.x = getRandomNumberInRange(100, 500);
        this.y = getRandomNumberInRange(100, 500);

        String sprava;
        sprava = "Dochodca "+this.id+" zmnenil poziciu na ["+this.x+","+this.y+"]";
        System.out.println(sprava);
        GUIko.vypis(sprava);
    }

    public void vypniCasovac() {
        if (timer != null)
            timer.cancel();;
    }
}
