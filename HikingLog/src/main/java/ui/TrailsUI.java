package ui;/**
 * Created by Hillary on 11/2/2017.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.TrailModel;
import models.TrailsModel;

public class TrailsUI {

    final private static int ROW_HEIGHT = 24;
    private static TrailsModel trailsModel = new TrailsModel();
    private static TrailModel trailModel = new TrailModel();
    private static ObservableList trailNames = FXCollections.observableArrayList();

    public static Scene trails(Stage stage)
    {
        GridPane gridPane = TempleteUI.generateGridPane();

        populateTrailNames();

        ListView trails = new ListView(trailNames);
        listPrefHeight(trails);
        trails.setMaxWidth(250);
        HBox addRemoveButtons = new HBox();

        HBox addTrailBox = new HBox();
        Label addTrailLabel = new Label("Add Trail: ");
        TextField addTrailField = new TextField();
        Button addTrail = new Button("Add Trail");
        addTrailBox.getChildren().addAll(addTrailLabel, addTrailField, addTrail);

        Button removeTrail = new Button("Remove Trail");

        addRemoveButtons.getChildren().addAll(addTrail, removeTrail, TempleteUI.homeBar(stage));

        gridPane.add(addTrailBox, 0, 1);
        gridPane.add(addRemoveButtons, 0, 3, 3, 1);
        gridPane.add(trails, 0, 5, 3, 1);

        trails.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 2)
                {
                    //Use ListView's getSelected Item
                    trailModel.setSelectedTrail(String.valueOf(trails.getSelectionModel().getSelectedItem()));
                    //use this to do whatever you want to. Open Link etc.
                    stage.setScene(TrailUI.trail(stage, trailModel));
                }
            }
        });

        addTrail.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                trailsModel.addTrail(addTrailField.getText());
                addTrailField.clear();
                populateTrailNames();
                listPrefHeight(trails);
            }
        });

        removeTrail.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                trailsModel.removeTrail((String) trails.getSelectionModel().getSelectedItem());
                populateTrailNames();
                listPrefHeight(trails);
            }
        });

        return new Scene(gridPane, 350, 650);
    }

    private static void listPrefHeight(ListView list)
    {
        list.setPrefHeight(trailNames.size() * ROW_HEIGHT + 2);
    }

    private static void populateTrailNames()
    {
        trailNames.clear();
        trailNames.addAll(trailsModel.getTrails());
    }
}
