package main;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import gui.StarobinecGUI;
import javafx.application.Application;

/**
 * Trieda, ktora sa spusti ihned po starte programu.
 * Ma na starosti sputenie GUI triedy, ktora uz dalej
 * spusti vsetko potrebne.
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(StarobinecGUI.class, args);
        //StarobinecGUI GUIko = StarobinecGUI.getInstance();
    }
}
