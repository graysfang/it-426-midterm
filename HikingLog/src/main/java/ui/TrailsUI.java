package ui;/**
 * Created by Hillary on 11/2/2017.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TrailsUI {

    public static Scene trails(Stage stage) {

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(15, 25, 15, 25));

        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100));

        Button addTrail = new Button("Add Trail");
        Button removeTrail = new Button("Remove Trail");

        //temporary
        Button home = new Button("Home");

        HBox addRemoveButtons = new HBox();
        addRemoveButtons.getChildren().addAll(addTrail, removeTrail, home);

        gridPane.add(addRemoveButtons, 0, 3, 3, 1);

        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(HikeLogUI.assemble(stage));
            }
        });

        return new Scene(gridPane, 350, 650);
    }
}
