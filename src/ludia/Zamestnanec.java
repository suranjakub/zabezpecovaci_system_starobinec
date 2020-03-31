package ludia;

public abstract class Zamestnanec {
    private String meno;
    private String typ;

    public Zamestnanec(String meno) {
        this.meno = meno;
    }

    public abstract void skontrRecepciu();
}
