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

    public void setName(String meno) {
        this.meno = meno;
    }

    public int getId() {
        return this.id;
    }
}
