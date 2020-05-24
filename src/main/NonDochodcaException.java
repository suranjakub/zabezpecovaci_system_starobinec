package main;

/**
 * Vlastna trieda, ktora je pouzivana na vyhadzovanie vlastnej vynimky,
 * ked nastane situacia, ze neexistuju dochodcovia
 */
public class NonDochodcaException extends Exception {
    public NonDochodcaException(String s) {
        super(s);
    }
}
