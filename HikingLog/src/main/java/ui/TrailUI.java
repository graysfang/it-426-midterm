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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.TrailsModel;

/**
 * Created by Hillary on 11/4/2017.
 */
public class TrailUI {

    public static Scene trail(Stage stage, TrailsModel trailsModel)
    {
        GridPane gridPane = TempleteUI.generateGridPane();

        VBox healthDetailFields = new VBox();
        Label trailTitle = new Label(trailsModel.getSelectedTrail());
        Label stepCount = new Label("Step Count:");
        TextField stepCountField = new TextField();

        HBox stepsHistory = new HBox();
        Text steps = new Text(trailsModel.getStepCountHistory());
        steps.setWrappingWidth(200);


        stepCountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    stepCountField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Button submitStepsAndHeartRate = new Button("Submit");
        submitStepsAndHeartRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                trailsModel.addStepCount(stepCountField.getText());
            }
        });
        stepsHistory.getChildren().addAll(steps);
        stepsHistory.setPadding(new Insets(10));
        healthDetailFields.getChildren().addAll(trailTitle, stepCount, stepCountField);
        gridPane.getChildren().addAll(healthDetailFields, stepsHistory, submitStepsAndHeartRate);

        return new Scene(gridPane, 350, 650);
    }
}
