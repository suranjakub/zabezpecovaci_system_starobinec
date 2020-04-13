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
        String newLine = System.getProperty("line.separator");
        String s = "Predstaveny " + this.typ + " " + this.meno + newLine;
        return s;
    }
}
