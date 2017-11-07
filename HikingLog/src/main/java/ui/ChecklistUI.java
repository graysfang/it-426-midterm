package ui;
/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 11/6/2017
 * ChecklistUI.java
 *
 * This class controls views for the checklist.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.ChecklistModel;

import java.util.ArrayList;


/**
 * This class controls views for the checklist.
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 **/
public class ChecklistUI
{
    private static ChecklistModel checklistModel = new ChecklistModel();


    /**
     * The main checklist view
     * @param stage current stage
     * @return returns the built stage
     */
    public static Scene checklist(Stage stage)
    {
        GridPane gridPane = TempleteUI.generateGridPane();
        ArrayList<String> checklist = checklistModel.getChecklist();

        VBox checklistVBox = new VBox();
        HBox topButtonBar = new HBox();
        BorderPane borderPane = new BorderPane();
        Button addItem = new Button("Add");
        Button removeItem = new Button("Remove");

        for (String item: checklist)
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

        topButtonBar.getChildren().addAll(addItem, removeItem);
        topButtonBar.getStyleClass().add("smallButtons");
        topButtonBar.setSpacing(35);
        topButtonBar.setAlignment(Pos.CENTER);

        borderPane.setTop(topButtonBar);
        borderPane.setCenter(checklistVBox);
        borderPane.setBottom(TempleteUI.homeBar(stage));

        gridPane.add(borderPane, 0, 0, 4,1);

        return new Scene(gridPane,  350, 500);
    }

    //sub-page for adding items to the checklist
    private static Scene addItem(Stage stage)
    {
        GridPane gridPane = TempleteUI.generateGridPane();
        VBox addItemPartsBox = new VBox();
        HBox buttonBox = new HBox();



        //input area
        TextField itemToBeAdded = new TextField();
        itemToBeAdded.setPromptText("Enter an item for the checklist.");

        Button addItem = new Button("Add");
        addItem.setOnAction(event ->
        {
            checklistModel.setChecklist(itemToBeAdded.getText());

            stage.setScene(checklist(stage));
        });

        Button cancel = new Button("Cancel");
        cancel.setOnAction(event ->
        {
            stage.setScene(checklist(stage));
        });


        buttonBox.getChildren().addAll(addItem, cancel);
        buttonBox.setSpacing(15);
        addItemPartsBox.getChildren().addAll(itemToBeAdded,buttonBox);
        itemToBeAdded.setMaxWidth(300);

        gridPane.add(addItemPartsBox, 0,0,4,1);



        gridPane.getStyleClass().add("smallButtons");
        return new Scene(gridPane,  350, 500);
    }

    //sub-page for removing items to the checklist
    private static Scene removeItem(Stage stage)
    {
        GridPane gridPane = TempleteUI.generateGridPane();
        VBox removeItemPartsBox = new VBox();
        HBox buttonBox = new HBox();

        //input area
        TextField itemToBeRemoved = new TextField();
        itemToBeRemoved.setPromptText("Enter an item to be removed");

        Button removeItem = new Button("Remove");
        removeItem.setOnAction(event ->
        {
            checklistModel.removeItem(itemToBeRemoved.getText());

            stage.setScene(checklist(stage));
        });

        Button cancel = new Button("Cancel");
        cancel.setOnAction(event ->
        {
            stage.setScene(checklist(stage));
        });

        buttonBox.getChildren().addAll(removeItem, cancel);
        buttonBox.setSpacing(15);
        removeItemPartsBox.getChildren().addAll(itemToBeRemoved,buttonBox);
        itemToBeRemoved.setMaxWidth(300);

        gridPane.add(removeItemPartsBox, 0,0,4,1);

        gridPane.getStyleClass().add("smallButtons");
        return new Scene(gridPane,  350, 500);
    }
}
