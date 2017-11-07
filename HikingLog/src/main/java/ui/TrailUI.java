package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.TrailModel;

/**
 * Created by Hillary on 11/4/2017.
 */
public class TrailUI {

    public static Scene trail(Stage stage, TrailModel trailModel)
    {
        GridPane gridPane = TempleteUI.generateGridPane();


        Button submitStepsAndHeartRate = new Button("Submit");
        submitStepsAndHeartRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
//                trails.inputHeartRate();
            }
        });

        VBox healthDetailFields = new VBox();
        Label trailTitle = new Label(trailModel.getSelectedTrail());
        Label stepCount = new Label("Step Count:");
        TextField stepCountField = new TextField ();
        stepCountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    stepCountField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        healthDetailFields.getChildren().addAll(trailTitle, stepCount, stepCountField);
        gridPane.getChildren().addAll(healthDetailFields, submitStepsAndHeartRate);

        return new Scene(gridPane, 350, 650);
    }
}
