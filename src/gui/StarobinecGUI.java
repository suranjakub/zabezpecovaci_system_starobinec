package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Starobinec;

public class StarobinecGUI extends Application {
    private static StarobinecGUI instancia;
    private Button vytvorZamestnancov = new Button("Vytvor zamestnancov");
    private Button vytvorDochodcov = new Button("Vytvor dochodcov");
    private Button spustiZabezpecenie = new Button("Spusti zabezpecovaci system");
    private TextField dochodci = new TextField();
    private Label dochodciOzn = new Label("Dochodci");
    private TextArea vypis = new TextArea();
    private ScrollPane skrol = new ScrollPane();
    private Text casovacText = new Text("Zabezpecovaci system nespusteny");

    public StarobinecGUI() {
        instancia = this;
    }

    public synchronized static StarobinecGUI getInstance() {
        /*if(!instancia) {
            Thread.start {
                // Have to run in a thread because launch doesn't return
                Application.launch(MyClass.class);
            }
            while(!instance)
                Thread.sleep(100);
        }*/
        return instancia;
    }

    @Override
    public void start(Stage hlavneOkno) throws Exception {
        hlavneOkno.setTitle("Starobinec");

        FlowPane pane = new FlowPane();
        Starobinec starobinec = new Starobinec();
        starobinec.ziskajGUI(instancia);

        pane.getChildren().add(vytvorZamestnancov);
        pane.getChildren().add(dochodciOzn);
        pane.getChildren().add(dochodci);
        pane.getChildren().add(vypis);
        pane.getChildren().add(vytvorDochodcov);
        pane.getChildren().add(spustiZabezpecenie);
        pane.getChildren().add(casovacText);

        skrol.setContent(pane);

        vytvorZamestnancov.setOnAction(e -> {
                starobinec.vytvorZamestnancov();
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
                String s = "Zabecovaci system aktivovany" + System.lineSeparator();
                vypis.appendText(s);
            }
        );

        hlavneOkno.setScene(new Scene(skrol, 500, 300));
        hlavneOkno.show();
    }

    public void vypisCas(int cas) {
        casovacText.setText("Kontrola zabezpecenia o " + cas + "s");
    }

    public void skontrolovane() {
        String s = "Kamery a senzory skontrolovane" + System.lineSeparator();
        vypis.appendText(s);
    }
}
