package main;

import gui.StarobinecGUI;

import java.util.Timer;
import java.util.TimerTask;

public class Casovac {
    public static int i = 10;

    public static void spusti()
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(i == 0) {

                    i = 8;
                }
                //System.out.println("Cas: " + i);
                StarobinecGUI.vypisCas(i--);
            }
        }, 0, 1000);
    }
}
