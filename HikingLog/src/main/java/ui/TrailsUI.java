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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.TrailModel;
import models.TrailsModel;

public class TrailsUI {

    final private static int ROW_HEIGHT = 24;
    private static TrailsModel trailsModel = new TrailsModel();
    private static TrailModel trailModel = new TrailModel();
    final private static ObservableList trailNames = FXCollections.observableArrayList();
    static ListView trails;

    public static Scene trails(Stage stage)
    {

        GridPane gridPane = new GridPane();
        gridPane.getStylesheets().add("styles.css");

        gridPane.setPadding(new Insets(15, 25, 15, 25));

        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100));

        HBox addTrailBox = new HBox();
        Label addTrailLabel = new Label("Add Trail: ");
        TextField addTrailField = new TextField();
        Button addTrail = new Button("Add Trail");
        addTrailBox.getChildren().addAll(addTrailLabel, addTrailField, addTrail);


        Button removeTrail = new Button("Remove Trail");

        //temporary
        Button home = new Button("Home");
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                stage.setScene(HikeLogUI.assemble(stage));
            }
        });

        trails = new ListView();
        trails.setPrefHeight(trailsModel.getTrails().size() * ROW_HEIGHT + 2);
        refreshList();

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
                refreshList();

            }
        });

        HBox addRemoveButtons = new HBox();
        addRemoveButtons.getChildren().addAll(addTrail, removeTrail, home);

        gridPane.add(addTrailBox, 0, 1);
        gridPane.add(addRemoveButtons, 0, 3, 3, 1);
        gridPane.add(trails, 0, 5);

        return new Scene(gridPane, 350, 650);
    }

    private static void refreshList()
    {
        trailNames.clear();
        trailNames.addAll(FXCollections.observableArrayList(trailsModel.getTrails()));
        trails.setItems(trailNames);
    }

    private HBox section(String name)
    {

        return null;
    }
}
