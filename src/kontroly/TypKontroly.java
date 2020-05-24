package kontroly;

import ludia.Dochodca;

/**
 * Interface, ktory bol vytvoreny za ucelom
 * uplatnenia navrhoveho vzoru Strategy.
 */
public interface TypKontroly {
    boolean skontroluj(Dochodca dochodca);
}
