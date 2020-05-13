package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Starobinec;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StarobinecGUI extends Application {
    private Starobinec starobinec;
    private Button predstavZamestnancov = new Button("Predstav zamestnancov");
    private Button vytvorDochodcov = new Button("Vytvor dochodcov");
    private Button spustiZabezpecenie = new Button("Spusti zabezpecovaci system");
    private Button nacitajVstup = new Button("Nacitaj vstup");
    private TextField dochodci = new TextField();
    private Label dochodciOzn = new Label("Dochodci");
    private TextArea vypis = new TextArea();
    private ScrollPane skrol = new ScrollPane();
    private Text casovacText = new Text("Zabezpecovaci system nespusteny");
    private Text textUdrzbaKamier = new Text("Udrzba kamier");
    private Button udrzbaKamierTl = new Button("Zapnut udrzbu kamier");
    private Text textUdrzbaSenzorov = new Text("Udrzba senzorov");
    private Button udrzbaSenzorovTl = new Button("Zapnut udrzbu senzorov");
    private HBox hbox = new HBox();
    HBox hbox2 = new HBox();
    Separator separator = new Separator(Orientation.HORIZONTAL);

    @Override
    public void start(Stage hlavneOkno) throws Exception {
        hlavneOkno.setTitle("Starobinec");

        FlowPane pane = new FlowPane();
        pane.setHgap(10);
        pane.setVgap(10);

        starobinec = new Starobinec();
        starobinec.setGUI(this);

        pane.getChildren().add(nacitajVstup);
        pane.getChildren().add(predstavZamestnancov);
        pane.getChildren().add(dochodciOzn);
        pane.getChildren().add(dochodci);
        pane.getChildren().add(vypis);
        pane.getChildren().add(vytvorDochodcov);
        pane.getChildren().add(spustiZabezpecenie);
        pane.getChildren().add(casovacText);
        pane.getChildren().add(separator);
        pane.getChildren().add(hbox);
        pane.getChildren().add(hbox2);

        separator.setMinWidth(400);

        //Udrzba kamier prvky
        hbox.getChildren().add(textUdrzbaKamier);
        hbox.getChildren().add(udrzbaKamierTl);

        //Udrzba senzorov prvky
        hbox2.getChildren().add(textUdrzbaSenzorov);
        hbox2.getChildren().add(udrzbaSenzorovTl);

        hbox.setSpacing(10);
        hbox2.setSpacing(10);
        /*hbox.setPadding(new Insets(0, 5, 0, 0));
        hbox2.setPadding(new Insets(0, 5, 0, 0));*/

        skrol.setContent(pane);
        skrol.fitToHeightProperty().set(true);
        skrol.fitToWidthProperty().set(true);

        nacitajVstup.setOnAction(e -> {
            ObjectInputStream input = null;
            try {
                input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("input.txt"))));
                //load starobinec object from file
                starobinec = (Starobinec)input.readObject();
                starobinec.setGUI(this);

                if (starobinec.getPocDochodcovia() > 0) {
                    starobinec.getDochodcovia().get(0).setArraylist(starobinec.getDochodcovia());
                    starobinec.getDochodcovia().get(0).naplanujUtek();
                    System.out.println(starobinec.getPocDochodcovia());
                    Alert hlaska = new Alert(Alert.AlertType.INFORMATION);
                    hlaska.setHeaderText("Uspesne nacitanie");
                    hlaska.setContentText("Uspesne nacitane zo suboru");
                    hlaska.showAndWait();

                    //text do GUI vypisu
                    vypis.appendText("Uspesne nacitane zo suboru\n");
                    vypis.appendText(starobinec.predstavZamestnancov());
                    vypis.appendText(starobinec.predstavDochodcov());
                }
                else {
                    Alert upozornenie = new Alert(Alert.AlertType.ERROR);
                    upozornenie.setHeaderText("Chyba pri nacitavani!");
                    upozornenie.setContentText("V nacitanom subore neboli dochodcovia, treba ich vytvorit teraz!");
                    upozornenie.showAndWait();
                }
            } catch (IOException | ClassNotFoundException ex) {
                starobinec = new Starobinec();
                starobinec.setGUI(this);
                Alert upozornenie = new Alert(Alert.AlertType.ERROR);
                upozornenie.setHeaderText("Chyba pri nacitavani!");
                upozornenie.setContentText("Neexistuje vstupny subor, treba vytvorit dochodcov!");
                upozornenie.showAndWait();
                /*System.out.println("Spravilo sa??");
                System.out.println("Input: "+input);*/
            }
            finally {
                try {
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException ex) {
                    //ex.printStackTrace();
                }
            }
        });

        predstavZamestnancov.setOnAction(e -> {
                vypis.appendText(starobinec.predstavZamestnancov());
            }
        );

        vytvorDochodcov.setOnAction(e -> {
                starobinec.vytvorDochodcov(Integer.parseInt(dochodci.getText()));
                vypis.appendText(starobinec.predstavDochodcov());
            }
        );

        spustiZabezpecenie.setOnAction(e -> {
                starobinec.spustiZabezpecenie();
            }
        );

        udrzbaKamierTl.setOnAction(e -> {
            udrzbaKamierTl.setText(starobinec.udrzbaKamier(udrzbaKamierTl.getText()));
        });

        udrzbaSenzorovTl.setOnAction(e -> {
            udrzbaSenzorovTl.setText(starobinec.udrzbaSenzorov(udrzbaSenzorovTl.getText()));
        });

        hlavneOkno.setScene(new Scene(skrol, 500, 300));
        hlavneOkno.show();
    }

    @Override
    public void stop() throws Exception {
        ObjectOutputStream input = null;
        try {
            input = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("input.txt"))));
            input.writeObject(starobinec);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                input.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        super.stop();
        starobinec.vypniSa();
        System.out.println("Aplikacia bola vypnuta so vsetkymi procesmi");
    }

    public void vypisCas(int cas) {
        casovacText.setText("Kontrola zabezpecenia o " + cas + "s");
    }

    public void vypis(String s) {
        s += System.lineSeparator();
        vypis.appendText(s);
    }
}
