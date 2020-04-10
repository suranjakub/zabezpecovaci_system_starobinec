package ludia;

import gui.StarobinecGUI;
import main.Starobinec;

import java.util.ArrayList;
import java.util.Timer;

public class Dochodca {
    private int x, y, id;
    private static boolean naplanovanyUtek = false;
    private String meno;
    private static int pocDochodcov = 0;
    private static ArrayList<Dochodca> dochodcovia = new ArrayList<>();
    private Starobinec starobinec;
    private static StarobinecGUI GUIko;
    private static final int cas = 15;
    private static Timer timer = null;
    private int pocUteceni;

    public Dochodca(Starobinec starobinec) {
        this.meno = "Jozko";
        this.x = getRandomNumberInRange(1, 100);
        this.y = getRandomNumberInRange(1, 100);
        this.id = pocDochodcov++;
        this.starobinec = starobinec;
        this.pocUteceni = 0;
        //this.ziskajGUI();
        /*if(!naplanovanyUtek)
            this.naplanujUtek();*/
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

    public void naplanujUtek() {
        if(!naplanovanyUtek)
            naplanovanyUtek = true;
        int cislo = getRandomNumberInRange(0, pocDochodcov-1);
        dochodcovia.get(cislo).utec();
        /*//randomne sa vyberie, ktory dochodca utecie
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //kazdych cas sekund random dochodca utecie
                int cislo = getRandomNumberInRange(0, pocDochodcov-1);
                dochodcovia.get(cislo).utec();
            }
        }, cas*1000, cas*1000);*/
    }

    public void ziskajGUI() {
        Starobinec starobinec = Starobinec.getInstance();
        GUIko = starobinec.getGUI();
    }

    private void utec() {
        //este nie je iste, ze utecie, nemusi sa dostat do zakazanej zony
        this.x = getRandomNumberInRange(0, 500); //neskorej sem daj 100 - 500
        if(x > 250)
            this.y = getRandomNumberInRange(250, 500); //neskorej sem daj 100 - 500
        else if(x < 250 && x > 150)
            this.y = getRandomNumberInRange(150, 250);
        else
            this.y = getRandomNumberInRange(0, 150);

        String sprava;
        sprava = "Dochodca "+this.id+" zmnenil poziciu na ["+this.x+","+this.y+"]";
        System.out.println(sprava);
        starobinec.vypisDoGUI(sprava);
    }

    public void vypniCasovac() {
        if (timer != null)
            timer.cancel();;
    }

    public int getPocUteceni() {
        return this.pocUteceni;
    }

    public void zvysPocUteceni() {
        this.pocUteceni++;
    }
}
