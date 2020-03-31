package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import main.Starobinec;

public class StarobinecGUI extends Application {
    private Button vytvorZamestnancov = new Button("Vytvor zamestnancov");
    private TextArea vypis = new TextArea();
    private ScrollPane skrol = new ScrollPane();

    @Override
    public void start(Stage hlavneOkno) throws Exception {
        hlavneOkno.setTitle("Starobinec");

        FlowPane pane = new FlowPane();
        Starobinec starobinec = new Starobinec();

        pane.getChildren().add(vytvorZamestnancov);
        pane.getChildren().add(vypis);

        skrol.setContent(pane);

        vytvorZamestnancov.setOnAction(e -> {
                starobinec.vytvorZamestnancov();
                vypis.appendText(starobinec.predstavZamestnancov());
            }
        );

        hlavneOkno.setScene(new Scene(skrol, 500, 300));
        hlavneOkno.show();
    }
}
