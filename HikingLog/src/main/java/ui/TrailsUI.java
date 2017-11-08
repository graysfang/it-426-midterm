package ui;/**
 * Created by Hillary on 11/2/2017.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.TrailModel;
import models.TrailsModel;
import models.UtilitiesIO;

public class TrailsUI {

    final private static int ROW_HEIGHT = 28;
    private static final int LIST_ROW_PADDING = 2;
    private static TrailsModel trailsModel = new TrailsModel();
    private static ObservableList trailNames = FXCollections.observableArrayList();

    public static Scene trails(Stage stage)
    {
        GridPane gridPane = TempleteUI.generateGridPane();
        gridPane.getStyleClass().add("smallButtons");

        populateTrailNames();

        HBox addTrailBox = new HBox();
        addTrailBox.setSpacing(10);
        addTrailBox.setPadding(new Insets(0,0,10,0));
        addTrailBox.getStyleClass().add("trails");

        Label addTrailLabel = new Label("Add Trail: ");
        TextField addTrailField = new TextField();
        Button addTrail = new Button("Add Trail");
        addTrailBox.getChildren().addAll(addTrailLabel, addTrailField);

        HBox addRemoveButtons = new HBox();
        addRemoveButtons.setSpacing(5);

        VBox trailsList = new VBox();
        trailsList.setPadding(new Insets(10, 0, 0, 0));

        ListView trails = new ListView(trailNames);
//        trails.getStyleClass().add("listView");
        listPrefHeight(trails);
        trails.setMaxWidth(225);
        trailsList.getChildren().add(trails);


        Button removeTrail = new Button("Remove Trail");
        addRemoveButtons.getChildren().addAll(addTrail, removeTrail, TempleteUI.homeBar(stage));

        gridPane.add(addTrailBox, 0, 1, 3, 1);
        gridPane.add(addRemoveButtons, 0, 3, 3, 1);
        gridPane.add(trailsList, 0, 5, 3, 1);

        trails.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click)
            {
                String selection = String.valueOf(trails.getSelectionModel().getSelectedItem()).trim();
                if (click.getClickCount() == 2 && !selection.equals("null"))
                {
                    //Use ListView's getSelected Item
                    trailsModel.setSelectedTrail(selection);
                    //use this to do whatever you want to. Open Link etc.
                    stage.setScene(TrailUI.trail(stage, trailsModel));
                }
            }
        });

        addTrail.setOnAction(event ->
        {
            trailsModel.addTrail(addTrailField.getText());
            addTrailField.clear();
            populateTrailNames();
            listPrefHeight(trails);
        });

        removeTrail.setOnAction(event ->
        {
            trailsModel.removeTrail((String) trails.getSelectionModel().getSelectedItem());
            populateTrailNames();
            listPrefHeight(trails);
        });

        return new Scene(gridPane, 350, 650);
    }

    private static void listPrefHeight(ListView list)
    {
        list.setPrefHeight(trailNames.size() * ROW_HEIGHT + LIST_ROW_PADDING);
    }

    private static void populateTrailNames()
    {
        trailNames.clear();
        trailNames.addAll(trailsModel.getTrails());
    }
}
