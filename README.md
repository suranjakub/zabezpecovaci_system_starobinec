# Starobinec - zabezpečovací systém

Tento projekt reprezentuje simuláciu zabezpečovacieho systému pozostávajúceho z kamier a senzorov v starobinci, ktoré v pravidelných intervaloch (15 sekúnd) kontrolujú, či dôchodcovia neutiekli zo zón pre nich určených. Zóny sú reprezentované ako hranice X a Y. Zóna určená pre dôchodcov je v rozmedzí 0-150 pre X aj Y. Ak sa dôchodca pohybuje za týmito hranicami, zachytia ich senzory (v rozmedzí 150 - 250) a kamery (v rozmedzí 250 - 500). Dôchodci si pravidelne striedajú rolu "utečenca" a to konkrétne v intervale 12 sekúnd. 

## Spustenie v Eclipse

1. Naimportovať projekt.
2. File > Properties > Java Build Path > Libraries > Modulepath > Add External JARs .. zvolíme všetky *.jar súbory nachádzajúce sa v "oop-2020-project\javafx-sdk-14\lib", kliknúť na apply.
3. Run > Run Configurations > Main > Arguments > VM arguments, sem pridáme nasledujúce:

```bash
--module-path "\path\to\javafx-sdk-14\lib" --add-modules javafx.controls,javafx.fxml
```
4. Stlačíme apply a všetko je prichystané na spustenie projektu - spúšťacia/hlavná trieda je main/Main.class

## Uplatnenie OOP princípov
### Polymorfizmus
v zariadenia/Kamera.java line 25-80 sa nasledovná metóda:

```java
public void skontrolujDochodcov(ArrayList<Dochodca> dochodcovia)
```
vykonáva inak ako rovnomenná metóda v zariadenia/Senzor.java line 23-55

### Dedenie
Prvú hierarchiu dedenia predstavujú triedy v balíku "ludia", kedy od abstraktnej triedy "Zamestnanec" dedí "Recepcny" a "Zamestnanec"

Druhá hierarchia: od abstraktnej triedy "Zariadenie" dedí "Kamera" a "Senzor"

### Agregácia / Kompozícia
najmä v triede "Starobinec" line 17-26:

```java
private static Starobinec starobinec = null;
    private ArrayList<Zamestnanec> zamestnanci = new ArrayList<>();
    private ArrayList<Dochodca> dochodcovia = null;
    private ArrayList<Dochodca> dbUtecencov = new ArrayList<>();
    private ArrayList<Zariadenie> zariadenia = new ArrayList<>();
    private int cas = 0;
    private StarobinecGUI GUIko;
    private Timer timer = null;
    private Recepcny recepcny;
    private Manazer manazer;
```
### Oddelenie aplikačnej logiky od view
realizuje sa v triede "gui/Starobinec.class", napr. pri tlačidle "Vytvor dochôdcov", kde sa na OnActionEvent vykoná funkcia, pričom view nerieši ako sa vykonáva a nezasahuje do logiky

```java
spustiZabezpecenie.setOnAction(e -> {
        starobinec.spustiZabezpecenie();
    }
);
```

## Ovládanie programu
Pri spustení programu máme k dispozícii tlačidlá na ovládanie programu. 
- Tlačidlo `Predstav zamestnancov` slúži len na zobrazenie zamestnancov. 
- Do textového poľa `Dôchodci` je potrebné zadať počet dôchodcov na vytvorenie, odporúčam cca 8, aby nebol príliš zahltený výpis, následne treba kliknúť na `Vytvoriť dôchodcov` Po vytvorení dôchodcov po stanovenom intervale (12s) začnú dôchodcovia "utekať" (pre zjednodušenie čitateľnosti výpisu uteká vždy len jeden dôchodca).
- Následne je potrebné kliknúť `Spusti zabezpecovaci system`, od teraz sa bude každých 15s kontrolovať zabezpečenie - kamery a senzory.
- Odteraz je program bezobslužný, všetky potrebné výpisy budú zobrazené.


## Autor projektu
Copyright &copy; 2020 Jakub Šuran