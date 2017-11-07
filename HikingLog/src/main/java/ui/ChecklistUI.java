package ui;/*
 * Nicholas Perez
 * 11/6/2017
 * ChecklistUI.java
 *
 * DESCRIPTION
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.ChecklistModel;

import java.util.ArrayList;


/**
 * DESCRIPTION
 *
 * @author Nicholas Perez
 * @version 1.0
 **/
public class ChecklistUI {
    private static ChecklistModel checklistModel = new ChecklistModel();

    public static Scene checklist(Stage stage)
    {
        GridPane gridPane = TempleteUI.generateGridPane();
        ArrayList<String> checklist = checklistModel.getChecklist();

        VBox checklistVBox = new VBox();
        HBox topButtonBar = new HBox();
        BorderPane borderPane = new BorderPane();
        Button addItem = new Button("Add");
        Button removeItem = new Button("Remove");


        for (String item : checklist)
        {
            checklistVBox.getChildren().add(new CheckBox(item));
        }
        checklistVBox.getStyleClass().add("checklist");

        addItem.setOnAction(event ->
        {
            stage.setScene(addItem(stage));
        });

        removeItem.setOnAction(event ->
        {
            stage.setScene(removeItem(stage));
        });

        borderPane.setBottom(TempleteUI.homeBar(stage));

        topButtonBar.getChildren().addAll(addItem, removeItem);
        topButtonBar.getStyleClass().add("smallButtons");
        topButtonBar.setSpacing(35);
        topButtonBar.setAlignment(Pos.CENTER);

        borderPane.setCenter(checklistVBox);
        borderPane.setTop(topButtonBar);

        gridPane.add(borderPane, 0, 0, 4, 1);

        return new Scene(gridPane, 350, 650);
    }

    private static Scene addItem(Stage stage)
    {
        GridPane gridPane = TempleteUI.generateGridPane();

        return new Scene(gridPane, 350, 650);
    }

    private static Scene removeItem(Stage stage)
    {
        GridPane gridPane = TempleteUI.generateGridPane();

        return new Scene(gridPane, 350, 650);
    }
}
