package gui;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import main.Starobinec;

public class StarobinecGUI extends Application {
    private Button vytvorZamestnancov = new Button("Vytvor zamestnancov");
    private TextArea vypis = new TextArea();

    @Override
    public void start(Stage hlavneOkno) throws Exception {
        hlavneOkno.setTitle("main.Starobinec");

        FlowPane pane = new FlowPane();
        Starobinec starobinec = new Starobinec();

        pane.getChildren().add(vytvorZamestnancov);
        pane.getChildren().add(vypis);

        vytvorZamestnancov.setOnAction(e -> {
                starobinec.vytvorZamestnancov();
                vypis.appendText("Vytvoreny zamestnanci");
            }
        );

        hlavneOkno.show();
    }
}
