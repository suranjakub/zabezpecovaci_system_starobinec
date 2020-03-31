package ludia;

public class Dochodca {
    private int x, y, id;
    private String meno;
    private static int pocDochodcov = 0;

    public Dochodca() {
        this.meno = "Jozko";
        this.x = getRandomNumberInRange(1, 200);
        this.y = getRandomNumberInRange(1, 200);
        this.id = ++pocDochodcov;
    }

    private static int getRandomNumberInRange(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    public String predstavSa() {
        String newLine = System.getProperty("line.separator");
        String s = "Prijaty dochodca " + this.meno + " " + this.id;
        s += ", izbu ma na x:" + this.x + " y:" + this.y + newLine;
        return s;
    }
}
