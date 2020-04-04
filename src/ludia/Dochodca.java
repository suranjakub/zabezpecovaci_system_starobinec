package ludia;

import java.util.ArrayList;

public class Dochodca {
    private int x, y, id;
    private String meno;
    private static int pocDochodcov = 0;
    private ArrayList<Dochodca> dochodcovia = new ArrayList<>();

    public Dochodca() {
        this.meno = "Jozko";
        this.x = getRandomNumberInRange(1, 100);
        this.y = getRandomNumberInRange(1, 100);
        this.id = ++pocDochodcov;
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
        //ArrayList<Dochodca> dochodcovia =
    }
}
