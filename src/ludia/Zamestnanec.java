package ludia;

public abstract class Zamestnanec {
    protected String meno;
    protected String typ;

    protected Zamestnanec(String meno, String typ) {
        this.meno = meno;
        this.typ = typ;
    }

    public String getTyp() {
        return this.typ;
    }
    public String predstavSa() {
        return this.meno + this.typ;
    }
    public abstract void skontrRecepciu();
}
