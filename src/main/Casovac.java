package main;

import gui.StarobinecGUI;
import main.Starobinec;
import java.util.Timer;
import java.util.TimerTask;

import static main.Starobinec.*;

public class Casovac {
    public static int i = 10;

    public static void spusti()
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(i == 0) {
                    Starobinec.
                    i = 10;
                }
                //System.out.println("Cas: " + i);
                StarobinecGUI.vypisCas(i--);
            }
        }, 0, 1000);
    }
}
