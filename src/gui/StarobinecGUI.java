package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import main.Starobinec;

public class StarobinecGUI extends Application {
    private Button vytvorZamestnancov = new Button("Vytvor zamestnancov");
    private Button vytvorDochodcov = new Button("Vytvor dochodcov");
    private TextField dochodci = new TextField();
    private Label dochodciOzn = new Label("Dochodci");
    private TextArea vypis = new TextArea();
    private ScrollPane skrol = new ScrollPane();

    @Override
    public void start(Stage hlavneOkno) throws Exception {
        hlavneOkno.setTitle("Starobinec");

        FlowPane pane = new FlowPane();
        Starobinec starobinec = new Starobinec();

        pane.getChildren().add(vytvorZamestnancov);
        pane.getChildren().add(dochodciOzn);
        pane.getChildren().add(dochodci);
        pane.getChildren().add(vypis);
        pane.getChildren().add(vytvorDochodcov);

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

        hlavneOkno.setScene(new Scene(skrol, 500, 300));
        hlavneOkno.show();
    }
}
