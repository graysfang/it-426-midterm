package ui;
/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 11/4/2017
 * TrailUI.java
 *
 * This class controls views for the trailUI.
 */
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.TrailsModel;

/**
 * This class controls views for the trailUI.
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 **/
public class TrailUI {

    private static final int FIELD_PREF_WIDTH = 90;
    private static final String STEPS_TAG_NAME = "steps";
    private static final String HEARTRATE_TAG_NAME = "heartrate";
    private static final String HEART_RATE_START = "Heart Rates: ";
    private static final String STEPS_START = "Steps: ";
    private static String stepHistory;
    private static String heartRatesHistory;
    private static Label avgSteps;
    private static Label avgHeartRate;
    private static String stepAverage;
    private static String heartRateAverage;

    /**
     * The trail scene
     *
     * @param stage the current stage
     * @param trailsModel model/controller object
     * @return the set scene
     */
    public static Scene trail(Stage stage, TrailsModel trailsModel)
    {

        GridPane gridPane = TempleteUI.generateGridPane();
        gridPane.getStyleClass().addAll("trailUI", "smallButtons");
        loadCountHistories(trailsModel);
        loadAverages(trailsModel);

        Button back = new Button("Back");
        Button submitStepsAndHeartRate = new Button("Submit");
        submitStepsAndHeartRate.setWrapText(true);

        VBox title = new VBox();
        title.setPadding(new Insets(10));
        Label trailTitle = new Label(trailsModel.getSelectedTrail());
        title.getChildren().add(trailTitle);

        HBox healthDetailFields = new HBox();
        healthDetailFields.setSpacing(5);
        healthDetailFields.setPadding(new Insets(10));

        VBox addSteps = new VBox();
        Label stepCount = new Label("Steps: ");
        TextField stepCountField = new TextField();
        stepCountField.setPrefWidth(FIELD_PREF_WIDTH);
        addSteps.getChildren().addAll(stepCount, stepCountField);

        VBox addHeartRate = new VBox();
        Label heartRate = new Label("Heart Rate: ");
        TextField heartRateField = new TextField();
        heartRateField.setPrefWidth(FIELD_PREF_WIDTH);
        addHeartRate.getChildren().addAll(heartRate, heartRateField);

        healthDetailFields.getChildren().addAll(addSteps, addHeartRate, submitStepsAndHeartRate);
        healthDetailFields.setAlignment(Pos.BOTTOM_LEFT);

        HBox averages = new HBox();
        averages.setSpacing(10);
        averages.setPadding(new Insets(10));
        avgSteps = new Label( );
        avgHeartRate = new Label( );



        HBox stepsHistory = new HBox();
        Text steps = new Text(stepHistory);
        steps.setWrappingWidth(200);
        steps.setFill(Color.valueOf("#F2F2F2"));
        stepsHistory.getChildren().addAll(steps);
        stepsHistory.setPadding(new Insets(10));

        HBox heartRateHistory = new HBox();
        Text heartRates = new Text(heartRatesHistory);
        heartRates.setWrappingWidth(200);
        heartRates.setFill(Color.valueOf("#F2F2F2"));
        heartRateHistory.getChildren().addAll(heartRates);
        heartRateHistory.setPadding(new Insets(10));

        gridPane.add(title, 0, 1, 2, 1);
        gridPane.add(back, 2, 1, 1, 1);
        gridPane.add(healthDetailFields, 0, 2, 3, 1);
        gridPane.add(stepsHistory, 0, 4, 4, 2);
        gridPane.add(heartRateHistory, 0, 6, 4, 2);


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
        heartRateField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    heartRateField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        submitStepsAndHeartRate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                if (!stepCountField.getText().isEmpty() || !heartRateField.getText().isEmpty())
                {
                    trailsModel.addCount(STEPS_TAG_NAME, stepCountField.getText());
                    trailsModel.addCount(HEARTRATE_TAG_NAME, heartRateField.getText());
                    loadCountHistories(trailsModel);
                    loadAverages(trailsModel);
                    stepCountField.clear();
                    heartRateField.clear();
                    steps.setText(stepHistory);
                    heartRates.setText(heartRatesHistory);
                }
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                trailsModel.clearSelectedTrailInformation();
                stage.setScene(TrailsUI.trails(stage));
            }
        });

        return new Scene(gridPane, 350, 500);
    }

    //loads the averages
    private static void loadAverages(TrailsModel trailsModel)
    {
        heartRateAverage = "Heart Rate Average: " +
                trailsModel.getAverage(trailsModel.getCountHistory(HEARTRATE_TAG_NAME));
        stepAverage = "Step Average: " + trailsModel.getAverage(trailsModel.getCountHistory(STEPS_TAG_NAME));
    }

    //loads all the counts
    private static void loadCountHistories(TrailsModel trailsModel)
    {

        stepHistory = STEPS_START + trailsModel.getCountHistory(STEPS_TAG_NAME);

        heartRatesHistory = HEART_RATE_START + trailsModel.getCountHistory(HEARTRATE_TAG_NAME);

    }
}
